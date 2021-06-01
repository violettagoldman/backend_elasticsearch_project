package parser;

import logic.DatasOnDisk;
import logic.Table;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class CSVParser {

	/**
	 * Get the content of a local CSV and write it on the disk
	 * @param file file
	 * @param table table
	 * @throws IOException
	 */
	public void readFileLocal(File file, Table table) throws IOException {

		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		DatasOnDisk dod = new DatasOnDisk();

		for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
			String[] lineSplited = line.split(",");
			dod.writeLine(lineSplited, table);
		}

		bufferedReader.close();
		fileReader.close();
	}

	/**
	 * Get the content of a online CSV and write it on the disk
	 * @param url url
	 * @param table table
	 * @throws IOException e
	 */
	public void readFileURL(URL url, Table table) throws IOException {

		URLConnection connection = url.openConnection();
		InputStreamReader input = new InputStreamReader(connection.getInputStream());
		BufferedReader bufferedReader = new BufferedReader(input);

		DatasOnDisk dod = new DatasOnDisk();

		for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
			String[] lineSplited = line.split(",");
			dod.writeLine(lineSplited, table);
		}

		bufferedReader.close();
	}

}
