package logic.json;

import com.google.gson.annotations.SerializedName;

public class Request__1 {

    @SerializedName("method")
    private String method;

    public Request__1() {
    }

    public Request__1(String method) {
        super();
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}