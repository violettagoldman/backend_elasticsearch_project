package bTree;

import logic.bTree.BTree;
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
        t.insert("nancy", 7);

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
        t.insert("nancy", 7);

        System.out.println(t.search("jeanne"));
    }
}