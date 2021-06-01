package logic.request;

import logic.DataBase;
import logic.request.wTree.ArgWhere;
import logic.request.wTree.WTree;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * class that implements the where of the request
 */
public class Where {
    private ArrayList<Integer> result;

    /**
     * constructor
     * @param args list of arguments of where
     * @param tableName the table of the request
     * @throws NoSuchAlgorithmException exception
     */
    public Where(List<ArgWhere> args, String tableName) throws NoSuchAlgorithmException {
        result = new ArrayList();
        WTree tree = new WTree(DataBase.getInstance().getTables().get(tableName));
        tree.insert(args);
        result = tree.calculator();
    }

    /**
     * return the result
     * @return the rows of result
     */
    public ArrayList<Integer> getResult() {
        return result;
    }
}
