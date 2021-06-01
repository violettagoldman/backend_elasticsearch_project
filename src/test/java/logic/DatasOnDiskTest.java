package logic;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class DatasOnDiskTest {

    private DatasOnDisk dod = new DatasOnDisk();
    private File file = new File("src/resources/dogs.csv");

    public DatasOnDiskTest() throws FileNotFoundException {
    }

    @Test
    public void write_and_read_all_file() throws IOException {
        ArrayList<String> colsNames = new ArrayList<>();
        colsNames.add("id");
        colsNames.add("name");
        colsNames.add("color");
        colsNames.add("age");

        ArrayList<String> colsType = new ArrayList<>();
        colsType.add("int");
        colsType.add("String");
        colsType.add("String");
        colsType.add("int");

        Table table = new Table("dogs", colsNames, colsType);

        Column id = new Column("Id", "int");
        Column prenom = new Column("Prenom", "String");
        Column couleur = new Column("couleur", "String");
        Column age = new Column("age", "int");

        ArrayList<Column> cols = new ArrayList<>();
        cols.add(id);
        cols.add(prenom);
        cols.add(couleur);
        cols.add(age);

        String[] line1 = {"1", "Titi", "Jaune", "5"};
        String[] line2 = {"2", "Medor", "Noir", "10"};
        String[] line3 = {"3", "Pitie", "Noir", "5"};
        String[] line4 = {"4", "Juju", "Gris", "5"};
        String[] line5 = {"5", "Vanille", "Blanc", "7"};
        String[] line6 = {"6", "Chocolat", "Marron", "12"};
        String[] line7 = {"7", "Milou", "Blanc", "3"};
        String[] line8 = {"8", "Idefix", "Blanc", "14"};
        String[] line9 = {"9", "Pluto", "Jaune", "17"};
        String[] line10 = {"10", "Dingo", "Roux", "1"};

        dod.writeLine(line1, table);
        dod.writeLine(line2, table);
        dod.writeLine(line3, table);
        dod.writeLine(line4, table);
        dod.writeLine(line5, table);
        dod.writeLine(line6, table);
        dod.writeLine(line7, table);
        dod.writeLine(line8, table);
        dod.writeLine(line9, table);
        dod.writeLine(line10, table);

        ArrayList<String[]> datas = new ArrayList<>();
        datas.add(line1);
        datas.add(line2);
        datas.add(line3);
        datas.add(line4);
        datas.add(line5);
        datas.add(line6);
        datas.add(line7);
        datas.add(line8);
        datas.add(line9);
        datas.add(line10);

        Object[] data;
        for (int i = 0; i < 10; i++) {
            data = dod.readLine(i, cols, cols);
            for (int j = 0; j < 4; j++) {
                assertEquals(datas.get(i)[j], data[j]);
                System.out.print(data[j] + ", ");
            }
            System.out.print("\n");
        }
    }

    @Test
    public void write_and_read_part_file() throws IOException {
        ArrayList<String> colsNames = new ArrayList<>();
        colsNames.add("id");
        colsNames.add("name");
        colsNames.add("color");
        colsNames.add("age");

        ArrayList<String> colsType = new ArrayList<>();
        colsType.add("int");
        colsType.add("String");
        colsType.add("String");
        colsType.add("int");

        Table table = new Table("dogs", colsNames, colsType);

        Column id = new Column("Id", "int");
        Column prenom = new Column("Prenom", "String");
        Column couleur = new Column("couleur", "String");
        Column age = new Column("age", "int");

        ArrayList<Column> cols = new ArrayList<>();
        cols.add(id);
        cols.add(prenom);
        cols.add(couleur);
        cols.add(age);

        ArrayList<Column> colsSelected = new ArrayList<>();
        colsSelected.add(id);
        colsSelected.add(prenom);

        String[] line1 = {"1", "Titi", "Jaune", "5"};
        String[] line2 = {"2", "Medor", "Noir", "10"};
        String[] line3 = {"3", "Pitie", "Noir", "5"};
        String[] line4 = {"4", "Juju", "Gris", "5"};
        String[] line5 = {"5", "Vanille", "Blanc", "7"};
        String[] line6 = {"6", "Chocolat", "Marron", "12"};
        String[] line7 = {"7", "Milou", "Blanc", "3"};
        String[] line8 = {"8", "Idefix", "Blanc", "14"};
        String[] line9 = {"9", "Pluto", "Jaune", "17"};
        String[] line10 = {"10", "Dingo", "Roux", "1"};

        dod.writeLine(line1, table);
        dod.writeLine(line2, table);
        dod.writeLine(line3, table);
        dod.writeLine(line4, table);
        dod.writeLine(line5, table);
        dod.writeLine(line6, table);
        dod.writeLine(line7, table);
        dod.writeLine(line8, table);
        dod.writeLine(line9, table);
        dod.writeLine(line10, table);

        ArrayList<String[]> datas = new ArrayList<>();
        datas.add(line1);
        datas.add(line2);
        datas.add(line3);
        datas.add(line4);
        datas.add(line5);
        datas.add(line6);
        datas.add(line7);
        datas.add(line8);
        datas.add(line9);
        datas.add(line10);

        Object[] data;

        data = dod.readLine(0, cols, colsSelected);
        for (int i = 0; i < colsSelected.size(); i++) {
            assertEquals(datas.get(0)[i], data[i]);
            System.out.print(data[i] + ", ");
        }
        System.out.print("\n");

        data = dod.readLine(4, cols, colsSelected);
        for (int i = 0; i < colsSelected.size(); i++) {
            assertEquals(datas.get(4)[i], data[i]);
            System.out.print(data[i] + ", ");
        }
        System.out.print("\n");

        data = dod.readLine(9, cols, colsSelected);
        for (int i = 0; i < colsSelected.size(); i++) {
            assertEquals(datas.get(9)[i], data[i]);
            System.out.print(data[i] + ", ");
        }
    }

}
