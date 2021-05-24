package logic.json;

import com.google.gson.Gson;
import logic.DataBase;

public class Json {

    private static String method;
    private static String table;
    private static String column;
    private static String value;

    private static DataBase db;

    public static void json(String request) {

        Gson gson = new Gson();
        Request result = gson.fromJson(request, Request.class);

        method = result.getMethod();
        table = result.getTable();

        for (Arg a : result.getArgs()) {
            column = a.getColumn();
            value = a.getValue();
        }

        switch (method) {
            case "select":
                System.out.println("Method : " + method);
                System.out.println("table_name : " + table
                        + " / column : " + column
                        + " / value : " + value);

                //Appel de fonction
                //System.out.println(db.selectFromWhere(table_name, column, value));
                //System.out.println(db.getTables().get(table_name).toString());
                break;

            default:
                System.out.println("No method find");
                break;
        }
    }

    public static void setDb(DataBase db) {
        Json.db = db;
    }
}


