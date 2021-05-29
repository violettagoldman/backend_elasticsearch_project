package logic.request;

import logic.Table;

import java.util.ArrayList;

public class Option {
    private String result;
    public enum Type {
        ORDER_BY,
        LIMIT,
    }

    public String getResult() {
        return result;
    }

    public Option(Table table, String option, Type type){
        switch (type){
            case ORDER_BY :
                ArrayList<Integer> order = table.getColumns().get(option).orderBy();
                result = table.toStringByOrder(order);
                break;
            case LIMIT :
                try {
                    int limit = Integer.parseInt(option);
                    result = table.toStringLimit(limit);
                } catch (NumberFormatException e) {
                    result = "Limit not number";
                }
            default: result = "error options";
        }
    }
}
