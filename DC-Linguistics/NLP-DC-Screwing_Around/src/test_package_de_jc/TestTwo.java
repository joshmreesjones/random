package test_package_de_jc;

import java.io.*;
import java.util.ArrayList;

import opennlp.tools.tokenize.*;

public class TestTwo {
	public static void main(String[] args){
		InputStream modelIn = null;
		TokenizerModel model = null;
		try {
			modelIn = new FileInputStream("models\\en-token.bin");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
		  model = new TokenizerModel(modelIn);
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
		}
		Tokenizer tokenizer = new TokenizerME(model);
		ArrayList<String> words = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("lib\\txt\\full.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line;
		try {
			while ((line = br.readLine()) != null) {
				String[] temp = tokenizer.tokenize(line);
				for(int i = 0; i < temp.length; i++){
					words.add(temp[i]);
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
		WordCountList WC = new WordCountList(words);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("lib\\txt\\words.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	 
		for (WordCount wc : WC.getWordList()) {
			try {
				int num = wc.getCount();
				String word = wc.getWord();
				bw.write(num + "     " + word);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bw.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
