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
    private ArrayList result;
    private WTree tree;

    /**
     * constructor
     * @param args
     * @param tableName
     * @throws NoSuchAlgorithmException
     */
    public Where(List<ArgWhere> args, String tableName) throws NoSuchAlgorithmException {
        result = new ArrayList();
        tree = new WTree(DataBase.getInstance().getTables().get(tableName));
        tree.insert(args);
        tree.draw();
        result = tree.calculator();
    }

    /**
     * return the result
     * @return
     */
    public ArrayList getResult() {
        return result;
    }
}
