package logic.request.wTree;

import logic.Column;
import logic.Table;
import logic.bTree.BTree;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static logic.request.wTree.Visiteur.Panneau.NOMBRE;

public class WTree {
    private WNode root = null;
    private Table table;

    public WTree(Table table){
        this.table = table;
    }

    public void insert(List<ArgWhere> args) throws NoSuchAlgorithmException {
        ArgWhere arg = args.remove(0);
        WNode node;
        int start = 0;
        switch (arg.getType()) {
            case OPERATOR:
                node = new WNode(Symbol.newOperator(arg.getOperator()), false);
                root = root == null ? node : root.insert(node);
                break;
            case CONDITION:
                Object column = table.getIndexOrColumn(arg.getColumn());
                node = new WNode(Symbol.newCondition(arg.getValue(), (BTree) column), true);
                root = root == null ? node : root.insert(node);
                break;
            case START: start++;
                        List<ArgWhere> args2 = new ArrayList<>();
                        while(start>0){
                            ArgWhere arg2 = args.remove(0);
                            if(arg2.getType()== ArgWhere.Type.START)start++;
                            else if(arg2.getType()== ArgWhere.Type.END)start--;
                            if(start > 0) args2.add(arg2);
                        }
                        WTree tree = new WTree(table);
                        tree.insert(args2);
                        root = root == null ? tree.root : root.insert(tree.root);;
                break;
            default:
        }
        if(args.size()<1)return;
        this.insert(args);
    }

    public WNode getRoot(){
        return root;
    }

    public void draw(){
        Visiteur.Panneau panneau = new Visiteur.Panneau(root);

        JFrame cadre = new JFrame("Arbre binaire aléatoire - n = " + NOMBRE);
        cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cadre.setBounds(100, 100, 600, 400);
        cadre.setContentPane(panneau);
        cadre.setVisible(true);
    }

    public void drawConsole(){
        Visiteur afficheur = new Visiteur() {
            public void visiter(Stack chemin) {
                int n = chemin.size();
                while (n-- > 0)
                    System.out.print("   ");
                System.out.println(((WNode) chemin.peek()).toString());

            }
        };
        root.inOrdre(afficheur, new Stack());
    }

    public ArrayList calculator() {
        while(root.getSymbol().type != Symbol.Type.CONDITION){
            root.calculatorCondition();
        }
        return root.getSymbol().getCondition().getResult();
    }
}
