package parser;

import logic.DataBase;

import java.io.IOException;
import java.util.*;

public class NewDataBase {

    CSVParser csvp = new CSVParser();

    /**
     * Put the content of a csv in a database (table and lines)
     * @param fileName
     * @return the created database
     * @throws IOException
     */
    public DataBase inIndex(String fileName) throws IOException {
        // Ressources
        List<String> ressources = csvp.readFileLocal(fileName);

        // Columns Names
        List<String> columnsNames = Arrays.asList(ressources.get(0).split(","));

        // Creation DataBase
        DataBase db = new DataBase("DB_Name");

        String tableName = trimName(fileName);

        // Creation Table
        Map<String, String> columnsNamesMap = new HashMap<>();
        for (String columnName :
             columnsNames) {
            columnsNamesMap.put(columnName, "String");
        }

        db.newTable(tableName, columnsNamesMap);

        // Creation Lines
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

    /**
     * Get the name of the table with the name of the file
     * @param name
     * @return the trimed name
     */
    public String trimName(String name) {
        name = name.substring(0, name.length()-4);

        int pos = name.lastIndexOf('/');
        if (pos >= 0) {
            name = name.substring(pos+1);
        }

        return name;
    }

}
