package logic.request;

import logic.Table;
import logic.request.wTree.ArgWhere;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * class that implements a request
 */
public class Request {
    private Select select;
    private From from;
    private Where where;
    private Table result;

    /**
     * constructor
     * @param tableName
     * @param columnsNames
     * @param args
     * @throws NoSuchAlgorithmException
     */
    public Request(String tableName, String [] columnsNames, List<ArgWhere> args) throws NoSuchAlgorithmException {
        from = new From(tableName);
        where = new Where(args, tableName);
        select = new Select(where.getResult(), columnsNames, from);
        result = select.getResult();
    }

    /**
     * return the result
     * @return
     */
    public Table getResult() {
        return result;
    }

}
