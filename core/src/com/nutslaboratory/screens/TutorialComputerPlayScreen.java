package com.nutslaboratory.screens;

import com.nutslaboratory.gameobjects.BackMenu;
import com.nutslaboratory.gameobjects.GameManager;
import com.nutslaboratory.gameobjects.HintBox;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutGame;
import com.nutslaboratory.nutlibgdxgameengine.NutSpriteLayer;

public class TutorialComputerPlayScreen extends ComputerPlayScreen{
	public final static int EXIT_TO_START_SCREEN = 0;
	public final static int EXIT_TO_MAIN_MENU_SCREEN = 1;
	
	private int exitTo;
	
	private HintBox hintBox;
	
	private NutSpriteLayer spriteLayer2 = new NutSpriteLayer(this, "layer2");

	public TutorialComputerPlayScreen(NutGame game, GameManager gameManager, int exitTo) {
		super(game, gameManager);
		
		this.exitTo = exitTo;
	}
	
	@Override
	public void show() {
		super.show();
		
		//
		addSpriteLayer(spriteLayer2);
		
		
		
	}
	
	@Override
	protected void endTurn(){
		AssetLoader.robotSound.stop();
		
		gameManager.nextTurn();
		
		getGame().setScreen(new TutorialPunctuateScreen(getGame(), gameManager, exitTo));
	}
	
	
	@Override
	public void update(float delta){
		super.update(delta);
		
	}
	
	@Override
	public void doEvents(){
		switch(state){
		case BACK_MENU_STATE:
			if(backMenu.getYesButton().isClicked()){
				AssetLoader.robotSound.stop();
				
				if(exitTo == EXIT_TO_START_SCREEN){
					getGame().setScreen(new StartScreen(getGame()));
				}else{
					getGame().setScreen(new MainMenuScreen(getGame()));
				}
			}
			else if(backMenu.getNoButton().isClicked()){
				removeBackMenu();
				setState(State.MAIN_STATE);
			}
			
			break;
		}
	}
	
	
	public void createBackMenu(){
		//
		backMenu = new BackMenu(this, AssetLoader.charlemagne24Font, "Exit Tutorial ?");
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
	
	@Override
	public void pressBack() {
		
		if(state.equals(State.BACK_MENU_STATE)){
			removeBackMenu();
			setState(State.MAIN_STATE);
		}else{
			createBackMenu();
			setState(State.BACK_MENU_STATE);
		}
	}
	
	@Override
	protected void computerProcess() {
		//
		if(gameManager.getRoundNum() == 1){
			guessOneCompleteWord();
		}else if(gameManager.getRoundNum() == 2){
			
		}	
		
		
		
	}

}
