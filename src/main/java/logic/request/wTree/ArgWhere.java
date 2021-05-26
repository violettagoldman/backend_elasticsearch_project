package logic.request.wTree;

/**
 * class that implements the arguments that can be given to Where
 */
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

    /**
     * constructor
     * @param type
     * @param column
     * @param value
     * @param operator
     */
    private ArgWhere(Type type, String column, String value, Operator.Type operator) {
        this.type = type;
        this.column = column;
        this.value = value;
        this.operator = operator;
    }

    /**
     * return a new Condition (column = value)
     * @param column
     * @param value
     * @return
     */
    public static ArgWhere newCondition(String column, String value){
        return new ArgWhere(Type.CONDITION, column, value, null);
    }

    /**
     * return a new operator (OR ; AND)
     * @param operator
     * @return
     */
    public static ArgWhere newOperator(Operator.Type operator){
        return new ArgWhere(Type.OPERATOR, null, null, operator);
    }

    /**
     * return a new Start '('
     * @return
     */
    public static ArgWhere newStart(){
        return new ArgWhere(Type.START, null, null, null);
    }

    /**
     * return a new Start ')'
     * @return
     */
    public static ArgWhere newEnd(){
        return new ArgWhere(Type.END, null, null, null);
    }

    /**
     * return the type
     * @return
     */
    public Type getType() { return type; }

    /**
     * return the operator
     * @return
     */
    public Operator.Type getOperator() { return operator; }

    /**
     * return the column of the condition
     * @return
     */
    public String getColumn() {
        return column;
    }

    /**
     * set the column
     * @param column
     */
    public void setColumn(String column) {
        this.column = column;
    }

    /**
     * return the value of the Condition
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * set the value of the Condition
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

}

