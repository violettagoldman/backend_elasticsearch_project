package logic.json;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("method")
    private Method method;
    @SerializedName("table")
    private Table table;
    @SerializedName("args")
    private List<Arg> args = null;

    public Request() {
    }

    public Request(Method method, Table table, List<Arg> args) {
        super();
        this.method = method;
        this.table = table;
        this.args = args;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public List<Arg> getArgs() {
        return args;
    }

    public void setArgs(List<Arg> args) {
        this.args = args;
    }

}
