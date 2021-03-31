package parser;

import logic.DataBase;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class NewDataBase {

    CSVParser csvp = new CSVParser();

    public DataBase inIndex(String fileName) throws IOException {
        File file = csvp.getResource(fileName);
        List<String> ressources = csvp.readFileLocal(file);

        List<String> columnsNames = Arrays.asList(ressources.get(0).split(","));

        DataBase db = new DataBase("DB_Name");

        String tableName = trimName(fileName);

        // Table
        Map<String, String> columnsNamesMap = new HashMap<>();
        for (String columnName :
             columnsNames) {
            columnsNamesMap.put(columnName, "String");
        }

        db.newTable(tableName, columnsNamesMap);

        // Lines
        for (int i = 1; i < ressources.size(); i++) {
            Map<String, String> datasInLineMaps = new HashMap<>();

            List<String> datasInLine = Arrays.asList(ressources.get(i).split(","));
            for (int j = 0; j < datasInLine.size(); j++) {
                datasInLineMaps.put(columnsNames.get(j), datasInLine.get(j));
            }

            db.newLine(tableName, datasInLineMaps);
        }

        return db;
    }

    public String trimName(String name) {
        name = name.substring(0, name.length()-4);

        int pos = name.lastIndexOf('/');
        if (pos >= 0) {
            name = name.substring(pos+1);
        }

        return name;
    }

}
