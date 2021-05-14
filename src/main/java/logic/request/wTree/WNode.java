package logic.request.wTree;

import javax.sound.midi.Soundbank;

public class WNode {
    private WNode left;
    private WNode right;
    private Symbol  symbol;
    private Boolean isLeaf;

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

    public String toString(){
        return symbol.toString();
    }

    public WNode insert(WNode node) {
        WNode current = this;
        if(symbol.type == Symbol.OPERATOR){
            if(node.symbol.type == Symbol.CONDITION){
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

    public void prefix(WNode n) {
        System.out.println(this.toString());
        if (n.left != null) prefix(n.left);
        if (n.right != null) prefix(n.right);
    }
    public void infix(WNode n) {
        if (n.left != null) infix(n.left);
        System.out.println(this.toString());
        if (n.right != null) infix(n.right);
    }
    public void suffix(WNode n) {
        if (n.left != null) suffix(n.left);
        if (n.right != null) suffix(n.right);
        System.out.println(this.toString());
    }
}
