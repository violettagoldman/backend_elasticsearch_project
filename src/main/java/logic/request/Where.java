package logic.request;

import logic.json.Arg;

import java.util.List;

public class Where {
    private int [] result;
    private List<ArgWhere> args;

    public Where(List<ArgWhere> args){

        this.args = args;
        if(args.size() == 0)
        result = new int[0];
        else{
            for (ArgWhere arg: args) {

            }
        }
    }

    public int[] getResult() {
        return result;
    }
}
