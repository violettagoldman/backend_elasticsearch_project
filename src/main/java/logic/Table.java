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
      * @param columnsMap
      */
     public Table(String name, Map<String, String> columnsMap){
         this.name = name;
         columns = new TreeMap<String, Column>();
         rowsId = 0;
         index = new HashMap<String, BTree>();
         for (Map.Entry entry: columnsMap.entrySet()){
             Column column = new Column((String) entry.getKey(), (String) entry.getValue());
             columns.put((String) entry.getKey(), column );
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
         result.columns = new TreeMap<String, Column>();
         result.rowsId = this.rowsId;
         if(columnsNames.length>0){
             for (String name : columnsNames ){
                 result.columns.put(name, columns.get(name));
             }
         } else {
             for (Map.Entry entry : columns.entrySet()) {
                 result.columns.put((String)entry.getKey(), (Column) entry.getValue());
             }
         }
         return result;
     }

     /**
      * adds a new row to the table, map: <column name, data>
      * @param columnsMap
      */
     public void addLine(Map<String, String> columnsMap){
         for (Map.Entry entry: columnsMap.entrySet()){
             columns.get(entry.getKey()).addDataValue(rowsId, (String)entry.getValue());
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
             BTree btree = new BTree(2, columnsName[j]);
             for (int i = 0 ; i<rowsId ; i++){
                btree.insert(columns.get(columnsName[j]).getById(i),i);
            }
             index.put(columnsName[j], btree);
        }
         if(columnsName.length>1){
             for (int i = 0 ; i < rowsId ; i++){
                 for(int j = 0; j < columnsName.length-1 ; j++){
                     String firstData = columns.get(columnsName[j]).getById(i);
                     BTree firstIndex = index.get(columnsName[j]);
                     Entry firstEntry = firstIndex.search(firstData);

                     String secondData = columns.get(columnsName[j+1]).getById(i);
                     BTree secondIndex = index.get(columnsName[j+1]);
                     Entry secondEntry = secondIndex.search(secondData);
                     firstEntry.getAfters().put(i,secondEntry);
                 }
             }
         }
     }

     /**
      * returns a string containing the table name, columns and all rows
      * @return
      */
     public String toString(){
         String str = "Nom de la table : "+name+"\n";
         str = str + "id |";
         for (Map.Entry entry : columns.entrySet()) {
             str = str + " " +entry.getKey() + " |";
         }
         str = str + "\n";
         for (int i = 0 ; i < rowsId ; i++){
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