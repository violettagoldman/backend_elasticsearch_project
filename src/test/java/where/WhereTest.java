package where;

import logic.DataBase;
import logic.Table;
import logic.request.Request;
import logic.request.wTree.ArgWhere;
import logic.request.wTree.Operator;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static logic.DataBase.createInstance;

public class WhereTest {
    @Test
    public void where_test() throws NoSuchAlgorithmException {

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

        table.createIndex(new String [] {"Ville Départ"});
        table.createIndex(new String [] {"Ville arrivée"});
        table.createIndex(new String [] {"Prix"});

        DataBase db = createInstance("Voyage");
        db.getTables().put("voyage", table);

        List args = new ArrayList<>();
        args.add(new ArgWhere(null, "Ville Départ", "Paris"));
        args.add(new ArgWhere(Operator.Type.AND, null, null));
        args.add(new ArgWhere(null, "Ville arrivée", "Test"));
        args.add(new ArgWhere(Operator.Type.AND, null, null));
        args.add(new ArgWhere(null, "Ville Départ", "Paris"));
        args.add(new ArgWhere(Operator.Type.AND, null, null));
        args.add(new ArgWhere(null, "Ville arrivée", "Test"));

        Request r = new Request("voyage", new String[]{"Ville Départ","Ville arrivée", "Prix"},args );
        System.out.println("test ____ request");
        System.out.println(r.getResult().toString());


    }
}