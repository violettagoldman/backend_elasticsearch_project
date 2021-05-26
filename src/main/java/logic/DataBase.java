package logic;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

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
     * @param name
     * @return
     */
    public static DataBase setName(String name){
        instance.name = name;
        return instance;
    }

    /**
     * Initialise the database tables
     * @param tables
     */
    public void setTables(Map<String, Table> tables) {
        this.tables = tables;
    }

    /**
     * Returns the instance of the database
     * @return
     */
    public static DataBase getInstance(){
        return instance;
    }

    /**
     * Returns the map of the tables
     * @return
     */
    public Map<String, Table> getTables() {
        return tables;
    }

    /**
     * adds a table to the database
     * @param name
     * @param columnsMap
     */
    public void newTable(String name, Map<String, String> columnsMap){
        tables.put(name, new Table(name, columnsMap));
    }

    /**
     * adds a row to the database
     * @param name
     * @param columnsMap
     */
    public void newLine(String name, Map<String, String> columnsMap){
        tables.get(name).addLine(columnsMap);
    }

    /**
     * create an index in the requested table for the desired columns
     * @param table
     * @param columns
     * @throws NoSuchAlgorithmException
     */
    public void createIndex(String table, String [] columns) throws NoSuchAlgorithmException {
        tables.get(table).createIndex(columns);
    }

    /**
     * returns a string describing the database
     * @return
     */
    public String toString(){
        String str = "DataBase :"+ name +"\n";
        for (Map.Entry entry:
             tables.entrySet()) {
            str = entry.toString();
        }
        return str;
    }
}
