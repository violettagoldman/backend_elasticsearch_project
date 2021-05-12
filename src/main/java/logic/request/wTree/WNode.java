package logic.request.wTree;

public class WNode {
    private WNode left;
    private WNode right;
    private Boolean isLeaf;

    public WNode(Boolean isLeaf){
        left = null;
        right = null;
        this.isLeaf = isLeaf;
    }

    public WNode(WNode left, WNode right){
        this.right = right;
        this.left = left;
    }
}
