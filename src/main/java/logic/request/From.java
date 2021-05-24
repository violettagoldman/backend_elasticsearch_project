package logic.request;

import logic.DataBase;
import logic.Table;

public class From {
    public final String tableName;
    public final Table table;
    public final DataBase db = DataBase.getInstance();


    public From(String tableName) {
        this.tableName = tableName;
        table = db.getTables().get(tableName);
    }
}
