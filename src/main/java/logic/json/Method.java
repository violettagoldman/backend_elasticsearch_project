package logic.json;

import com.google.gson.annotations.SerializedName;

public class Method {

    @SerializedName("method_name")
    private String methodName;

    public Method() {
    }

    public Method(String methodName) {
        super();
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

}
