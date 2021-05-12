package logic.request;

import logic.Table;
import logic.json.Json;

import java.util.List;

public class Request {
    private Select select;
    private From from;
    private Where where;
    private Table result;

    public Request(String tableName, String [] columnsNames, List<ArgWhere> args){
        from = new From(tableName);
        where = new Where(null);
        select = new Select(where.getResult(), columnsNames, from);
    }

    public void Select(){

    }

}
