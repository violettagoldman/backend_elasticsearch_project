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
    private Table result;

    /**
     * constructor
     * @param rows
     * @param columnsName
     * @param from
     */
    public Select(ArrayList rows, String [] columnsName, From from) throws IOException {
        Table table = from.table;
        result = table.clone(columnsName);
        DatasOnDisk dod = new DatasOnDisk();
        System.out.println(rows.size());
        for (Object row: rows) {
            HashMap<String, String > dataList = new HashMap<>();
            ArrayList<String> columnsNamesList = new ArrayList<>();
            for (String i : columnsName) {
                columnsNamesList.add(i);
            }
            for (String name: columnsName) {
                // take data if they are index
                if(result.getIndex().get(name)!=null){
                    System.out.println(name);
                    dataList.put(name, result.getIndex().get(name).getData((int)row) );
                    columnsNamesList.remove(name);
                }
            }
            // take the rest of data in disk
            ArrayList<Column> listColumn = new ArrayList<>();
            for (String name : columnsNamesList) {
                listColumn.add(result.getColumns().get(name));
            }
            Object [] data =  dod.readLine((int)row, result.getColumnsList(), listColumn);
            for(int i = 0; i<data.length; i++) {
                dataList.put(columnsNamesList.get(i), (String) data[i]);
            }

            //TEST
            for(Map.Entry entry : dataList.entrySet()) {
                System.out.println(entry.getKey());
                System.out.println("/" + entry.getValue());
            }

            // add the line in the table
            result.addLineColumn(dataList);
        }
    }

    /**
     * return the result
     * @return table contains result
     */
    public Table getResult() {
        return result;
    }
}
