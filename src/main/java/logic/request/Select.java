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
     * @param rows the rows of the result
     * @param columnsName list of columns to return
     * @param from table of the request
     */
    public Select(ArrayList<Integer> rows, String [] columnsName, From from) throws IOException {
        Table table = from.table;
        result = table.clone(columnsName);
        DatasOnDisk dod = new DatasOnDisk();

        // if select * we take all of columns


        // columnnames => arraylist
        ArrayList<String> columnsNamesList = new ArrayList<>();
        if(columnsName.length==0)for (Object c : result.getColumnsList()) columnsNamesList.add(((Column)c).getName());
        else Collections.addAll(columnsNamesList, columnsName);

        // remove columns in index
        for (String name: columnsName) if(result.getIndex().get(name)!=null)columnsNamesList.remove(name);

        //list of selectionned columns
        ArrayList<Column> listColumnSeclected = new ArrayList<>();
        for (String name : columnsNamesList) listColumnSeclected.add(from.table.getColumns().get(name));

        //list of all columns
        ArrayList<Column> listColumnTotal = new ArrayList<>();
        for (Object c : result.getColumnsList()) listColumnTotal.add(from.table.getColumns().get(((Column)c).getName()));


        for (Object row: rows) {
            HashMap<String, String > dataList = new HashMap<>();
            for (String name: columnsName) {
                // take data if they are index
                if(result.getIndex().get(name)!=null){
                    dataList.put(name, result.getIndex().get(name).getData((int)row) );
                }
            }
            // take the rest of data in disk
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
