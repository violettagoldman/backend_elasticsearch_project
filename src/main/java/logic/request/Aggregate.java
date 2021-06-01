package logic.request;

import logic.Table;
import logic.request.wTree.ArgWhere;

import java.lang.invoke.SwitchPoint;

public class Aggregate {
    private String result;
    public enum Type {
        SUM,
        AVERAGE,
        MIN,
        MAX,
        COUNT,
        COUNT_DISTINCT,
    }

    public Aggregate(Table table, String column, Type type){
        switch (type){
            case SUM :
                result = table.getColumns().get(column).sum();
                break;
            case AVERAGE:
                result = table.getColumns().get(column).average();
                break;
            case MIN :
                result = table.getColumns().get(column).min();
                break;
            case MAX :
                result = table.getColumns().get(column).max();
                break;
            case COUNT :
                result = table.getColumns().get(column).count();
                break;
            case COUNT_DISTINCT:
                result = table.getColumns().get(column).countDistinct();
                break;
            default : result = "Error";
        }
    }

    public String getResult() {
        return result;
    }
}
