/**
 * 
 */
package edu.ncsu.hon202.screwing_around;

import java.io.*;
import java.util.Scanner;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * @author Djvacto
 *
 */
public class screwing_around
{

	/**
	 * Prints out the 2 sentences in testSentences.txt as 2 sentences (one per line) and one with all the individual
	 * tokens (also one per line)
	 * @param args an irrelevant parameter
	 */
	public static void main(String[] args)
	{
		String[] printSentences = testSentenceDetector(new File("textfile\\testSentences.txt"));
		for(int i = 0; i < printSentences.length; i++)
		{
			System.out.println(printSentences[i]);
		}
		printSentences = testSentenceDetector(new File("textfile\\David Copperfield by Charles Dickens.txt"));
		for(int i = 0; i < printSentences.length; i++)
		{
			System.out.println(printSentences[i]);
		}
		String[] printTokens = testTokenizer(new File("textfile\\testSentences.txt"));
		for(int i = 0; i < printTokens.length; i++)
		{
			System.out.println(printTokens[i]);
		}
	}
	
	/**
	 * 
	 * @param f the file object that is passed in as a parameter to be read
	 * @return a String[] array where each element is a token (separated by a space)
	 */
	public static String[] testTokenizer(File f)
	{
		String[] tokens = null;
		InputStream is = null;
		try
		{
			is = new FileInputStream("models\\en-token.bin");
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TokenizerModel model = null;
		try
		{
			model = new TokenizerModel(is);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tokenizer tokenizer = new TokenizerME(model);
		Scanner inFile = null;
		try
		{
			inFile = new Scanner(f);
		}
		catch(FileNotFoundException e)		
		{
			System.out.println("File Not Found!");
		}
		String fileInput = "";
		while(inFile.hasNext())
		{
			fileInput += inFile.next() + " ";
		}
		inFile.close();
		tokens = tokenizer.tokenize(fileInput);
		return tokens;
	}

	/**
	 * 
	 * @param f the file object that is passed in as a parameter to be read
	 * @return an array of String objects where each element in the array is a sentence
	 */
	public static String[] testSentenceDetector(File f)
	{
		InputStream is = null;
		try
		{
			is = new FileInputStream("models\\en-sent.bin");
		} catch (FileNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SentenceModel model = null;
		try
		{
			model = new SentenceModel(is);
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SentenceDetectorME sdetector = new SentenceDetectorME(model);
		
		Scanner inFile = null;
		try
		{
			inFile = new Scanner(f);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File Not Found!");
		}
		String fileText = "";
		while(inFile.hasNext())
		{
			fileText += inFile.next() + " ";
		}
		inFile.close();
		String[] sentences = sdetector.sentDetect(fileText);
		return sentences;
	}
}
