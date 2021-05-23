package logic.request;

import logic.DataBase;
import logic.Table;
import logic.request.wTree.ArgWhere;
import logic.request.wTree.Visiteur;
import logic.request.wTree.WTree;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Where {
    private ArrayList result;
    private List<ArgWhere> args;
    private Table table;
    private WTree tree;

    public Where(List<ArgWhere> args, String tableName) throws NoSuchAlgorithmException {
        this.args = args;
        result = new ArrayList();
        tree = new WTree(DataBase.getInstance().getTables().get(tableName));
        tree.insert(args);

//        System.out.println("parcours infixe");
//        tree.getRoot().infix();
//        System.out.println("");
//        System.out.println("parcours sufix");
//        tree.getRoot().suffix();
//        System.out.println("");
//        System.out.println("parcours prefix");
//        tree.getRoot().prefix();
//        System.out.println("");

        tree.drawConsole();

        result = tree.calculator();
    }

    public ArrayList getResult() {
        return result;
    }
}
