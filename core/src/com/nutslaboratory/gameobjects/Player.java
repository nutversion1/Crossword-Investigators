package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.utils.Array;

public class Player {
	public static final String NONE_PLAYER_TYPE = "none_player_type";
	public static final String HUMAN_PLAYER_TYPE = "human_player_type";
	public static final String COMPUTER_PLAYER_TYPE = "computer_player_type";
	
	public static final String HUMAN_1 = "human_1";
	public static final String HUMAN_2 = "human_2";
	public static final String HUMAN_3 = "human_3";
	public static final String HUMAN_4 = "human_4";
	public static final String ROBOT_1 = "robot_1";
	public static final String ROBOT_2 = "robot_2";
	public static final String ROBOT_3 = "robot_3";
	public static final String ROBOT_4 = "robot_4";
	
	private static int TOTAL_TIME;
	private static int TOTAL_HINT_POINTS;
	private static int TOTAL_WRONG_GUESS;
	
	private int timeLeft;
	private int hintPointsLeft;
	private int wrongGuess;
	private int completeWordTotal;
	private int completeBlockTotal;
	
	private GameManager gameManager;
	
	private int playerNum;
	
	Crosswords crosswords;
	
	private Array<Array<Letter>> wordTable;
	
	private Array<BlockData> blockDatas;
	
	private boolean hasFinished = false;
	
	private int rank;
	
	private Array<WordData> wordDatas = new Array<WordData>();
	
	private String playerType;
	private String playerName;
	
	public Player(int playerNum, GameManager gameManager, String playerType, String playerName){
		this.playerNum = playerNum;
		this.playerType = playerType;
		this.playerName = playerName;
		this.gameManager = gameManager;
		this.crosswords = gameManager.getCrosswords();
		this.wordTable = crosswords.getCopyWordTable();
		
		TOTAL_TIME = gameManager.getTotalTime();
		TOTAL_HINT_POINTS = gameManager.getTotalHint();
		TOTAL_WRONG_GUESS = gameManager.getTotalWrongGuess();
		
		timeLeft = TOTAL_TIME;
		hintPointsLeft = TOTAL_HINT_POINTS;
		wrongGuess = 0;
		completeWordTotal = 0;
		completeBlockTotal = 0;
		
		//
		createBlockDatas();
		
		//
		for(Word tempWord : crosswords.getWords()){
			WordData newWordData = new WordData(tempWord);
			wordDatas.add(newWordData);
		}
		
		//System.out.println("Player:"+playerNum +" - "+ playerType);
		
	}
	
	public void reset(){
		//System.out.println("reset");
		timeLeft = TOTAL_TIME;
		hintPointsLeft = TOTAL_HINT_POINTS;
		wrongGuess = 0;
	}
	
	private void createBlockDatas() {
		blockDatas = new Array<BlockData>();
		
		for(int column = 0; column < crosswords.getTotalColumns(); column++){
			for(int row = 0; row < crosswords.getTotalRows(); row++){
				
				if(wordTable.get(column).get(row).getName() != "-" && wordTable.get(column).get(row).getName() != "#"){
					
					Letter newLetter = wordTable.get(column).get(row);
					
					BlockData newBlockData = new BlockData(newLetter);
					
					blockDatas.add(newBlockData);
				}
			}
		}
		
	}

	public int getPlayerNum(){
		return playerNum;
	}
	
	public String getPlayerType(){
		return playerType;
	}
	
	public Crosswords getCrosswords(){
		return crosswords;
	}
	
	public Array<BlockData> getBlockDatas(){
		return blockDatas;
	}
	
	public int getTimeLeft(){
		return timeLeft;
	}
	
	public int getHintPointsLeft(){
		return hintPointsLeft;
	}
	
	public int getWrongGuess(){
		return wrongGuess;
	}
	
	public void setCompleteWord(int total){
		completeWordTotal = total;
	}
	
	public int getCompleteWordTotal(){
		return completeWordTotal;
	}
	
	public int getCompleteBlockTotal(){
		return completeBlockTotal;
	}
	
	public void decreaseTime(int total){
		timeLeft -= total;
		
		if(timeLeft <= 0){
			timeLeft = 0;
		}
	}
	
	public void wrongGuess(){
		wrongGuess++;
	}
	
	public void useHint(int total){
		hintPointsLeft -= total;
	}
	
	public void completeBlock(){
		completeBlockTotal++;
	}
	
	public void finish(int rank){
		hasFinished = true;
		
		setRank(rank);
		//System.out.println("player rank "+rank);
		gameManager.somePlayersHasFinishThisRound();
	}
	
	public boolean hasFinished(){
		return hasFinished;
	}
	
	private void setRank(int rank){
		this.rank = rank;
	}
	
	public int getRank(){
		return rank;
	}
	
	public Array<WordData> getWordDatas(){
		return wordDatas;
	}
	
	public float getProgress(){
		return ((float)getCompleteBlockTotal() / (float)this.blockDatas.size) * 100;
		
	}
	
	public String getPlayerName(){
		return playerName;
	}
}
