package logic.json;

import com.google.gson.Gson;
import logic.DataBase;

public class Json {

    private static String table_name;
    private static String column;
    private static String value;

    private static DataBase db;

    public static void json(String request) {

        Gson gson = new Gson();
        Request result = gson.fromJson(request, Request.class);

        Method j = result.getRequest();
        String method = j.getMethod();

        for (Arg t : result.getArgs()) {
            table_name = t.getTableName();
            column = t.getColumn();
            value = t.getValue();
        }

        switch (method) {
            case "select":
                System.out.println("Method : " + method);
                System.out.println("table_name : " + table_name
                        + " / column : " + column
                        + " / value : " + value);

                //Appel de fonction
                System.out.println(db.selectFromWhere(table_name, column, value));
                System.out.println(db.getTables().get(table_name).toString());
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


