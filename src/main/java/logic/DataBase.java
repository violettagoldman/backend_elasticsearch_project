package logic;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Class that represents the database.
 * A database has a name, a map of tables referenced by name.
 * The class implements a singleton.
 */
public class DataBase {
    private String name;
    private Map<String, Table> tables;
    private static final DataBase instance = new DataBase();

    /**
     * Initialise a new database
     */
    private DataBase(){
        this.name = null;
        tables = new HashMap<>();
    }

    /**
     * Changes the name of the database and returns the database
     * @param name the name
     * @return the database
     */
    public static DataBase setName(String name){
        instance.name = name;
        return instance;
    }

    /**
     * Initialise the database tables
     * @param tables new table
     */
    public void setTables(Map<String, Table> tables) {
        this.tables = tables;
    }

    /**
     * Returns the instance of the database
     * @return the instance of database
     */
    public static DataBase getInstance(){
        return instance;
    }

    /**
     * Returns the map of the tables
     * @return the map of tables
     */
    public Map<String, Table> getTables() {
        return tables;
    }

    /**
     * adds a table to the database
     * @param name name of the table
     * @param columnsNames list of names of columns
     * @param columnsType list of types of columns
     */
    public void newTable(String name, ArrayList<String> columnsNames, ArrayList<String> columnsType){
        tables.put(name, new Table(name, columnsNames, columnsType));
    }


    /**
     * create an index in the requested table for the desired columns
     * @param table the table of request
     * @param columns the columns to index
     * @throws NoSuchAlgorithmException
     */
    public void createIndex(String table, String [] columns) throws NoSuchAlgorithmException {
        tables.get(table).createIndex(columns);
    }

    /**
     * returns a string describing the database
     * @return a string
     */
    public String toString(){
        String str = "DataBase :"+ name +"\n";
        for (Entry entry:
             tables.entrySet()) {
            str = entry.toString();
        }
        return str;
    }
}
