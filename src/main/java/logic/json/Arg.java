package logic.json;

import com.google.gson.annotations.SerializedName;

public class Arg {

    @SerializedName("column")
    private String column;
    @SerializedName("value")
    private String value;

    public Arg() {
    }

    public Arg(String column, String value) {
        super();
        this.column = column;
        this.value = value;
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

}
