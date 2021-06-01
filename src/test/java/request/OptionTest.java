package request;

import dataBase.DataBaseTest;
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
        String str2 = "{\"lines\":[{\"Prénom\":\"Chocolat\",\"Couleur\":\"Marron\",\"Id\":\"6\",\"Age\":\"12\"},{\"Prénom\":\"Dingo\",\"Couleur\":\"Roux\",\"Id\":\"10\",\"Age\":\"1\"},{\"Prénom\":\"Idefix\",\"Couleur\":\"Blanc\",\"Id\":\"8\",\"Age\":\"14\"},{\"Prénom\":\"Juju\",\"Couleur\":\"Gris\",\"Id\":\"4\",\"Age\":\"5\"},{\"Prénom\":\"Milou\",\"Couleur\":\"Blanc\",\"Id\":\"7\",\"Age\":\"3\"},{\"Prénom\":\"Médor\",\"Couleur\":\"Noir\",\"Id\":\"2\",\"Age\":\"10\"},{\"Prénom\":\"Pitié\",\"Couleur\":\"Noir\",\"Id\":\"3\",\"Age\":\"5\"},{\"Prénom\":\"Pluto\",\"Couleur\":\"Jaune\",\"Id\":\"9\",\"Age\":\"17\"},{\"Prénom\":\"Titi\",\"Couleur\":\"Jaune\",\"Id\":\"1\",\"Age\":\"5\"},{\"Prénom\":\"Vanille\",\"Couleur\":\"Blanc\",\"Id\":\"5\",\"Age\":\"7\"}]}";

        assertEquals(str, str2);
    }

    @Test
    public void request_Limit() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        Request r = new Request("dogs", new String[]{}, null, null, Option.Type.LIMIT, "3");
        String str = r.getResult();

        String str2 = "{\"lines\":[{\"Prénom\":\"Titi\",\"Couleur\":\"Jaune\",\"Id\":\"1\",\"Age\":\"5\"},{\"Prénom\":\"Médor\",\"Couleur\":\"Noir\",\"Id\":\"2\",\"Age\":\"10\"},{\"Prénom\":\"Pitié\",\"Couleur\":\"Noir\",\"Id\":\"3\",\"Age\":\"5\"}]}";
        assertEquals(str, str2);
    }

}
