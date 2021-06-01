package logic.IndexBTree;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * class that implements the index entries
 */
public class Entry {
    private int key;
    private final String data;
    private final Map<Integer, Entry> afters;

    /**
     * constructor that transforms the data into int to create the key
     * @param data the data
     * @param id the id of the data
     */
    public Entry(String data, int id) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(data.getBytes());
        byte byteData[] = md.digest();
        key = ByteBuffer.wrap(byteData).getInt();
        key = key < 0 ? key*-1 : key;
        this.data = data;
        afters = new HashMap<Integer , Entry>();
    }

    /**
     * return the map of after for concatenated indexes
     * @return the data in the column after
     */
    public Map<Integer, Entry> getAfters() {
        return afters;
    }

    /**
     * return the key
     * @return the key
     */
    public int getKey() { return key; }

    /**
     * return the data
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * returns the list of occurrences
     * @return the list of occurrences
     */
    public ArrayList<Integer> getOccurrencesList(){
        ArrayList<Integer> result = new ArrayList<>();
        afters.forEach((key1, value) -> result.add(key1));
        return result;
    }

    /**
     * returns a string that describes the entry
     * @return the string that describes the entry
     */
    public String toString(){
        String str = "key : "+key+" | data : "+data+" | Occurrence [ ";
        for (Map.Entry after: afters.entrySet()){
            str = str + after.getKey() +" : ";
            Entry current = (Entry) after.getValue();
            while(current != null){
                str = str + current.data +" ; ";
                current = current.afters.get(after.getKey());
            }
            str = str.substring(0, str.length()-2) + " | ";
        }
        str = str.substring(0, str.length()-2);
        str = str + "]\n";
        return str;
    }
}
