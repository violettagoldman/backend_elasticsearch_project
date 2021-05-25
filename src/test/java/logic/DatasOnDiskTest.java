package logic;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import parser.CSVParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DatasOnDiskTest {

    DatasOnDisk dod = new DatasOnDisk();
    CSVParser csvp = new CSVParser();
    File file = new File("src/resources/dogs.csv");

    public DatasOnDiskTest() throws FileNotFoundException {
    }

    @Test
    public void writeAndRead() throws IOException {
        List<String> csvContent = csvp.readFileLocal(file);
        List<String[]> lines = new ArrayList<>();

        for (String line : csvContent) {
            String[] lineSplited = line.split(",");
            lines.add(lineSplited);
        }

        Column id = new Column("Id", "int");
        Column prenom = new Column("Prenom", "String");
        Column couleur = new Column("couleur", "String");
        Column age = new Column("age", "int");

        ArrayList<Column> columns = new ArrayList<>();
        columns.add(id);
        columns.add(prenom);
        columns.add(couleur);
        columns.add(age);

        for (String[] line : lines) {
            dod.writeLine(line, columns);
        }

        Object[] data;
        String[] line;
        for (int i = 0; i < 10; i++) {
            data = dod.readLine(i, columns);
            line = lines.get(i);
            for (int j = 0; j < 4; j++) {
                assertEquals(data[j], line[j]);
            }
        }

    }

}