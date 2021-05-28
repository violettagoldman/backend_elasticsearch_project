package logic.IndexBTree;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * class that implements a btree to serve as an index
 */
public class BTree{
    public final String name;
    private BTreeNode root;
    private int MinDeg;
    private Map<Integer, Entry> list;


    /**
     * Constructor
     * @param deg
     * @param name
     */
    public BTree(int deg, String name){
        this.name = name;
        this.root = null;
        this.MinDeg = deg;
        list = new HashMap<>();
    }

    /**
     * displays the index
     */
    public void traverse(){
        System.out.println("____Index "+name+"____\n");
        if (root != null){
            root.traverse();
        }
    }

    /**
     * returns the node containing the data
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public BTreeNode searchNode(String data) throws NoSuchAlgorithmException{
        return root == null ? null : root.searchNode(data);
    }

    /**
     * return the data by the id
     * @param id
     * @return
     */
    public String getData(int id){
        return list.get(id).getData();
    }

    /**
     * returns the entry containing the data
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public Entry search(String data) throws NoSuchAlgorithmException{
        return root == null ? null : root.search(data);
    }

    /**
     * returns the list of occurrences
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public ArrayList occurrences(String data) throws NoSuchAlgorithmException {
        return search(data) != null ? search(data).getOccurrencesList() : new ArrayList<>();
    }

    /**
     * inserts a new data or adds the new occurrence to the existing entry
     * @param data
     * @param id
     * @throws NoSuchAlgorithmException
     */
    public void insert(String data, int id) throws NoSuchAlgorithmException {
        Entry entry = new Entry(data,id);
        entry.getAfters().put(id, null);
        int key = entry.getKey();
        if (root == null){

            root = new BTreeNode(MinDeg,true);
            root.keys[0] = entry;
            root.num = 1;
            list.put(id, entry);
        } else if(this.search(data)!=null){
            this.search(data).getAfters().put(id, null);
            list.put(id, this.search(data));
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
                list.put(id, entry);
            } else {
                root.insertNotFull(entry);
                list.put(id, entry);
            }

        }
    }

    /**
     * removes an occurrence from a data
     * @param key
     */
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
