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

import static org.junit.Assert.assertEquals;

public class RequestTest {
    @Test
    public void request_OR() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        List args = new ArrayList<>();

        args.add(ArgWhere.newCondition("Couleur", "Blanc"));
        args.add(ArgWhere.newOperator(Operator.Type.OR));
        args.add(ArgWhere.newCondition("Age", "3"));

        Request r = new Request("dogs", new String[]{"Id", "Prénom", "Couleur", "Age"},args, null, null, null);

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
    public void request_AND() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        List args = new ArrayList<>();

        args.add(ArgWhere.newCondition("Couleur", "Blanc"));
        args.add(ArgWhere.newOperator(Operator.Type.OR));
        args.add(ArgWhere.newCondition("Age", "3"));

        Request r = new Request("dogs", new String[]{"Id", "Prénom", "Couleur", "Age"},args, null, null, null);

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
    public void request_Parenthesis() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        List args = new ArrayList<>();

        args.add(ArgWhere.newStart());
        args.add(ArgWhere.newCondition("Couleur", "Blanc"));
        args.add(ArgWhere.newOperator(Operator.Type.AND));
        args.add(ArgWhere.newCondition("Age", "3"));
        args.add(ArgWhere.newEnd());
        args.add(ArgWhere.newOperator(Operator.Type.OR));
        args.add(ArgWhere.newCondition("Couleur", "Roux"));

        Request r = new Request("dogs", new String[]{"Id", "Prénom", "Couleur", "Age"},args, null, null, null);

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
