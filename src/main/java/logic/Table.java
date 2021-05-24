 package logic;

 import logic.bTree.BTree;
 import logic.bTree.Entry;
 import logic.bTree.Occurence;

 import java.security.NoSuchAlgorithmException;
 import java.util.*;

 public class Table
 {
     private String name;
     private Map<String, Column> columns;
     private int rowsId;
     private Map<String, BTree> index;

     public Table(String name, Map<String, String> columnsMap){
         this.name = name;
         columns = new TreeMap<String, Column>();
         rowsId = 0;
         index = new HashMap<String, BTree>();
         for (Map.Entry entry: columnsMap.entrySet()){
             columns.put((String) entry.getKey(), new Column((String) entry.getKey(), (String) entry.getValue()));
         }
     }

     public Map<String, Column> getColumns() {
         return columns;
     }

     public void setColumns(Map<String, Column> columns) {
         this.columns = columns;
     }

     public Table(String name){
         this.name = name;
         columns = null;
         rowsId = 0;
         index = null;
     }

     public Table clone(String [] columnsNames){
         Table result = new Table("result");
         result.columns = new TreeMap<String, Column>(); ;
         result.rowsId = this.rowsId;
         for (String name : columnsNames ){
             result.columns.put(name, columns.get(name));
         }
         return result;
     }


     public void addLine(Map<String, String> columnsMap){
         for (Map.Entry entry: columnsMap.entrySet()){
             columns.get(entry.getKey()).addDataValue(rowsId, (String)entry.getValue());
         }
         rowsId++;
     }

     public Object getIndexOrColumn(String columnName){
         return index.get(columnName) == null ? columns.get(columnName) : index.get(columnName);
     }

     public String toString(){
         String str = "Nom de la table : "+name+"\n";
         for (Map.Entry entry :
                 columns.entrySet()) {
             str = str + "Nom de la colonne : "+entry.getKey()+ entry.getValue().toString()+"\n";
         }
         return  str;
     }

//     public String FromWhere(String column, String value){
//         String str = "id \t";
//         for (Map.Entry entry:
//              columns.entrySet()) {
//             str = str+entry.getKey()+"\t";
//         }
//         str = str+"\n";
//         for (Object i: columns.get(column).where(value)) {
//             str = str +i+"\t";
//             for (Map.Entry entry:
//                     columns.entrySet()) {
//                str =  str + ((Column)entry.getValue()).getById(i)+"\t\t";
//             }
//             str = str + "\n";
//         }
//         return str;
//     }


     public void createIndex(String[] columnsName) throws NoSuchAlgorithmException {
         for (int j = 0 ; j< columnsName.length ; j++){
             BTree btree = new BTree(2);
             for (int i = 0 ; i<rowsId ; i++){
                btree.insert(columns.get(columnsName[j]).getById(i),i);
            }
             System.out.println(columnsName[j]);
             btree.traverse();
             index.put(columnsName[j], btree);
        }
         for (int i = 0 ; i < rowsId ; i++){
             for(int j = 0; j < columnsName.length-1 ; j++){
                 String firstData = columns.get(columnsName[j]).getById(i);
                 BTree firstIndex = index.get(columnsName[j]);
                 Entry firstEntry = firstIndex.search(firstData);
                 Occurence firstOccurence = (Occurence) firstEntry.getOccurrences().get(i);

                 String secondData = columns.get(columnsName[j+1]).getById(i);
                 BTree secondIndex = index.get(columnsName[j+1]);
                 Entry secondEntry = secondIndex.search(secondData);
                 Occurence secondOccurence = (Occurence) secondEntry.getOccurrences().get(i);

                 firstOccurence.setAfter(secondEntry);
                 secondOccurence.setBefore(firstEntry);
             }
         }
     }

 }