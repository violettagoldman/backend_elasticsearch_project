 package logic;

 import java.util.ArrayList;
 import java.util.List;
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
         this.index = null;
     }

     public void addDataValue(int id, String value) {
            data.put(id, value);
     //    data.put(id, index.addDataValue(value, id));
     }

     public String toString(){
         String str = " Type : "+type+"\n";
         for (Map.Entry entry :
                 data.entrySet()) {
             if(index == null)
             str = str + "id : "+entry.getKey()+" value : "+entry.getValue()+"\n";
             else  str = str + "id : "+entry.getKey()+" value : "+entry.getValue()+" occurrences : "+index.getDataOccurrence((String)entry.getValue())+"\n";

         }
         return str;
     }

     public List<Integer> where(String value) {
         List <Integer> list = new ArrayList<>();
         for (Map.Entry entry : data.entrySet()){
             if(entry.getValue() == value)list.add((Integer) entry.getKey());
         }
         return list;
        // return index.getLines(value);
     }

     public String getById(Object i) {
         return data.get((int)i);
     }
 }