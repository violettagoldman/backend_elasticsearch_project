package logic;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
    private String name;
    private Map<String, Table> tables;

    public DataBase(){
        tables = new HashMap<>();
    }

    public void newTable(String name, Map<String, String> columnsMap){
        tables.put(name, new Table(name, columnsMap));
    }

    public void newLine(Map<String, String> columnsMap){
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

    public void createIndex(String table, String [] columns){
        tables.get(table).createIndex(columns);
    }

    //Retourne l'ensemble de la table
    public String selectFrom(String table){
        return tables.get(table).toString();
    }

    public String selectFromWhere(String table, String column, String value){
        return tables.get(table).FromWhere(column,value);
    }

    public Map<String, Table> getTables() {
        return tables;
    }

    //main
    public static void main(String[] argv) {
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

        DataBase db = new DataBase();
        db.tables.put("voyage", table);



      //  System.out.println(db.selectFromWhere("voyage","Ville arrivée", "Bordeaux" ));

        System.out.println(table.toString());

    }



}
