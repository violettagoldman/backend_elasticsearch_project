package logic.request;

import logic.Column;
import logic.Table;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Select {
    private int[] rows;
    private String [] columnsNames;
    private Table result;

    public Table getResult() {
        return result;
    }

    public Select(int [] rows, String [] columnsName, From from){
        this.rows = rows;
        Table table = from.table;
        this.columnsNames = columnsName;
        result = table.clone(columnsNames);
        Map<String, Column> columns = new TreeMap<String, Column>();
        for (Map.Entry column: result.getColumns().entrySet()) {
            if(Arrays.binarySearch(columnsNames,column.getKey())>=0) {
                System.out.println(column.getValue());
                columns.put((String)column.getKey(), ((Column)column.getValue()).filterByRows(rows));
            }
        };
        result.setColumns(columns);
    }
}
