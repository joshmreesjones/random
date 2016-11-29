package dcnlp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * DataParser reads files with word frequency data.
 * 
 * Data read is a list of words in descending order of frequency around the
 * given character. DataParser removes unwanted data then takes the first chunk
 * of words and writes them to a file in a more convenient format. This
 * file will be used by JavaScript to generate a graph.
 * 
 * @author Josh Rees-Jones
 */
public class DataParser {
	// path to data file
	private String path;

	/**
	 * Creates a new DataParser with the specified path.
	 * 
	 * @param path the path to the data file
	 */
	public DataParser(String path) {
		this.path = path;
	}

	/**
	 * Reads the data file, removes unwanted data, extracts the specified number
	 * of entries, and writes them to a specified file.
	 * 
	 * @param chunkSize the number of entries to extract from the top of the data
	 * @param outputFile the path to the desired outputFile
	 * 
	 * @return true if the operation completed successfully
	 */
	public void process(int chunkSize, String outputFile) throws IOException {
		// Read the data file
		// Selectively assemble into a Hashtable (only add wanted entries)
		Reader reader = null;
		BufferedReader inputStream = null;

		//Hashtable data = new Hashtable<String, Integer>();
		LinkedHashMap data = new LinkedHashMap<String, Integer>();

		try {
			//reader = new InputStreamReader(new FileInputStream(path), "utf-8");
			inputStream = new BufferedReader(new FileReader(path));

			String line;

			while ((line = inputStream.readLine()) != null) {
				String[] separatedLine = line.split(" ");
				String token = separatedLine[1];
				
				// regular expression for normal characters (letters a-z and A-Z)
				Pattern p = Pattern.compile("[^a-zA-Z]");

				// if we don't find any weird characters
				if (p.matcher(token).find()) {
					int frequency = Integer.parseInt(separatedLine[0]);

					// do not distinguish between upper and lower case
					token = token.toLowerCase();

					if (data.get(token) != null) {
						data.put(token, frequency);
					}
				}
			}
		} finally {
			if (reader != null) {
				reader.close();
			}

			if (inputStream != null) {
				inputStream.close();
			}

			if (data != null) {
				System.out.println(data);
			}
		}

		// Remove unwanted data

		// Extract specified number of entities

		// Write entities to file
	}
}
