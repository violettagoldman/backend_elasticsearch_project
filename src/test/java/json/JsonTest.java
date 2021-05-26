package json;

import dataBase.DataBaseTest;
import logic.DataBase;
import logic.Table;
import logic.json.Json;
import org.junit.Test;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static logic.json.Json.json;

public class JsonTest {

    @Test
    public void no_method() throws IOException, NoSuchAlgorithmException {

        final String data = "{\n" +
                "    \"method\": \"no_method\",\n" +
                "    \"table\": \"table_name\",\n" +
                "  \t\"args\":\n" +
                "    [\n" +
                "      {\n" +
                "        \"column\": \"test_column\",\n" +
                "        \"value\": \"test_value\"\n" +
                "      }\n" +
                "    ]\n" +
                "}";

        String test = json(data);
        System.out.println(test);
    }

    @Test
    public void select() throws IOException, NoSuchAlgorithmException {

        DataBaseTest db = new DataBaseTest();
        db.initDataBase();
        DataBase.getInstance().getTables().get("voyage").createIndex(
                new String [] {"Ville Départ", "Ville arrivée", "Prix"});

        final String data = "{\n" +
                "    \"method\": \"select\",\n" +
                "    \"table\": \"voyage\",\n" +
                "  \t\"args\":\n" +
                "    [\n" +
                "      {\n" +
                "        \"column\": \"Ville arrivée\",\n" +
                "        \"value\": \"Bordeaux\"\n" +
                "      }\n" +
                "    ]\n" +
                "}";

        String test = json(data);
        System.out.println(test);
    }
}
