package json;

import dataBase.DataBaseTest;
import logic.DataBase;
import logic.Table;
import logic.json.jsonTable.JsonTable;
import org.junit.Test;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static logic.json.jsonGet.JsonGet.json;

import static logic.json.jsonIndex.JsonIndex.jsonIndex;
import static org.junit.Assert.assertEquals;

public class JsonTest {


    @Test
    public void jsonGet() throws IOException, NoSuchAlgorithmException {

        DataBaseTest db = new DataBaseTest();
        db.initDataBase();
        Table table = DataBase.getInstance().getTables().get("dogs");
        final String data = "{\n" +
                "    \"method\": \"select\",\n" +
                "    \"table\": \"dogs\",\n" +
                "  \t\"args\":\n" +
                "    [\n" +
                "      {\n" +
                "        \"column\": \"Couleur\",\n" +
                "        \"value\": \"Noir\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"operator\": \"OR\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"column\": \"Age\",\n" +
                "        \"value\": \"10\"\n" +
                "      }\n" +
                "    ]\n" +
                "}";

        String str = json(data);
        System.out.println(str);

        String str2 =
                "Nom de la table : result\n" +
                "id | Ville Départ | Ville arrivée |\n" +
                " 0 | Paris | Bordeaux |\n" +
                " 1 | Paris | Lavandia |\n" +
                " 3 | Paris | Test |\n";

        //assertEquals(str,str2);
    }

    @Test
    public void jsonIndex() throws IOException, NoSuchAlgorithmException {

        final String data = "{\n" +
                "    \"table_name\": \"Table_Name\",\n" +
                "  \t\"columns\":\n" +
                "    [\n" +
                "      {\n" +
                "      \"column\": \"Couleur\"\n" +
                "      },\n" +
                "      {\n" +
                "      \"column\": \"Age\"\n" +
                "      }\n" +
                "    ]\n" +
                "}";

        String[] str = logic.json.jsonIndex.JsonIndex.jsonIndex(data);
        System.out.println(str);
    }

    @Test
    public void jsonTable() throws IOException, NoSuchAlgorithmException {

        final String data = "{\n" +
                "    \"table_name\": \"Table_Name\",\n" +
                "  \t\"columns\":\n" +
                "    [\n" +
                "      {\n" +
                "      \"column\": \"Couleur\",\n" +
                "      \"type\": \"String\"\n" +
                "      },\n" +
                "      {\n" +
                "      \"column\": \"Age\",\n" +
                "      \"type\": \"Int\"\n" +
                "      }\n" +
                "    ]\n" +
                "}";

        ArrayList str = (ArrayList) JsonTable.jsonTable(data, true);
        System.out.println(str);

        Map<String, String> str2 = new HashMap<String, String>();
        str2.put("Couleur", "String");
        str2.put("Age", "Int");

        //assertEquals(str,str2);
    }
}
