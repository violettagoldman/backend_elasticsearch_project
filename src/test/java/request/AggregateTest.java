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

public class AggregateTest {

    @Test
    public void request_Count() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        List args = new ArrayList<>();

        args.add(ArgWhere.newCondition("Couleur", "Blanc"));

        Request r = new Request("dogs", new String[]{"Id", "Prénom", "Couleur", "Age"},args, Aggregate.Type.COUNT, null, "Couleur");

        String str = r.getResult();
        String str2 = "{\"count\":3}";
        assertEquals(str, str2);
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

        String str2 ="{\"count distinct\":2}";
        assertEquals(str, str2);
    }

    @Test
    public void request_SUM() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        Request r = new Request("dogs", new String[]{"Age"}, null , Aggregate.Type.SUM, null, "Age");

        String str = r.getResult();

        String str2 ="{\"sum\":79}";
        assertEquals(str, str2);
    }

    @Test
    public void request_AVG() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        Request r = new Request("dogs", new String[]{"Age"}, null , Aggregate.Type.AVERAGE, null, "Age");

        String str = r.getResult();
        String str2 = "{\"Average\":7.9}";
        assertEquals(str, str2);
    }

    @Test
    public void request_min() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        Request r = new Request("dogs", new String[]{"Age"}, null , Aggregate.Type.MIN, null, "Age");

        String str = r.getResult();
        String str2 = "{\"min\":1}";
        assertEquals(str, str2);
    }

    @Test
    public void request_max() throws NoSuchAlgorithmException, IOException {

        DataBaseTest test = new DataBaseTest();
        test.initDataBase();

        Request r = new Request("dogs", new String[]{"Age"}, null , Aggregate.Type.MAX, null, "Age");

        String str = r.getResult();
        String str2 = "{\"max\":17}";
        assertEquals(str, str2);
    }
}
