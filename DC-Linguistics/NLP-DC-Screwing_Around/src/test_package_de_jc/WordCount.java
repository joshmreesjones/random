package test_package_de_jc;

public class WordCount {
	private int count;
	private String word;
	
	public WordCount(int count, String word){
		this.count = count;
		this.word = word;
	}
	
	public WordCount(String word){
		this.count = 1;
		this.word = word;
	}
	
	public int getCount(){
		return count;
	}
	
	public String getWord(){
		return word;
	}
	
	public void incrementCount(){
		count++;
	}
}
