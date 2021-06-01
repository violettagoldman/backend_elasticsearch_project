package logic.request;

import logic.Table;

import java.util.ArrayList;

public class Option {
    private String result;
    public enum Type {
        ORDER_BY,
        LIMIT,
    }

    /**
     * return the result
     * @return
     */
    public String getResult() {
        return result;
    }

    /**
     * switch the good option
     * @param table the table of the request
     * @param option the limit or the column to order by
     * @param type the type of the option
     */
    public Option(Table table, String option, Type type){
        switch (type){
            case ORDER_BY :
                ArrayList<Integer> order = table.getColumns().get(option).orderBy();
                result = table.toStringByOrder(order);
                break;
            case LIMIT :
                try {
                    int limit = Integer.parseInt(option);
                    result = table.toJsonLimit(limit);
                } catch (NumberFormatException e) {
                    result = "Limit not number";
                }
                break;
            default: result = "error options";
        }
    }
}
