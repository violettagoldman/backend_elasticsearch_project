package logic.json.jsonGet;

import com.google.gson.annotations.SerializedName;

public class Option {

    @SerializedName("optionName")
    private String optionName;
    @SerializedName("arg")
    private String arg;

    public Option() {
    }

    /**
     *
     * @param optionName optionName
     * @param arg arg
     */
    public Option(String optionName, String arg) {
        super();
        this.optionName = optionName;
        this.arg = arg;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

}
