 package logic;

 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import java.util.TreeMap;

 public class Column
 {
     private String name;
     private String type;
     private Map<Integer, String> data;
     private Index index;

     public Column(String name, String type) {
         this.name = name;
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

     public List<Integer> getOccurences(String value) {
         List <Integer> list = new ArrayList<>();
         for (Map.Entry entry : data.entrySet()){
             if(entry.getValue() == value)list.add((Integer) entry.getKey());
         }
         return list;
        // return index.getLines(value);
     }

     public Column filterByRows(int [] rows){
         Column column = new Column(this.name, this.type);
         if(rows.length==0){
             for (Map.Entry entry: data.entrySet()) {
                 column.addDataValue((int)entry.getKey(), (String)entry.getValue());
             }
         }else{
             for (int i : rows) {
                 column.addDataValue(i, this.getById(i));
             }

         }
         return column;
     }

     public String getById(Object i) {
         return data.get((int)i);
     }
 }