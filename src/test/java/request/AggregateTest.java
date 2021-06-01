package request;

import dataBase.DataBaseTest;
import logic.request.Aggregate;
import logic.request.Request;
import logic.request.wTree.ArgWhere;
import logic.request.wTree.Operator;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class AggregateTest {

    @Test
    public void request_Count() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        List args = new ArrayList<>();

        args.add(ArgWhere.newCondition("Couleur", "Blanc"));

        Request r = new Request("dogs", new String[]{"Id", "Prénom", "Couleur", "Age"},args, Aggregate.Type.COUNT, null, "Couleur");

        String str = r.getResult();
        System.out.println(str);

//        String str2 =
//        "Nom de la table : result\n"+
//        "id | Prix | Ville Départ | Ville arrivée |\n"+
//               " 0 | 75 | Paris | Bordeaux |\n"+
//               " 1 | 75 | Paris | Lavandia |\n"+
//               " 2 | 3000 | Azuria | StDenis |\n"+
//               " 3 | 45 | Paris | Test |\n";
//
//        assertEquals(str, str2);
    }

    @Test
    public void request_CountDistinct() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        List args = new ArrayList<>();

        args.add(ArgWhere.newCondition("Couleur", "Noir"));
        args.add(ArgWhere.newOperator(Operator.Type.OR));
        args.add(ArgWhere.newCondition("Couleur", "Blanc"));

        Request r = new Request("dogs", new String[]{"Id", "Prénom", "Couleur", "Age"},args, Aggregate.Type.COUNT_DISTINCT, null, "Couleur");

        String str = r.getResult();
        System.out.println(str);

//        String str2 =
//        "Nom de la table : result\n"+
//        "id | Prix | Ville Départ | Ville arrivée |\n"+
//               " 0 | 75 | Paris | Bordeaux |\n"+
//               " 1 | 75 | Paris | Lavandia |\n"+
//               " 2 | 3000 | Azuria | StDenis |\n"+
//               " 3 | 45 | Paris | Test |\n";
//
//        assertEquals(str, str2);
    }

    @Test
    public void request_SUM() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        Request r = new Request("dogs", new String[]{"Age"}, null , Aggregate.Type.SUM, null, "Age");

        String str = r.getResult();
        System.out.println(str);

//        String str2 =
//        "Nom de la table : result\n"+
//        "id | Prix | Ville Départ | Ville arrivée |\n"+
//               " 0 | 75 | Paris | Bordeaux |\n"+
//               " 1 | 75 | Paris | Lavandia |\n"+
//               " 2 | 3000 | Azuria | StDenis |\n"+
//               " 3 | 45 | Paris | Test |\n";
//
//        assertEquals(str, str2);
    }

    @Test
    public void request_AVG() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        Request r = new Request("dogs", new String[]{"Age"}, null , Aggregate.Type.AVERAGE, null, "Age");

        String str = r.getResult();
        System.out.println(str);

//        String str2 =
//        "Nom de la table : result\n"+
//        "id | Prix | Ville Départ | Ville arrivée |\n"+
//               " 0 | 75 | Paris | Bordeaux |\n"+
//               " 1 | 75 | Paris | Lavandia |\n"+
//               " 2 | 3000 | Azuria | StDenis |\n"+
//               " 3 | 45 | Paris | Test |\n";
//
//        assertEquals(str, str2);
    }

    @Test
    public void request_min() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        Request r = new Request("dogs", new String[]{"Age"}, null , Aggregate.Type.MIN, null, "Age");

        String str = r.getResult();
        System.out.println(str);

//        String str2 =
//        "Nom de la table : result\n"+
//        "id | Prix | Ville Départ | Ville arrivée |\n"+
//               " 0 | 75 | Paris | Bordeaux |\n"+
//               " 1 | 75 | Paris | Lavandia |\n"+
//               " 2 | 3000 | Azuria | StDenis |\n"+
//               " 3 | 45 | Paris | Test |\n";
//
//        assertEquals(str, str2);
    }

    @Test
    public void request_max() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        Request r = new Request("dogs", new String[]{"Age"}, null , Aggregate.Type.MAX, null, "Age");

        String str = r.getResult();
        System.out.println(str);

//        String str2 =
//        "Nom de la table : result\n"+
//        "id | Prix | Ville Départ | Ville arrivée |\n"+
//               " 0 | 75 | Paris | Bordeaux |\n"+
//               " 1 | 75 | Paris | Lavandia |\n"+
//               " 2 | 3000 | Azuria | StDenis |\n"+
//               " 3 | 45 | Paris | Test |\n";
//
//        assertEquals(str, str2);
    }
}
