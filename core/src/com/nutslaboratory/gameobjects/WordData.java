package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.utils.Array;

public class WordData {
	public static final String NOT_GUESSED = "not_guessed";
	public static final String INSIDE_WORD = "inside_word";
	public static final String OUTSIDE_WORD = "outside_word";
	
	private Word word;
	private Array<String> guessedCharacterStrings;
	private boolean hasGuessedSyllable = false;
	private boolean hasGuessedType = false;
	private boolean hasFinished = false;
	
	public WordData(Word word){
		this.word = word;
		
		//
		guessedCharacterStrings = new Array<String>();
		for(int i = 0; i < 26; i++){
			guessedCharacterStrings.add(NOT_GUESSED);
		}
		
		
	}
	
	public Word getWord(){
		return word;
	}
	
	public Array<String> getGuessedCharacterStrings(){
		return guessedCharacterStrings;
	}
	
	public void setHasGuessedSyllable(boolean b){
		hasGuessedSyllable = b;
	}
	
	public boolean getHasGuessedSyllable(){
		return hasGuessedSyllable;
	}
	
	public void setHasGuessedType(boolean b){
		hasGuessedType = b;
	}
	
	public boolean getHasGuessedType(){
		return hasGuessedType;
	}
	
	public void finish(){
		hasFinished = true;
	}
	
	public boolean hasFinished(){
		return hasFinished;
	}
}
