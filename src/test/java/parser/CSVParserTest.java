package parser;

import logic.Column;
import logic.DatasOnDisk;
import logic.Table;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class CSVParserTest {

	private static CSVParser csvp = new CSVParser();
    private DatasOnDisk dod = new DatasOnDisk();

	private static File file;
	private static URL url_CSV;

    public CSVParserTest() throws FileNotFoundException {
    }

    @BeforeClass
	public static void beforeClass() throws MalformedURLException {
		file = new File("src/resources/dogs.csv");
		url_CSV = new URL("https://data.cityofnewyork.us/api/views/7yq2-hq9c/rows.csv");
		//http://winterolympicsmedals.com/medals.csv
	}

	@Test
	public void get_content_for_local_CSV() throws IOException {
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

        csvp.readFileLocal(file, table);

        Column id = new Column("Id", "int");
        Column prenom = new Column("Prenom", "String");
        Column couleur = new Column("couleur", "String");
        Column age = new Column("age", "int");

        ArrayList<Column> cols = new ArrayList<>();
        cols.add(id);
        cols.add(prenom);
        cols.add(couleur);
        cols.add(age);

        String[] s1 = {"1","Titi","Jaune","5"};
        String[] s2 = {"2","Médor","Noir","10"};
        String[] s3 = {"3","Pitié","Noir","5"};
        String[] s4 = {"4","Juju","Gris","5"};
        String[] s5 = {"5","Vanille","Blanc","7"};
        String[] s6 = {"6","Chocolat","Marron","12"};
        String[] s7 = {"7","Milou","Blanc","3"};
        String[] s8 = {"8","Idefix","Blanc","14"};
        String[] s9 = {"9","Pluto","Jaune","17"};
        String[] s10 = {"10","Dingo","Roux","1"};

        ArrayList<String[]> datas = new ArrayList<>();
        datas.add(s1);
        datas.add(s2);
        datas.add(s3);
        datas.add(s4);
        datas.add(s5);
        datas.add(s6);
        datas.add(s7);
        datas.add(s8);
        datas.add(s9);
        datas.add(s10);

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
}
