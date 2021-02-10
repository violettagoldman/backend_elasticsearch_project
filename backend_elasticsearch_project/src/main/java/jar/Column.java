package jar;

import java.util.HashMap;

public class Column
{
    private String nom;
    private String type;
    private HashMap<Integer, String> data;

    public Column(String nom, String type) {
        this.nom = nom;
        this.type = type;
        this.data = new HashMap<Integer, String>();
    }

    public void addDataValue(int ids, String value) {
        data.put(ids, value);
    }
}