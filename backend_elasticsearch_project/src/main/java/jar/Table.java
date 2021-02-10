package jar;

import java.util.HashMap;
import java.util.Map;

public class Table
{
    private String name;
    private Map<String, Column> columns;
    private int rowsId;

    public Table(String name, Map<String, String> columnsMap{
        this.name = name;
        columns = new HashMap<String, Column>();
        rowsId = 0;
        columns.put("id", new Column("id", "int"));
        for (Map.Entry entry: columnsMap.entrySet()){
            columns.put((String) entry.getKey(), new Column((String) entry.getKey(), (String) entry.getValue()));
        }
    }

    //hashmap ordre de sortie : utilise la méthode hashcode; donc on suit le hashcode
    // lintmap pour l'ordre d'implémentation

    public void addColumn(String name, String type){
        columns.put(name, new Column(name, type));
    }

    public void addLine(Map<String, String> columnsMap){
        for (Map.Entry entry: columnsMap.entrySet()){
            columns.get(entry.getKey()).addDataValue(rowsId, (String)entry.getValue());
        }
        rowsId++;
    }

//    public void deleteColumn(String name){
//        //enlever aussi dans l'index
//        //columns.remove(name);
//    }

    public void deleteTable()
    {
        
    }
}