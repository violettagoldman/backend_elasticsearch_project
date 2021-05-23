package logic.request;

import logic.Column;
import logic.Table;

import java.util.*;

public class Select {
    private List rows;
    private String [] columnsNames;
    private Table result;

    public Table getResult() {
        return result;
    }

    public Select(ArrayList rows, String [] columnsName, From from){
        this.rows = rows;
        Table table = from.table;
        this.columnsNames = columnsName;
        ArrayList al = new ArrayList(Arrays.asList(columnsName));
        result = table.clone(columnsNames);
        Map<String, Column> columns = new TreeMap<String, Column>();
        for (Map.Entry column: result.getColumns().entrySet()) {
            if(al.contains(column.getKey())) {
                columns.put((String)column.getKey(), ((Column)column.getValue()).filterByRows(rows));
            }
        }
        result.setRowsId(rows.size());
        result.setColumns(columns);
    }
}
