package test_package_de_jc;

import java.util.ArrayList;

public class WordCountList {
	private ArrayList<WordCount> wordList;
	private ArrayList<String> words;
	public WordCountList (ArrayList<String> words){
		wordList = new ArrayList<WordCount>();
		this.words = words;
		initializeList();
	}
	//checks to see if the word already exists.  If it does, adds one to the count
	private boolean contains(String word){
		for(WordCount wc : wordList){
			String temp = wc.getWord();
			if (temp.equals(word)){
				wc.incrementCount();
				return true;
			}
		}
		return false;
	}
	private void initializeList()
	{
		for(String word : words)
		{
			if(this.contains(word))
			{
				
			}
			else
			{
				this.addWord(word);
			}
		}
	}
	//adds a new word, default at count 0
	private void addWord(String word)
	{
		WordCount temp = new WordCount(1, word);
		wordList.add(temp);
	}
	public ArrayList<WordCount> getWordList(){
		return wordList;
	}
}