package logic.json.jsonIndex;

import com.google.gson.annotations.SerializedName;

public class Column {

    @SerializedName("column")
    private String column;

    public Column() {
    }

    /**
     *
     * @param column column
     */
    public Column(String column) {
        super();
        this.column = column;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

}
