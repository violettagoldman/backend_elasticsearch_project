package logic.request;

import com.google.gson.annotations.SerializedName;
import logic.request.wTree.Operator;

public class ArgWhere {

    @SerializedName("type")
    private String type;
    @SerializedName("operator")
    private Operator.Type operator;
    @SerializedName("column")
    private String column;
    @SerializedName("value")
    private String value;



    public ArgWhere(Operator.Type operator, String column, String value) {
        super();
        this.column = column;
        this.value = value;
        this.operator = operator;
        type = operator == null ? "CONDITION" : "OPERATOR";
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public Operator.Type getOperator() { return operator; }

    public void setOperator(Operator.Type operator) { this.operator = operator; }

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

