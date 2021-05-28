package where;

import dataBase.DataBaseTest;
import logic.DataBase;
import logic.Table;
import logic.request.Request;
import logic.request.wTree.ArgWhere;
import logic.request.wTree.Operator;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static logic.DataBase.setName;
import static org.junit.Assert.assertEquals;
/*
public class WhereTest {
    @Test
    public void request_test() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();
        DataBase.getInstance().getTables().get("voyage").createIndex(
                new String [] {"Ville Départ", "Ville arrivée", "Prix"});

        List args = new ArrayList<>();
        args.add(ArgWhere.newStart());
        args.add(ArgWhere.newStart());
        args.add(ArgWhere.newCondition("Ville Départ", "Paris"));
        args.add(ArgWhere.newOperator(Operator.Type.OR));
        args.add(ArgWhere.newCondition("Ville arrivée", "Test"));
        args.add(ArgWhere.newEnd());
        args.add(ArgWhere.newOperator(Operator.Type.OR));
        args.add(ArgWhere.newCondition("Ville Départ", "AZURIA"));
        args.add(ArgWhere.newEnd());
        args.add(ArgWhere.newOperator(Operator.Type.OR));
        args.add(ArgWhere.newStart());
        args.add(ArgWhere.newCondition("Ville Départ", "Azuria"));
        args.add(ArgWhere.newOperator(Operator.Type.AND));
        args.add(ArgWhere.newCondition("Ville arrivée", "StDenis"));
        args.add(ArgWhere.newEnd());
        Request r = new Request("voyage", new String[]{},args );

        String str = r.getResult().toString();

        String str2 =
        "Nom de la table : result\n"+
        "id | Prix | Ville Départ | Ville arrivée |\n"+
               " 0 | 75 | Paris | Bordeaux |\n"+
               " 1 | 75 | Paris | Lavandia |\n"+
               " 2 | 3000 | Azuria | StDenis |\n"+
               " 3 | 45 | Paris | Test |\n";

        assertEquals(str, str2);
    }

}
*/