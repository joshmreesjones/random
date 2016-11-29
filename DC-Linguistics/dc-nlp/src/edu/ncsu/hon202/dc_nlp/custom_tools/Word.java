/**
 * 
 */
package edu.ncsu.hon202.dc_nlp.custom_tools;


/**
 * @author Djvacto
 *
 */
public class Word
{
	private String word;
	private int count;
	private String partOfSpeech;
	
	
	/**
	 *  Constructs a word object
	 * @param w the actual string of the word
	 * @param c the count of the word in the given text
	 * @param pos part of speech tag
	 */
	public Word(String w, int c, String pos)
	{
		word = w;
		count = c;
		partOfSpeech = pos;
	}
	
	public void setCount(int n)
	{
		count = n;
	}
	
	public String toString()
	{
		String s = "";
		return s;
	}
	
	/**
	 * @return the word
	 */
	public String getWord()
	{
		return word;
	}

	/**
	 * @return the count
	 */
	public int getCount()
	{
		return count;
	}

	/**
	 * @return the partOfSpeech
	 */
	public String getPartOfSpeech()
	{
		return partOfSpeech;
	}
	
	/**
	 * Increases the count by 1
	 */
	public void increaseCount()
	{
		count++;
	}
}
