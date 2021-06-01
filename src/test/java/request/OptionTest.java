package request;

import dataBase.DataBaseTest;
import logic.request.Aggregate;
import logic.request.Option;
import logic.request.Request;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class OptionTest {

    @Test
    public void request_OrderBy() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        Request r = new Request("dogs", new String[]{"Id", "Prénom", "Couleur", "Age"}, null, null, Option.Type.ORDER_BY, "Prénom");
        String str = r.getResult();

       String str2 = "Nom de la table : result\n" +
               "id | Age | Couleur | Id | Prénom |\n" +
               " 5 | 12 | Marron | 6 | Chocolat |\n" +
               " 9 | 1 | Roux | 10 | Dingo |\n" +
               " 7 | 14 | Blanc | 8 | Idefix |\n" +
               " 3 | 5 | Gris | 4 | Juju |\n" +
               " 6 | 3 | Blanc | 7 | Milou |\n" +
               " 1 | 10 | Noir | 2 | Médor |\n" +
               " 2 | 5 | Noir | 3 | Pitié |\n" +
               " 8 | 17 | Jaune | 9 | Pluto |\n" +
               " 0 | 5 | Jaune | 1 | Titi |\n" +
               " 4 | 7 | Blanc | 5 | Vanille |\n";

        assertEquals(str, str2);
    }

    @Test
    public void request_Limit() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        Request r = new Request("dogs", new String[]{}, null, null, Option.Type.LIMIT, "3");
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
