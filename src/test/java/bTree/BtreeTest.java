package bTree;

import dataBase.DataBaseTest;
import logic.DataBase;
import logic.IndexBTree.BTree;
import org.junit.Test;
import java.security.NoSuchAlgorithmException;

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
        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        DataBase.getInstance().getTables().get("voyage").createIndex(new String [] {"Ville arrivée"});
        DataBase.getInstance().getTables().get("voyage").getIndex().get("Ville arrivée").traverse();
    }
}