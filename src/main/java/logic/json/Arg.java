package logic.json;

import com.google.gson.annotations.SerializedName;

public class Arg {

    @SerializedName("table_name")
    private String tableName;
    @SerializedName("column")
    private String column;
    @SerializedName("value")
    private String value;


    public Arg(String tableName, String column, String value) {
        super();
        this.tableName = tableName;
        this.column = column;
        this.value = value;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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

