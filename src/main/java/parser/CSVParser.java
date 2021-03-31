package parser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

	public File getResource(String fileName) {
		final File initFile = new File("");
		final String pathFileName = initFile.getAbsolutePath() + File.separator + fileName; // File.separator = '\'

		File file = new File(pathFileName);
		return file;
	}

	public List<String> readFileLocal(File file) throws IOException {

		List<String> result = new ArrayList<>();

		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
			result.add(line);
		}

		bufferedReader.close();
		fileReader.close();

		return result;
	}

	public List<String> readFileURL(URL url) throws IOException {

		List<String> result = new ArrayList<>();

		URLConnection connection = url.openConnection();
		InputStreamReader input = new InputStreamReader(connection.getInputStream());
		BufferedReader bufferedReader = new BufferedReader(input);

		for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
			result.add(line);
		}

		bufferedReader.close();

		return result;
	}

	public static void main(String[] argv) throws IOException {

		CSVParser csvp = new CSVParser();

		URL url = new URL("https://data.cityofnewyork.us/api/views/7yq2-hq9c/rows.csv");
		List<String> list = csvp.readFileURL(url);

		for (String value :
			 list) {
			System.out.println(value);
		}

	}

}
