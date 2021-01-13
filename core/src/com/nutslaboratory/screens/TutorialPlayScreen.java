package com.nutslaboratory.screens;

import com.nutslaboratory.gameobjects.BackMenu;
import com.nutslaboratory.gameobjects.GameManager;
import com.nutslaboratory.gameobjects.HintBox;
import com.nutslaboratory.gameobjects.LetterButton;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutGame;
import com.nutslaboratory.nutlibgdxgameengine.NutSpriteLayer;

public class TutorialPlayScreen extends PlayScreen {
	
	public final static int EXIT_TO_START_SCREEN = 0;
	public final static int EXIT_TO_MAIN_MENU_SCREEN = 1;
	
	private int exitTo;
	
	private HintBox hintBox;
	
	protected BackMenu backMenu;
	
	protected NutSpriteLayer spriteLayer8 = new NutSpriteLayer(this, "layer8");
	protected NutSpriteLayer spriteLayer9 = new NutSpriteLayer(this, "layer9");

	
	public TutorialPlayScreen(NutGame game, GameManager gameManager, int exitTo) {
		super(game, gameManager);
		
		this.exitTo = exitTo;
	
	}
	
	
	
	@Override
	public void show() {
		super.show();
		
		//
		timeInfinite = true;
		glossaryTimeInfinite = true;
		
		//
		spriteLayer5.setInfluence(false);
		
		//
		addSpriteLayer(spriteLayer8);
		addSpriteLayer(spriteLayer9);
		
		//
		nextHintBox();
		
		//
		headMainBlocks.get(0).getBlockData().getLetter().reveal();
		
		
		
	}
	
	private void nextHintBox(){
		if(hintBox != null){
			spriteLayer8.removeSprite(hintBox);
		}
		
		hintBox = gameManager.getTutorialMessage().getNewHintBox(this);
		spriteLayer8.addSprite(hintBox);
	
	}
	
	
	
	@Override
	public void endTurn(){
		gameManager.nextTurn();
		
		getGame().setScreen(new TutorialPunctuateScreen(getGame(), gameManager, exitTo));
	}
	
	public void createBackMenu(){
		//
		backMenu = new BackMenu(this, AssetLoader.charlemagne24Font, "Exit Tutorial ?");
		backMenu.setPosition(getWorldWidth()/2 - backMenu.getWidth()/2,
				getWorldHeight()/2 - backMenu.getHeight()/2);
		spriteLayer9.addSprite(backMenu);
		
		//
		spriteLayer9.setInfluence(true);
		spriteLayer9.setOverlay(true);
		
	}
	

	public void removeBackMenu(){
		spriteLayer9.removeSprite(backMenu);
		
		
		//
		spriteLayer9.setInfluence(false);
		spriteLayer9.setOverlay(false);
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
	public void update(float delta){
		super.update(delta);
		
		
		
	}

	@Override
	public void doEvents() {
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
				setState(oldState);
			}
			
			break;
		}
		
		
		//
		switch(gameManager.getTutorialMessage().getCurrentHintStep()){
		case 3:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				
			}
			break;
		case 4:
			if(glossariesTab.getNextButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				glossariesTab.nextPage();
				
			
			}
			break;
		case 5:
			if(glossariesTab.getNextButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				glossariesTab.nextPage();
				
			}
			break;
		case 6:
			if(glossariesTab.getPreviousButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				glossariesTab.previousPage();
			}
			break;
		case 7:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 8:
			if(glossariesTab.getCloseButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				removeGlossaryTab();
			}
			break;
		case 9:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 10:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 11:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 12:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 13:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 14:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 15:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 16:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 17:
			if(headMainBlocks.get(1).isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				selectHeadMainBlock(headMainBlocks.get(1));
			}
			
			break;
		case 18:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
			
		case 19:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
			}
			break;
		case 20:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 21:
			if(investigateTab.getInvestigateBlocks().get(2).isClicked()){
				//
				showGuessTab(investigateTab.getInvestigateBlocks().get(2));
				
				//
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 22:
			if(investigateTab.getGuessTab().getLetterButtons().get(14).isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				LetterButton tempLetterButton = investigateTab.getGuessTab().getLetterButtons().get(14);
				
				
				selectGuessTabLetter(tempLetterButton);
				
			}
			break;
		case 23:
			if(investigateTab.getGuessTab().getGuessButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			
				//
				guessLetter();
			}
			break;
		case 24:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 25:
			if(investigateTab.getInvestigateBlocks().get(0).isClicked()){
				//
				showGuessTab(investigateTab.getInvestigateBlocks().get(0));
				
				//
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 26:
			if(investigateTab.getGuessTab().getLetterButtons().get(6).isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				LetterButton tempLetterButton = investigateTab.getGuessTab().getLetterButtons().get(6);
				
				
				selectGuessTabLetter(tempLetterButton);
				
			}
			break;
		case 27:
			if(investigateTab.getGuessTab().getGuessButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			
				//
				guessLetter();
			}
			break;
		case 28:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 29:
			if(investigateTab.getGuessTab().getCloseButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				investigateTab.getGuessTab().moveOut();
			}
			
			break;
		
		case 30:
			if(investigateTab.getTypeTab().getHintButton().isClicked()){
				//
				showTypeHintTab();
				
				//
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				
			}
			
			break;
		case 31:
			if(investigateTab.getTypeTab().getTypeHintTab().getUseButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				useTypeHint();
			}
			
			break;
		case 32:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 33:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 34:
			if(investigateTab.getSyllableTab().getHintButton().isClicked()){
				//
				showSyllableHintTab();
				
				//
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				
			}
			
			break;
			
		case 35:
			if(investigateTab.getSyllableTab().getSyllableHintTab().getUseButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				useSyllableHint();
			}
			
			break;
		case 36:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 37:
			if(investigateTab.getCharacterTab().getHintButton().isClicked()){
				//
				showCharacterHintTab();
				
				//
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				
			}
			break;
		case 38:
			if(investigateTab.getCharacterTab().getCharacterHintTab().getLetterButtons().get(5).isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				LetterButton tempLetterButton = investigateTab.getCharacterTab().getCharacterHintTab().getLetterButtons().get(5);
				
				selectCharacterHintTabLetter(tempLetterButton);
			}
			break;
		case 39:
			if(investigateTab.getCharacterTab().getCharacterHintTab().getUseButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			
				//
				useCharacterHint();
			}
			
			break;
		case 40:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 41:
			if(investigateTab.getCharacterTab().getCharacterHintTab().getLetterButtons().get(22).isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				LetterButton tempLetterButton = investigateTab.getCharacterTab().getCharacterHintTab().getLetterButtons().get(22);
				
				selectCharacterHintTabLetter(tempLetterButton);
			}
			break;
		case 42:
			if(investigateTab.getCharacterTab().getCharacterHintTab().getUseButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			
				//
				useCharacterHint();
			}
			
			break;
		case 43:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 44:
			if(investigateTab.getCharacterTab().getCharacterHintTab().getCloseButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				investigateTab.getCharacterTab().getCharacterHintTab().moveOut();
			}
			break;
		case 45:
			if(investigateTab.getInvestigateBlocks().get(0).isClicked()){
				//
				showGuessTab(investigateTab.getInvestigateBlocks().get(0));
				
				//
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 46:
			if(investigateTab.getGuessTab().getLetterButtons().get(22).isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				LetterButton tempLetterButton = investigateTab.getGuessTab().getLetterButtons().get(22);
				
				
				selectGuessTabLetter(tempLetterButton);
				
			}
			break;
		case 47:
			if(investigateTab.getGuessTab().getGuessButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			
				//
				guessLetter();
			}
			
			break;
		case 48:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 49:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
		case 50:
			if(headMainBlocks.get(0).isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				selectHeadMainBlock(headMainBlocks.get(0));
			}
			break;
		case 51:
			if(investigateTab.getSyllableTab().getHintButton().isClicked()){
				//
				showSyllableHintTab();
				
				//
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				
			}
			
			break;
		case 52:
			if(investigateTab.getSyllableTab().getSyllableHintTab().getUseButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				useSyllableHint();
			}
			
			break;
		case 53:
			if(investigateTab.getInvestigateBlocks().get(1).isClicked()){
				//
				showGuessTab(investigateTab.getInvestigateBlocks().get(1));
				
				//
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				
			}
			break;
		case 54:
			if(investigateTab.getGuessTab().getLetterButtons().get(0).isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				LetterButton tempLetterButton = investigateTab.getGuessTab().getLetterButtons().get(0);
				
				
				selectGuessTabLetter(tempLetterButton);
				
			}
			break;
		case 55:
			if(investigateTab.getGuessTab().getGuessButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			
				//
				guessLetter();
			}
			
			break;
		case 56:
			if(investigateTab.getInvestigateBlocks().get(3).isClicked()){
				//
				showGuessTab(investigateTab.getInvestigateBlocks().get(3));
				
				//
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				
			}
			break;
		case 57:
			if(investigateTab.getGuessTab().getLetterButtons().get(0).isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				LetterButton tempLetterButton = investigateTab.getGuessTab().getLetterButtons().get(0);
				
				
				selectGuessTabLetter(tempLetterButton);
				
			}
			break;
		case 58:
			if(investigateTab.getGuessTab().getGuessButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			
				//
				guessLetter();
			}
			
			break;
		case 59:
			if(investigateTab.getGuessTab().getLetterButtons().get(4).isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				LetterButton tempLetterButton = investigateTab.getGuessTab().getLetterButtons().get(4);
				
				
				selectGuessTabLetter(tempLetterButton);
				
			}
			break;
		case 60:
			if(investigateTab.getGuessTab().getGuessButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			
				//
				guessLetter();
			}
			
			break;
		case 61:
			if(investigateTab.getGuessTab().getCloseButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				investigateTab.getGuessTab().moveOut();
			}
			
			break;	
		case 62:
			if(hintBox.getOkButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			}
			break;
			
			
		case 63:
			if(investigateTab.getCloseButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				closeInvestigateTab();
			}
			
			break;
		case 64:
			if(statusButton.isClicked()){
				//
				createStatusTab();
				
				//
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				
			}
			
			break;
		case 65:
			if(statusTab.getEndTurnButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				
				//
				endTurn();
			}
			break;
			
		case 68:
			if(headMainBlocks.get(0).isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				selectHeadMainBlock(headMainBlocks.get(0));
			}
			break;
		case 69:
			if(investigateTab.getCharacterTab().getHintButton().isClicked()){
				//
				showCharacterHintTab();
				
				//
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				
			}
			break;
		case 70:
			if(investigateTab.getCharacterTab().getCharacterHintTab().getLetterButtons().get(8).isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				LetterButton tempLetterButton = investigateTab.getCharacterTab().getCharacterHintTab().getLetterButtons().get(8);
				
				selectCharacterHintTabLetter(tempLetterButton);
			}
			break;
		case 71:
			if(investigateTab.getCharacterTab().getCharacterHintTab().getUseButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			
				//
				useCharacterHint();
			}
			
			break;
		case 72:
			if(investigateTab.getCharacterTab().getCharacterHintTab().getLetterButtons().get(14).isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				LetterButton tempLetterButton = investigateTab.getCharacterTab().getCharacterHintTab().getLetterButtons().get(14);
				
				selectCharacterHintTabLetter(tempLetterButton);
			}
			break;
		case 73:
			if(investigateTab.getCharacterTab().getCharacterHintTab().getUseButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			
				//
				useCharacterHint();
			}
			
			break;
		case 74:
			if(investigateTab.getCharacterTab().getCharacterHintTab().getCloseButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				investigateTab.getCharacterTab().getCharacterHintTab().moveOut();
			}
			break;
		case 75:
			if(investigateTab.getInvestigateBlocks().get(3).isClicked()){
				//
				showGuessTab(investigateTab.getInvestigateBlocks().get(3));
				
				//
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				
			}
			break;
		case 76:
			if(investigateTab.getGuessTab().getLetterButtons().get(8).isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				LetterButton tempLetterButton = investigateTab.getGuessTab().getLetterButtons().get(8);
				
				
				selectGuessTabLetter(tempLetterButton);
				
			}
			break;
		case 77:
			if(investigateTab.getGuessTab().getGuessButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
			
				//
				guessLetter();
			}
			
			break;
		
		case 78:
			if(investigateTab.getInvestigateBlocks().get(4).isClicked()){
				//
				showGuessTab(investigateTab.getInvestigateBlocks().get(4));
				
				//
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				
			}
			break;
		case 79:
			if(investigateTab.getGuessTab().getLetterButtons().get(14).isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
				nextHintBox();
				
				//
				LetterButton tempLetterButton = investigateTab.getGuessTab().getLetterButtons().get(14);
				
				
				selectGuessTabLetter(tempLetterButton);
				
			}
			break;
		case 80:
			if(investigateTab.getGuessTab().getGuessButton().isClicked()){
				gameManager.getTutorialMessage().nextHintStep();
			
				
				//
				hintBox.setVisible(false);
				
				//
				guessLetter();
			}
			
			break;
			
		
		}
	}
	
	

}
