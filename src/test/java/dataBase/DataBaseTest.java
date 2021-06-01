package dataBase;

import io.vertx.core.json.JsonObject;
import logic.DataBase;
import logic.Table;
import org.junit.Test;
import parser.CSVParser;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static logic.DataBase.setName;
import static org.junit.Assert.assertEquals;

public class DataBaseTest {

    public DataBaseTest(){    }

    @Test
    public void initDataBase(){
        ArrayList<String> cn = new ArrayList<>();
        ArrayList<String> ct = new ArrayList<>();
        cn.add("Id");
        cn.add("Prénom");
        cn.add("Couleur");
        cn.add("Age");
        ct.add("int");
        ct.add("String");
        ct.add("String");
        ct.add("int");
        DataBase.getInstance().newTable("dogs", cn , ct);
        try {
            DataBase.getInstance().getTables().get("dogs").createIndex(new String []{"Couleur", "Age"});
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        CSVParser csvp = new CSVParser();
        File file = new File("src/resources/dogs.csv");
        try {
            csvp.readFileLocal(file, DataBase.getInstance().getTables().get("dogs"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = (DataBase.getInstance().getTables().get("dogs").getIndex().get("Couleur").search("Jaune").toString());
        String str2 = "key : 559286933 | data : Jaune | Occurrence [ 0  | 8  ]\n";
        assertEquals(str, str2);
    }

}
