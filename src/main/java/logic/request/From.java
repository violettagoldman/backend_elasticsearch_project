package logic.request;

import logic.DataBase;
import logic.Table;

/**
 * class that implements the from of the request
 */
public class From {
    public final String tableName;
    public final Table table;
    public final DataBase db = DataBase.getInstance();

    /**
     * constructeur
     * @param tableName
     */
    public From(String tableName) {
        this.tableName = tableName;
        table = db.getTables().get(tableName);
    }
}
