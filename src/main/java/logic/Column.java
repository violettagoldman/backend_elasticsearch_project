 package logic;

 import java.util.Map;
 import java.util.TreeMap;

 public class Column
 {
     private String nom;
     private String type;
     private Map<Integer, String> data;
     private Index index;

     public Column(String nom, String type) {
         this.nom = nom;
         this.type = type;
         this.data = new TreeMap<>();
         this.index = new Index();
     }

     public void addDataValue(int ids, String value) {
         data.put(ids, index.addDataValue(value));
     }

     public String toString(){
         String str = " Type : "+type+"\n";
         for (Map.Entry entry :
                 data.entrySet()) {
             str = str + "id : "+entry.getKey()+" index : "+entry.getValue()+" value : "+index.getDataOccurrence((String)entry.getValue())+"\n";
         }
         return str;
     }
 }