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
        this.data = new HashMap<>();;
    }
}