package com.nutslaboratory.gameobjects;

import java.util.Random;

import com.badlogic.gdx.utils.Array;

public class Crosswords {
	
	public final static int REAL_GAME_TYPE = 0;
	public final static int TUTORIAL_GAME_TYPE = 1;
	private int gameType;
	
	public static final String NEAR_LETTER_LEFT_SIDE = "near_letter_left_side";
	public static final String NEAR_LETTER_RIGHT_SIDE  = "near_letter_right_side";
	public static final String NEAR_LETTER_UP_SIDE  = "near_letter_up_side";
	public static final String NEAR_LETTER_DOWN_SIDE  = "near_letter_down_side";
	
	private Random random = new Random();
	
	private Array<Word> glossaryWords;
	private Array<Word> words;
	private int totalWords;
	private int totalColumns; 
	private int totalRows;
	
	private Array<Array<Letter>> wordTable; 
	
	private Array<Word> showGlossariesWords;
	
	public Crosswords(int gameType, Array<Word> glossaryWords , int totalWords, int totalColumns, int totalRows){
		this.gameType = gameType;
		
		this.totalWords = totalWords;
		this.totalColumns = totalColumns;
		this.totalRows = totalRows;
		
		
		if(gameType == REAL_GAME_TYPE){
			//
			int createBoardNum = 0;
			
			Word placeWord = null;
			
			do{
				
				//copy from glossary words
				this.glossaryWords = new Array<Word>(glossaryWords);
				
				//
				words = new Array<Word>();
				
				//create word table
				createBoardNum++;
				//Gdx.app.log("","*****************************************************");
				//Gdx.app.log("CrosswordsBoard","create word table " + " ["+ createBoardNum  +"]");
				
				createWordTable();
				
				//place first word at word table
				//Gdx.app.log("CrosswordsBoard","place first word at word table");
				
				placeWord = placeFirstWordAtWordTable();
				
				words.add(placeWord);
				
				//place other words at word table
				for(int i = 0; i < totalWords-1; i++){
					//Gdx.app.log("CrosswordsBoard","place other word (" + (i+1) +") at word table");
					
					placeWord = placeOtherWordAtWordTable(i+2);
					
					words.add(placeWord);
					
					if(placeWord == null){
						break;
					}
				}
				
			}while(placeWord == null);

		}
		else if(gameType == TUTORIAL_GAME_TYPE){
			
			this.glossaryWords = new Array<Word>(glossaryWords);
			
			words = new Array<Word>();
			
			createWordTable();
			
			//wood
			Word firstWord = this.glossaryWords.get(10);
			placeWord(wordTable, firstWord,Word.VERTICAL_AXIS, 3, 2, 1);
			words.add(firstWord);
			
			//radio
			Word secondWord = this.glossaryWords.get(3);
			placeWord(wordTable, secondWord, Word.HORIZONTAL_AXIS, 1, 5, 2);
			words.add(secondWord);
			
					
		}
	}
		
	/*
	//this method is for test only
	public Crosswords(int totalColumns, int totalRows){
		this.totalColumns = totalColumns;
		this.totalRows = totalRows;
		
		System.out.println("test");
		
		createWordTable();
		
		System.out.println("*********************");
		
		placeWord(wordTable, new Word("rate","1",null), Word.VERTICAL_AXIS, 4, 4, 1);
		traceCrosswords();
		
		System.out.println("*********************");
		
		placeWord(wordTable, new Word("karate","1",null), Word.VERTICAL_AXIS, 4, 2, 1);
		//placeWord(wordTable, new Word("ate","1",null), Word.HORIZONTAL_AXIS, 3, 3, 1);
		traceCrosswords();
		
		System.out.println("*********************");
			
		
	}*/
	
	

	private Array<Array<Letter>> createWordTable() {
		//Gdx.app.log("CrosswordsBoard","create empty board");
		
		//create word table
		wordTable = new Array<Array<Letter>>();
		
		for(int column = 0; column < totalColumns; column++){
			wordTable.add(new Array<Letter>());
			
			for(int row = 0; row < totalRows; row++){
				Letter letter = new Letter("-", null, column, row);
				
				wordTable.get(column).add(letter);
				
			}
		}
		
		
		//assign unavailable letter to word table
		Letter unavailableLetter = new Letter("#", null, totalColumns-1, totalRows-1);
		wordTable.get(totalColumns-1).set(totalRows-1, unavailableLetter);
		
		
		//trace
		//traceCrosswords();
		
		
		return wordTable;
		
	}
	
	
	
	private Word placeFirstWordAtWordTable() {
		
		Word placeWord = null;
		
		int placeNum = 0;
		
		//shuffle words
		glossaryWords.shuffle();
		
		
		
		
		for(Word word : glossaryWords){
			/////
			placeNum++;
			//Gdx.app.log("CrosswordsBoard","place first word at word table" + " [" +placeNum +"]");
			
			////set word
			
			//set axis
			String axis = null;
			if(random.nextInt(2) == 0){
				axis = Word.HORIZONTAL_AXIS;
			}else{
				axis = Word.VERTICAL_AXIS;
			}
			
			//set start column, row
			int startColumn = 0;
			int startRow = 0;
			if(axis.equals(Word.HORIZONTAL_AXIS)){
				startColumn = (totalColumns - word.getName().length()) / 2;
				startRow = 4;
			}else if(axis.equals(Word.VERTICAL_AXIS)){
				startColumn = 3;
				startRow = (totalRows - word.getName().length()) / 2;
			}
			
			
			////place word
			if(placeWord(duplicateWordTable(wordTable), word, axis, startColumn, startRow, 1)){
				//
				placeWord(wordTable, word, axis, startColumn, startRow, 1);
				//remove this word from words
				glossaryWords.removeValue(word, true);
				
				//trace
				//Gdx.app.log("CrosswordsBoard", word.getName());
				//traceCrosswords();
				
				placeWord = word;

				break;
			}else{
				//find new word
				continue;
			}
			
		}
		
		return placeWord;
		
		
		
	}
	



	private Word placeOtherWordAtWordTable(int group) {
		
		Word placeWord = null;
		
		//shuffle words
		glossaryWords.shuffle();
		
		//
		for(Letter possibleLinkLetter : getPossilebleLinkLetters()){
			//Gdx.app.log("CrosswordsBoard","Link Letter: "+possibleLinkLetter.getName());
			
			
			
			for(Word word : glossaryWords){
				
				if (word.getName().indexOf(possibleLinkLetter.getName()) != -1){
					//Gdx.app.log("CrosswordsBoard",word.getName());
					
					
					for(int letterIndex : word.getLetterIndexs(possibleLinkLetter)){
						
						//Gdx.app.log("CrosswordsBoard","Letter index: "+letterIndex);
						
						String axis = null;
						if(possibleLinkLetter.getAxis().equals(Word.HORIZONTAL_AXIS)){
							axis = Word.VERTICAL_AXIS;
						}else if(possibleLinkLetter.getAxis().equals(Word.VERTICAL_AXIS)){
							axis = Word.HORIZONTAL_AXIS;
						}
						
						//
						int startColumn = 0;
						int startRow = 0;
						if(axis.equals(Word.HORIZONTAL_AXIS)){
							startColumn = possibleLinkLetter.getColumn() - letterIndex;
							startRow = possibleLinkLetter.getRow();
						}else if(axis.equals(Word.VERTICAL_AXIS)){
							startColumn = possibleLinkLetter.getColumn();
							startRow = possibleLinkLetter.getRow() - letterIndex;
						}
						
						////place word
						if(placeWord(duplicateWordTable(wordTable), word, axis, startColumn, startRow, group)){
							//
							placeWord(wordTable, word, axis, startColumn, startRow, group);
							//remove this word from words
							glossaryWords.removeValue(word, true);
							
							//trace
							//Gdx.app.log("CrosswordsBoard", word.getName());
							//traceCrosswords();
							
							placeWord = word;
	
							return placeWord;
						}else{
							//find new word
							continue;
						}
						
					}
				}
			}
		}
		
		
		
		return placeWord;
		
	}
	
	private boolean placeWord(Array<Array<Letter>> receivedWordTable, Word word, String axis, int startColumn, int startRow, int group) {
		if(!checkWordIsInTable(word, axis, startColumn, startRow)){
			//Gdx.app.log("CrosswordsBoard","can't place word: word is too long");
			return false;
		}
		
		//////set start column, row
		int column = startColumn;
		int row = startRow;
		
		//////
		for(int i = 0; i < word.getName().length(); i++){
			////set letter
			
			//
			Letter oldLetter = receivedWordTable.get(column).get(row);
			
			//set letter name
			String newLetterName = String.valueOf(word.getName().charAt(i));
			//set head
			Boolean isHead = false;
			if(i == 0){
				isHead = true;
			}else{
				isHead = false;
			}
			
			
			////create letter
			Letter newLetter  = new Letter(newLetterName, axis, column, row);
			//
			if(isHead){
				newLetter.setHead(true, group, axis);
			}
			//
			for(int newGroup : oldLetter.getGroups()){
				if(!newLetter.getGroups().contains(newGroup, false)){
					newLetter.addGroup(newGroup);
				}
			}
			newLetter.addGroup(group);
			//set can link
			if(!oldLetter.getName().equals("-")){
				newLetter.setCanLink(false);
			}
			
			
			
			////check if word can place
			if(checkNewHeadLetterIsReplaceOldHeadLetter(oldLetter, newLetter)){
				return false;
			}else if(checkReplaceLetterIsDifferentName(oldLetter, newLetter)){
				return false;
			}else if(checkNearLetterIsAvailable(oldLetter, newLetter, receivedWordTable, word, axis, i)){
				return false;
			}
			
			
			
			
			////place letter
			if(!oldLetter.isHead()){
				receivedWordTable.get(column).set(row, newLetter);
			}
			
			if(oldLetter.isHead() && !newLetter.isHead()){
				newLetter.setHead(true, oldLetter.getHeadGroup(), oldLetter.getHeadAxis());
				
				receivedWordTable.get(column).set(row, newLetter);
			}
			
		
		
			
			
			
			////next column, row to add letter
			if(axis.equals(Word.HORIZONTAL_AXIS)){
				column++;
			}else if(axis.equals(Word.VERTICAL_AXIS)){
				row++;
			}
		}
		
		
		
		
		
		return true;
	}
	
	

	

	private boolean checkWordIsInTable(Word word, String axis, int startColumn, int startRow){
		boolean canPlaceWord = true;
		
		////check word is not too long
		if(axis.equals(Word.HORIZONTAL_AXIS)){
			if(startColumn < 0 || word.getName().length() + startColumn > totalColumns){
				//Gdx.app.log("CrosswordsBoard","can't place word: word is too long");
				canPlaceWord = false;
			}
		}else if(axis.equals(Word.VERTICAL_AXIS)){
			if(startRow < 0 || word.getName().length() + startRow > totalRows){
				//Gdx.app.log("CrosswordsBoard","can't place word: word is too long");
				canPlaceWord = false;
			}
		}
		
		////
		return canPlaceWord;
	}
	
	private boolean checkNewHeadLetterIsReplaceOldHeadLetter(Letter oldLetter, Letter newLetter){
		if(oldLetter.isHead() && newLetter.isHead()){
			//Gdx.app.log("CrosswordsBoard","can't place word: head letter replace head letter");
			return true;
		}
		
		return false;
	}
	
	private boolean checkReplaceLetterIsDifferentName(Letter oldLetter, Letter newLetter) {
		if(!oldLetter.getName().equals("-")){
			if(!oldLetter.getName().equals(newLetter.getName())){
				//Gdx.app.log("CrosswordsBoard","can't place word: place on other letter");
				return true;
			}
		}
		return false;
	}
	
	private boolean checkNearLetterIsAvailable(Letter oldLetter, Letter newLetter, Array<Array<Letter>> receivedWordTable, Word word, String axis, int wordIndex) {
		if(axis.equals(Word.VERTICAL_AXIS)){
			if(newLetter.isHead()){
				if(getNearLetter(oldLetter, NEAR_LETTER_UP_SIDE, receivedWordTable) != null){
					if(!getNearLetter(oldLetter, NEAR_LETTER_UP_SIDE, receivedWordTable).getName().equals("-")){
						//Gdx.app.log("CrosswordsBoard","can't place word: type 3 ");
						return true;
					}
				}
			}
			
			if(oldLetter.getName().equals("-")){
				if(getNearLetter(oldLetter, NEAR_LETTER_LEFT_SIDE, receivedWordTable) != null){
					if(!getNearLetter(oldLetter, NEAR_LETTER_LEFT_SIDE, receivedWordTable).getName().equals("-")){
						//Gdx.app.log("CrosswordsBoard","can't place word: type 4 / left");
						return true;
					}
				}
				
				if(getNearLetter(oldLetter, NEAR_LETTER_RIGHT_SIDE, receivedWordTable) != null){
					if(!getNearLetter(oldLetter, NEAR_LETTER_RIGHT_SIDE, receivedWordTable).getName().equals("-")){
						//Gdx.app.log("CrosswordsBoard","can't place word: type 4 / right");
						return true;
					}
				}
			}
			
			if(wordIndex == word.getName().length()-1){
				if(getNearLetter(oldLetter, NEAR_LETTER_DOWN_SIDE, receivedWordTable) != null){
					if(!getNearLetter(oldLetter, NEAR_LETTER_DOWN_SIDE, receivedWordTable).getName().equals("-")){
						//Gdx.app.log("CrosswordsBoard","can't place word: type last");
						return true;
					}
				}
			}
			
		}
		
		if(axis.equals(Word.HORIZONTAL_AXIS)){
			if(newLetter.isHead()){
				if(getNearLetter(oldLetter, NEAR_LETTER_LEFT_SIDE, receivedWordTable) != null){
					if(!getNearLetter(oldLetter, NEAR_LETTER_LEFT_SIDE, receivedWordTable).getName().equals("-")){
						//Gdx.app.log("CrosswordsBoard","can't place word: type 5 ");
						return true;
					}
				}
			}
			
			if(oldLetter.getName().equals("-")){
				if(getNearLetter(oldLetter, NEAR_LETTER_UP_SIDE, receivedWordTable) != null){
					if(!getNearLetter(oldLetter, NEAR_LETTER_UP_SIDE, receivedWordTable).getName().equals("-")){
						//Gdx.app.log("CrosswordsBoard","can't place word: type 5 / up");
						return true;
					}
				}
				
				if(getNearLetter(oldLetter, NEAR_LETTER_DOWN_SIDE, receivedWordTable) != null){
					if(!getNearLetter(oldLetter, NEAR_LETTER_DOWN_SIDE, receivedWordTable).getName().equals("-")){
						//Gdx.app.log("CrosswordsBoard","can't place word: type 5 / down");
						return true;
					}
				}
			}
			
			if(wordIndex == word.getName().length()-1){
				if(getNearLetter(oldLetter, NEAR_LETTER_RIGHT_SIDE, receivedWordTable) != null){
					if(!getNearLetter(oldLetter, NEAR_LETTER_RIGHT_SIDE, receivedWordTable).getName().equals("-")){
						//Gdx.app.log("CrosswordsBoard","can't place word: type last ");
						return true;
					}
				}
			}
			
		}
		
		return false;
	}
	
	private Array<Letter> getPossilebleLinkLetters(){
		Array<Letter> possibleLinkLetters = new Array<Letter>();
		
		for(int column = 0; column < totalColumns; column++){
			for(int row = 0; row < totalRows; row++){
				Letter letter = wordTable.get(column).get(row);
				
				if(!letter.getName().equals("-")){
					if(letter.getCanLink()){
						possibleLinkLetters.add(letter);
					}
				}
			}
		}
		
		/*
		//
		System.out.print("Link: ");
		for(Letter tempPossibleLinkLetter : possibleLinkLetters){
			System.out.print(tempPossibleLinkLetter.getName()+" ");
		}
		System.out.println();
		*/
		
		//
		possibleLinkLetters.shuffle();
		
		return possibleLinkLetters;
	}
	
	public boolean checkLetterIsInTable(int column, int row){
		return column >= 0 && column <= totalColumns-1 && row >= 0 && row <= totalRows-1;
	}
	
	public Array<Array<Letter>> duplicateWordTable(Array<Array<Letter>> originalWordTable){
		Array<Array<Letter>> copyWordTable = new Array<Array<Letter>>();
		
		for(int column = 0; column < originalWordTable.size; column++){
			copyWordTable.add(new Array<Letter>());
			
			for(int row = 0; row < originalWordTable.get(column).size; row++){
				Letter originalLetter = originalWordTable.get(column).get(row);
				
				Letter copyLetter = originalLetter.getCopy();
				
				copyWordTable.get(column).add(copyLetter);
				
			}
		}
		
		return copyWordTable;
	}

	public void traceCrosswords(){
		for(int row = 0; row < totalRows; row++){
			for(int column = 0; column < totalColumns; column++){
				//System.out.print(wordTable.get(column).get(row).getGroupsString());
				System.out.print(wordTable.get(column).get(row).getString());
				//System.out.print(wordTable.get(column).get(row).getAxisString());
				System.out.print(" , ");
			}
			System.out.println("");
		}
		
	}
	
	private Array<Array<Letter>> getWordTable(){
		return wordTable;
	}
	
	public Array<Array<Letter>> getCopyWordTable(){
		return duplicateWordTable(wordTable);
	}
	
	public int getTotalColumns(){
		return totalColumns;
	}
	
	public int getTotalRows(){
		return totalRows;
	}
	
	public Letter getNearLetter(Letter letter, String nearSide, Array<Array<Letter>> receivedWordTable){
		//
		int nearLetterColumn = 0;
		int nearLetterRow = 0;
		
		if(nearSide == NEAR_LETTER_LEFT_SIDE){
			nearLetterColumn = letter.getColumn() - 1;
			nearLetterRow = letter.getRow();
		}
		else if(nearSide == NEAR_LETTER_RIGHT_SIDE){
			nearLetterColumn = letter.getColumn() + 1;
			nearLetterRow = letter.getRow();
		}
		else if(nearSide == NEAR_LETTER_UP_SIDE){
			nearLetterColumn = letter.getColumn();
			nearLetterRow = letter.getRow() - 1;
		}
		else if(nearSide == NEAR_LETTER_DOWN_SIDE){
			nearLetterColumn = letter.getColumn();
			nearLetterRow = letter.getRow() + 1;
		}
		
		//
		if(nearLetterColumn >= 0 && nearLetterColumn <= totalColumns-1 && nearLetterRow  >= 0 && nearLetterRow <= totalRows - 1){
			return receivedWordTable.get(nearLetterColumn).get(nearLetterRow);
		}else{
			return null;
		}
		
	}
	
	public Array<Word> getWords(){
		return words;
	}
	
	public void createShowGlossariesWords(int totalGlossaries){
		if(gameType == REAL_GAME_TYPE){
			//
			showGlossariesWords = new Array<Word>();
			showGlossariesWords.addAll(words);
			
			//
			int remainingWordsTotal = totalGlossaries - showGlossariesWords.size;
			/*
			System.out.println("totalGlossaries:"+totalGlossaries);
			System.out.println("words.size:"+words.size);
			System.out.println("showGlossariesWords.size:"+showGlossariesWords.size);
			System.out.println("remainingWordsTotal:"+remainingWordsTotal);*/
			
			for(int i = 0; i < remainingWordsTotal; i++){
				Word tempWord = glossaryWords.get(i);
				showGlossariesWords.add(tempWord);
			}
		
			//	
			showGlossariesWords.shuffle();
		}else{
			
			showGlossariesWords = glossaryWords;
		}
		
	
		
	}
	
	public Array<Word> getShowGlossariesWords(){
		return showGlossariesWords;
		
	}
	
	private int getTotalLetters(){
		int totalLetters = 0;
		
		for(int column = 0; column < totalColumns; column++){
			for(int row = 0; row < totalRows; row++){
				Letter tempLetter = wordTable.get(column).get(row);
				
				if(!tempLetter.getName().equals("-")){
					totalLetters++;
				}
			}
		}
		
		return totalLetters;
	}
	
	
	
	///////////////// reveal exist letter /////////////////////////////////////////
	
	public void revealExistLetter(int totalExistLetter){
		
		if(gameType == REAL_GAME_TYPE){
			Array<Array<Letter>> lettersGroup = getLettersGroupFromWordTable();
			
			/*
			//trace
			for(int i = 0 ; i < lettersGroup.size; i++){
				for(int j = 0; j < lettersGroup.get(i).size; j++){
					System.out.print(lettersGroup.get(i).get(j).getName());
				}
				System.out.println();
			}
			System.out.println(this.getTotalLetters());
			 */
			
			int totalLetters = getTotalLetters();
			
			//set exist letters
			int total = 0;
			if(totalExistLetter == 1){
				total = (int) (totalLetters * (20 / 100.0));
			}else if(totalExistLetter == 2){
				total = (int) (totalLetters * (30 / 100.0));
			}else if(totalExistLetter == 3){
				total = (int) (totalLetters * (40 / 100.0));
			}
			
			if(total == 0){
				return;
			}
			//System.out.println(total);
			
			while(true){
				lettersGroup.shuffle();
				
				for(Array<Letter> tempLetters : lettersGroup){
					tempLetters.shuffle();
					
					for(Letter tempLetter : tempLetters){
						if(tempLetter.getHasRevealed()){
							continue;
						}else{	
							tempLetter.reveal();
							if(checkSomeLettersGroupHasComplete()){
								tempLetter.unreveal();
								break;
							}
							
							total--;
							if(total == 0){
								return;
							}else{
								break;
							}
						}
					}
					
				}
			}
		}
		else if(gameType == TUTORIAL_GAME_TYPE){
			Array<Array<Letter>> lettersGroup = getLettersGroupFromWordTable();
			
			lettersGroup.get(0).get(1).reveal();
			lettersGroup.get(0).get(3).reveal();
			
			lettersGroup.get(1).get(0).reveal();
		}
		
	}
	
	public Array<Array<Letter>> getLettersGroupFromWordTable(){
		Array<Array<Letter>> lettersGroup = new Array<Array<Letter>>();
		
		int currentGroup = 1;
		
		for(int i = 0; i < totalWords; i++){
			Array<Letter> innerList = new Array<Letter>();
			
			for(int column = 0; column < totalColumns; column++){
				for(int row = 0; row < totalRows; row++){
					Letter tempLetter = wordTable.get(column).get(row);
					
					if(tempLetter.getGroups().contains(currentGroup , true)){
						
						innerList.add(tempLetter);
					}
					
					
				}
			}
			
			lettersGroup.add(innerList);
			currentGroup++;
			
		}
		
		return lettersGroup;
	}
	
	public boolean checkSomeLettersGroupHasComplete(){
		boolean someLettersGroupHasCompleted = false;
		

		Array<Array<Letter>> lettersGroup = getLettersGroupFromWordTable();
		
		
		for(Array<Letter> tempGroupLetters : lettersGroup){
			boolean complete = true;
			for(Letter tempLetter : tempGroupLetters){
				if(!tempLetter.getHasRevealed()){
					complete = false;
					break;
				}
			}
			
			if(complete == true){
				someLettersGroupHasCompleted = true;
				break;
			}
		}
		
		return someLettersGroupHasCompleted;
	}
	
}
