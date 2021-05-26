package logic.json.jsonTable;

import com.google.gson.annotations.SerializedName;

public class Column {

    @SerializedName("column")
    private String column;
    @SerializedName("type")
    private String type;

    public Column() {
    }

    public Column(String column, String type) {
        super();
        this.column = column;
        this.type = type;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

