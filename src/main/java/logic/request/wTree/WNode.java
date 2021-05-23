package logic.request.wTree;

import logic.Table;
import logic.bTree.BTree;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Stack;

public class WNode {
    private WNode left;
    private WNode right;
    private Symbol  symbol;
    private Boolean isLeaf;
    int x, y;

    public void setRight(WNode right) {
        this.right = right;
    }

    public WNode(Symbol symbol, Boolean isLeaf){
        left = null;
        right = null;
        this.symbol = symbol;
        this.isLeaf = isLeaf;
    }

    public WNode(Symbol symbol, WNode left, WNode right){
        this.symbol = symbol;
        this.right = right;
        this.left = left;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public String toString(){
        return symbol.toString();
    }

    public WNode insert(WNode node) {
        WNode current = this;
        if(symbol.type == Symbol.Type.OPERATOR){
            if(node.symbol.type == Symbol.Type.OPERATOR){
                if(node.left == null){
                    node.left = current;
                    return node;
                }
                else this.right = node;
                return this;
            }
            else if(node.symbol.type == Symbol.Type.CONDITION){
                if(this.left == null)this.left = node;
                else this.right = node;
                return this;
            }else{
                node.left = current;
                return node;
            }
        }else{
            node.left = current;
            return node;
        }
    }

    public void prefix() {
        System.out.print(this.toString());
        if (this.left != null) this.left.prefix();
        if (this.right != null) this.right.prefix();
    }
    public void infix() {
        if (this.left != null) this.left.infix();
        System.out.print(this.toString());
        if (this.right != null) this.right.infix();
    }
    public void suffix() {
        if (this.left != null) this.left.suffix();
        if (this.right != null) this.right.suffix();
        System.out.print(this.toString());
    }

    public void calculatorCondition() {
        if(this.left.getSymbol().type == Symbol.Type.CONDITION && this.right.getSymbol().type == Symbol.Type.CONDITION) {
            if(this.symbol.operator.getType() == Operator.Type.OR) left.symbol.getCondition().or(right.symbol.getCondition());
            else left.symbol.getCondition().and(right.symbol.getCondition());
            this.symbol = left.symbol;
        }
        if (this.left.getSymbol().type != Symbol.Type.CONDITION ) this.left.calculatorCondition();
        if (this.right.getSymbol().type != Symbol.Type.CONDITION ) this.right.calculatorCondition();
    }


    // Pour afficher

    public int calculerPositions(int xCourant, int yCourant) {
        /* on augmente xCourant afin d'accueillir toute la largeur du fils gauche */
        if (existLeft())
            xCourant = left().calculerPositions(xCourant, yCourant + 1);

        /* la valeur de xCourant est maintenant l'abscisse de notre noeud */
        fixerPosition(xCourant, yCourant);
        xCourant = xCourant + 1;

        /* on deplace xCourant afin d'accueillir toute la largeur du fils droit */
        if (existRight())
            xCourant = right().calculerPositions(xCourant, yCourant + 1);

        /* la valeur de xCourant comprend maintenant la largeur de notre arbre */
        return xCourant;
    }

    public boolean existLeft() {
        return left != null;
    }
    public boolean existRight() {
        return right != null;
    }
    public WNode left() {
        return left;
    }
    public WNode right() {
        return right;
    }

    public void fixerPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int X() {
        return x;
    }
    public int Y() {
        return y;
    }

    public int hauteur() {
        int g = existLeft() ? left.hauteur() : 0;
        int d = existRight() ? right.hauteur() : 0;
        return Math.max(g, d) + 1;
    }

    public boolean externe() {
        return !existLeft() && !existRight();
    }

    public void inOrdre(Visiteur traitement, Stack chemin) {
        chemin.push(this);
        if (existLeft())
            left().inOrdre(traitement, chemin);
        traitement.visiter(chemin);
        if (existRight())
            right().inOrdre(traitement, chemin);
        chemin.pop();
    }
}
