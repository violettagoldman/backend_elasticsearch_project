package logic.request.wTree;

import com.google.gson.annotations.SerializedName;
import logic.request.wTree.Operator;

public class ArgWhere {

    public enum Type {
        OPERATOR,
        CONDITION,
        START,
        END
    }
    private ArgWhere.Type type;
    private Operator.Type operator;
    private String column;
    private String value;

    public ArgWhere(Type type, String column, String value, Operator.Type operator) {
        this.type = type;
        this.column = column;
        this.value = value;
        this.operator = operator;
    }

    public static ArgWhere newCondition(String column, String value){
        return new ArgWhere(Type.CONDITION, column, value, null);
    }

    public static ArgWhere newOperator(Operator.Type operator){
        return new ArgWhere(Type.OPERATOR, null, null, operator);
    }

    public static ArgWhere newStart(){
        return new ArgWhere(Type.START, null, null, null);
    }

    public static ArgWhere newEnd(){
        return new ArgWhere(Type.END, null, null, null);
    }

    public Type getType() { return type; }

    public Operator.Type getOperator() { return operator; }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

