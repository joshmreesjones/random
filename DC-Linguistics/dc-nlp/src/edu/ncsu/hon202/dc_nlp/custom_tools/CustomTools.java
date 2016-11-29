/**
 * 
 */
package edu.ncsu.hon202.dc_nlp.custom_tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTagger;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * This class holds some of our custom tools. Try to make method names descriptive, and document well.
 * @author Djvacto
 *
 */
public class CustomTools
{
	public CustomTools()
	{
		
	}
	/**
	 * This method creates a simple SentenceDetector object to avoid messy setup in other code
	 * @return a sentence detector that separates sentences
	 */
	public SentenceDetector createSentenceDetector()
	{
		InputStream is = null;
		try
		{
			is = new FileInputStream("models\\en-sent.bin");
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		SentenceModel model = null;
		try
		{
			model = new SentenceModel(is);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		SentenceDetectorME sdetector = new SentenceDetectorME(model);
		return sdetector;
	}
	
	/**
	 * Creates a simple POSTagger
	 * @return POSTagger that can tag each words part of speech
	 */
	public POSTagger createPOSTagger()
	{
		InputStream modelIn = null;
		POSTaggerME posTagger = null;
		POSModel posModel = null;
		try
		{
			modelIn = new FileInputStream("models\\en-pos-maxent.bin");
			posModel = new POSModel(modelIn);
		}
		catch(final IOException ioe)
		{
			ioe.printStackTrace();
		}
		finally
		{
			if(modelIn != null)
			{
				try
				{
					modelIn.close();
				}
				catch(final IOException e)
				{
					
				}
			}
		}
		posTagger = new POSTaggerME(posModel);
		return posTagger;
	}
	
	/**
	 * This method creates a simple tokenizer object to avoid messy setup in other code
	 * @return tokenizer object used to tokenize a string into just the words
	 */
	public Tokenizer createTokenizer()
	{
		Tokenizer tokenizer;
		InputStream is = null;
		try
		{
			is = new FileInputStream("models\\en-token.bin");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		TokenizerModel model = null;
		try
		{
			model = new TokenizerModel(is);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		tokenizer = new TokenizerME(model);
		return tokenizer;
	}
	
	/**
	 * This method will take a file and read it all into a String.
	 * *NOTE* Stings are NOT big enough for the entire text, so for larger texts (like the whole book) use the
	 * readFileToStringArray version of this method
	 * @param f the File to be read
	 * @return a String containing the contents of the file
	 */
	public String readFileToString(File f)
	{
		Scanner inFile = generateFileScanner(f);
		String fileContents = "";
		while(inFile.hasNext())
		{
			fileContents += inFile.next();
		}
		return fileContents;
	}
	
	/**
	 * This method will take a file and read it all into a String Array. Meant to be used when an array is needed because
	 * of ease of use or if the File is too big for a String
	 * *NOTE* Each element of the String Array will be a line
	 * @param f the File to be read
	 * @return a String[] array containing the contents of the file
	 */
	public String[] readFileToStringArrayOfLines(File f)
	{
		Scanner inFile = generateFileScanner(f);
		ArrayList<String> fileContents = new ArrayList<String>();
		while(inFile.hasNext())
		{
			fileContents.add(inFile.nextLine());
		}
		return (String[]) fileContents.toArray();
	}
	
	/**
	 * This method will take a file and read it all into a String Array. Meant to be used when an array is needed because
	 * of ease of use or if the File is too big for a String
	 * *NOTE* Each element of the String Array will be an individual word
	 * @param f the File to be read
	 * @return a String[] array containing the contents of the file
	 */
	public String[] readFileToStringArrayOfWords(File f)
	{
		Scanner inFile = generateFileScanner(f);
		ArrayList<String> fileContents = new ArrayList<String>();
		while(inFile.hasNext())
		{
			fileContents.add(inFile.next());
		}
		return (String[]) fileContents.toArray();
	}
	
	/**
	 * This method will take a file and read it all into a String ArrayList. Meant to be used when an arrayList is needed 
	 * because of ease of use or if the File is too big for a String
	 * *NOTE* Each element of the String ArrayList will be an individual word
	 * @param f the File to be read
	 * @return a String arrayList containing the contents of the file
	 */
	public ArrayList<String> readFileToStringArrayListOfWords(File f)
	{
		Scanner inFile = generateFileScanner(f);
		ArrayList<String> fileContents = new ArrayList<String>();
		while(inFile.hasNext())
		{
			fileContents.add(inFile.next());
		}
		return fileContents;
	}
	
	/**
	 * This method will take a file and read it all into a String ArrayList. Meant to be used when an arrayList is needed 
	 * because of ease of use or if the File is too big for a String
	 * *NOTE* Each element of the String ArrayList will be a line
	 * @param f the File to be read
	 * @return a String arrayList containing the contents of the file
	 */
	public ArrayList<String> readFileToStringArrayListOfLines(File f)
	{
		Scanner inFile = generateFileScanner(f);
		ArrayList<String> fileContents = new ArrayList<String>();
		while(inFile.hasNext())
		{
			fileContents.add(inFile.next());
		}
		return fileContents;
	}
	
	/**
	 * This method creates a Scanner object to read the File parameter
	 * Makes code look neater (no try-catches in our main code)
	 * @param f the File to be made into a Scanner
	 * @return a Scanner of the file parameter
	 */
	public Scanner generateFileScanner(File f)
	{
		Scanner inFile = null;
		try
		{
			inFile = new Scanner(f);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return inFile;
	}
}
