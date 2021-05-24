package logic.request.wTree;

import logic.IndexBTree.BTree;

import java.security.NoSuchAlgorithmException;

/**
 * class that implements the tree symbols
 */
public class Symbol {
    Condition condition;
    public final Operator operator;
    public enum Type {
        CONDITION,
        OPERATOR
    }
    public final Symbol.Type type;

    /**
     * constructor
     * @param type
     * @param condition
     * @param operator
     */
    private Symbol(Symbol.Type type, Condition condition, Operator operator) {
        this.type = type ;
        this.condition = condition ;
        this.operator = operator ;
    }

    /**
     * return a new condition
     * @param data
     * @param column
     * @return
     * @throws NoSuchAlgorithmException
     */
    static Symbol newCondition (String data, BTree column) throws NoSuchAlgorithmException {
        return new Symbol (Type.CONDITION, new Condition(data, column), null) ;
    }

    /**
     * return a new Operator
     * @param operator
     * @return
     */
    static Symbol newOperator (Operator.Type operator) {
        return new Symbol (Type.OPERATOR, null, new Operator(operator) );
    }

    /**
     * return the Condition
     * @return
     */
    public Condition getCondition() {
        return condition;
    }

    /**
     * returns a string that describes the symbol
     * @return
     */
    public String toString () {
        if (type == Type.CONDITION) return condition == null ? " null " : " " + condition.getColumn() +" = "+condition.value ;
        else return " " + operator.type + " ";
    }

}
