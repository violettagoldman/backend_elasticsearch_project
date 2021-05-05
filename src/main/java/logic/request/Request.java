package logic.request;

import logic.Table;
import logic.json.Json;

public class Request {
    private Select select;
    private From from;
    private Where where;
    private Table result;

    public Request(String tableName, String [] columnsNames){
        from = new From(tableName);
        where = new Where();
        select = new Select(where.getResult(), columnsNames, from);
    }

    public void Select(){

    }

}
