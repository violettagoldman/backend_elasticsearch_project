package logic.IndexBTree;

/**
 * class that implements the btree nodes
 */
class BTreeNode{
    Entry[] keys; // entry contained in the node
    int MinDeg; // Minimum degree of B-tree node
    public final BTreeNode[] children; // Child node
    int num; // Number of keys of node
    boolean isLeaf; // True when leaf node

    /**
     * constructor
     * @param deg the degrees of the node
     * @param isLeaf the node is a leaf
     */
    public BTreeNode(int deg,boolean isLeaf){
        this.MinDeg = deg;
        this.isLeaf = isLeaf;
        this.keys = new Entry[2*this.MinDeg-1]; // Node has 2*MinDeg-1 keys at most
        this.children = new BTreeNode[2*this.MinDeg];
        this.num = 0;
    }

    /**
     * insert the new Entry
     * @param entry the entry to insert
     */
    public void insertNotFull(Entry entry){
        int key = entry.getKey();
        int i = num -1; // Initialize i as the rightmost index

        if (isLeaf){ // When it is a leaf node
            // Find the location request the new key should be inserted
            while (i >= 0 && keys[i].getKey() > key){
                keys[i+1] = keys[i]; // keys backward shift
                i--;
            }
            keys[i+1]=entry;
            num = num +1;
        }
        else{
            // Find the child node location that should be inserted
            while (i >= 0 && keys[i].getKey() > key)
                i--;
            if (children[i+1].num == 2*MinDeg - 1){ // When the child node is full
                splitChild(i+1,children[i+1]);
                // After splitting, the key in the middle of the child node moves up, and the child node splits into two
                if (keys[i+1].getKey() < key)
                    i++;
            }
            children[i+1].insertNotFull(entry);
        }
    }

    /**
     *  When the child node is full, split the child
     * @param i the key
     * @param y the node
     */
    public void splitChild(int i ,BTreeNode y){

        // First, create a node to hold the keys of MinDeg-1 of y
        BTreeNode z = new BTreeNode(y.MinDeg,y.isLeaf);
        z.num = MinDeg - 1;

        // Pass the properties of y to z
        for (int j = 0; j < MinDeg-1; j++)
            z.keys[j] = y.keys[j+MinDeg];
        if (!y.isLeaf){
            for (int j = 0; j < MinDeg; j++)
                z.children[j] = y.children[j+MinDeg];
        }
        y.num = MinDeg-1;

        // Insert a new child into the child
        for (int j = num; j >= i+1; j--) children[j+1] = children[j];
        children[i+1] = z;

        // Move a key in y to this node
        for (int j = num-1;j >= i;j--)
            keys[j+1] = keys[j];
        keys[i] = y.keys[MinDeg-1];

        num = num + 1;
    }

    /**
     * used by the btree to display the index
     */
    public void traverse(){
        int i;
        for (i = 0; i< num; i++){
            if (!isLeaf)
                children[i].traverse();
            System.out.print(keys[i].toString());
        }

        if (!isLeaf){
            children[i].traverse();
        }
    }

    /**
     * returns the node containing the data
     * @param data the data to search
     * @return the node contains the data
     */
    public BTreeNode searchNode(String data) {
        int key =  new Entry(data, 0).getKey();
        int i = 0;
        while (i < num && key > keys[i].getKey())
            i++;
        if (i<2*MinDeg-1 && keys[i]!=null && keys[i].getKey() == key)
            return this;
        if (isLeaf)
            return null;
        return children[i].searchNode(data);
    }

    /**
     * returns the entry containing the data
     * @param data the data search
     * @return the entry contains the data
     */
    public Entry search(String data){
        int key =  new Entry(data, 0).getKey();
        int i = 0;
        while (i < num &&  key > keys[i].getKey() )
            i++;
        if (i<2*MinDeg-1 && keys[i]!=null && keys[i].getKey() == key) {
            return keys[i];
        }
        if (isLeaf)
            return null;
        return children[i].search(data);
    }

}
