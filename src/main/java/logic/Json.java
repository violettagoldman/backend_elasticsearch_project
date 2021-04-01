package logic;
import org.json.JSONArray;
import org.json.JSONObject;

public class Json {

    public static void main(String args[]) {

        /*

        //createtable
        String request1 = "{\"request\":{\"method\":\"createtable\"},\"args\":[{\"table_name\":\"test_table_name\", \"table_headers\":\"test_table_headers\",}]}";

        //createindex
        String request2 = "{\"request\":{\"method\":\"createindex\"},\"args\":[{\"table_name\":\"test_table_name\", \"columns\":\"test_columns\",}]}";

        //uploadcsv
        String request3 = "{\"request\":{\"method\":\"uploadcsv\"},\"args\":[{\"table_name\":\"test_table_name\", \"csv\":\"test_csv\",}]}";

        //get
        String request4 = "{\"request\":{\"method\":\"get\"},\"args\":[{\"table_name\":\"test_table_name\", \"query\":\"test_query\",}]}";

        json(request1);
        json(request2);
        json(request3);
        json(request4);

        */

        String request = "{\"request\":{\"method\":\"select\"},\"args\":[{\"table_name\":\"test_table_name\", \"column\":\"test_column\", \"value\":\"test_value\",}]}";
        json(request);

    }

    public static void getArg(JSONObject jsonO, String arg){
        JSONArray arr = jsonO.getJSONArray("args");
        String value = arr.getJSONObject(0).getString(arg);
        System.out.println(value);
    }

    public static void json(String request) {

        JSONObject jsonO = new JSONObject(request);
        String method = jsonO.getJSONObject("request").getString("method");

        switch(method){

            case "createtable":
                System.out.println("Method : "+ method);
                getArg(jsonO, "table_name");
                getArg(jsonO, "table_headers");
                //Appel de fonction
                break;

            case "createindex":
                System.out.println("Method : "+ method);
                getArg(jsonO, "table_name");
                getArg(jsonO, "columns");
                //Appel de fonction
                break;

            case "uploadcsv":
                System.out.println("Method : "+ method);
                getArg(jsonO, "table_name");
                getArg(jsonO, "csv");
                //Appel de fonction
                break;

            case "get":
                System.out.println("Method : "+ method);
                getArg(jsonO, "table_name");
                getArg(jsonO, "query");
                //Appel de fonction
                break;

            case "select":
                System.out.println("Method : "+ method);
                getArg(jsonO, "table_name");
                getArg(jsonO, "column");
                getArg(jsonO, "value");
                //Appel de fonction
                break;

            default:
                System.out.println("No Method Find");
                break;
        }
    }
}


