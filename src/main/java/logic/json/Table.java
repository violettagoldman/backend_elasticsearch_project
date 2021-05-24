package logic.json;

import com.google.gson.annotations.SerializedName;

public class Table {

    @SerializedName("table_name")
    private String tableName;

    public Table() {
    }

    public Table(String tableName) {
        super();
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}
