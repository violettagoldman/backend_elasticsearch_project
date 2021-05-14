package logic.request;

import logic.DataBase;
import logic.Table;
import logic.request.wTree.ArgWhere;
import logic.request.wTree.WTree;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Where {
    private ArrayList result;
    private List<ArgWhere> args;
    private Table table;

    public Where(List<ArgWhere> args, String tableName) throws NoSuchAlgorithmException {
        this.args = args;
        result = new ArrayList();
        WTree tree = new WTree(DataBase.getInstance().getTables().get(tableName));
        for (ArgWhere arg: args) {
            tree.insert(arg);
        }
        result = tree.calculator();
    }

    public ArrayList getResult() {
        return result;
    }
}
