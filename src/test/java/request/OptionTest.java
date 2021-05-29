package request;

import dataBase.DataBaseTest;
import logic.request.Aggregate;
import logic.request.Option;
import logic.request.Request;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class OptionTest {

    @Test
    public void request_OrderBy() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        Request r = new Request("dogs", new String[]{"Id", "Prénom", "Couleur", "Age"}, null, null, Option.Type.ORDER_BY, "Prénom");
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
