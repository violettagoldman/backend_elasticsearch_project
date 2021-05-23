 package logic;

 import logic.bTree.BTree;
 import logic.bTree.Entry;

 import java.security.NoSuchAlgorithmException;
 import java.util.*;

 public class Table
 {
     private String name;
     private Map<String, Column> columns;
     private int rowsId;
     private Map<String, BTree> index;

     /**
      * Initialise une nouvelle table à partir d'un nom et d'une map <nom colonne , type colonne>
      * @param name
      * @param columnsMap
      */
     public Table(String name, Map<String, String> columnsMap){
         this.name = name;
         columns = new TreeMap<String, Column>();
         rowsId = 0;
         index = new HashMap<String, BTree>();
         for (Map.Entry entry: columnsMap.entrySet()){
             columns.put((String) entry.getKey(), new Column((String) entry.getKey(), (String) entry.getValue()));
         }
     }

     /**
      * créer une nouvelle table vide
      * @param name
      */
     public Table(String name){
         this.name = name;
         columns = null;
         rowsId = 0;
         index = null;
     }

     /**
      * change le nombre de lignes contenues dans la table
      * @param rowsId
      */
     public void setRowsId(int rowsId) {
         this.rowsId = rowsId;
     }

     /**
      * retourne les colonne de la table
      * @return
      */
     public Map<String, Column> getColumns() {
         return columns;
     }

     /**
      * change les colonnes de la table
      * @param columns
      */
     public void setColumns(Map<String, Column> columns) {
         this.columns = columns;
     }

     /**
      * retoune l'index donné en parmètre ou la colonne si l'index n'existe pas
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
      * clone la table, la nouvelle table ne contient que les colonnes données en paramètre
      * @param columnsNames
      * @return
      */
     public Table clone(String [] columnsNames){
         Table result = new Table("result");
         result.columns = new TreeMap<String, Column>(); ;
         result.rowsId = this.rowsId;
         for (String name : columnsNames ){
             result.columns.put(name, columns.get(name));
         }
         return result;
     }

     /**
      * ajoute une nouvelle ligne à la table, map : <nom colonne, data>
      * @param columnsMap
      */
     public void addLine(Map<String, String> columnsMap){
         for (Map.Entry entry: columnsMap.entrySet()){
             columns.get(entry.getKey()).addDataValue(rowsId, (String)entry.getValue());
         }
         rowsId++;
     }

     /**
      * retoune une string contenant le nom de la table, les colonnes et l'ensemble des lignes
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

     /**
      * créer des index pour les colonnes données en paramètre
      * @param columnsName
      * @throws NoSuchAlgorithmException
      */
     public void createIndex(String[] columnsName) throws NoSuchAlgorithmException {
         for (int j = 0 ; j< columnsName.length ; j++){
             BTree btree = new BTree(2, columnsName[j]);
             for (int i = 0 ; i<rowsId ; i++){
                btree.insert(columns.get(columnsName[j]).getById(i),i);
            }
            // btree.traverse();
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
                   //  System.out.println("first :"+firstEntry.getData()+" second : "+secondEntry.getData() +" i : "+i);
                     firstEntry.getAfters().put(i,secondEntry);
                 }
             }
         }
         index.get(columnsName[0]).traverse();
     }

 }