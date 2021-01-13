package com.nutslaboratory.screens;

import com.nutslaboratory.cwi.CWIGame;
import com.nutslaboratory.gameobjects.BackMenu;
import com.nutslaboratory.gameobjects.GameManager;
import com.nutslaboratory.gameobjects.HintBox;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutGame;
import com.nutslaboratory.nutlibgdxgameengine.NutSpriteLayer;

public class TutorialPunctuateScreen extends PunctuateScreen{
	public final static int EXIT_TO_START_SCREEN = 0;
	public final static int EXIT_TO_MAIN_MENU_SCREEN = 1;
	
	private int exitTo;
	
	private HintBox hintBox;
	
	protected NutSpriteLayer spriteLayer3 = new NutSpriteLayer(this, "layer3");
	protected NutSpriteLayer spriteLayer4 = new NutSpriteLayer(this, "layer4");
	
	public TutorialPunctuateScreen(NutGame game, GameManager gameManager, int exitTo) {
		super(game, gameManager);
		
		this.exitTo = exitTo;
		
		((CWIGame)getGame()).passTutorial();
		
		
	}
	
	@Override
	public void show(){
		super.show();
		
		//
		setState(State.MAIN_STATE);
		
		//
		addSpriteLayer(spriteLayer3);
		addSpriteLayer(spriteLayer4);
		
		//
		nextHintBox();
		
	}
	
	private void nextHintBox(){
		if(hintBox != null){
			spriteLayer3.removeSprite(hintBox);
		}
		
		hintBox = gameManager.getTutorialMessage().getNewHintBox(this);
		spriteLayer3.addSprite(hintBox);
	}
	
	
	@Override
	public void createBackMenu(){
		//
		backMenu = new BackMenu(this, AssetLoader.charlemagne24Font, "Exit Tutorial ?");
		backMenu.setPosition(getWorldWidth()/2 - backMenu.getWidth()/2,
				getWorldHeight()/2 - backMenu.getHeight()/2);
		spriteLayer4.addSprite(backMenu);
		
		//
		spriteLayer4.setInfluence(true);
		spriteLayer4.setOverlay(true);
		
	}
	
	@Override
	public void removeBackMenu(){
		spriteLayer4.removeSprite(backMenu);
		
		//
		spriteLayer4.setInfluence(false);
		spriteLayer4.setOverlay(false);
	}
	
	@Override
	public void doEvents(){
		
		//
		switch(state){
		case BACK_MENU_STATE:
			if(backMenu.getYesButton().isClicked()){
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
		
		//
		switch(gameManager.getTutorialMessage().getCurrentHintStep()){
		case 1:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			
			break;
		case 2:
			if(startButton.isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				
				//
				getGame().setScreen(new TutorialPlayScreen(getGame(), gameManager, exitTo));
			}
			
			break;
		
		case 66:
			if(startButton.isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				
				getGame().setScreen(new TutorialComputerPlayScreen(getGame(), gameManager, exitTo));
			}
			
			break;
		case 67:
			if(startButton.isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				
				getGame().setScreen(new TutorialPlayScreen(getGame(), gameManager, exitTo));
			}
			
			break;
		case 81:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
			}
			break;
		case 82:
			if(startButton.isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				
				getGame().setScreen(new TutorialComputerPlayScreen(getGame(), gameManager, exitTo));
			}
			
			break;
		case 83:
			if(hintBox.getOkButton().isClicked()){
				if(exitTo == EXIT_TO_START_SCREEN){
					getGame().setScreen(new StartScreen(getGame()));
				}else{
					getGame().setScreen(new MainMenuScreen(getGame()));
				}
			}
			
			break;
		}
	}
}
