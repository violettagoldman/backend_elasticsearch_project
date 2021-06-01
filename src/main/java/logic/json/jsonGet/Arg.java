package logic.json.jsonGet;

import com.google.gson.annotations.SerializedName;

public class Arg {

    @SerializedName("column")
    private String column;
    @SerializedName("value")
    private String value;
    @SerializedName("operator")
    private String operator;

    public Arg() {
    }

    /**
     *
     * @param column column
     * @param value value
     * @param operator operator
     */
    public Arg(String column, String value, String operator) {
        super();
        this.column = column;
        this.value = value;
        this.operator = operator;
    }

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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
