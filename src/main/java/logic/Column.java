 package logic;

 import org.json.JSONObject;

 import java.io.IOException;
 import java.io.RandomAccessFile;
 import java.util.*;
 import java.util.Map.Entry;

 /**
  * Class that implements the columns.
  * A column has a name, a type and a list of data referenced by their id.
  */
 public class Column {
     private final String name;
     private final String type;
     private final Map<Integer, String> data;

     /**
      * constructs a column with a name and type
      * @param name the name of the column
      * @param type the type of the data
      */
     public Column(String name, String type) {
         this.name = name;
         this.type = type;
         this.data = new TreeMap<>();
     }

     /**
      * gives a data corresponding to the id (object)
      * @param i the id of the data
      * @return return the data
      */
     public String getById(Object i) {
         return data.get(i);
     }

     /**
      * return the type
      * @return the type of the column
      */
     public String getType() {
         return type;
     }

     /**
      * return the name
      * @return the name of the column
      */
     public String getName() {
         return name;
     }


     /**
      * gives a data corresponding to the id (int)
      * @param i id of the data
      * @return the data
      */
     public String dataByID(int i){
         return data.get(i);
     }

     /**
      * returns the occurrences of a data
      * @param value
      * @return the occurrences of the data
      */
     public List<Integer> getOccurrences(String value) {
         List <Integer> list = new ArrayList<>();
         for (Entry entry : data.entrySet()){
             if(entry.getValue() == value)list.add((Integer) entry.getKey());
         }
         return list;
     }

     /**
      * adds a data to the column
      * @param id id of the data
      * @param value the data to add
      */
     public void addDataValue(int id, String value) {
            data.put(id, value);
     }

     /**
      * returns the column with the given rows only,
      * if the row list is empty, returns the whole column
      * @param rows
      * @return a new column with the rows
      */
     public Column filterByRows(ArrayList rows){
         Column column = new Column(this.name, this.type);
         if(rows.size()==0){
             for (Entry entry: data.entrySet()) {
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
      * @return the list of id
      */
     public int [] listId(){
         int [] id = new int[data.size()];
         int i = 0;
         for (Entry entry: data.entrySet()) {
             id[i]= (int) entry.getKey();
             i++;
         }
         return id;
     }

     /**
      * returns a string describing the column
      * @return a string
      */
     public String toString(){
         String str = " Type : "+type+"\n";
         for (Entry entry :
                 data.entrySet()) {
             str = str + "id : "+entry.getKey()+" value : "+entry.getValue()+"\n";
         }
         return str;
     }

     /**
      * evalue equals
      * @param o object
      * @return boolean
      */
     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         Column column = (Column) o;
         return Objects.equals(name, column.name) && Objects.equals(type, column.type) && Objects.equals(data, column.data);
     }

     /**
      * hash an object
      * @return the hash
      */
     @Override
     public int hashCode() {
         return Objects.hash(name, type, data);
     }

     public int writeToFile(RandomAccessFile file, Object o) throws IOException {
         long lengthBefore = file.length();
         file.writeUTF((String) o);
         return (int) (file.length() - lengthBefore);
     }

     /**
      * read from the file
      * @param file the file to read
      * @return return object read
      * @throws IOException exception
      */
     public Object readFromFile(RandomAccessFile file) throws IOException {
         return file.readUTF();
     }

     
     // ___Functions for aggregates___

     /**
      * return the sum Json
      * @return string (JSon)
      */
     public String sum() {
         if(type != "float" && type != "int" && type != "double") return "bad type column";
         float result = 0;
         for (Entry nb: data.entrySet()) {
             result = result + Float.parseFloat((String) nb.getValue());
         }
         JSONObject jo = new JSONObject();
         jo.put("sum", result);
         return jo.toString();
     }

     /**
      * return the average Json
      * @return string (JSon)
      */
     public String average() {
         if(type != "float" && type != "int" && type != "double") return "bad type column";
         float result = 0;
         for (Entry nb: data.entrySet()) {
             result = result + Float.parseFloat((String) nb.getValue());
         }
         JSONObject jo = new JSONObject();
         jo.put("Average", result / data.size());
         return jo.toString();
     }

     /**
      * return the min Json
      * @return string (JSon)
      */
     public String min() {
         if(!type.equals("float") && !type.equals("int") && !type.equals("double")) return "bad type column";
         float result = Float.MAX_VALUE;
         for (Entry nb: data.entrySet()) {
             if(result > Float.parseFloat((String) nb.getValue())) result = Float.parseFloat((String) nb.getValue());
         }
         JSONObject jo = new JSONObject();
         jo.put("min", result);
         return jo.toString();
     }

     /**
      * return the max Json
      * @return string (JSon)
      */
     public String max() {
         if(!type.equals("float") && !type.equals("int") && !type.equals("double")) return "bad type column";
         float result = 0;
         for (Entry nb: data.entrySet()) {
             if(result < Float.parseFloat((String) nb.getValue())) result = Float.parseFloat((String) nb.getValue());
         }
         JSONObject jo = new JSONObject();
         jo.put("max", result);
         return jo.toString();
     }

     /**
      * return the rows of the column
      * @return string (JSon)
      */
     public String count() {
         JSONObject jo = new JSONObject();
         jo.put("count", data.size());
         return jo.toString();
     }

     /**
      * return the count of the distinct value in the column
      * @return string (JSon)
      */
     public String countDistinct() {
         HashSet<String> result = new HashSet();
         for (Entry nb: data.entrySet()) {
             result.add(String.valueOf(nb.getValue()));
         }
         JSONObject jo = new JSONObject();
         jo.put("count distinct", result.size());
         return jo.toString();
     }

     // function for option

     /**
      * order by the data
      * @return the new order of rows
      */
     public ArrayList<Integer> orderBy(){
         TreeMap<String, Integer> orderMap = new TreeMap<>();
         for (Entry entry: data.entrySet()) {
            orderMap.put((String)entry.getValue(), (Integer)entry.getKey());
         }
         ArrayList<Integer> order = new ArrayList<>(orderMap.values());
         return order;
     }
 }
