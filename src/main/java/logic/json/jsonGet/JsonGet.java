package logic.json.jsonGet;

import com.google.gson.Gson;
import logic.request.Aggregate;
import logic.request.Option;
import logic.request.wTree.ArgWhere;
import logic.request.wTree.Operator;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class JsonGet {

    private static String method;
    private static String table;

    private static String column;
    private static String value;
    private static String operator;

    private static String aggregateName;
    private static String aggregateColumm;
    private static Aggregate.Type agrType;
    private static String agrOption;

    private static String optionName;
    private static String optionArg;
    private static Option.Type optionType;

    public static String str;

    public static String json(String request) throws NoSuchAlgorithmException {

        Gson gson = new Gson();
        Request result = gson.fromJson(request, logic.json.jsonGet.Request.class);

        method = result.getMethod();
        table = result.getTable();

        ArrayList<String> columnsNames = new ArrayList<String>();
        List args = new ArrayList<>();

        for (Arg a : result.getArgs()) {

            column = a.getColumn();
            value = a.getValue();
            operator = a.getOperator();
            
            if(operator == "("){
                args.add(ArgWhere.newStart());
            }

            else if(operator == ")"){
                args.add(ArgWhere.newEnd());
            }

            else if(operator != null && operator.equalsIgnoreCase("OR")){
                args.add(ArgWhere.newOperator(Operator.Type.OR));
            }

            else if(operator != null && operator.equalsIgnoreCase("AND")){
                args.add(ArgWhere.newOperator(Operator.Type.AND));
            }

            else if(column != null && value != null){
                columnsNames.add(column);
                args.add(ArgWhere.newCondition(column, value));
            }

            column = null;
            value = null;
            operator = null;
        }

        if (result.getAggregate() !=  null){
            aggregateName = result.getAggregate().getAggregateName();
            aggregateColumm = result.getAggregate().getColumn();
        }

        Aggregate.Type agrType = null;
        agrOption = null;

        if(aggregateName != null){
            agrOption = aggregateColumm;
            switch(aggregateName)
            {
                case "SUM":
                    agrType = Aggregate.Type.SUM;
                    break;
                case "AVERAGE":
                    agrType = Aggregate.Type.AVERAGE;
                    break;
                case "MIN":
                    agrType = Aggregate.Type.MIN;
                    break;
                case "MAX":
                    agrType = Aggregate.Type.MAX;
                    break;
                case "COUNT":
                    agrType = Aggregate.Type.COUNT;
                    break;
                case "COUNT_DISTINCT":
                    agrType = Aggregate.Type.COUNT_DISTINCT;
                    break;
            }
        }

        if (result.getOption() !=  null){
            optionName = result.getOption().getOptionName();
            optionArg = result.getOption().getArg();
        }

        Option.Type optionType = null;
        agrOption = null;

        if(aggregateName != null){
            agrOption = optionArg;
            switch(optionName)
            {
                case "ORDER_BY":
                    optionType = Option.Type.ORDER_BY;
                    break;
                case "LIMIT":
                    optionType = Option.Type.LIMIT;
                    break;
            }
        }

        //Appel de fonction
        logic.request.Request r = null;
        try {
            r = new logic.request.Request(table, columnsNames.toArray(new String[columnsNames.size()]) ,args, agrType, optionType, agrOption);
        } catch (IOException e) {
            e.printStackTrace();
        }

        str = r.getJson();

        return str;
    }

}

