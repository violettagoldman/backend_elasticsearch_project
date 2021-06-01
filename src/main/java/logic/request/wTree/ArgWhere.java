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
    private final ArgWhere.Type type;
    private final Operator.Type operator;
    private String column;
    private final String value;

    /**
     * constructor
     * @param type the type
     * @param column the column if condition
     * @param value the value if conditin
     * @param operator the opreator OR or AND
     */
    private ArgWhere(Type type, String column, String value, Operator.Type operator) {
        this.type = type;
        this.column = column;
        this.value = value;
        this.operator = operator;
    }

    /**
     * return a new Condition (column = value)
     * @param column column for the condition
     * @param value value of the data for the condition
     * @return argument for the tree
     */
    public static ArgWhere newCondition(String column, String value){
        return new ArgWhere(Type.CONDITION, column, value, null);
    }

    /**
     * return a new operator (OR ; AND)
     * @param operator OR or AND
     * @return the operator for the tree
     */
    public static ArgWhere newOperator(Operator.Type operator){
        return new ArgWhere(Type.OPERATOR, null, null, operator);
    }

    /**
     * return a new Start '('
     * @return a start parenthesis
     */
    public static ArgWhere newStart(){
        return new ArgWhere(Type.START, null, null, null);
    }

    /**
     * return a new End ')'
     * @return  a end parenthesis
     */
    public static ArgWhere newEnd(){
        return new ArgWhere(Type.END, null, null, null);
    }

    /**
     * return the type
     * @return the type
     */
    public Type getType() { return type; }

    /**
     * return the operator
     * @return return the type of operator
     */
    public Operator.Type getOperator() { return operator; }

    /**
     * return the column of the condition
     * @return return the column
     */
    public String getColumn() {
        return column;
    }

    /**
     * set the column
     * @param column the column to change
     */
    public void setColumn(String column) {
        this.column = column;
    }

    /**
     * return the value of the Condition
     * @return return the value of the data for the condition
     */
    public String getValue() {
        return value;
    }


}

