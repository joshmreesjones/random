package dcnlp;

import java.io.IOException;

import dcnlp.DataParser;

public class Main {
	public static void main(String[] args) {
		DataParser parser = new DataParser("res/project-8-data/Copperfield.txt");
		int chunkSize = 20;
		String outputFile = "XXXXXXXXXX";

		try {
			parser.process(chunkSize, outputFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
