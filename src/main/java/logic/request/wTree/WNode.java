package logic.request.wTree;

import java.util.Stack;

import static logic.request.wTree.Operator.Type.OR;
import static logic.request.wTree.Symbol.Type.CONDITION;
import static logic.request.wTree.Symbol.Type.OPERATOR;

/**
 * class that implements the nodes of the calculation tree
 */
public class WNode {
    private WNode left;
    private WNode right;
    private Symbol  symbol;

    /**
     * construtor
     * @param symbol symbol contains in the node
     */
    public WNode(Symbol symbol){
        left = null;
        right = null;
        this.symbol = symbol;
    }

    /**
     * return the symbol
     * @return the symbol of the node
     */
    public Symbol getSymbol() {
        return symbol;
    }

    /**
     * inserts the new node and returns the root of the tree
     * @param node the node to insert
     * @return the old or the new root
     */
    public WNode insert(WNode node) {
        WNode current = this;
        if (symbol.type == OPERATOR) {
            if (node.symbol.type == OPERATOR) {
                if (node.left == null) {
                    node.left = current;
                    return node;
                } else this.right = node;
                return this;
            } else if (node.symbol.type == CONDITION) {
                if (this.left == null) this.left = node;
                else this.right = node;
                return this;
            }
        }
        node.left = current;
        return node;
    }


    /**
     * calculates the result of a node
     */
    public void calculatorCondition() {
        if(this.left.getSymbol().type == CONDITION && this.right.getSymbol().type == CONDITION) {
            if(this.symbol.operator.type == OR) left.symbol.getCondition().or(right.symbol.getCondition());
            else left.symbol.getCondition().and(right.symbol.getCondition());
            this.symbol = left.symbol;
        } else {
            if (this.left.getSymbol().type != CONDITION) this.left.calculatorCondition();
            if (this.right.getSymbol().type != CONDITION) this.right.calculatorCondition();
        }
    }

    /**
     * checks that a left node exists
     * @return boolean true if the node have a son left
     */
    public boolean existLeft() {
        return left != null;
    }

    /**
     * checks that a right node exists
     * @return boolean true if the node have a son right
     */
    public boolean existRight() {
        return right != null;
    }

    /**
     * used to draw the tree
     * @param treatment treatment
     * @param path path
     */
    public void inOrder(Visitor treatment, Stack path) {
        path.push(this);
        if (existLeft())
            left.inOrder(treatment, path);
        treatment.visit(path);
        if (existRight())
            right.inOrder(treatment, path);
        path.pop();
    }

    /**
     * return a string describing the node
     * @return a string that describes the symbol
     */
    public String toString(){
        return symbol.toString();
    }
}
