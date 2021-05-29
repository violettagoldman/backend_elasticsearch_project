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
    private Select select;
    private From from;
    private Where where;
    private Table resultTable;
    private String result;

    /**
     * constructor
     * @param tableName
     * @param columnsNames
     * @param args
     * @throws NoSuchAlgorithmException
     */
    public Request(String tableName, String [] columnsNames, List<ArgWhere> args, Aggregate.Type aggregate, Option.Type option, String agrOption) throws NoSuchAlgorithmException, IOException {
        from = new From(tableName);
        if(args != null){
            where = new Where(args, tableName);
            select = new Select(where.getResult(), columnsNames, from);
        } else {
            ArrayList rows = new ArrayList();
            for (int i = 0 ; i< from.table.getRowsId() ; i++) rows.add(i);
            select = new Select(rows, columnsNames, from);
        }
        resultTable = select.getResult();
        if(aggregate != null){
            Aggregate ag = new Aggregate(resultTable, agrOption, aggregate);
            result = ag.getResult();
        } else if (option != null){
            Option op = new Option(resultTable, agrOption, option);
            result = op.getResult();
        } else {
            result = resultTable.toJson().toString();
        }
    }

    public String getResult() {
        return result;
    }

    /**
     * return the result
     * @return
     */
    public Table getResultTable() {
        return resultTable;
    }

}
