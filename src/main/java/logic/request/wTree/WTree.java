package logic.request.wTree;

import logic.Column;
import logic.Table;
import logic.bTree.BTree;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class WTree {
    private WNode root = null;
    private Table table;

    public WTree(Table table){
        this.table = table;
    }

    public void insert(ArgWhere arg) throws NoSuchAlgorithmException {
        WNode node;
        if(arg.getType() == "OPERATOR"){
            node = new WNode(Symbol.newOperator(arg.getOperator()), false);
        } else {
            Object column = table.getIndexOrColumn(arg.getColumn());
            node = new WNode(Symbol.newCondition(arg.getValue(), (BTree) column), true);
        }
        if(root == null)root = node;
        else root = root.insert(node);

    }

    public WNode getRoot(){
        return root;
    }


    public ArrayList calculator() {
        ArrayList result = new ArrayList();
        return result;
    }
}
