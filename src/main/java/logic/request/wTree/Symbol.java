package logic.request.wTree;

import logic.Column;
import logic.bTree.BTree;

import java.security.NoSuchAlgorithmException;

public class Symbol {
    public final int type;
    Condition condition;
    Operator operator;
    final static int CONDITION = 1 ;  // nature d'un nombre
    final static int OPERATOR = 2;

    private Symbol (int type, Condition condition, Operator operator) {
        this.type = type ;
        this.condition = condition ;
        this.operator = operator ;
    }

    static Symbol newCondition (String data, BTree column) throws NoSuchAlgorithmException {
        return new Symbol (Symbol.CONDITION, new Condition(data, column), null) ;
    }

    static Symbol newOperator (Operator.Type operator) {
        return new Symbol (Symbol.OPERATOR, null, new Operator(operator) );
    }

    public String toString ()
    {
        if (type == Symbol.CONDITION) return condition.getColumn() +" = "+condition.value ;
        else return "" + operator.getType() ;
    }

}
