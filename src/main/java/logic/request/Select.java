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

        // columnnames => arraylist
        ArrayList<String> columnsNamesList = new ArrayList<>();
        Collections.addAll(columnsNamesList, columnsName);

        // remove columns in index
        for (String name: columnsName) if(result.getIndex().get(name)!=null)columnsNamesList.remove(name);

        for (Object row: rows) {
            HashMap<String, String > dataList = new HashMap<>();
            for (String name: columnsName) {
                // take data if they are index
                if(result.getIndex().get(name)!=null){
                    dataList.put(name, result.getIndex().get(name).getData((int)row) );
                }
            }
            // take the rest of data in disk
            ArrayList<Column> listColumnSeclected = new ArrayList<>();
            for (String name : columnsNamesList) listColumnSeclected.add(from.table.getColumns().get(name));

            //list columns mises à jour
            ArrayList<Column> listColumnTotal = new ArrayList<>();
            for (Object c : result.getColumnsList()) listColumnTotal.add(from.table.getColumns().get(((Column)c).getName()));


            Object [] data =  dod.readLine((int)row, listColumnTotal, listColumnSeclected);
            for(int i = 0; i<data.length; i++) {
                dataList.put(columnsNamesList.get(i), (String) data[i]);
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
