/**
 * 
 */
package edu.ncsu.hon202.dc_nlp.emotional_analysis;

import java.io.File;
import java.util.ArrayList;

import opennlp.tools.postag.POSTagger;
import opennlp.tools.tokenize.Tokenizer;
import edu.ncsu.hon202.dc_nlp.custom_tools.*;

/**
 * @author Djvacto
 * This class has to do with idea 1 (ranking top 10 or 20 of each type of word and trying to assign an emotion to the chapter
 */
public class EmotionReading
{
	private static CustomTools CustomTools = new CustomTools();
	private static int currentChapterWordCount;
	
	public static void main(String[] args)
	{
		mainLoop(new String[0], "RB");
		mainLoop(new String[0], "VB");
		mainLoop(new String[0], "JJ");
	}
	
	
	/**
	 * The main loop to print tab-separated word frequencies.
	 * 
	 * @param args unused
	 * @param posTag part of speech to extract (see Apache OpenNLP POS tags)
	 */
	public static void mainLoop(String[] args, String posTag)
	{
		System.out.println(posTag + "List starts here: ***********************************");
		ArrayList<Word> wordsListed = new ArrayList<Word>();
		for(int i = 1; i <= 64; i++)
		{
			String fileName = "textfile\\DC-Ch" + i + ".txt";
			ArrayList<String> textList = CustomTools.readFileToStringArrayListOfLines(new File(fileName));
			ArrayList<Word> currentList = getHighestCountWords(textList, 10, posTag);
			for(int j = 0; j < currentList.size(); j++)
			{
				if(wordsListed.size() == 0)
				{
					wordsListed.add(currentList.get(j));
				}else{
					ArrayList<String> wordsStrings = new ArrayList<String>();
					for(int x = 0; x < wordsListed.size(); x++)
					{
						wordsStrings.add(wordsListed.get(x).getWord());
					}
					if(wordsStrings.contains(currentList.get(j).getWord()))
					{
						Word temp = wordsListed.get(wordsStrings.indexOf(currentList.get(j).getWord()));
						temp.setCount(wordsListed.get(wordsStrings.indexOf(currentList.get(j).getWord())).getCount() + currentList.get(j).getCount());
					}else{
						wordsListed.add(new Word(currentList.get(j).getWord(), currentList.get(j).getCount(), currentList.get(j).getPartOfSpeech()));
					}
				}
			}
			for(int j = 0; j < wordsListed.size(); j++)
			{
				wordsListed.get(j).setCount(0);
			}
		}
		for(int i = 0; i < wordsListed.size(); i++)
		{
			System.out.print(wordsListed.get(i).getWord() + "\t");
		}
		System.out.println();
		for(int i = 1; i < 64; i++)
		{
			String fileName = "textfile\\DC-Ch" + i + ".txt";
			ArrayList<String> textList = CustomTools.readFileToStringArrayListOfLines(new File(fileName));
			ArrayList<Word> currentList = getHighestCountWords(textList, wordsListed.size(), posTag);
			for(int j = 0; j < currentList.size(); j++)
			{
				ArrayList<String> wordsStrings = new ArrayList<String>();
				for(int x = 0; x < wordsListed.size(); x++)
				{
					wordsStrings.add(wordsListed.get(x).getWord());
				}
				if(wordsStrings.contains(currentList.get(j).getWord()))
				{
					Word temp = wordsListed.get(wordsStrings.indexOf(currentList.get(j).getWord()));
					temp.setCount(wordsListed.get(wordsStrings.indexOf(currentList.get(j).getWord())).getCount() + currentList.get(j).getCount());
				}
			}
			for(int x = 0; x < wordsListed.size(); x++)
			{
				double tempDouble = wordsListed.get(x).getCount() / currentChapterWordCount;
				wordsListed.get(x).setCount((int)(tempDouble * 100));
			}
			printWordArray(wordsListed);
			System.out.println();
			for(int j = 0; j < wordsListed.size(); j++)
			{
				wordsListed.get(j).setCount(0);
			}
		}
		System.out.println(posTag + " list ends here: ***********************************");
	}
	
	public static ArrayList<Word> getHighestCountWords(ArrayList<String> text, int n, String pos)
	{
		String[] tokens = null;
		Tokenizer tokenizer = CustomTools.createTokenizer();
		POSTagger posTagger = CustomTools.createPOSTagger();
		String textString = "";
		for(int i = 0; i < text.size(); i++)
		{
			textString += text.get(i) + " ";
		}
		tokens = tokenizer.tokenize(textString);
		int totalChapterWordCount = tokens.length;
		currentChapterWordCount = totalChapterWordCount;
		String[] taggedTokens = posTagger.tag(tokens); 
		ArrayList<Word> words = new ArrayList<Word>();
		for(int i = 0; i < taggedTokens.length; i++)
		{
			String tokenWord = tokens[i].toLowerCase();
			String tokenTag = taggedTokens[i];
			if(tokenTag.equals(",") || tokenTag.equals(".") || tokenTag.equals("!") || tokenTag.equals(":") || tokenTag.equals("?") || tokenTag.equals(";"))
			{
				// Ignores punctuation
			}else{
				if(tokenTag.equals(pos))
				{
					if(tokenWord.equals("n't"))
						tokenWord = "not";
					if(words.size() == 0)
					{
						words.add(new Word(tokenWord, 1, tokenTag));
					}else{
						ArrayList<String> wordsStrings = new ArrayList<String>();
						for(int j = 0; j < words.size(); j++)
						{
							wordsStrings.add(words.get(j).getWord());
						}
						if(wordsStrings.contains(tokenWord))
						{
							words.get(wordsStrings.indexOf(tokenWord)).increaseCount();
						}else{
							words.add(new Word(tokenWord, 1, tokenTag));
						}
					}
				}
			}
		}
		//Bubble Sort
		int j;
		boolean flag = true;
		Word temp;
		while(flag)
		{
			flag = false;
			for(j = 0; j < words.size() - 1; j++)
			{
				if(words.get(j).getCount() < words.get(j + 1).getCount())   // change to > for ascending sort
				{
					temp = words.get(j);
					words.set(j, words.get(j + 1));
					words.set(j + 1, temp);
					flag = true;  
				} 
			} 
		}
		ArrayList<Word> shortenedList = new ArrayList<Word>();
		for(int i = 0; i < n && i < words.size(); i++)
		{
			shortenedList.add(words.get(i));
		}
		return shortenedList;
	}
	
	private static void printWordArray(ArrayList<Word> list)
	{
//		for(int i = 0; i < list.size(); i++)
//		{
//			System.out.print(list.get(i).getWord() + "\t");
//		}
//		System.out.println();
		for(int i = 0; i < list.size(); i++)
		{
			System.out.print(list.get(i).getCount() + "\t");
		}
	}
}
