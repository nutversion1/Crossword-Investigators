package com.nutslaboratory.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.gameobjects.BackMenu;
import com.nutslaboratory.gameobjects.GameManager;
import com.nutslaboratory.gameobjects.Letter;
import com.nutslaboratory.gameobjects.Player;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutButton;
import com.nutslaboratory.nutlibgdxgameengine.NutGame;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutSpriteLayer;
import com.nutslaboratory.nutlibgdxgameengine.NutText;

public class PunctuateScreen extends NutScreen {
	
	private final int TOTAL_WORDS;
	private final int TOTAL_PLAYERS;
	
	private Array<Array<Letter>> wordTable;
	
	protected NutButton startButton;
	
	private Array<Player> players;
	
	private Player currentTurnPlayer;
	
	protected GameManager gameManager;
	
	private NutSprite background;
	
	private NutText roundText;
	private Array<NutSprite> playerLabels;
	private NutSprite currentTurnPlayerMarker;
	private Array<NutText> completeWordTotalTexts;
	private Array<NutSprite> rankIcons = new Array<NutSprite>();
	
	
	protected BackMenu backMenu;
	
	protected NutSpriteLayer spriteLayer1 = new NutSpriteLayer(this, "layer1");
	protected NutSpriteLayer spriteLayer2 = new NutSpriteLayer(this, "layer2");
	
	public enum State{
		MAIN_STATE, BACK_MENU_STATE;
	}
	
	protected State oldState;
	protected State state;
	
	public PunctuateScreen(NutGame game, GameManager gameManager) {
		super(game);
		
		this.gameManager = gameManager;
		this.currentTurnPlayer = gameManager.getCurrentTurnPlayer();
		this.players = gameManager.getPlayers();
		
		TOTAL_WORDS = gameManager.getTotalWords();
		TOTAL_PLAYERS = gameManager.getTotalPlayers();
	
	}

	@Override
	public void show() {
		//
		addSpriteLayer(spriteLayer1);
		addSpriteLayer(spriteLayer2);
		
		//
		//System.out.print("Round : "+gameManager.getRoundNum());
		//System.out.println(" , Turn : "+gameManager.getTurnNum());
		
		createBackground();
		
		createRoundText();
		createPlayerLabels();
		createCompleteWordTotalTexts();
		
		if(!gameManager.hasGameOver()){
			createCurrentTurnMarker();
			createStartButton();
		}else{
			
			
			for(NutSprite tempPlayerLabel : playerLabels){
				tempPlayerLabel.setX(tempPlayerLabel.getX()-60);
			}
			
			for(NutText tempText : completeWordTotalTexts){
				tempText.setX(tempText.getX()-60);
			}
			
			for(NutSprite tempRankIcon : rankIcons){
				tempRankIcon.setX(120-20);
			}
			
			
			
		}
		
		//
		setState(State.MAIN_STATE);
		
		//
		if(!AssetLoader.backgroundMusic.isPlaying()){
			AssetLoader.playMusic.stop();
			AssetLoader.backgroundMusic.play();
		}
		
		
		
		
	}
	
	

	private void createBackground() {
		background = new NutSprite(this, AssetLoader.punctuateBackgroundTexture);
		spriteLayer1.addSprite(background);
		
	}
	
	private void createRoundText() {
		String text = "";
		if(!gameManager.hasGameOver()){
			text = "Round "+gameManager.getRoundNum();
		}else{
			text = "  Result";
		}
		
		roundText = new NutText(this, AssetLoader.charlemagne32Font, text);
		roundText.setPosition(getWorldWidth()/2 - roundText.getWidth()/2, 560);
		roundText.setColor(Color.WHITE);
		spriteLayer1.addSprite(roundText);
		
	}
	
	private void createPlayerLabels() {
		playerLabels = new Array<NutSprite>();
		
		for(int i = 0; i < TOTAL_PLAYERS; i++){
			//create player label
			Player tempPlayer = players.get(i);
			
			
			NutSprite newPlayerLabel = null;
			if(tempPlayer.getPlayerName().equals(Player.HUMAN_1)){
				newPlayerLabel = new NutSprite(this, AssetLoader.player1LabelTexture);
			}else if(tempPlayer.getPlayerName().equals(Player.HUMAN_2)){
				newPlayerLabel = new NutSprite(this, AssetLoader.player2LabelTexture);
			}else if(tempPlayer.getPlayerName().equals(Player.HUMAN_3)){
				newPlayerLabel = new NutSprite(this, AssetLoader.player3LabelTexture);
			}else if(tempPlayer.getPlayerName().equals(Player.HUMAN_4)){
				newPlayerLabel = new NutSprite(this, AssetLoader.player4LabelTexture);
			}else if(tempPlayer.getPlayerName().equals(Player.ROBOT_1)){
				newPlayerLabel = new NutSprite(this, AssetLoader.robot1LabelTexture);
			}else if(tempPlayer.getPlayerName().equals(Player.ROBOT_2)){
				newPlayerLabel = new NutSprite(this, AssetLoader.robot2LabelTexture);
			}else if(tempPlayer.getPlayerName().equals(Player.ROBOT_3)){
				newPlayerLabel = new NutSprite(this, AssetLoader.robot3LabelTexture);
			}else if(tempPlayer.getPlayerName().equals(Player.ROBOT_4)){
				newPlayerLabel = new NutSprite(this, AssetLoader.robot4LabelTexture);
			}
			
			newPlayerLabel.setTag(tempPlayer.getPlayerName());
			newPlayerLabel.setPosition(120, 450-(i*70));
			spriteLayer1.addSprite(newPlayerLabel);
			playerLabels.add(newPlayerLabel);
			
			//create rank icon
			createRankIcon(tempPlayer);
			
			
		}
		
	}
	
	private void createRankIcon(Player player) {
		//finish
		if(player.hasFinished()){
			NutSprite currentPlayerLabel = getPlayerLabel(player);
			
			
			currentPlayerLabel.setX(currentPlayerLabel.getX()+60);
			
			
			TextureRegion texture = null;
			if(player.getRank() == 1){
				texture = AssetLoader.winFirstIconTexture;
			}else if(player.getRank() == 2){
				texture = AssetLoader.winSecondIconTexture;
			}else if(player.getRank() == 3){
				texture = AssetLoader.winThirdIconTexture;
			}
			
			NutSprite rankIcon = new NutSprite(this, texture);
			rankIcon.setPosition(currentPlayerLabel.getX()-20, currentPlayerLabel.getY());
			spriteLayer1.addSprite(rankIcon);
			rankIcons.add(rankIcon);

		}
		
		//unfinish
		if(gameManager.hasGameOver() && !player.hasFinished()){
			NutSprite currentPlayerLabel = getPlayerLabel(player);
			
			currentPlayerLabel.setX(currentPlayerLabel.getX()+60);
			
			TextureRegion texture = AssetLoader.winFourthIconTexture;
			
			NutSprite rankIcon = new NutSprite(this, texture);
			rankIcon.setPosition(currentPlayerLabel.getX()-20, currentPlayerLabel.getY());
			spriteLayer1.addSprite(rankIcon);
			rankIcons.add(rankIcon);
		}
		
	}

	private void createCompleteWordTotalTexts() {
		completeWordTotalTexts = new Array<NutText>();
		
		for(int i = 0; i < TOTAL_PLAYERS; i++){
			Player tempPlayer = players.get(i);
			NutSprite tempPlayerLabel = getPlayerLabel(tempPlayer);
			
			NutText newCompleteWordTotalText = new NutText(this, AssetLoader.charlemagne24Font,
					tempPlayer.getCompleteWordTotal()+"/"+TOTAL_WORDS);
			newCompleteWordTotalText.setColor(Color.BLACK);
			newCompleteWordTotalText.setPosition(tempPlayerLabel.getX()+200,
					tempPlayerLabel.getY()+20);
			spriteLayer1.addSprite(newCompleteWordTotalText);
			completeWordTotalTexts.add(newCompleteWordTotalText);
			
			if(tempPlayer.hasFinished()){
				newCompleteWordTotalText.setColor(Color.FOREST);
				
			}
		}
		
	}
	
	private void createCurrentTurnMarker() {
		
		NutSprite currentPlayerLabel = getPlayerLabel(currentTurnPlayer);
		
		currentTurnPlayerMarker = new NutSprite(this, AssetLoader.currentTurnPlayerMarkerTexture);
		currentTurnPlayerMarker.setPosition(currentPlayerLabel.getX()-45, currentPlayerLabel.getY()+15);
		spriteLayer1.addSprite(currentTurnPlayerMarker);
		
	}
	
	private NutSprite getPlayerLabel(Player player){
		NutSprite playerLabel = null;
		
		for(NutSprite tempPlayerLabel : playerLabels){
			if(tempPlayerLabel.getTag().equals(player.getPlayerName())){
				playerLabel = tempPlayerLabel;
				break;
			}
		}
		
		return playerLabel;
	}
	
	protected void createStartButton() {
		startButton = new NutButton(this, AssetLoader.startButtonTexture, null, AssetLoader.startButtonTexture);
		startButton.setTouchSound(AssetLoader.buttonSound);
		startButton.setPosition(getWorldWidth()/2-startButton.getWidth()/2, 100);
		spriteLayer1.addSprite(startButton);
		
		
	}

	@Override
	public void draw(SpriteBatch spriteBatch){
		
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
	
	public void createBackMenu(){
		//
		backMenu = new BackMenu(this, AssetLoader.charlemagne24Font, "Back to Main Menu ?");
		backMenu.setPosition(getWorldWidth()/2 - backMenu.getWidth()/2,
				getWorldHeight()/2 - backMenu.getHeight()/2);
		spriteLayer2.addSprite(backMenu);
		
		//
		spriteLayer2.setInfluence(true);
		spriteLayer2.setOverlay(true);
		
	}
	
	public void removeBackMenu(){
		spriteLayer2.removeSprite(backMenu);
		
		//
		spriteLayer2.setInfluence(false);
		spriteLayer2.setOverlay(false);
	}
	
	
	
	@Override
	public void update(float delta){
		super.update(delta);
		
	}
	
	@Override
	public void doEvents(){
		switch(state){
		case MAIN_STATE:
			if(!gameManager.hasGameOver()){
				if(startButton.isClicked()){
					if(!gameManager.hasGameOver()){
						if(gameManager.getCurrentTurnPlayer().getPlayerType().equals(Player.HUMAN_PLAYER_TYPE)){
							getGame().setScreen(new PlayScreen(getGame(), gameManager));
						}else{
							getGame().setScreen(new ComputerPlayScreen(getGame(), gameManager));
						}
					}
					
				}
			}
			
			break;
		case BACK_MENU_STATE:
			if(backMenu.getYesButton().isClicked()){
				getGame().setScreen(new MainMenuScreen(getGame()));
				
				
			}
			else if(backMenu.getNoButton().isClicked()){
				removeBackMenu();
				setState(State.MAIN_STATE);
				
				
			}
			
			break;
		default:
			break;
		}
	}
	
	@Override
	public void pressBack() {
		switch(state){
		case MAIN_STATE:
			createBackMenu();
			setState(State.BACK_MENU_STATE);
			break;
		case BACK_MENU_STATE:
			removeBackMenu();
			setState(State.MAIN_STATE);
			break;
		}
	}
}
