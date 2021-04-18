package logic.bTree;

import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {



        BTree t = new BTree(2, new String[]{"name"}); // A B-Tree with minium degree 2
        t.insert(new String[]{"jeanne"}, 1);
        t.insert(new String[]{"jeanne"}, 3);
        t.insert(new String[]{"jeanne"}, 2);
        t.insert(new String[]{"cecile"}, 3);
        t.insert(new String[]{"olivier"}, 4);
        t.insert(new String[]{"nancy"}, 7);
//        t.insert("camille");
//        t.insert("thomas");
//        t.insert("charlie");
//        t.insert("sebastien");
//        t.insert("christianne");
//        t.insert("constance");
//        t.insert("estelle");
//        t.insert("sarah");
//        t.insert("mathilde");
//        t.insert("guillaume");
//        t.insert("pauline");
//        t.insert("margaux");
//        t.insert("michelle");
//        t.insert("benedicte");
//        t.insert("jean hervé");
//        t.insert("clement");
//        t.insert("alex");

        System.out.println("Traversal of tree constructed is");
        t.traverse();
        System.out.println("\n test\n");

        System.out.println(t.search(new String[]{"jeanne"}));

//        t.remove(6);
//        System.out.println("Traversal of tree after removing 6");
//        t.traverse();
//        System.out.println();
//
//        t.remove(13);
//        System.out.println("Traversal of tree after removing 13");
//        t.traverse();
//        System.out.println();
//
//        t.remove(7);
//        System.out.println("Traversal of tree after removing 7");
//        t.traverse();
//        System.out.println();
//
//        t.remove(4);
//        System.out.println("Traversal of tree after removing 4");
//        t.traverse();
//        System.out.println();
//
//        t.remove(2);
//        System.out.println("Traversal of tree after removing 2");
//        t.traverse();
//        System.out.println();
//
//        t.remove(16);
//        System.out.println("Traversal of tree after removing 16");
//        t.traverse();
//        System.out.println();
    }
}

//Math.abs(str.hashCode() % 7)