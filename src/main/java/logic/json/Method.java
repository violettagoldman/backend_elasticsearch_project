package logic.json;

import com.google.gson.annotations.SerializedName;

public class Method {

    @SerializedName("method")
    private String method;

    public Method() {
    }

    public Method(String method) {
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


