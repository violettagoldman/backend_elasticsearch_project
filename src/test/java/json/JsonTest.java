package json;

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

        Map<String, String> columnsMap = new HashMap<>();
        columnsMap.put("Ville Départ", "String");
        columnsMap.put("Ville arrivée", "String");
        columnsMap.put("Prix", "Int");
        Table table = new Table("Voyages", columnsMap);

        Map<String, String> bordeauxParis = new HashMap<>();
        bordeauxParis.put("Ville Départ", "Paris");
        bordeauxParis.put("Ville arrivée", "Bordeaux");
        bordeauxParis.put("Prix", "75");
        table.addLine(bordeauxParis);

        Map<String, String> bordeauxParis2 = new HashMap<>();
        bordeauxParis2.put("Ville Départ", "Paris");
        bordeauxParis2.put("Ville arrivée", "Bordeaux");
        bordeauxParis2.put("Prix", "75");
        table.addLine(bordeauxParis2);

        Map<String, String> parisStDenis = new HashMap<>();
        parisStDenis.put("Ville Départ", "Paris");
        parisStDenis.put("Ville arrivée", "StDenis");
        parisStDenis.put("Prix", "3000");
        table.addLine(parisStDenis);

        table.createIndex(new String [] {"Ville Départ", "Ville arrivée"});

        DataBase db = DataBase.setName("Voyage");
        db.getTables().put("voyage", table);


        Json.setDb(db);
        final String data = "{\n" +
                "    \"method\": \"select\",\n" +
                "    \"table\": \"voyage\",\n" +
                "  \t\"args\":\n" +
                "    [\n" +
                "      {\n" +
                "        \"column\": \"Ville Départ\",\n" +
                "        \"value\": \"Paris\"\n" +
                "      }\n" +
                "    ]\n" +
                "}";

        String test = json(data);
        System.out.println(test);
    }
}
