 package logic;

 import logic.IndexBTree.BTree;
 import logic.IndexBTree.Entry;
 import parser.CSVParser;

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.security.NoSuchAlgorithmException;
 import java.util.*;

 public class Table {
     private String name;
     private Map<String, Column> columns;
     private int rowsId;
     private Map<String, BTree> index;
     private ArrayList columnsList;
    // ajouter liste columns
     /**
      * Initialise a new table from a name and a map <column name , column type>.
      * @param name
      * @param columnsNames, columnsType
      */
     public Table(String name, ArrayList columnsNames, ArrayList columnsType){
         this.name = name;
         columns = new HashMap<String, Column>();
         rowsId = 0;
         index = new HashMap<String, BTree>();
         columnsList = new ArrayList<>();
         for(int i = 0; i< columnsNames.size() ; i++){
             Column column = new Column((String) columnsNames.get(i), (String) columnsType.get(i));
             columns.put((String) columnsNames.get(i), column );
             columnsList.add(column);
         }
     }

     /**
      * create a new empty table
      * @param name
      */
     public Table(String name){
         this.name = name;
         columns = null;
         rowsId = 0;
         index = null;
     }

     /**
      * changes the number of rows contained in the table
      * @param rowsId
      */
     public void setRowsId(int rowsId) {
         this.rowsId = rowsId;
     }

     /**
      * returns the number of rows
      * @return
      */
     public int getRowsId() {
         return rowsId;
     }

     /**
      * returns the columns of the table
      * @return
      */
     public Map<String, Column> getColumns() {
         return columns;
     }

     /**
      * returns the list of columns of the table
      * @return
      */
     public ArrayList getColumnsList() {
         return columnsList;
     }

     /**
      * changes the columns of the table
      * @param columns
      */
     public void setColumns(Map<String, Column> columns) {
         this.columns = columns;
     }

     /**
      * returns the given index in perimeter or the column if the index does not exist
      * @param columnName
      * @return
      */
     public Object getIndexOrColumn(String columnName){
         return index.get(columnName) == null ? columns.get(columnName) : index.get(columnName);
     }

     /**
      * add a row
      */
     public void addRow(){
         rowsId++;
     }

     /**
      * return index
      * @return
      */
     public Map<String, BTree> getIndex() {
         return index;
     }

     /**
      * clones the table, the new table contains only the columns given in parameter
      * @param columnsNames
      * @return
      */
     public Table clone(String [] columnsNames){
         Table result = new Table("result");
         result.index = index;
         result.columns = new TreeMap<>();
         result.rowsId = 0;
         result.columnsList = columnsList;
         if(columnsNames.length>0){
             for (String columnName : columnsNames ){
                 result.columns.put(columnName, new Column(columnName, columns.get(columnName).getType()));
             }
         } else {
             for (Map.Entry column : columns.entrySet()) {
                 result.columns.put((String) column.getKey(), new Column((String) column.getKey(), columns.get(column.getKey()).getType()));
             }
         }
         return result;
     }

     /**
      * adds a new row to the table, map: <column name, data>
      * @param columnsMap
      */
     public void addLineColumn(Map<String, String> columnsMap){
         for (Map.Entry entry: columnsMap.entrySet()){
             columns.get(entry.getKey()).addDataValue(rowsId, (String)entry.getValue());
         }
         rowsId++;
     }

     /**
      * Add the line in the Index
      * @param columnsMap
      */
     public void addLineIndex(Map<String, String> columnsMap){
         for (Map.Entry entry: columnsMap.entrySet()){
             if(index.get(entry.getKey())!=null){
                 try {
                     index.get(entry.getKey()).insert((String)entry.getValue(), rowsId);
                 } catch (NoSuchAlgorithmException e) {
                     e.printStackTrace();
                 }
             }
         }
         rowsId++;
     }

     /**
      * create index for the columns given as parameters
      * @param columnsName
      * @throws NoSuchAlgorithmException
      */
     public void createIndex(String[] columnsName) throws NoSuchAlgorithmException {
         for (int j = 0 ; j< columnsName.length ; j++){
             BTree btree = new BTree(2, columnsName[j], columns.get(columnsName[j]).getType());
//             for (int i = 0 ; i<rowsId ; i++){
//                btree.insert(columns.get(columnsName[j]).getById(i),i);
//            }
             index.put(columnsName[j], btree);
        }
//         if(columnsName.length>1){
//             for (int i = 0 ; i < rowsId ; i++){
//                 for(int j = 0; j < columnsName.length-1 ; j++){
//                     String firstData = columns.get(columnsName[j]).getById(i);
//                     BTree firstIndex = index.get(columnsName[j]);
//                     Entry firstEntry = firstIndex.search(firstData);
//
//                     String secondData = columns.get(columnsName[j+1]).getById(i);
//                     BTree secondIndex = index.get(columnsName[j+1]);
//                     Entry secondEntry = secondIndex.search(secondData);
//                     firstEntry.getAfters().put(i,secondEntry);
//                 }
//             }
//         }
     }

     /**
      * returns a string containing the table name, columns and all rows
      * @return
      */
     public String toString(){
         String str = "{ \"NomTable\" : "+"\""+name+"\",\n";
         str = str + "\"columns\" : \" id |";
         for (Map.Entry entry : columns.entrySet()) {
             str = str + " " +entry.getKey() + " |";
         }
         str = str + "\",\n";
         for (int i = 0 ; i < rowsId ; i++){
             str = str + "\"line"+(i)+"\" : \"";
             Boolean id = true;
             int [] ids = new int[0];
             for (Map.Entry entry : columns.entrySet()) {
                 if(id){
                     ids = ((Column)entry.getValue()).listId();
                 }
                 str = str + " " +(id ? ids[i] +" | " : "" ) + ((Column) entry.getValue()).dataByID(ids[i])+ " |";
                 id = false;
             }
             str = str + "\",\n";
         }
         str = str.substring(0,str.length()-2);
         str = str + "}";

         return  str;
     }


     // function for option
     public String toStringByOrder(ArrayList<Integer> order){
         String str = "Nom de la table : "+name+"\n";
         str = str + "id |";
         for (Map.Entry entry : columns.entrySet()) {
             str = str + " " +entry.getKey() + " |";
         }
         str = str + "\n";
         for (int i: order) {
             Boolean id = true;
             int [] ids = new int[0];
             for (Map.Entry entry : columns.entrySet()) {
                 if(id){
                     ids = ((Column)entry.getValue()).listId();
                 }
                 str = str + " " +(id ? ids[i] +" | " : "" ) + ((Column) entry.getValue()).dataByID(ids[i])+ " |";
                 id = false;
             }
             str = str + "\n";
         }
         return  str;
     }

     public String toStringLimit(int limit){
         String str = "Nom de la table : "+name+"\n";
         str = str + "id |";
         for (Map.Entry entry : columns.entrySet()) {
             str = str + " " +entry.getKey() + " |";
         }
         str = str + "\n";
         for (int i = 0 ; i < rowsId && i < limit; i++){
             Boolean id = true;
             int [] ids = new int[0];
             for (Map.Entry entry : columns.entrySet()) {
                 if(id){
                     ids = ((Column)entry.getValue()).listId();
                 }
                 str = str + " " +(id ? ids[i] +" | " : "" ) + ((Column) entry.getValue()).dataByID(ids[i])+ " |";
                 id = false;
             }
             str = str + "\n";
         }
         return  str;
     }

 }