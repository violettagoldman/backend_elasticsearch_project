package logic.json.jsonGet;

import com.google.gson.annotations.SerializedName;

public class Aggregate {

    @SerializedName("aggregateName")
    private String aggregateName;
    @SerializedName("column")
    private String column;

    public Aggregate() {
    }

    /**
     *
     * @param aggregateName aggregateName
     * @param column column
     */
    public Aggregate(String aggregateName, String column) {
        super();
        this.aggregateName = aggregateName;
        this.column = column;
    }

    public String getAggregateName() {
        return aggregateName;
    }

    public void setAggregateName(String aggregateName) {
        this.aggregateName = aggregateName;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

}
