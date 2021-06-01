package bTree;

import dataBase.DataBaseTest;
import logic.DataBase;
import logic.IndexBTree.BTree;
import org.junit.Test;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class BtreeTest {

    @Test
    public void create_btree() throws NoSuchAlgorithmException {

        BTree t = new BTree(2, "name", "String"); // A B-Tree with minium degree 2
        t.insert("jeanne", 1);
        t.insert("jeanne", 3);
        t.insert("jeanne", 2);
        t.insert("cecile", 3);
        t.insert("olivier", 4);

        String str = t.getData(1);
        String str2 = "jeanne";
        assertEquals(str, str2);
    }

    @Test
    public void search_btree() throws NoSuchAlgorithmException {

        BTree t = new BTree(2, "name", "String"); // A B-Tree with minium degree 2
        t.insert("jeanne", 1);
        t.insert("jeanne", 3);
        t.insert("jeanne", 2);
        t.insert("cecile", 5);
        t.insert("olivier", 4);

        String str =t.search("jeanne").toString()+t.search("cecile")+t.search("olivier");
        String str2 = "key : 964362342 | data : jeanne | Occurrence [ 1  | 2  | 3  ]\n" +
                "key : 1482823034 | data : cecile | Occurrence [ 5  ]\n" +
                "key : 355452125 | data : olivier | Occurrence [ 4  ]\n";
        assertEquals(str, str2);
    }

    @Test
    public void btree_get_occurrences() throws NoSuchAlgorithmException {

        BTree t = new BTree(2, "name", "String"); // A B-Tree with minium degree 2
        t.insert("jeanne", 1);
        t.insert("jeanne", 3);
        t.insert("jeanne", 2);
        t.insert("cecile", 5);
        t.insert("olivier", 4);

        String str =t.search("jeanne").getOccurrencesList().toString();
        String str2 = "[1, 2, 3]";
        assertEquals(str, str2);
    }

}