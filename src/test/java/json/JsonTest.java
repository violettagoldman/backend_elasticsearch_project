package json;

import logic.DataBase;
import logic.Table;
import org.junit.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static logic.json.Json.json;

public class JsonTest {

    @Test
    public void select() throws IOException {

        final String data = "{\n" +
                "  \"request\":\n" +
                "  {\n" +
                "    \"method\" :\"select\"\n" +
                "  },\n" +
                "  \"args\":\n" +
                "  [\n" +
                "    {\n" +
                "      \"table_name\": \"test_table_name\",\n" +
                "      \"column\": \"test_column\",\n" +
                "      \"value\": \"test_value\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        json(data);
    }

    @Test
    public void no_method() throws IOException {

        final String data = "{\n" +
                "  \"request\":\n" +
                "  {\n" +
                "    \"method\" :\"test\"\n" +
                "  },\n" +
                "  \"args\":\n" +
                "  [\n" +
                "    {\n" +
                "      \"table_name\": \"test_table_name\",\n" +
                "      \"column\": \"test_column\",\n" +
                "      \"value\": \"test_value\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        json(data);
    }

    @Test
    public void select_with_function() throws IOException {

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

        DataBase db = new DataBase("Voyage");
        db.tables.put("voyage", table);

        final String data = "{\n" +
                "  \"request\":\n" +
                "  {\n" +
                "    \"method\" :\"select\"\n" +
                "  },\n" +
                "  \"args\":\n" +
                "  [\n" +
                "    {\n" +
                "      \"table_name\": \"voyage\",\n" +
                "      \"column\": \"Ville arrivée\",\n" +
                "      \"value\": \"Bordeaux\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        json(data);
    }
}
