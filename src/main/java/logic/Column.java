 package logic;

 import org.json.JSONObject;

 import java.io.IOException;
 import java.io.RandomAccessFile;
 import java.util.*;

 /**
  * Class that implements the columns.
  * A column has a name, a type and a list of data referenced by their id.
  */
 public class Column {
     private String name;
     private String type;
     private Map<Integer, String> data;

     /**
      * constructs a column with a name and type
      * @param name
      * @param type
      */
     public Column(String name, String type) {
         this.name = name;
         this.type = type;
         this.data = new TreeMap<>();
     }

     /**
      * gives a data corresponding to the id (object)
      * @param i
      * @return
      */
     public String getById(Object i) {
         return data.get(i);
     }

     /**
      * return the type
      * @return
      */
     public String getType() {
         return type;
     }

     /**
      * return the name
      * @return
      */
     public String getName() {
         return name;
     }


     /**
      * gives a data corresponding to the id (int)
      * @param i
      * @return
      */
     public String dataByID(int i){
         return data.get(i);
     }

     /**
      * returns the occurrences of a data
      * @param value
      * @return
      */
     public List<Integer> getOccurences(String value) {
         List <Integer> list = new ArrayList<>();
         for (Map.Entry entry : data.entrySet()){
             if(entry.getValue() == value)list.add((Integer) entry.getKey());
         }
         return list;
     }

     /**
      * adds a data to the column
      * @param id
      * @param value
      */
     public void addDataValue(int id, String value) {
            data.put(id, value);
     }

     /**
      * returns the column with the given rows only,
      * if the row list is empty, returns the whole column
      * @param rows
      * @return
      */
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

     /**
      * returns the list of data ids of the column
      * @return
      */
     public int [] listId(){
         int [] id = new int[data.size()];
         int i = 0;
         for (Map.Entry entry: data.entrySet()) {
             id[i]= (int) entry.getKey();
             i++;
         }
         return id;
     }

     /**
      * returns a string describing the column
      * @return
      */
     public String toString(){
         String str = " Type : "+type+"\n";
         for (Map.Entry entry :
                 data.entrySet()) {
             str = str + "id : "+entry.getKey()+" value : "+entry.getValue()+"\n";
         }
         return str;
     }

     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         Column column = (Column) o;
         return Objects.equals(name, column.name) && Objects.equals(type, column.type) && Objects.equals(data, column.data);
     }

     @Override
     public int hashCode() {
         return Objects.hash(name, type, data);
     }

     public int writeToFile(RandomAccessFile file, Object o) throws IOException {
         long lengthBefore = file.length();
         file.writeUTF((String) o);
         return (int) (file.length() - lengthBefore);
     }

     public Object readFromFile(RandomAccessFile file) throws IOException {
         return file.readUTF();
     }

     
     // ___Functions for aggregates___
     
     public String sum() {
         if(type != "float" && type != "int" && type != "double") return "bad type column";
         float result = 0;
         for (Map.Entry nb: data.entrySet()) {
             result = result + Float.parseFloat((String) nb.getValue());
         }
         JSONObject jo = new JSONObject();
         jo.put("sum", result);
         return jo.toString();
     }

     public String average() {
         if(type != "float" && type != "int" && type != "double") return "bad type column";
         float result = 0;
         for (Map.Entry nb: data.entrySet()) {
             result = result + Float.parseFloat((String) nb.getValue());
         }
         JSONObject jo = new JSONObject();
         jo.put("Average", result / data.size());
         return jo.toString();
     }

     public String min() {
         if(type != "float" && type != "int" && type != "double") return "bad type column";
         float result = Float.MAX_VALUE;
         for (Map.Entry nb: data.entrySet()) {
             if(result > Float.parseFloat((String) nb.getValue())) result = Float.parseFloat((String) nb.getValue());
         }
         JSONObject jo = new JSONObject();
         jo.put("min", result);
         return jo.toString();
     }

     public String max() {
         if(type != "float" && type != "int" && type != "double") return "bad type column";
         float result = 0;
         for (Map.Entry nb: data.entrySet()) {
             if(result < Float.parseFloat((String) nb.getValue())) result = Float.parseFloat((String) nb.getValue());
         }
         JSONObject jo = new JSONObject();
         jo.put("max", result);
         return jo.toString();
     }

     public String count() {
         JSONObject jo = new JSONObject();
         jo.put("count", data.size());
         return jo.toString();
     }

     public String countDistinct() {
         HashSet<String> result = new HashSet();
         for (Map.Entry nb: data.entrySet()) {
             result.add(String.valueOf(nb.getValue()));
         }
         JSONObject jo = new JSONObject();
         jo.put("count distinct", result.size());
         return jo.toString();
     }

     // function for option
     public ArrayList<Integer> orderBy(){
         TreeMap<String, Integer> orderMap = new TreeMap<>();
         for (Map.Entry entry: data.entrySet()) {
            orderMap.put((String)entry.getValue(), (Integer)entry.getKey());
         }
         ArrayList<Integer> order = new ArrayList<>(orderMap.values());
         return order;
     }
 }
