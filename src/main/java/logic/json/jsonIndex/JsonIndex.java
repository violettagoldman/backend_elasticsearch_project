package logic.json.jsonIndex;

import com.google.gson.Gson;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class JsonIndex {

    private static String column;
    public static String[] str;

    public static String[] jsonIndex(String request) throws NoSuchAlgorithmException {

        Gson gson = new Gson();
        Request result = gson.fromJson(request, Request.class);

        ArrayList<String> columnsArray = new ArrayList<String>();

        for (Column c : result.getColumns()) {
            column = c.getColumn();
            columnsArray.add(column);
        }

        str = columnsArray.toArray(new String[columnsArray.size()]);

        return str;
    }

}