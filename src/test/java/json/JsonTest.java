package json;

import dataBase.DataBaseTest;
import io.vertx.core.json.JsonObject;
import logic.DataBase;
import logic.Table;
import logic.json.jsonTable.JsonTable;
import org.junit.Test;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static logic.json.jsonGet.JsonGet.json;

import static logic.json.jsonIndex.JsonIndex.jsonIndex;
import static logic.json.jsonTable.JsonTable.jsonTable;
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
                "  \t\"columns\":\n" +
                "    [\n" +
                "      {\n" +
                "        \"column\": \"Couleur\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"column\": \"Age\"\n" +
                "      }\n" +
                "    ],\n" +
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

        JsonObject dataObject = new JsonObject(data);
        JsonObject result1 = json(dataObject);
        System.out.println(result1);

        String result2 = "{\"lines\":[{\"Couleur\":\"Noir\",\"Age\":\"10\"},{\"Couleur\":\"Noir\",\"Age\":\"5\"}]}";

        assertEquals(result1.toString(),result2);
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

        ArrayList resultNames = (ArrayList) JsonTable.jsonTable(data, true);
        ArrayList resultTypes = (ArrayList) JsonTable.jsonTable(data, false);

        System.out.println(resultNames);
        System.out.println(resultTypes);

        ArrayList resultNames2 = new ArrayList();
        resultNames2.add("Couleur");
        resultNames2.add("Age");
        System.out.println(resultNames2);

        ArrayList resultTypes2 = new ArrayList();
        resultTypes2.add("String");
        resultTypes2.add("Int");
        System.out.println(resultTypes2);

        assertEquals(resultNames,resultNames2);
        assertEquals(resultTypes,resultTypes2);
    }
}
