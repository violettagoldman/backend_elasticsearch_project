package logic.request.wTree;

import java.io.Serializable;

public class Operator extends WNode implements  Serializable{
    public enum Type implements Serializable {
        AND,
        OR
    }
    private Type type;

    public Operator(Type type){
        super(false);
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
