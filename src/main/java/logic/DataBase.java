package logic;

import parser.CSVParser;
import parser.NewDataBase;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {
    private String name;
    private Map<String, Table> tables;
    private static final DataBase instance = new DataBase();

    public void setTables(Map<String, Table> tables) {
        this.tables = tables;
    }

    public DataBase(){
        this.name = null;
        tables = new HashMap<>();
    }

    public static DataBase createInstance(String name){
        instance.name = name;
        return instance;
    }

    public static DataBase getInstance(){
        return instance;
    }

    public void newTable(String name, Map<String, String> columnsMap){
        tables.put(name, new Table(name, columnsMap));
    }

    public void newLine(String name, Map<String, String> columnsMap){
        tables.get(name).addLine(columnsMap);
    }

    public String toString(){
        String str = "DataBase :"+ name +"\n";
        for (Map.Entry entry:
             tables.entrySet()) {
            str = entry.toString();
        }
        return str;
    }

    public void createIndex(String table, String [] columns) throws NoSuchAlgorithmException {
        tables.get(table).createIndex(columns);
    }

    //Retourne l'ensemble de la table
    public String selectFrom(String table){
        return tables.get(table).toString();
    }

    public String selectFromWhere(String table, String column, String value){
        return null;
     //   return tables.get(table).FromWhere(column,value);
    }

    public Map<String, Table> getTables() {
        return tables;
    }

    //main
    public static void main(String[] argv) throws NoSuchAlgorithmException, IOException {
//        Map<String, String> columnsMap = new HashMap<>();
//        columnsMap.put("Ville Départ", "String");
//        columnsMap.put("Ville arrivée", "String");
//        columnsMap.put("Prix", "Int");
//        Table table = new Table("Voyages", columnsMap);
//
//        Map<String, String> bordeauxParis = new HashMap<>();
//        bordeauxParis.put("Ville Départ", "Paris");
//        bordeauxParis.put("Ville arrivée", "Bordeaux");
//        bordeauxParis.put("Prix", "75");
//        table.addLine(bordeauxParis);
//
//        Map<String, String> bordeauxParis2 = new HashMap<>();
//        bordeauxParis2.put("Ville Départ", "Paris");
//        bordeauxParis2.put("Ville arrivée", "Bordeaux");
//        bordeauxParis2.put("Prix", "75");
//        table.addLine(bordeauxParis2);
//
//        Map<String, String> parisStDenis = new HashMap<>();
//        parisStDenis.put("Ville Départ", "Paris");
//        parisStDenis.put("Ville arrivée", "StDenis");
//        parisStDenis.put("Prix", "3000");
//        table.addLine(parisStDenis);
//
//        table.createIndex(new String [] {"Ville Départ", "Ville arrivée"});
//
//       DataBase db = createInstance("Voyage");
//       db.tables.put("voyage", table);

        CSVParser csvp = new CSVParser();
        NewDataBase ndb = new NewDataBase();
        ndb.inIndex("dogs.csv");
        System.out.println(DataBase.getInstance().toString());


      // System.out.println(db.selectFromWhere("voyage","Ville arrivée", "Bordeaux"));

    //    System.out.println(table.toString());
    }

}
