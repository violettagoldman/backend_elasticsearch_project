package logic.request.wTree;

import logic.Table;
import logic.IndexBTree.BTree;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * class that implements the calculation tree
 */
public class WTree {
    private WNode root = null;
    private Table table;

    /**
     * constructor
     * @param table
     */
    public WTree(Table table){
        this.table = table;
    }

    /**
     * recursive insertion of arguments to build the calculation tree
     * @param args list of arguments in the where request
     * @throws NoSuchAlgorithmException
     */
    public void insert(List<ArgWhere> args) throws NoSuchAlgorithmException {
        if(args.isEmpty())return;
        ArgWhere arg = args.remove(0);
        WNode node;
        int start = 0;
        switch (arg.getType()) {
            case OPERATOR:
                node = new WNode(Symbol.newOperator(arg.getOperator()));
                root = root == null ? node : root.insert(node);
                break;
            case CONDITION:
                Object column = table.getIndexOrColumn(arg.getColumn());
                node = new WNode(Symbol.newCondition(arg.getValue(), (BTree) column));
                root = root == null ? node : root.insert(node);
                break;
            case START:
                start++;
                List<ArgWhere> args2 = new ArrayList<>();
                while(start>0){
                    ArgWhere arg2 = args.remove(0);
                    if(arg2.getType()== ArgWhere.Type.START)start++;
                    else if(arg2.getType()== ArgWhere.Type.END)start--;
                    if(start > 0) args2.add(arg2);
                }
                WTree tree = new WTree(table);
                tree.insert(args2);
                root = root == null ? tree.root : root.insert(tree.root);;
                break;
            default:
        }
        if(args.size()<1)return;
        this.insert(args);
    }

    /**
     * draws the calculation tree
     */
    public void draw(){
        Visitor visitor = new Visitor() {
            public void visit(Stack path) {
                int n = path.size();
                while (n-- > 0)
                    System.out.print("   ");
                System.out.println((path.peek()).toString());
            }
        };
        root.inOrder(visitor, new Stack());
    }

    /**
     * calculates the result of the calculation tree
     * @return the rows
     */
    public ArrayList<Integer> calculator() {
        while(root.getSymbol().type != Symbol.Type.CONDITION){
            root.calculatorCondition();
        }
        return root.getSymbol().getCondition().getResult();
    }
}
