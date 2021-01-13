package com.nutslaboratory.gameobjects;

import java.io.BufferedReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class Glossary {
	
	public final static int REAL_GAME_TYPE = 0;
	public final static int TUTORIAL_GAME_TYPE = 1;
	private int gameType;
	
	private Array<Word> words = new Array<Word>();
	
	public Glossary(int gameType) {
		this.gameType = gameType;
		
		createWordsFromFile();
	}

	private void createWordsFromFile() {
		//
		String pathName = "";
		if(gameType == REAL_GAME_TYPE){
			pathName = "data/glossary.txt";
		}else if(gameType == TUTORIAL_GAME_TYPE){
			pathName = "data/tutorial_glossary.txt";
		}
		
		//
		FileHandle fileHandle = Gdx.files.internal(pathName);
		BufferedReader bufferedReader = new BufferedReader(fileHandle.reader());
		
		int totalWords = 0;
		while(true){
			String line = null;
			try {
				line = bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(line == null){
				break;
			}
			
			String[] wordArray = line.split(",");
			
			String name = wordArray[0];
			String syllable = wordArray[1];
			String[] type = wordArray[2].split("-");
			
			Word word = new Word(name, syllable, type);
			
			if(name.length() > 1 && name.length() < 10){
				words.add(word);
			
				totalWords += 1;
				//Gdx.app.log(totalWords+"", name);
			}else{
				//Gdx.app.log("Disallow Word: ", name);
			}
			
			
		}	
	}
	
	public Array<Word> getWords(){
		return words;
	}
}
