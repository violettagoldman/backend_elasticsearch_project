 package logic;

 import java.util.HashMap;
 import java.util.Map;
 import java.util.TreeMap;

 public class Table
 {

     public static void main(String[] argv)
     {
         Map<String, String> columnsMap = new HashMap<>();
         columnsMap.put("Ville Départ", "String");
         columnsMap.put("Ville arrivée", "String");
         columnsMap.put("Prix", "Int");
         Table table = new Table("Voyages", columnsMap);

         Map<String, String> bordeauxParis = new HashMap<>();
         bordeauxParis.put("Ville Départ", "Paris");
         bordeauxParis.put("Ville arrivée", "Bordeaux");
         bordeauxParis.put("Prix", "75");
         table.addLine(bordeauxParis);

         Map<String, String> parisStDenis = new HashMap<>();
         parisStDenis.put("Ville Départ", "Paris");
         parisStDenis.put("Ville arrivée", "StDenis");
         parisStDenis.put("Prix", "3000");
         table.addLine(parisStDenis);

         System.out.println(table.toString());

     }


     private String name;
     private Map<String, Column> columns;
     private int rowsId;

     public Table(String name, Map<String, String> columnsMap){
         this.name = name;
         columns = new TreeMap<String, Column>();
         rowsId = 0;
         columns.put("id", new Column("id", "int"));
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

     public void deleteTable()
     {
        
     }
 }