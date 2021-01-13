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


public class GuessTab extends NutSprite{
	
	private Array<LetterButton> letterButtons;

	private InvestigateBlock selectedInvestigateBlock;
	
	private NutButton guessButton, closeButton;
	
	private InvestigateTab investigateTab;
	
	private LetterButton selectedLetterButton;
	
	private TweenManager tweenManager;
	private TweenCallback moveInCallBack, moveOutCallBack;
	private boolean isAvailable;
	
	private boolean hasMoveIn = false; 
	private boolean hasMoveOut = false; 
	
	private NutText helpText;
	
	public GuessTab(NutScreen screen, InvestigateBlock selectedInvestigateBlock, InvestigateTab investigateTab) {
		super(screen, AssetLoader.guessLetterTabTexture);
		
		this.selectedInvestigateBlock = selectedInvestigateBlock;
		this.investigateTab = investigateTab;
		
		createHelpText();
		createLetterButtons();
		createCloseButton();
		createGuessButton();
		
		
		setupTween();
		moveIn();
	}
	
	private void createHelpText() {
		helpText = new NutText(getScreen(), AssetLoader.charlemagne14Font,
				"select a letter for a guess");
		helpText.setColor(Color.FIREBRICK);
		helpText.setPosition(getWidth()/2-helpText.getWidth()/2, 170);
		addChild(helpText);
	}
	
	private void createLetterButtons() {
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
			boolean hasGuessed = selectedInvestigateBlock.getBlockData().getGuessedLetterBooleans().get(i);
			if(hasGuessed){
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
	
	private void createGuessButton() {
		guessButton  = new NutButton(getScreen(), AssetLoader.defaultUseButtonTexture,
				AssetLoader.useButtonUnavailableTexture, null);
		guessButton.setTouchSound(AssetLoader.buttonSound);
		guessButton.setPosition(335, 25);
		addChild(guessButton);
		
		//
		guessButton.unavailable();
		
	}
	
	public NutText getHelpText(){
		return helpText;
	}
	
	public NutButton getGuessButton(){
		return guessButton;
	}
	
	public NutButton getCloseButton(){
		return closeButton;
	}
	
	
	public Array<LetterButton> getLetterButtons(){
		return letterButtons;
	}
	
	public void selectLetterButton(LetterButton letterButton){
		selectedLetterButton = letterButton;
		
		//
		guessButton.unselect();
	}
	
	public LetterButton getSelectedLetterButton(){
		return selectedLetterButton;
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
				
				hasMoveIn = true;
				
				//
				closeButton.setEnable(true);
				guessButton.setEnable(true);
				
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
			}
		};
		
	}
	
	public void moveIn(){
		Tween.to(this, SpriteAccessor.POSITION_X, 0.5f)
		.target(75)
		.ease(TweenEquations.easeOutBack)
		.setCallback(moveInCallBack).setCallbackTriggers(TweenCallback.COMPLETE)
		.start(tweenManager);
		
		//
		closeButton.setEnable(false);
		guessButton.setEnable(false);
		
		for(LetterButton tempLetterButton : letterButtons){
			tempLetterButton.setEnable(false);
		}
		
		//
		AssetLoader.moveSound.play(CWIGame.getSoundVolume());
	}
	
	public void moveOut(){
		Tween.to(this, SpriteAccessor.POSITION_X, 0.5f)
		.target(475)
		.ease(TweenEquations.easeInBack)
		.setCallback(moveOutCallBack).setCallbackTriggers(TweenCallback.COMPLETE)
		.start(tweenManager);
		
		isAvailable = false;
		
		//
		closeButton.setEnable(false);
		guessButton.setEnable(false);
		
		for(LetterButton tempLetterButton : letterButtons){
			tempLetterButton.setEnable(false);
		}
		
		//
		AssetLoader.moveSound.play(CWIGame.getSoundVolume());
	}
	
	public InvestigateBlock getSelectedInvestigateBlock(){
		return selectedInvestigateBlock;
	}
	
	public boolean hasMoveIn(){
		return hasMoveIn;
	}
	
	public boolean hasMoveOut(){
		return hasMoveOut;
	}

}
