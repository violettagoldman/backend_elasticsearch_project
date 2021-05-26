package logic.json;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("method")
    private String method;
    @SerializedName("table")
    private String table;
    @SerializedName("args")
    private List<Arg> args = null;

    public Request(String voyage, String[] strings, List args) {
    }

    public Request(String method, String table, List<Arg> args) {
        super();
        this.method = method;
        this.table = table;
        this.args = args;
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

    public List<Arg> getArgs() {
        return args;
    }

    public void setArgs(List<Arg> args) {
        this.args = args;
    }

}
