package com.nutslaboratory.screens;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.cwi.CWIGame;
import com.nutslaboratory.gameobjects.BackMenu;
import com.nutslaboratory.gameobjects.BlockData;
import com.nutslaboratory.gameobjects.GameManager;
import com.nutslaboratory.gameobjects.Player;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutAnimationSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutGame;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutSpriteLayer;

public class ComputerPlayScreen extends NutScreen{
	
	protected static final float DECREASE_TIME_TOTAL = 1f;
	
	protected float timer = DECREASE_TIME_TOTAL;
	protected int computerPlayingTimeLeft = 5;
	protected GameManager gameManager;
	
	private Array<Array<BlockData>> groupBlockObjectsList;
	
	private Random random = new Random();
	
	private NutAnimationSprite robot;
	
	private NutSpriteLayer spriteLayer1 = new NutSpriteLayer(this, "layer1");
	
	protected BackMenu backMenu;
	
	public enum State{
		MAIN_STATE, BACK_MENU_STATE;
	}
	
	protected State oldState;
	protected State state;
	
	public ComputerPlayScreen(NutGame game, GameManager gameManager) {
		super(game);
		
		this.gameManager = gameManager;
		
	}

	@Override
	public void show() {
		addSpriteLayer(spriteLayer1);
		
		createBackground();
		createRobot();
		createGroupBlockObjectsList();
		
		computerProcess();
		
		updateCompleteWordTotal();
		checkForFinish();
		
		//
		setState(State.MAIN_STATE);
		
		//
		AssetLoader.backgroundMusic.pause();
	}
	
	private void createRobot() {
		robot = new NutAnimationSprite(this, Animation.PlayMode.LOOP, 1f, 
				AssetLoader.robotThinkATexture, AssetLoader.robotThinkBTexture);
		spriteLayer1.addSprite(robot);
		robot.setPosition(70, 160);
		robot.start();
		
		AssetLoader.robotSound.loop(CWIGame.getSoundVolume());
	}

	protected void computerProcess() {
		//
		int canGuessPercentage = 100;
		if(gameManager.getRoundNum() % 4 == 1){
			canGuessPercentage = 20;
		}else if(gameManager.getRoundNum() % 4 == 2){
			canGuessPercentage = 40;
		}else if(gameManager.getRoundNum() % 4 == 3){
			canGuessPercentage = 80;
		}else if(gameManager.getRoundNum() % 4 == 0){
			canGuessPercentage = 100;
		}
		
		//
		int randomNum = random.nextInt(100)+1;
		if(randomNum < canGuessPercentage){
			guessOneCompleteWord();
		}
		
		
		
	}

	
	
	private int getCurrentGroup(){
		int currentGroup = 1;
		
		outerLoop: for(int i = 0 ; i < groupBlockObjectsList.size; i++){
			for(int j = 0; j < groupBlockObjectsList.get(i).size; j++){
			
				if(!groupBlockObjectsList.get(i).get(j).getLetter().getHasRevealed()){
					break outerLoop;
				}
			}
			
			currentGroup++;
		}	
		
		return currentGroup;
	}
	
	private void createGroupBlockObjectsList() {
		groupBlockObjectsList = new Array<Array<BlockData>>();
		
		int currentGroup = 1;
		
		for(int i = 0; i < gameManager.getTotalWords(); i++){
			Array<BlockData> innerList = new Array<BlockData>();
			
			for(BlockData tempBlockObject : gameManager.getCurrentTurnPlayer().getBlockDatas()){
				if(tempBlockObject.getLetter().getGroups().contains(currentGroup , true)){
					
					innerList.add(tempBlockObject);
				}
			}
			
			groupBlockObjectsList.add(innerList);
			currentGroup++;
		}
		
	}
	
	protected void endTurn(){
		AssetLoader.robotSound.stop();
		
		gameManager.nextTurn();
		
		getGame().setScreen(new PunctuateScreen(getGame(), gameManager));
	}
	
	private void updateCompleteWordTotal(){
		int completeWordTotal = 0;
		
		for(int i = 0; i < groupBlockObjectsList.size; i++){
			int currentGroup = i+1;
			
			boolean hasCompleteWord = true;
			
			for(int j = 0; j < groupBlockObjectsList.get(i).size; j++){
				if(!groupBlockObjectsList.get(i).get(j).getLetter().getHasRevealed()){
					hasCompleteWord = false;
					break;
				}
			}
			
			if(hasCompleteWord){
				completeWordTotal++;
				
			}
			
		}
		
		gameManager.getCurrentTurnPlayer().setCompleteWord(completeWordTotal);
	}
	
	private void checkForFinish() {
		if(gameManager.getCurrentTurnPlayer().getCompleteWordTotal() == gameManager.getTotalWords()){
			gameManager.getCurrentTurnPlayer().finish(gameManager.getCurrentRank());
		}
		
	}
	
	protected void guessOneCompleteWord() {
		int currentGroup = getCurrentGroup();
		for(int i = 0; i < groupBlockObjectsList.get(currentGroup-1).size; i++){
			if(!groupBlockObjectsList.get(currentGroup-1).get(i).getLetter().getHasRevealed()){
				groupBlockObjectsList.get(currentGroup-1).get(i).getLetter().reveal();
				gameManager.getCurrentTurnPlayer().completeBlock();
			}
		}
		
	}

	private void createBackground() {
		TextureRegion backgroundTexture = null;
		
		if(gameManager.getCurrentTurnPlayer().getPlayerName().equals(Player.ROBOT_1)){
			backgroundTexture = AssetLoader.computer1BackgroundTexture;
		}else if(gameManager.getCurrentTurnPlayer().getPlayerName().equals(Player.ROBOT_2)){
			backgroundTexture = AssetLoader.computer2BackgroundTexture;
		}else if(gameManager.getCurrentTurnPlayer().getPlayerName().equals(Player.ROBOT_3)){
			backgroundTexture = AssetLoader.computer3BackgroundTexture;
		}else if(gameManager.getCurrentTurnPlayer().getPlayerName().equals(Player.ROBOT_4)){
			backgroundTexture = AssetLoader.computer4BackgroundTexture;
		}
		
		NutSprite bg = new NutSprite(this, backgroundTexture);
		bg.setPosition(0, 0);
		spriteLayer1.addSprite(bg);
		
	}
	
	@Override
	public void update(float delta){
		
		timer -= delta;
		if(timer <= 0){
			timer = DECREASE_TIME_TOTAL;
			
			switch(state){
			case BACK_MENU_STATE:
				break;
			case MAIN_STATE:
				computerPlayingTimeLeft -= 1;
				if(computerPlayingTimeLeft == 0){
					
					endTurn();
				}
				break;
			default:
				break;
			
			}
			
			
		}
	}
	
	public State getState(){
		return state;
	}
	
	public State getOldState(){
		return oldState;
	}
	
	public void setState(State state){
		//System.out.println(state);	
		
		oldState = this.state;
		
		this.state = state;
	}

}
