package logic.request;

import logic.Column;
import logic.Table;
import java.util.*;

/**
 * class that implements the request select
 */
public class Select {
    private List rows;
    private String [] columnsNames;
    private Table result;

    /**
     * constructor
     * @param rows
     * @param columnsName
     * @param from
     */
    public Select(ArrayList rows, String [] columnsName, From from){
        this.rows = rows;
        Table table = from.table;
        this.columnsNames = columnsName;
        ArrayList al = new ArrayList(Arrays.asList(columnsName));
        result = table.clone(columnsNames);
        Map<String, Column> columns = new TreeMap<String, Column>();
        for (Map.Entry column: result.getColumns().entrySet()) {
            if( al.size()==0 || al.contains(column.getKey())) {
                columns.put((String)column.getKey(), ((Column)column.getValue()).filterByRows(rows));
            }
        }
        result.setRowsId(rows.size());
        result.setColumns(columns);
    }

    /**
     * return the result
     * @return
     */
    public Table getResult() {
        return result;
    }
}
