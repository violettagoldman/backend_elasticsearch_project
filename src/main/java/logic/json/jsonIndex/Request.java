package logic.json.jsonIndex;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Request {

    @SerializedName("table")
    private String table;
    @SerializedName("columns")
    private List<Column> columns = null;

    public Request() {
    }

    public Request(String table, List<Column> columns) {
        super();
        this.table = table;
        this.columns = columns;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

}
