package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.utils.Array;

public class BlockData {
	
	private Letter letter;
	
	private Array<Boolean> guessedLetterBooleans;
	
	public BlockData(Letter letter){
		this.letter = letter;
		
		//
		guessedLetterBooleans = new Array<Boolean>();
		for(int i = 0; i < 26; i++){
			guessedLetterBooleans.add(false);
		}
	}
	
	public Letter getLetter(){
		return letter;
	}
	
	public Array<Boolean> getGuessedLetterBooleans(){
		return guessedLetterBooleans;
	}
	
	
}
