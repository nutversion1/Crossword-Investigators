package com.nutslaboratory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.cwi.CWIGame;
import com.nutslaboratory.gameobjects.BackMenu;
import com.nutslaboratory.gameobjects.GameManager;
import com.nutslaboratory.gameobjects.Player;
import com.nutslaboratory.gameobjects.TutorialMessage;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutButton;
import com.nutslaboratory.nutlibgdxgameengine.NutGame;
import com.nutslaboratory.nutlibgdxgameengine.NutMovingSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutSpriteLayer;

public class StartScreen extends NutScreen{
	
	private NutSprite background;
	
	private NutMovingSprite magnifier;
	private NutSprite title;
	
	private NutButton playButton, tutorialButton, aboutButton;
	
	public enum State{
		MAIN_STATE, BACK_MENU_STATE;
	}
	
	private State oldState;
	private State state;
	
	private BackMenu backMenu;
	
	private NutSpriteLayer spriteLayer1 = new NutSpriteLayer(this, "layer1");
	private NutSpriteLayer spriteLayer2 = new NutSpriteLayer(this, "layer2");
	
	public StartScreen(NutGame game) {
		super(game);
	}

	@Override
	public void show() {
		
		addSpriteLayer(spriteLayer1);
		addSpriteLayer(spriteLayer2);
		
		createBackground();
		createTitle();
		createMagnifier();
		
		createPlayButton();
		createTutorialButton();
		createAboutButton();
		
		setState(State.MAIN_STATE);
		
		//
		AssetLoader.backgroundMusic.setVolume(CWIGame.getMusicVolume());
		AssetLoader.backgroundMusic.setLooping(true);
		
		AssetLoader.playMusic.setVolume(CWIGame.getMusicVolume());
		AssetLoader.playMusic.setLooping(true);
		
		//
		if(!AssetLoader.backgroundMusic.isPlaying()){
			AssetLoader.playMusic.stop();
			AssetLoader.backgroundMusic.play();
		}
		
		//show ads
		if(getGame().isAndroid){
			getGame().handler.showAds(true);
		}
		
	}

	private void createBackground() {
		background = new NutSprite(this, AssetLoader.openBackgroundTexture);
		spriteLayer1.addSprite(background);
		
	}
	
	private void createMagnifier() {
		magnifier = new NutMovingSprite(this, AssetLoader.magnifierTexture);
		magnifier.setPosition(getWorldWidth()/2 - magnifier.getWidth()/2,
				getWorldHeight()/2 - magnifier.getHeight()/2 + 180);
		spriteLayer1.addSprite(magnifier);
		
		magnifier.moveTo(magnifier.getX()-20, magnifier.getY()-70, 3f);
		magnifier.startTween();
		
	}
	
	private void createTitle() {
		title = new NutSprite(this, AssetLoader.titleTexture);
		title.setPosition(getWorldWidth()/2 - title.getWidth()/2,
				getWorldHeight()/2 - title.getHeight()/2 + 80);
		spriteLayer1.addSprite(title);
		
		
	}

	private void createPlayButton() {
		playButton = new NutButton(this, AssetLoader.playButtonTexture, null, null);
		playButton.setTouchSound(AssetLoader.buttonSound);
		playButton.setPosition(140, 280);
		spriteLayer1.addSprite(playButton);
		
		
	}

	private void createTutorialButton() {
		tutorialButton = new NutButton(this, AssetLoader.tutorialButtonTexture, null, null);
		tutorialButton.setTouchSound(AssetLoader.buttonSound);
		tutorialButton.setPosition(140, 210);
		spriteLayer1.addSprite(tutorialButton);
		
	}

	private void createAboutButton() {
		aboutButton = new NutButton(this, AssetLoader.aboutButtonTexture, null, null);
		aboutButton.setTouchSound(AssetLoader.buttonSound);
		aboutButton.setPosition(140, 140);
		spriteLayer1.addSprite(aboutButton);
	}
	
	public void setState(State state){
		//System.out.println(state);	
		
		oldState = this.state;
		
		this.state = state;
	}
	
	public void createBackMenu(){
		//
		backMenu = new BackMenu(this, AssetLoader.charlemagne32Font, "Quit ?");
		backMenu.setPosition(getWorldWidth()/2 - backMenu.getWidth()/2,
				getWorldHeight()/2 - backMenu.getHeight()/2);
		spriteLayer2.addSprite(backMenu);
		
		//
		spriteLayer2.setInfluence(true);
		spriteLayer2.setOverlay(true);
	}
	
	public void removeBackMenu(){
		spriteLayer2.removeSprite(backMenu);
		backMenu = null;
		
		//
		spriteLayer2.setInfluence(false);
		spriteLayer2.setOverlay(false);
	}
	
	private void goToMainMenuScreen() {
		getGame().setScreen(new MainMenuScreen(getGame()));
		
	}
	
	private void goToTutorialScreen() {
		Array<String> playerTypes = new Array<String>();
		playerTypes.add(Player.HUMAN_PLAYER_TYPE);
		playerTypes.add(Player.COMPUTER_PLAYER_TYPE);
		
		int totalWords = 2;
		int totalGlossaries = 20;
		int totalTime = 60;
		int totalHint = 5;
		int totalWrongGuess = 3;
		int totalExistLetter = 3;
		
		GameManager gameManager = new GameManager(GameManager.TUTORIAL_GAME_TYPE,
				totalWords,
				totalGlossaries, 
				totalTime, 
				totalHint, 
				totalWrongGuess,
				totalExistLetter,
				playerTypes,
				new TutorialMessage());
		
		getGame().setScreen(new TutorialPunctuateScreen(getGame(), gameManager, 
				TutorialPunctuateScreen.EXIT_TO_START_SCREEN));
		
	}
	
	private void goToAboutScreen() {	
		getGame().setScreen(new AboutScreen(getGame()));
	}
	
	@Override
	public void update(float delta){
		super.update(delta);
	}

	@Override
	public void doEvents(){
	    
	    switch(state){
		case MAIN_STATE:
			if(playButton.isClicked()){
				goToMainMenuScreen();
				
				//hide ads
				if(getGame().isAndroid){
					getGame().handler.showAds(false);
				}
			}
			else if(tutorialButton.isClicked()){
				goToTutorialScreen();
				
				//hide ads
				if(getGame().isAndroid){
					getGame().handler.showAds(false);
				}
			}
			else if(aboutButton.isClicked()){
				goToAboutScreen();
				
				//hide ads
				if(getGame().isAndroid){
					getGame().handler.showAds(false);
				}
			}
			
			break;

		case BACK_MENU_STATE:
			if(backMenu.getYesButton().isClicked()){
				Gdx.app.exit();
			}else if(backMenu.getNoButton().isClicked()){
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
