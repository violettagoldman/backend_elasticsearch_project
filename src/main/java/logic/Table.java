 package logic;

 import java.util.*;

 public class Table
 {

     private String name;
     private Map<String, Column> columns;
     private int rowsId;
     private Map<String[], Index> index;

     public Table(String name, Map<String, String> columnsMap){
         this.name = name;
         columns = new TreeMap<String, Column>();
         rowsId = 0;
         index = new HashMap<>();
         for (Map.Entry entry: columnsMap.entrySet()){
             columns.put((String) entry.getKey(), new Column((String) entry.getKey(), (String) entry.getValue()));
         }
     }


     public void addLine(Map<String, String> columnsMap){
         for (Map.Entry entry: columnsMap.entrySet()){
             columns.get(entry.getKey()).addDataValue(rowsId, (String)entry.getValue());
         }
         rowsId++;
     }

     public String toString(){
         String str = "Nom de la table : "+name+"\n";
         for (Map.Entry entry :
                 columns.entrySet()) {
             str = str + "Nom de la colonne : "+entry.getKey()+ entry.getValue().toString()+"\n";
         }
         return  str;
     }

     public String FromWhere(String column, String value){
         String str = "id \t";
         for (Map.Entry entry:
              columns.entrySet()) {
             str = str+entry.getKey()+"\t";
         }
         str = str+"\n";
         for (Object i: columns.get(column).where(value)) {
             str = str +i+"\t";
             for (Map.Entry entry:
                     columns.entrySet()) {
                str =  str + ((Column)entry.getValue()).getById(i)+"\t\t";
             }
             str = str + "\n";
         }
         return str;
     }


     public void createIndex(String[] columnsName) {
        Index index = new Index();
        for (int i = 0 ; i<rowsId ; i++){
            List<String> entry = new ArrayList<>();
            for (int j = 0 ; j< columnsName.length ; j++){
                entry.add(columns.get(columnsName[j]).getById(i));
            }
            index.addDataValue(entry, i);
        }
         System.out.println(index.toString());
     }

 }