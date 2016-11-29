package test_package_de_jc;

import java.io.*;
import java.util.ArrayList;

import opennlp.tools.sentdetect.*;

public class TestMethods {

	public static void main(String[] args) {
		InputStream modelIn = null;
		SentenceModel model = null;
		try {
			modelIn = new FileInputStream("models\\en-sent.bin");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			model = new SentenceModel(modelIn);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				}
				catch (IOException e) {
				}
			}
			ArrayList<String> sentences = new ArrayList<String>();
			SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader("lib\\txt\\test.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String line;
			try {
				while ((line = br.readLine()) != null) {
					String[] temp = sentenceDetector.sentDetect(line);
					for(int i = 0; i < temp.length; i++){
						sentences.add(temp[i]);
						}
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(String e : sentences)
			{	
				System.out.println(e);
			}
		}
	}
}