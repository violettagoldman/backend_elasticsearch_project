package logic.json.jsonGet;

import com.google.gson.Gson;
import logic.request.wTree.ArgWhere;
import logic.request.wTree.Operator;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class JsonGet {

    private static String method;
    private static String table;
    private static String column;
    private static String value;
    private static String operator;

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

            if(operator == ")"){
                args.add(ArgWhere.newEnd());
            }

            if(operator == "OR" || operator == "or"){
                args.add(ArgWhere.newOperator(Operator.Type.OR));
            }

            if(operator == "AND" || operator == "and"){
                args.add(ArgWhere.newOperator(Operator.Type.AND));
            }

            if(column != null && value != null){
                columnsNames.add(column);
                args.add(ArgWhere.newCondition(column, value));
            }

            column = null;
            value = null;
            operator = null;
        }

        //Appel de fonction
        logic.request.Request r = new logic.request.Request(table, columnsNames.toArray(new String[columnsNames.size()]) ,args );

        str = r.getResult().toString();

        return str;
    }

}


