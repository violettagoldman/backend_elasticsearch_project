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

        List<ArgWhere> args = new ArrayList<>();

        args.add(ArgWhere.newCondition("Couleur", "Blanc"));
        args.add(ArgWhere.newOperator(Operator.Type.OR));
        args.add(ArgWhere.newCondition("Age", "3"));

        Request r = new Request("dogs", new String[]{"Id", "Prénom", "Couleur", "Age"},args, null, null, null);

        String str = r.getResult();

        String str2 = "{\"lines\":[{\"Prénom\":\"Vanille\",\"Couleur\":\"Blanc\",\"Id\":\"5\",\"Age\":\"7\"},{\"Prénom\":\"Milou\",\"Couleur\":\"Blanc\",\"Id\":\"7\",\"Age\":\"3\"},{\"Prénom\":\"Idefix\",\"Couleur\":\"Blanc\",\"Id\":\"8\",\"Age\":\"14\"}]}";
        assertEquals(str, str2);
    }

    @Test
    public void request_AND() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        List<ArgWhere> args = new ArrayList<>();

        args.add(ArgWhere.newCondition("Couleur", "Noir"));
        args.add(ArgWhere.newOperator(Operator.Type.AND));
        args.add(ArgWhere.newCondition("Age", "5"));

        Request r = new Request("dogs", new String[]{"Id", "Prénom", "Couleur", "Age"},args, null, null, null);

        String str = r.getResult();

        String str2 = "{\"lines\":[{\"Prénom\":\"Pitié\",\"Couleur\":\"Noir\",\"Id\":\"3\",\"Age\":\"5\"}]}";
        assertEquals(str, str2);
    }

    @Test
    public void request_Parenthesis() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        List<ArgWhere> args = new ArrayList<>();

        args.add(ArgWhere.newStart());
        args.add(ArgWhere.newCondition("Couleur", "Blanc"));
        args.add(ArgWhere.newOperator(Operator.Type.AND));
        args.add(ArgWhere.newCondition("Age", "3"));
        args.add(ArgWhere.newEnd());
        args.add(ArgWhere.newOperator(Operator.Type.OR));
        args.add(ArgWhere.newCondition("Couleur", "Roux"));

        Request r = new Request("dogs", new String[0],args, null, null, null);

        String str = r.getResult();
        String str2 = "{\"lines\":[{\"Prénom\":\"Milou\",\"Couleur\":\"Blanc\",\"Id\":\"7\",\"Age\":\"3\"},{\"Prénom\":\"Dingo\",\"Couleur\":\"Roux\",\"Id\":\"10\",\"Age\":\"1\"}]}";

        assertEquals(str, str2);
    }

    @Test
    public void request_select_all() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();
        List<ArgWhere> args = new ArrayList<>();
        Request r = new Request("dogs", new String[0],args, null, null, null);

        String str = r.getResult();
        String str2 = "{\"lines\":[{\"Prénom\":\"Titi\",\"Couleur\":\"Jaune\",\"Id\":\"1\",\"Age\":\"5\"},{\"Prénom\":\"Médor\",\"Couleur\":\"Noir\",\"Id\":\"2\",\"Age\":\"10\"},{\"Prénom\":\"Pitié\",\"Couleur\":\"Noir\",\"Id\":\"3\",\"Age\":\"5\"},{\"Prénom\":\"Juju\",\"Couleur\":\"Gris\",\"Id\":\"4\",\"Age\":\"5\"},{\"Prénom\":\"Vanille\",\"Couleur\":\"Blanc\",\"Id\":\"5\",\"Age\":\"7\"},{\"Prénom\":\"Chocolat\",\"Couleur\":\"Marron\",\"Id\":\"6\",\"Age\":\"12\"},{\"Prénom\":\"Milou\",\"Couleur\":\"Blanc\",\"Id\":\"7\",\"Age\":\"3\"},{\"Prénom\":\"Idefix\",\"Couleur\":\"Blanc\",\"Id\":\"8\",\"Age\":\"14\"},{\"Prénom\":\"Pluto\",\"Couleur\":\"Jaune\",\"Id\":\"9\",\"Age\":\"17\"},{\"Prénom\":\"Dingo\",\"Couleur\":\"Roux\",\"Id\":\"10\",\"Age\":\"1\"}]}";

        assertEquals(str, str2);
    }
}
