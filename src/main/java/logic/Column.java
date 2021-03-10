 package logic;

 import java.util.ArrayList;
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

     public void addDataValue(int id, String value) {
         data.put(id, index.addDataValue(value, id));
     }

     public String toString(){
         String str = " Type : "+type+"\n";
         for (Map.Entry entry :
                 data.entrySet()) {
             str = str + "id : "+entry.getKey()+" value : "+entry.getValue()+" occurrences : "+index.getDataOccurrence((String)entry.getValue())+"\n";
         }
         return str;
     }

     public ArrayList where(String value) {
        return index.getLines(value);
     }

     public String getById(Object i) {
         return data.get((int)i);
     }
 }