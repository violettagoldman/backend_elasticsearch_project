package bTree;

import logic.DataBase;
import logic.Table;
import logic.bTree.BTree;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static logic.DataBase.createInstance;

public class BtreeTest {

    @Test
    public void create_btree() throws NoSuchAlgorithmException {

        BTree t = new BTree(2, "name"); // A B-Tree with minium degree 2
        t.insert("jeanne", 1);
        t.insert("jeanne", 3);
        t.insert("jeanne", 2);
        t.insert("cecile", 3);
        t.insert("olivier", 4);
        t.insert("test", 7);
        t.insert("te", 7);
        t.insert("frznfez", 7);
        t.insert("nfezi", 7);
        t.insert("nfeiz", 7);

        System.out.println("Traversal of tree constructed is");
        t.traverse();

    }

    @Test
    public void search_btree() throws NoSuchAlgorithmException {

        BTree t = new BTree(2, "name"); // A B-Tree with minium degree 2
        t.insert("jeanne", 1);
        t.insert("jeanne", 3);
        t.insert("jeanne", 2);
        t.insert("cecile", 3);
        t.insert("olivier", 4);
        t.insert("test", 7);
        t.insert("te", 7);
        t.insert("frznfez", 7);
        t.insert("nfezi", 7);
        t.insert("nfeiz", 7);

        System.out.println(t.search("jeanne"));
        System.out.println(t.search("cecile"));
        System.out.println(t.search("olivier"));
        System.out.println(t.search("test"));
        System.out.println(t.search("te"));
        System.out.println(t.search("frznfez"));
        System.out.println(t.search("nfezi"));
        System.out.println(t.search("nfeiz"));

        System.out.println(t.search("jeanne"));
    }

    @Test
    public void create_by_table() throws NoSuchAlgorithmException {
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
        System.out.println(table.getIndex().get("Ville Départ").occurences("Paris"));
       // table.createIndex(new String [] {"Prix"});

        DataBase db = createInstance("Voyage");
        db.getTables().put("voyage", table);
    }
}