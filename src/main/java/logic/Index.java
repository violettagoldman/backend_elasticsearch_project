 package logic;

 import java.util.HashMap;
 import java.util.Map;
 import java.util.TreeMap;

 public class Index
 {
     private Map<String, Integer> dataMap;

     public Index() {
         this.dataMap = new HashMap<>();
     }

     //sauvegarder aussi le nombre d'occurence
     public String addDataValue(String value){
         int i = dataMap.getOrDefault(value,0);
         i++;
         dataMap.put(value, i);
         return value;
     }

     public int getDataOccurrence(String value) {
         return dataMap.get(value);
     }

     public String toString() {
         String str = "Index :\n";
         for (Map.Entry entry:
                 dataMap.entrySet()) {
             str = entry.getKey().toString()+" : "+entry.getValue()+"\n";
         }
         return str;
     }
 }


