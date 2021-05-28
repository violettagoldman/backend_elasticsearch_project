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
        ct.add("Int");
        ct.add("String");
        ct.add("String");
        ct.add("Int");
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
        DataBase.getInstance().getTables().get("dogs").getIndex().get("Couleur").traverse();

    }

    @Test
    public void test(){
        initDataBase();
        Table table = DataBase.getInstance().getTables().get("voyage");
        String str = table.toString();
        String str2 = "Nom de la table : Voyages\n"+
        "id | Prix | Ville Départ | Ville arrivée |\n"+
                " 0 | 75 | Paris | Bordeaux |\n"+
                " 1 | 75 | Paris | Lavandia |\n"+
                " 2 | 3000 | Azuria | StDenis |\n"+
                " 3 | 45 | Paris | Test |\n";

        assertEquals(str, str2);
    }
}
