package logic.IndexBTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * class that implements a btree to serve as an index
 */
public class BTree{
    public final String name;
    public final String type;
    private BTreeNode root;
    private final int MinDeg;
    private final Map<Integer, Entry> list;


    /**
     * Constructor
     * @param deg max degrees of tree
     * @param name name of the column
     */
    public BTree(int deg, String name, String type){
        this.type = type;
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
     * @param data the data to search
     * @return the node of the data
     */
    public BTreeNode searchNode(String data){
        return root == null ? null : root.searchNode(data);
    }

    /**
     * return the data by the id
     * @param id the id of the line
     * @return return the data
     */
    public String getData(int id){
        return list.get(id).getData();
    }

    /**
     * returns the entry containing the data
     * @param data the data to search
     * @return the entry contains the data
     */
    public Entry search(String data){
        return root == null ? null : root.search(data);
    }

    /**
     * returns the list of occurrences
     * @param data the data to search
     * @return return the occurrences
     */
    public ArrayList<Integer> occurrences(String data){
        return search(data) != null ? search(data).getOccurrencesList() : new ArrayList<>();
    }

    /**
     * inserts a new data or adds the new occurrence to the existing entry
     * @param data the data to insert
     * @param id the id of the data
     */
    public void insert(String data, int id){
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

}
