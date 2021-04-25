package logic;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import org.json.*;

public class Json {
    //Classe Java à laquelle va correspondre le JSON
    private class Tasse {
        public String couleur;

        public Tasse(String couleur) {
            this.couleur = couleur;
        }
    }
    public static void main(String args[]) {

        JsonObject objet = new JsonParser().parse(maTasse).getAsJsonObject();
        System.out.println(objet.get(couleur).getAsString()); //Rouge
        //JSON de test
        String maTasse = "{\"couleur\": \"Rouge\"}";
        JSONObject tasse = new JSONObject(maTasse);
        System.out.println(tasse.getString(couleur)); //Rouge

        /*
        final Gson gson = new GsonBuilder().create();

        final String json = "{\"request\":{\"method\":\"select\"},\"args\":[{\"table_name\":\"test_table_name\", \"column\":\"test_column\", \"value\":\"test_value\",}]}";
        */


    }

}


