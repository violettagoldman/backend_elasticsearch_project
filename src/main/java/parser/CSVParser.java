package parser;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

	/**
	 * Get the absolute path of the file (used for readFileLocal)
	 * @param fileName
	 * @return the file we need
	 */
	public File getResource(String fileName) {
		final File initFile = new File("");
		final String pathFileName = initFile.getAbsolutePath() + File.separator + fileName; // File.separator = '\'

		File file = new File(pathFileName);
		return file;
	}

	/**
	 * Get the content of the file when it is in local
	 * @param fileName
	 * @return the list (String) of each line
	 * @throws IOException
	 */
	public List<String> readFileLocal(String fileName) throws IOException {

		File file = getResource(fileName);

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

	/**
	 * Get the content of the file when it is online
	 * @param url
	 * @return the list (String) of each line
	 * @throws IOException
	 */
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

}
