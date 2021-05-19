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
        while(root.getSymbol().type != Symbol.CONDITION){
            root.calculatorCondition();
        }
        return root.getSymbol().getCondition().getResult();
    }
}
