package logic.request.wTree;

import logic.Column;
import logic.bTree.BTree;

import java.security.NoSuchAlgorithmException;

public class Symbol {
    Condition condition;
    public final Operator operator;
    public enum Type {
        CONDITION,
        OPERATOR
    }
    public final Symbol.Type type;
  //  final static int PARENTHESIS = parenthesis

    public Symbol(Symbol.Type type, Condition condition, Operator operator) {
        this.type = type ;
        this.condition = condition ;
        this.operator = operator ;
    }

    public Condition getCondition() {
        return condition;
    }

    static Symbol newCondition (String data, BTree column) throws NoSuchAlgorithmException {
        return new Symbol (Type.CONDITION, new Condition(data, column), null) ;
    }

    static Symbol newOperator (Operator.Type operator) {
        return new Symbol (Type.OPERATOR, null, new Operator(operator) );
    }

    public String toString ()
    {
        if (type == Type.CONDITION) return condition == null ? " null " : " " + condition.getColumn() +" = "+condition.value ;
        else return " " + operator.getType() + " ";
    }

}
