 package logic;

 import java.util.*;

 public class Index
 {
     private Map<List<String>, ArrayList> dataMap;

     public Index() {
         this.dataMap = new HashMap<>();
     }

     //sauvegarder aussi le nombre d'occurence
//     public String addDataValue(String value, int id){
//         ArrayList list = dataMap.getOrDefault(value, new ArrayList());
//         list.add(id);
//         dataMap.put(value, list);
//         return value;
//     }

     public void addDataValue(List<String> value, int id){
         ArrayList list = dataMap.getOrDefault(value, new ArrayList());
         list.add(id);
         dataMap.put(value, list);
     }

     public int getDataOccurrence(String value) {
         return (dataMap.get(value)).size();
     }

     public String toString() {
         String str = "Index :\n";
         for (Map.Entry entry:
                 dataMap.entrySet()) {
             str = str + entry.getKey().toString()+" : "+entry.getValue()+"\n";
         }
         return str;
     }

     public List<Integer> getLines(String value) {
         return dataMap.get(value);
     }
 }


