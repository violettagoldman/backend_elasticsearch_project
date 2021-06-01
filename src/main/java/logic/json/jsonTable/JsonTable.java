package logic.json.jsonTable;

import com.google.gson.Gson;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonTable {

    private static String column;
    private static String type;
    public static ArrayList str;

    public static ArrayList jsonTable(String request, boolean name ) throws NoSuchAlgorithmException {

        Gson gson = new Gson();
        Request result = gson.fromJson(request, Request.class);

        str =  new ArrayList<String>();

        for (Column c : result.getColumns()) {
            column = c.getColumn();
            type = c.getType();
            if(name) str.add(column);
            else str.add(type);
        }

        return str;
    }

}
