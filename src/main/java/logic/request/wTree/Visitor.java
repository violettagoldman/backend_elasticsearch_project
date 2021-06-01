package logic.request.wTree;

import java.util.Stack;

/**
 * interface for drawing the decision tree
 */
public interface Visitor {
    void visit(Stack path);

}
