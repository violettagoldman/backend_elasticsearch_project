package dataBase;

import logic.DataBase;
import logic.Table;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static logic.DataBase.setName;
import static org.junit.Assert.assertEquals;

public class DataBaseTest {

    public DataBaseTest(){    }

    @Test
    public void initDataBase(){
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
        bordeauxParis2.put("Ville arrivée", "Lavandia");
        bordeauxParis2.put("Prix", "75");
        table.addLine(bordeauxParis2);

        Map<String, String> parisStDenis = new HashMap<>();
        parisStDenis.put("Ville Départ", "Azuria");
        parisStDenis.put("Ville arrivée", "StDenis");
        parisStDenis.put("Prix", "3000");
        table.addLine(parisStDenis);

        Map<String, String> a = new HashMap<>();
        a.put("Ville Départ", "Paris");
        a.put("Ville arrivée", "Test");
        a.put("Prix", "45");
        table.addLine(a);

        DataBase db = setName("Voyage");
        db.getTables().put("voyage", table);


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
