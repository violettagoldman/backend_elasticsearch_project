package logic;

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



}
