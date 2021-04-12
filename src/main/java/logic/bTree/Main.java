package logic.bTree;

import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {


//
//        System.out.println((new Entry("jeanne").getKey()));
//        System.out.println((new Entry("jeanne").getKey()));
//        System.out.println((new Entry("jeanne").getKey()));
//        System.out.println((new Entry("jeanne").getKey()));
//        System.out.println((new Entry("jeanne").getKey()));

//        System.out.println(Math.abs("pierre".hashCode() % 7));
//        System.out.println(Math.abs("paul".hashCode() % 7));
//        System.out.println(Math.abs("cecile".hashCode() % 7));
//        System.out.println(Math.abs(data.hashCode() % 7)"olivier");
//        System.out.println(Math.abs(data.hashCode() % 7)"nancy");
//        System.out.println(Math.abs(data.hashCode() % 7)"camille");
//        System.out.println(Math.abs(data.hashCode() % 7)"thomas");
//        System.out.println(Math.abs(data.hashCode() % 7)"charlie");
//        System.out.println(Math.abs(data.hashCode() % 7)"sebastien");
//        System.out.println(Math.abs(data.hashCode() % 7)"christianne");
//        System.out.println(Math.abs(data.hashCode() % 7)"constance");
//        System.out.println(Math.abs(data.hashCode() % 7)"estelle");
//        System.out.println(Math.abs(data.hashCode() % 7)"sarah");
//        System.out.println(Math.abs(data.hashCode() % 7)"mathilde");
//        System.out.println(Math.abs(data.hashCode() % 7)"guillaume");
//        System.out.println(Math.abs(data.hashCode() % 7)"pauline");
//        System.out.println(Math.abs(data.hashCode() % 7)"margaux");
//        System.out.println(Math.abs(data.hashCode() % 7)"michelle");
//        System.out.println(Math.abs(data.hashCode() % 7)"benedicte");
//        System.out.println(Math.abs(data.hashCode() % 7)"jean hervé");
//        System.out.println(Math.abs(data.hashCode() % 7)"clement");
//        System.out.println(Math.abs(data.hashCode() % 7)"alex");

        BTree t = new BTree(2); // A B-Tree with minium degree 2
        t.insert("jeanne");
        t.insert("pierre");
        t.insert("paul");
        t.insert("cecile");
        t.insert("olivier");
        t.insert("nancy");
        t.insert("camille");
        t.insert("thomas");
        t.insert("charlie");
        t.insert("sebastien");
        t.insert("christianne");
        t.insert("constance");
        t.insert("estelle");
        t.insert("sarah");
        t.insert("mathilde");
        t.insert("guillaume");
        t.insert("pauline");
        t.insert("margaux");
        t.insert("michelle");
        t.insert("benedicte");
        t.insert("jean hervé");
        t.insert("clement");
        t.insert("alex");

        System.out.println("Traversal of tree constructed is");
        t.traverse();
        System.out.println("\n test\n");

        t.searchNode("jeanne").traverse();;

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