package jar;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.List;

public class App {

    public static void main( String[] args ) throws IOException {

        File file = CSVParser.getResource("test.csv");

        URL url = new URL("https://data.cityofnewyork.us/api/views/7yq2-hq9c/rows.csv");
        //http://winterolympicsmedals.com/medals.csv

        List contentLocal = CSVParser.readFileLocal(file);
        List contentURL = CSVParser.readFileURL(url);

        System.out.println(contentLocal);
        System.out.println(contentURL);

    }

}
