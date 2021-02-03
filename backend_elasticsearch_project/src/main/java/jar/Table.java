package jar;

import java.util.HashMap;
import java.util.Map;

public class Table
{
    private String name;
    private HashMap<Integer, Column> columns;
    private int cptColumn;

    public Table(String name, Map<String, String> columnsMap) {
        this.name = name;
        this.columns = new HashMap<>();
//        for (Map.Entry:columnsMap){
//            columns.put(cptColumn, new Column(map.K, ));
//        }
    }

    public void deleteTable()
    {
        
    }
}