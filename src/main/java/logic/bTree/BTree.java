package logic.bTree;

import java.security.NoSuchAlgorithmException;

public class BTree{
    BTreeNode root;
    int MinDeg;

    // Constructor
    public BTree(int deg){
        this.root = null;
        this.MinDeg = deg;
    }

    public void traverse(){
        if (root != null){
            root.traverse();
        }
    }

    // Function to find key
    public BTreeNode searchNode(String data) throws NoSuchAlgorithmException{
        return root == null ? null : root.searchNode(data);
    }

    //fonction to find a entry
    public Entry search(String data) throws NoSuchAlgorithmException{
        return root == null ? null : root.search(data);
    }

    public void insert(String data, int id) throws NoSuchAlgorithmException {
        Entry entry = new Entry(data);
        entry.getOccurrences().put(id, new Occurence(id));
        int key = entry.getKey();
        if (root == null){

            root = new BTreeNode(MinDeg,true);
            root.keys[0] = entry;
            root.num = 1;
        } else if(this.search(data)!=null){
            this.search(data).getOccurrences().put(id, new Occurence(id));
        } else {
            // When the root node is full, the tree will grow high
            if (root.num == 2*MinDeg-1){
                BTreeNode s = new BTreeNode(MinDeg,false);
                // The old root node becomes a child of the new root node
                s.children[0] = root;
                // Separate the old root node and give a key to the new node
                s.splitChild(0,root);
                // The new root node has 2 child nodes. Move the old one over there
                int i = 0;
                if (s.keys[0].getKey()< key)
                    i++;
                s.children[i].insertNotFull(entry);

                root = s;
            } else
                root.insertNotFull(entry);
        }
    }

    public void remove(int key){
        if (root == null){
            System.out.println("The tree is empty");
            return;
        }

        root.remove(key);

        if (root.num == 0){ // If the root node has 0 keys
            // If it has a child, its first child is taken as the new root,
            // Otherwise, set the root node to null
            if (root.isLeaf)
                root = null;
            else
                root = root.children[0];
        }
    }
}
