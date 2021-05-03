package logic.json;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("request")
    private Method request;
    private List<Arg> args = null;

    public Request(Method request, List<Arg> args) {
        super();
        this.request = request;
        this.args = args;
    }

    public Method getRequest() {
        return request;
    }

    public void setRequest(Method request) {
        this.request = request;
    }

    public List<Arg> getArgs() {
        return args;
    }

    public void setArgs(List<Arg> args) {
        this.args = args;
    }

}

