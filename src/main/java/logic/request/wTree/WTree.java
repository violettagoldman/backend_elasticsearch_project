package logic.request.wTree;

import logic.Column;
import logic.Table;
import logic.bTree.BTree;
import logic.request.ArgWhere;

import java.security.NoSuchAlgorithmException;

public class WTree {
    private WNode root;
    private Table table;

    public WTree(){
        root = new Operator(Operator.Type.AND);
    }

    public void insert(ArgWhere arg) throws NoSuchAlgorithmException {
        WNode node;
        if(arg.getType() == "OPERATOR"){
            node = new Operator(arg.getOperator());
        } else {
            Object column = table.getIndexOrColumn(arg.getColumn());
            if(column instanceof Column)
                node = new Condition(arg.getValue(), (Column) column);
            else
                node = new Condition(arg.getValue(), (BTree) column);
        }

    }



}
