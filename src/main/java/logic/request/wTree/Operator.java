package logic.request.wTree;

/**
 * class that implements and and or
 */
public class Operator {
    public enum Type {
        AND,
        OR
    }
    public final Type type;

    /**
     * constructor
     * @param type
     */
    public Operator(Type type){ this.type = type; }
}
