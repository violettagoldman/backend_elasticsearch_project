 package logic;

 import logic.IndexBTree.BTree;
 import org.json.JSONArray;
 import org.json.JSONObject;

 import java.security.NoSuchAlgorithmException;
 import java.util.*;
 import java.util.Map.Entry;

 public class Table {
     private String name;
     private Map<String, Column> columns;
     private int rowsId;
     private Map<String, BTree> index;
     private ArrayList<Column> columnsList;

     /**
      * Initialise a new table from a name and a map column name , column type.
      * @param name name of tabme
      * @param columnsNames, columnsType
      */
     public Table(String name, ArrayList<String> columnsNames, ArrayList<String> columnsType){
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
      * @param name name
      */
     public Table(String name){
         this.name = name;
         columns = null;
         rowsId = 0;
         index = null;
     }

     /**
      * changes the number of rows contained in the table
      * @param rowsId rowsId
      */
     public void setRowsId(int rowsId) {
         this.rowsId = rowsId;
     }

     /**
      * returns the number of rows
      * @return int
      */
     public int getRowsId() {
         return rowsId;
     }

     /**
      * returns the columns of the table
      * @return map string / column
      */
     public Map<String, Column> getColumns() {
         return columns;
     }

     /**
      * returns the list of columns of the table
      * @return List of column
      */
     public ArrayList<Column> getColumnsList() {
         return columnsList;
     }

     /**
      * changes the columns of the table
      * @param columns column
      */
     public void setColumns(Map<String, Column> columns) {
         this.columns = columns;
     }

     /**
      * returns the given index in perimeter or the column if the index does not exist
      * @param columnName the name
      * @return object
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
      * @return Map of inder
      */
     public Map<String, BTree> getIndex() {
         return index;
     }

     /**
      * clones the table, the new table contains only the columns given in parameter
      * @param columnsNames name of new column
      * @return new table
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
             for (Entry column : columns.entrySet()) {
                 result.columns.put((String) column.getKey(), new Column((String) column.getKey(), columns.get(column.getKey()).getType()));
             }
         }
         return result;
     }

     /**
      * adds a new row to the table, map: column name, data
      * @param columnsMap data
      */
     public void addLineColumn(Map<String, String> columnsMap){
         for (Entry entry: columnsMap.entrySet()){
             columns.get(entry.getKey()).addDataValue(rowsId, (String)entry.getValue());
         }
         rowsId++;
     }

     /**
      * Add the line in the Index
      * @param columnsMap data
      */
     public void addLineIndex(Map<String, String> columnsMap){
         for (Entry entry: columnsMap.entrySet()){
             if(index.get(entry.getKey())!=null){
                 index.get(entry.getKey()).insert((String)entry.getValue(), rowsId);
             }
         }
         rowsId++;
     }

     /**
      * create index for the columns given as parameters
      * @param columnsName the names of new index
      * @throws NoSuchAlgorithmException execption
      */
     public void createIndex(String[] columnsName) throws NoSuchAlgorithmException {
         for (int j = 0 ; j< columnsName.length ; j++){
             BTree btree = new BTree(2, columnsName[j], columns.get(columnsName[j]).getType());
             index.put(columnsName[j], btree);
        }
     }

     /**
      * returns a string containing the table name, columns and all rows
      * @return a string
      */
     public String toString(){
         String str = "Nom de la table : "+name+"\n";
         str = str + "id |";
         for (Entry entry : columns.entrySet()) {
             str = str + " " +entry.getKey() + " |";
         }
         str = str + "\n";
         for (int i = 0 ; i < rowsId ; i++){
             Boolean id = true;
             int [] ids = new int[0];
             for (Entry entry : columns.entrySet()) {
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

     /**
      * return the line in the table in JSON (String)
      * @return string
      */
     public String toJson(){
         JSONArray ja = new JSONArray();
         for (int i = 0 ; i < rowsId ; i++){
             JSONObject jo = new JSONObject();
             for (Entry entry : columns.entrySet()) {
                 jo.put((String) entry.getKey(), ((Column) entry.getValue()).dataByID(i));
             }
             ja.put(jo);
         }
         JSONObject mainObj = new JSONObject();
         mainObj.put("lines", ja);
         return mainObj.toString();
     }

     /**
      * return the line in the table in JSON (String)
      * @param limit the rows limit
      * @return a string
      */
     public String toJsonLimit(int limit){
         JSONArray ja = new JSONArray();
         for (int i = 0 ; i < rowsId && i<limit; i++){
             JSONObject jo = new JSONObject();
             for (Entry entry : columns.entrySet()) {
                 jo.put((String) entry.getKey(), ((Column) entry.getValue()).dataByID(i));
             }
             ja.put(jo);
         }
         JSONObject mainObj = new JSONObject();
         mainObj.put("lines", ja);
         return mainObj.toString();
     }

     /**
      * return the line in the table in JSON (String)
      * @param order the column to order
      * @return string
      */
     public String toJsonByOrder(ArrayList<Integer> order){
         JSONArray ja = new JSONArray();
         for (int i : order){
             JSONObject jo = new JSONObject();
             for (Entry entry : columns.entrySet()) {
                 jo.put((String) entry.getKey(), ((Column) entry.getValue()).dataByID(i));
             }
             ja.put(jo);
         }
         JSONObject mainObj = new JSONObject();
         mainObj.put("lines", ja);
         return mainObj.toString();
     }


 }