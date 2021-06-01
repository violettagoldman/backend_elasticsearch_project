package logic.request.wTree;

import logic.IndexBTree.BTree;
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
     * @param type condition or operator
     * @param condition column = value
     * @param operator OR / AND
     */
    private Symbol(Symbol.Type type, Condition condition, Operator operator) {
        this.type = type ;
        this.condition = condition ;
        this.operator = operator ;
    }

    /**
     * return a new condition
     * @param data the value of the condition
     * @param column the column of the condition
     * @return a new object condition
     */
    static Symbol newCondition (String data, BTree column) {
        return new Symbol (Type.CONDITION, new Condition(data, column), null) ;
    }

    /**
     * return a new Operator
     * @param operator OR / AND
     * @return a new object operator
     */
    static Symbol newOperator (Operator.Type operator) {
        return new Symbol (Type.OPERATOR, null, new Operator(operator) );
    }

    /**
     * return the Condition
     * @return the condition
     */
    public Condition getCondition() {
        return condition;
    }

    /**
     * returns a string that describes the symbol
     * @return a string describe the symbol
     */
    public String toString () {
        if (type == Type.CONDITION) return condition == null ? " null " : " " + condition.getColumn() +" = "+condition.value ;
        else return " " + operator.type + " ";
    }

}
