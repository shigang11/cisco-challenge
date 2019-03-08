package com.cisco.word;

import java.util.Map;
import java.util.TreeMap;

/*
 *I assume words are only consecutive alphabets, and convert all words to lower 
 * case to count
 */
public class WordCounter {
	
	public Map<String, Integer> count(String text) {
		
		//make sure it's not empty		
		if(text == null || text.length()==0)
			return null;
		
		//at least one alphabets, otherwise no words
		if(!text.matches(".*[a-zA-Z]+.*"))
			return null;
		
		//convert to lower case. And replace all non alphabets with spaces
		text = text.toLowerCase().replaceAll("[^A-Za-z]", " "); 
		
		//replace consecutive spaces with ';'
		text = text.replaceAll("\\s+", ";"); 
		
		//get individual words in an array
		String[] listOfWords = text.split(";"); 
		
		//tree map will auto sort the words
		Map<String, Integer> wordMap = new TreeMap<String, Integer>();
		
		//now count occurrence for each word
		for(String word : listOfWords)
		{
			if(wordMap.containsKey(word)) 
				wordMap.put(word, 1 + wordMap.get(word));
			else
				wordMap.put(word, 1);
		}		
		
		//now we have words and their counts, in sorted order
		return wordMap;
	}
}
