package logic.request.wTree;

import java.io.Serializable;

public class Operator {
    public enum Type {
        AND,
        OR
    }
    private Type type;

    public Operator(Type type){
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
