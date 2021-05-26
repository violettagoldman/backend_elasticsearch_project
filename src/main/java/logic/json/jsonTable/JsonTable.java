package logic.json.jsonTable;

import com.google.gson.Gson;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class JsonTable {

    private static String column;
    private static String type;
    public static Map<String, String> str;

    public static Map<String, String> jsonTable(String request) throws NoSuchAlgorithmException {

        Gson gson = new Gson();
        Request result = gson.fromJson(request, Request.class);

        str =  new HashMap<String, String>();

        for (Column c : result.getColumns()) {
            column = c.getColumn();
            type = c.getType();
            str.put(column, type);
        }

        return str;
    }

}
