package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.utils.Array;

public class GameManager {
	
	public final static int REAL_GAME_TYPE = 0;
	public final static int TUTORIAL_GAME_TYPE = 1;
	private int gameType;
	
	private static final int BLOCK_COLUMNS = 8;
	private static final int BLOCK_ROWS = 9;
	
	private final int TOTAL_WORDS;
	private final int TOTAL_GLOSSARIES;
	private final int TOTAL_TIME;
	private final int TOTAL_HINT;
	private final int TOTAL_WRONG_GUESS;
	private final int TOTAL_EXIST_LETTER;
	private final int TOTAL_PLAYERS;
	
	private Array<Player> players = new  Array<Player>();
	
	private TutorialMessage tutorialMessage;
	
	private Player currentTurnPlayer;
	
	private int turnNum = 1;
	private int roundNum = 1;
	
	private boolean gameIsOver = false;
	
	private int currentRank = 1;
	
	private Crosswords crosswords;
	
	private boolean somePlayersHasFinishThisRound = false;
	
	public GameManager(int gameType, int totalWords, int totalGlossaries, int totalTime, 
			int totalHint, int totalWrongGuess, int totalExistLetter,Array<String> playerTypes,
			TutorialMessage tutorialMessage){
		//
		this.gameType = gameType;
		
		TOTAL_WORDS = totalWords;
		TOTAL_GLOSSARIES = totalGlossaries;
		TOTAL_TIME = totalTime;
		TOTAL_HINT = totalHint;
		TOTAL_WRONG_GUESS = totalWrongGuess;
		TOTAL_EXIST_LETTER = totalExistLetter;
		
		//
		this.tutorialMessage = tutorialMessage;
		
		//
		Glossary glossary = new Glossary(gameType);
		
		//
		crosswords = new Crosswords(gameType, glossary.getWords(), 
				TOTAL_WORDS, BLOCK_COLUMNS, BLOCK_ROWS);
		
		
		//reveal exist letters
		if(TOTAL_EXIST_LETTER != 0){
			crosswords.revealExistLetter(TOTAL_EXIST_LETTER);
		}
		
		
		//
		crosswords.createShowGlossariesWords(TOTAL_GLOSSARIES);
		
		//
		for(int i = 0; i < playerTypes.size; i++){
			if(playerTypes.get(i).equals(Player.NONE_PLAYER_TYPE)){
				continue;
			}
			
			int playerNum = i+1;
			
			String playerName = "";
			if(playerTypes.get(i).equals(Player.HUMAN_PLAYER_TYPE)){
				if(playerNum == 1){
					playerName = Player.HUMAN_1;
				}else if(playerNum == 2){
					playerName = Player.HUMAN_2;
				}else if(playerNum == 3){
					playerName = Player.HUMAN_3;
				}else if(playerNum == 4){
					playerName = Player.HUMAN_4;
				}
			}else if(playerTypes.get(i).equals(Player.COMPUTER_PLAYER_TYPE)){
				if(playerNum == 1){
					playerName = Player.ROBOT_1;
				}else if(playerNum == 2){
					playerName = Player.ROBOT_2;
				}else if(playerNum == 3){
					playerName = Player.ROBOT_3;
				}else if(playerNum == 4){
					playerName = Player.ROBOT_4;
				}
			}
			
			Player player = new Player(playerNum, this, playerTypes.get(i), playerName); 
			players.add(player);
		}
		
		
		
		//get total players
		TOTAL_PLAYERS = players.size;
		
		//random player's queue
		if(gameType == REAL_GAME_TYPE){
			players.shuffle();
		}
		
		//get first player to play
		currentTurnPlayer = players.first();
		

	}
	
	public Crosswords getCrosswords(){
		return crosswords;
	}
	
	public int getTotalWords(){
		return TOTAL_WORDS;
	}
	
	public int getTotalPlayers(){
		return TOTAL_PLAYERS;
	}
	
	public int getTotalGlossaries(){
		return TOTAL_GLOSSARIES;
	}
	
	public  Array<Player> getPlayers(){
		return players;
	}
	
	public Player getCurrentTurnPlayer(){
		return currentTurnPlayer;
	}
	
	public void nextTurn(){
		//
		do{
			turnNum++;
			
			if(turnNum > players.size){
				turnNum = 1;
				
				if(checkGameIsOver()){
					gameOver();
					return;
				}else{
					nextRound();
					checkForIncreaseCurrentRank();
					
				}
				
			}
			
			setCurrentTurnPlayer(turnNum-1);
			
		}while (currentTurnPlayer.hasFinished());
		
		//
		currentTurnPlayer.reset();
	}
	
	private void checkForIncreaseCurrentRank() {
		if(somePlayersHasFinishThisRound){
			increaseCurrentRank();
		}
		
		somePlayersHasFinishThisRound = false;
		
	}

	private void setCurrentTurnPlayer(int index) {
		currentTurnPlayer = players.get(index);
	}


	public int getTurnNum(){
		return turnNum;
	}
	
	public void nextRound(){
		roundNum++;
	}
	
	private boolean checkGameIsOver() {
		int hasntFinishedPlayersLeft = TOTAL_PLAYERS;
		
		for(Player tempPlayer : players){
			if(tempPlayer.hasFinished()){
				hasntFinishedPlayersLeft--;
			}
		}
		
		if(hasntFinishedPlayersLeft == 1 || hasntFinishedPlayersLeft == 0){
			return true;
		}else{
			return false;
		}
		
	}


	private void gameOver() {
		gameIsOver = true;
		
	}


	public int getRoundNum(){
		return roundNum;
	}
	
	public boolean hasGameOver(){
		return gameIsOver;
	}
	
	private void increaseCurrentRank(){
		currentRank++;
	}
	
	public int getCurrentRank(){
		return currentRank;
	}
	
	public void somePlayersHasFinishThisRound(){
		somePlayersHasFinishThisRound = true;
	}
	
	public int getTotalTime(){
		return TOTAL_TIME;
	}
	
	public int getTotalHint(){
		return TOTAL_HINT;
	}
	
	public int getTotalWrongGuess(){
		return TOTAL_WRONG_GUESS;
	}
	
	public int getTotalExistLetter(){
		return TOTAL_EXIST_LETTER;
	}
	
	public TutorialMessage getTutorialMessage(){
		return tutorialMessage;
	}
	
}
