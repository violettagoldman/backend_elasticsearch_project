package logic.request;

import logic.Table;
import logic.request.wTree.ArgWhere;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * class that implements a request
 */
public class Request {
    private final String result;

    /**
     * constructor
     * @param tableName the table of the request
     * @param columnsNames the list of the columns to return
     * @param args list of where arguments
     */
    public Request(String tableName, String [] columnsNames, List<ArgWhere> args, Aggregate.Type aggregate, Option.Type option, String agrOption) throws NoSuchAlgorithmException, IOException {
        From from = new From(tableName);
        Select select;
        if(args != null && !args.isEmpty()){
            Where where = new Where(args, tableName);
            select = new Select(where.getResult(), columnsNames, from);
        } else {
            ArrayList<Integer> rows = new ArrayList<Integer>();
            for (int i = 0; i< from.table.getRowsId() ; i++) rows.add(i);
            select = new Select(rows, columnsNames, from);
        }
        Table resultTable = select.getResult();
        if(aggregate != null){
            Aggregate ag = new Aggregate(resultTable, agrOption, aggregate);
            result = ag.getResult();
        } else if (option != null){
            Option op = new Option(resultTable, agrOption, option);
            result = op.getResult();
        } else {
            result = resultTable.toJson();
        }
    }

    public String getResult() {
        return result;
    }
}
