package logic.request;

import logic.Column;
import logic.DatasOnDisk;
import logic.Table;
import parser.CSVParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * class that implements the request select
 */
public class Select {
    private List rows;
    private Table result;

    /**
     * constructor
     * @param rows
     * @param columnsName
     * @param from
     */
    public Select(ArrayList rows, String [] columnsName, From from) throws IOException {
        this.rows = rows;
        Table table = from.table;
        result = table.clone(columnsName);
        //Map<String, Column> columns = new TreeMap<String, Column>();
        DatasOnDisk dod = new DatasOnDisk();
        for (Object row: rows) {
            HashMap<String, String > list = new HashMap<>();
            List<String> aList = Arrays.asList(columnsName);
            for (String name: columnsName) {
                // take data if they are index
                if(result.getIndex().get(name)!=null){
                    list.put(name, result.getIndex().get(name).getData((int)row) );
                    aList.remove(name);
                }
            }
            // take the rest of data in disk
            ArrayList<Column> listColumn = new ArrayList<>();
            for (String name : aList) {
                listColumn.add(result.getColumns().get(name));
            }
            String [] data = (String[]) dod.readLine((int)row, result.getColumnsList(), listColumn);
            for(int i = 0; i<data.length; i++) {
                list.put(aList.get(i), data[i]);
            }
            result.addLineColumn(list);
        }
    }

    /**
     * return the result
     * @return
     */
    public Table getResult() {
        return result;
    }
}
