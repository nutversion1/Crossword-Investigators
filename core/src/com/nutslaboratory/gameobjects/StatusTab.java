package com.nutslaboratory.gameobjects;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.helpers.SpriteAccessor;
import com.nutslaboratory.nutlibgdxgameengine.NutButton;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutText;

public class StatusTab extends NutSprite{
	
	private GameManager gameManager;
	private NutText roundText;
	private Array<NutSprite> playerLabels;
	private NutSprite currentTurnPlayerMarker;
	private Array<NutText> completeWordTotalTexts;
	private NutButton endTurnButton;
	private boolean isAvailable;
	
	private TweenManager tweenManager;
	private TweenCallback moveInCallBack, moveOutCallBack;
	
	private NutButton closeButton;
	
	private boolean hasMoveOut = false;
	
	public StatusTab(NutScreen screen, GameManager gameManager) {
		super(screen, AssetLoader.statusTabTexture);
		
		this.gameManager = gameManager;
		
		
		
		createRoundText();
		createPlayerLabels();
		createCurrentTurnMarker();
		createCompleteWordTotalTexts();
		createEndTurnButton();
		createCloseButton();
		
		setupTween();
		
		moveIn();
	}
	
	private void createRoundText() {
		roundText = new NutText(getScreen(), AssetLoader.charlemagne32Font, "Round "+gameManager.getRoundNum());
		roundText.setPosition(getWidth()/2-roundText.getWidth()/2, 445);
		roundText.setColor(Color.WHITE);
		addChild(roundText);
	}
	
	private void createPlayerLabels() {
		playerLabels = new Array<NutSprite>();
		
		for(int i = 0; i < gameManager.getTotalPlayers(); i++){
			//create player label
			Player tempPlayer = gameManager.getPlayers().get(i);
			
			
			NutSprite newPlayerLabel = null;
			if(tempPlayer.getPlayerName() == Player.HUMAN_1){
				newPlayerLabel = new NutSprite(getScreen(), AssetLoader.player1LabelTexture);
			}else if(tempPlayer.getPlayerName() == Player.HUMAN_2){
				newPlayerLabel = new NutSprite(getScreen(), AssetLoader.player2LabelTexture);
			}else if(tempPlayer.getPlayerName() == Player.HUMAN_3){
				newPlayerLabel = new NutSprite(getScreen(), AssetLoader.player3LabelTexture);
			}else if(tempPlayer.getPlayerName() == Player.HUMAN_4){
				newPlayerLabel = new NutSprite(getScreen(), AssetLoader.player4LabelTexture);
			}else if(tempPlayer.getPlayerName() == Player.ROBOT_1){
				newPlayerLabel = new NutSprite(getScreen(), AssetLoader.robot1LabelTexture);
			}else if(tempPlayer.getPlayerName() == Player.ROBOT_2){
				newPlayerLabel = new NutSprite(getScreen(), AssetLoader.robot2LabelTexture);
			}else if(tempPlayer.getPlayerName() == Player.ROBOT_3){
				newPlayerLabel = new NutSprite(getScreen(), AssetLoader.robot3LabelTexture);
			}else if(tempPlayer.getPlayerName() == Player.ROBOT_4){
				newPlayerLabel = new NutSprite(getScreen(), AssetLoader.robot4LabelTexture);
			}
			
			newPlayerLabel.setTag(tempPlayer.getPlayerName());
			newPlayerLabel.setPosition(70, 340-(i*70));
			addChild(newPlayerLabel);
			playerLabels.add(newPlayerLabel);
			
			//create rank icon
			createRankIcon(tempPlayer);
			
			
		}
		
	}
	
	private void createRankIcon(Player player) {
		//finish
		if(player.hasFinished()){
			NutSprite currentPlayerLabel = getPlayerLabel(player);
			
			
			currentPlayerLabel.setX(currentPlayerLabel.getX()+30);
			
			
			TextureRegion texture = null;
			if(player.getRank() == 1){
				texture = AssetLoader.winFirstIconTexture;
			}else if(player.getRank() == 2){
				texture = AssetLoader.winSecondIconTexture;
			}else if(player.getRank() == 3){
				texture = AssetLoader.winThirdIconTexture;
			}
			
			NutSprite rankIcon = new NutSprite(getScreen(), texture);
			rankIcon.setPosition(currentPlayerLabel.getX()-20, currentPlayerLabel.getY());
			addChild(rankIcon);

		}
		
		//unfinish
		if(gameManager.hasGameOver() && !player.hasFinished()){
			NutSprite currentPlayerLabel = getPlayerLabel(player);
			
			currentPlayerLabel.setX(currentPlayerLabel.getX()+60);
			
			TextureRegion texture = AssetLoader.winFourthIconTexture;
			
			NutSprite rankIcon = new NutSprite(getScreen(), texture);
			rankIcon.setPosition(currentPlayerLabel.getX()-20, currentPlayerLabel.getY());
			addChild(rankIcon);
		}
		
	}
	
	
	
	
	private void createCompleteWordTotalTexts() {
		completeWordTotalTexts = new Array<NutText>();
		
		for(int i = 0; i < gameManager.getTotalPlayers(); i++){
			Player tempPlayer = gameManager.getPlayers().get(i);
			NutSprite tempPlayerLabel = getPlayerLabel(tempPlayer);
			
			NutText newCompleteWordTotalText = new NutText(getScreen(), AssetLoader.charlemagne24Font,
					tempPlayer.getCompleteWordTotal()+"/"+gameManager.getTotalWords());
			newCompleteWordTotalText.setColor(Color.BLACK);
			newCompleteWordTotalText.setPosition(tempPlayerLabel.getX()+200,
					tempPlayerLabel.getY()+20);
			addChild(newCompleteWordTotalText);
			completeWordTotalTexts.add(newCompleteWordTotalText);
			
			if(tempPlayer.hasFinished()){
				newCompleteWordTotalText.setColor(Color.FOREST);
			}
		}
		
	}
	
	private void createCurrentTurnMarker() {
		
		NutSprite currentPlayerLabel = getPlayerLabel(gameManager.getCurrentTurnPlayer());
		
		currentTurnPlayerMarker = new NutSprite(getScreen(), AssetLoader.currentTurnPlayerMarkerTexture);
		currentTurnPlayerMarker.setPosition(currentPlayerLabel.getX()-45, currentPlayerLabel.getY()+15);
		addChild(currentTurnPlayerMarker);
		
	}
	
	private NutSprite getPlayerLabel(Player player){
		NutSprite playerLabel = null;
		
		for(NutSprite tempPlayerLabel : playerLabels){
			if(tempPlayerLabel.getTag() == player.getPlayerName()){
				playerLabel = tempPlayerLabel;
				break;
			}
		}
		
		return playerLabel;
	}
	
	private void createEndTurnButton() {
		endTurnButton = new NutButton(getScreen(), AssetLoader.endTurnButtonTexture, 
				null, AssetLoader.endTurnButtonTexture);
		endTurnButton.setTouchSound(AssetLoader.buttonSound);
		endTurnButton.setPosition(getWidth()/2 - endTurnButton.getWidth()/2, 50);
		addChild(endTurnButton);
		
	}
	
	public NutButton getEndTurnButton(){
		return endTurnButton;
	}
	
	public NutButton getCloseButton(){
		return closeButton;
	}
	
	public boolean isAvailable(){
		return isAvailable;
	}
	
	private void setupTween() {
		Tween.registerAccessor(NutSprite.class, new SpriteAccessor());
		tweenManager = new TweenManager();
		
		
		moveInCallBack = new TweenCallback(){
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				System.out.println("move in complete");
				isAvailable = true;
			}
		};
		
		moveOutCallBack = new TweenCallback(){
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				System.out.println("move out complete");

				hasMoveOut = true;
				
			}
		};
		
	}
	
	
	public void moveIn(){
		Tween.to(this, SpriteAccessor.POSITION_X, 1.5f)
		.target(getScreen().getWorldWidth()/2 - getWidth()/2)
		.ease(TweenEquations.easeOutElastic)
		.setCallback(moveInCallBack).setCallbackTriggers(TweenCallback.COMPLETE)
		.start(tweenManager);
	}
	
	public void moveOut(){
		Tween.to(this, SpriteAccessor.POSITION_X, 0.2f)
		.target(getScreen().getWorldWidth()/2 - getWidth()/2 + 500)
		.setCallback(moveOutCallBack).setCallbackTriggers(TweenCallback.COMPLETE)
		.start(tweenManager);
		
		isAvailable = false;
		
		//
		closeButton.setEnable(false);
	}
	
	
	@Override
	public void update(float delta){
		super.update(delta);
		
		tweenManager.update(delta);
	}
	
	private void createCloseButton(){
		closeButton = new NutButton(getScreen(), AssetLoader.closeTabButtonTexture, 
				null, AssetLoader.closeTabButtonTexture);
		closeButton.setTouchSound(AssetLoader.buttonSound);
		closeButton.setPosition(160, -50);
		addChild(closeButton);
		
	}
	
	public boolean hasMoveOut(){
		return hasMoveOut;
	}

}
