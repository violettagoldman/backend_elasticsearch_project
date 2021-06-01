package logic.json.jsonGet;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import logic.json.jsonIndex.Column;

public class Request {

    @SerializedName("method")
    private String method;
    @SerializedName("table")
    private String table;
    @SerializedName("columns")
    private List<Column> columns = null;
    @SerializedName("args")
    private List<Arg> args = null;
    @SerializedName("aggregate")
    private Aggregate aggregate;
    @SerializedName("option")
    private Option option;

    public Request() {
    }

    /**
     *
     * @param method method
     * @param table table
     * @param columns columns
     * @param args args
     * @param aggregate aggregate
     * @param option option
     */
    public Request(String method, String table, List<Column> columns, List<Arg> args, Aggregate aggregate, Option option) {
        super();
        this.method = method;
        this.table = table;
        this.columns = columns;
        this.args = args;
        this.aggregate = aggregate;
        this.option = option;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public List<Arg> getArgs() {
        return args;
    }

    public void setArgs(List<Arg> args) {
        this.args = args;
    }

    public Aggregate getAggregate() {
        return aggregate;
    }

    public void setAggregate(Aggregate aggregate) {
        this.aggregate = aggregate;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

}
