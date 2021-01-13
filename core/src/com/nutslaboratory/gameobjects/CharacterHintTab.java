package com.nutslaboratory.gameobjects;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.cwi.CWIGame;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.helpers.SpriteAccessor;
import com.nutslaboratory.nutlibgdxgameengine.NutButton;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutText;


public class CharacterHintTab extends NutSprite{
	
	private Array<LetterButton> letterButtons;
	
	private NutButton useButton, closeButton;
	
	private CharacterTab characterTab;
	private InvestigateTab investigateTab;
	
	private LetterButton selectedLetterButton = null;
	
	private TweenManager tweenManager;
	private TweenCallback moveInCallBack, moveOutCallBack;
	private boolean isAvailable;
	
	private boolean hasMoveOut = false;
	
	private NutText helpText;
	
	public CharacterHintTab(NutScreen screen, CharacterTab characterTab, InvestigateTab investigateTab) {
		super(screen, AssetLoader.guessLetterTabTexture);
		
		this.characterTab = characterTab;
		this.investigateTab = investigateTab;
		
		createHelpText();
		createLetterButtons();
		createCloseButton();
		createUseButton();
		
		
		setupTween();
		moveIn();
	}
	
	private void createHelpText() {
		helpText = new NutText(getScreen(), AssetLoader.charlemagne14Font,
				"select a letter for a hint");
		helpText.setColor(Color.FIREBRICK);
		helpText.setPosition(getWidth()/2-helpText.getWidth()/2, 170);
		addChild(helpText);
	}

	private void createLetterButtons() {
		//
		String[] letterNames = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", 
				"w", "x", "y", "z"}; 
		
		//
		letterButtons = new Array<LetterButton>();
		
		int column = 0;
		int row = 0;
		
		for(int i = 0; i < letterNames.length; i++){
			String letterName = letterNames[i];
			
			LetterButton letterButton = new LetterButton(getScreen(), AssetLoader.letterButtonTexture,
					AssetLoader.letterButtonUnavailableTexture,AssetLoader.letterButtonSelectTexture,
					AssetLoader.charlemagne20Font, letterName.toUpperCase(), i);
			letterButton.setTouchSound(AssetLoader.buttonSound);
			letterButton.setPosition(15 + (column * (letterButton.getWidth()+2)), 95-(row*(letterButton.getHeight()+3)));
			addChild(letterButton);
			letterButtons.add(letterButton);
			
			//
			if(investigateTab.getInvestigateWordData().getGuessedCharacterStrings().
					get(i).contains(WordData.INSIDE_WORD)){
				letterButton.unavailable();
			}else if(investigateTab.getInvestigateWordData().getGuessedCharacterStrings().
					get(i).contains(WordData.OUTSIDE_WORD)){
				letterButton.unavailable();
			}
			
			//
			column++;
			if(column >= 9){
				column = 0;
				row++;
			}	
		}
		
	}

	private void createCloseButton() {
		closeButton = new NutButton(getScreen(), AssetLoader.closeTabButtonTexture,
				null, AssetLoader.closeTabButtonTexture);
		closeButton.setTouchSound(AssetLoader.buttonSound);
		closeButton.setPosition(335, 80);
		addChild(closeButton);
		
		
	}
	
	private void createUseButton() {
		useButton  = new NutButton(getScreen(), AssetLoader.defaultUseButtonTexture, 
				AssetLoader.useButtonUnavailableTexture, null);
		useButton.setTouchSound(AssetLoader.buttonSound);
		useButton.setPosition(335, 25);
		addChild(useButton);
		
		//
		useButton.unavailable();
		
	}
	
	public Array<LetterButton> getLetterButtons(){
		return letterButtons;
	}
	
	public void selectLetterButton(LetterButton letterButton){
		selectedLetterButton = letterButton;
		
		//
		useButton.unselect();
	}
	
	public LetterButton getSelectedLetterButton(){
		return selectedLetterButton;
	}
	
	
	public void useHint(){
		investigateTab.getCurrentTurnPlayer().useHint(1);
		
		selectedLetterButton.unavailable();
		
		//
		if(investigateTab.getInvestigateWordData().getWord().getName().toLowerCase().contains(selectedLetterButton.getText().getText().toLowerCase())){
			investigateTab.getInvestigateWordData().getGuessedCharacterStrings().set(selectedLetterButton.getNum(), WordData.INSIDE_WORD);
			
			//charactertab.getTexts().get(selectedLetterButton.getNum()).setColor(Color.GREEN);
			characterTab.createInsideWordMark(characterTab.getTexts().get(selectedLetterButton.getNum()));
			
		}else{
			investigateTab.getInvestigateWordData().getGuessedCharacterStrings().set(selectedLetterButton.getNum(), WordData.OUTSIDE_WORD);
			
			//charactertab.getTexts().get(selectedLetterButton.getNum()).setColor(Color.RED);
			characterTab.createOutsideWordMark(characterTab.getTexts().get(selectedLetterButton.getNum()));
			
		}
		
		//set hint button to unavailable (if guessed all)
		boolean hasGuessedAll = true;
		
		for(String guessedStatus : investigateTab.getInvestigateWordData().getGuessedCharacterStrings()){
			if(guessedStatus.equals(WordData.NOT_GUESSED)){
				hasGuessedAll = false;
				break;
			}
		}
		
		if(hasGuessedAll){
			characterTab.getHintButton().unavailable();
		}
		
		//
		useButton.unavailable();
		
		
	}
	
	@Override
	public void update(float delta){
		super.update(delta);
		
		tweenManager.update(delta);
	}
	
	private void setupTween() {
		Tween.registerAccessor(NutSprite.class, new SpriteAccessor());
		tweenManager = new TweenManager();
		
		
		moveInCallBack = new TweenCallback(){
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				//System.out.println("move in complete");
				isAvailable = true;
				
				//
				closeButton.setEnable(true);
				useButton.setEnable(true);
				
				for(LetterButton tempLetterButton : letterButtons){
					tempLetterButton.setEnable(true);
				}
			}
		};
		
		moveOutCallBack = new TweenCallback(){
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				//System.out.println("move out complete");
				hasMoveOut = true;
				
				//
				if(characterTab.getHintButton().getState().equals(NutButton.SELECT_STATE)){
					characterTab.getHintButton().unselect();
				}
				
			}
		};
		
	}
	
	public void moveIn(){
		Tween.to(this, SpriteAccessor.POSITION_X, 0.5f)
		.target(-5)
		.ease(TweenEquations.easeOutBack)
		.setCallback(moveInCallBack).setCallbackTriggers(TweenCallback.COMPLETE)
		.start(tweenManager);
		
		//
		closeButton.setEnable(false);
		useButton.setEnable(false);
		
		for(LetterButton tempLetterButton : letterButtons){
			tempLetterButton.setEnable(false);
		}
		
		//
		AssetLoader.moveSound.play(CWIGame.getSoundVolume());
	}
	
	public void moveOut(){
		Tween.to(this, SpriteAccessor.POSITION_X, 0.5f)
		.target(395)
		.ease(TweenEquations.easeInBack)
		.setCallback(moveOutCallBack).setCallbackTriggers(TweenCallback.COMPLETE)
		.start(tweenManager);
		
		isAvailable = false;
		
		//
		closeButton.setEnable(false);
		useButton.setEnable(false);
		
		for(LetterButton tempLetterButton : letterButtons){
			tempLetterButton.setEnable(false);
		}
		
		//
		AssetLoader.moveSound.play(CWIGame.getSoundVolume());
	}
	
	public NutText getHelpText(){
		return helpText;
	}
	
	public NutButton getCloseButton(){
		return closeButton;
	}
	
	public NutButton getUseButton(){
		return useButton;
	}
	
	public boolean hasMoveOut(){
		return hasMoveOut;
	}
	

}
