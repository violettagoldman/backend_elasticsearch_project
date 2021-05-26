package logic.json;

import com.google.gson.Gson;
import io.vertx.core.json.JsonObject;
import logic.DataBase;
import logic.request.wTree.ArgWhere;
import logic.request.wTree.Operator;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Json {

    private static String method;
    private static String table;
    private static String column;
    private static String value;
    public static String str;

    private static DataBase db;

    public static String json(String request) throws NoSuchAlgorithmException {

        Gson gson = new Gson();
        Request result = gson.fromJson(request, logic.json.Request.class);

        method = result.getMethod();
        table = result.getTable();

        //String[] columnsNames = new String[]{};
        ArrayList<String> columnsNames = new ArrayList<String>();

        for (Arg a : result.getArgs()) {
            column = a.getColumn();
            columnsNames.add(column);

            value = a.getValue();
        }


        switch (method) {
            case "select":
                System.out.println("Method : " + method);
                System.out.println("table_name : " + table
                        + " / column : " + column
                        + " / value : " + value);

                List args = new ArrayList<>();
                args.add(ArgWhere.newStart());
                args.add(ArgWhere.newCondition(column, value));
                args.add(ArgWhere.newEnd());

                //Appel de fonction
                logic.request.Request r = new logic.request.Request(table, columnsNames.toArray(new String[0]) ,args );


                str = r.getResult().toString();
                System.out.println(str);
                break;

            default:
                System.out.println("No method find");
                str = "No method find";
                break;
        }
        return str;
    }

    public static void setDb(DataBase db) {
        Json.db = db;
    }
}


