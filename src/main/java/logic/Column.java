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


     public Column(String name, String type) {
         this.name = name;
         this.type = type;
         this.data = new TreeMap<>();
     }

     public void addDataValue(int id, String value) {
            data.put(id, value);
     //    data.put(id, index.addDataValue(value, id));
     }

     public String toString(){
         String str = " Type : "+type+"\n";
         for (Map.Entry entry :
                 data.entrySet()) {
             str = str + "id : "+entry.getKey()+" value : "+entry.getValue()+"\n";
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

     public String dataByID(int i){
         return data.get(i);
     }

     public Column filterByRows(ArrayList rows){
         Column column = new Column(this.name, this.type);
         if(rows.size()==0){
             for (Map.Entry entry: data.entrySet()) {
                 column.addDataValue((int)entry.getKey(), (String)entry.getValue());
             }
         }else{
             for (Object i : rows) {
                 column.addDataValue((int)i, this.getById(i));
             }

         }
         return column;
     }

     public int [] listId(){
         int [] id = new int[data.size()];
         int i = 0;
         for (Map.Entry entry: data.entrySet()) {
             id[i]= (int) entry.getKey();
             i++;
         }
         return id;
     }

     public String getById(Object i) {
         return data.get((int)i);
     }
 }