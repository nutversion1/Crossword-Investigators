package com.nutslaboratory.gameobjects;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Color;
import com.nutslaboratory.cwi.CWIGame;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.helpers.SpriteAccessor;
import com.nutslaboratory.nutlibgdxgameengine.NutButton;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutText;

public class SyllableHintTab extends NutSprite{
	
	private NutButton useButton;
	private NutButton closeButton;

	private SyllableTab syllableTab;
	
	private TweenManager tweenManager;
	private TweenCallback moveInCallBack, moveOutCallBack;
	private boolean isAvailable;
	
	private boolean hasMoveOut = false;
	
	private NutText helpText;
	
	public SyllableHintTab(NutScreen screen, SyllableTab syllableTab) {
		super(screen, AssetLoader.smallKeyboardTabTexture);

		
		this.syllableTab = syllableTab;
		
		createHelpText();
		createUseButton();
		createCloseButton();
		
		setupTween();
		moveIn();
	}
	
	private void createHelpText() {
		helpText = new NutText(getScreen(), AssetLoader.charlemagne14Font,
				"use a hint ?");
		helpText.setColor(Color.FIREBRICK);
		helpText.setPosition(getWidth()/2-helpText.getWidth()/2, 130);
		addChild(helpText);
	}

	private void createUseButton(){
		useButton = new NutButton(getScreen(), AssetLoader.defaultUseButtonTexture,
				null, AssetLoader.defaultUseButtonTexture);
		useButton.setTouchSound(AssetLoader.buttonSound);
		useButton.setPosition(20, 25);
		addChild(useButton);
		
	}
	

	private void createCloseButton(){
		closeButton = new NutButton(getScreen(), AssetLoader.closeTabButtonTexture,
				null, AssetLoader.closeTabButtonTexture);
		closeButton.setTouchSound(AssetLoader.buttonSound);
		closeButton.setPosition(90, 25);
		addChild(closeButton);
		
	}
	
	
	
	public void useHint(){
		syllableTab.getInvestigateTab().getCurrentTurnPlayer().useHint(1);
		
		//
		syllableTab.getInvestigateTab().getInvestigateWordData().setHasGuessedSyllable(true);
		
		syllableTab.getText().setText(syllableTab.getInvestigateTab().getInvestigateWordData().getWord().getSyllable());
		syllableTab.getText().setX(syllableTab.getWidth()/2 - syllableTab.getText().getWidth()/2 - 25);
		syllableTab.getText().setColor(Color.BLACK);
		
		//
		syllableTab.getHintButton().unavailable();
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
			}
		};
		
		moveOutCallBack = new TweenCallback(){
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				//System.out.println("move out complete");
				hasMoveOut = true;
				
				//
				if(syllableTab.getHintButton().getState().equals(NutButton.SELECT_STATE)){
					syllableTab.getHintButton().unselect();
				}
				
				
			}
		};
		
	}
	
	public void moveIn(){
		Tween.to(this, SpriteAccessor.POSITION_X, 0.5f)
		.target(110)
		.ease(TweenEquations.easeOutBack)
		.setCallback(moveInCallBack).setCallbackTriggers(TweenCallback.COMPLETE)
		.start(tweenManager);
		
		//
		closeButton.setEnable(false);
		useButton.setEnable(false);
		
		//
		AssetLoader.moveSound.play(CWIGame.getSoundVolume());
	}
	
	public void moveOut(){
		Tween.to(this, SpriteAccessor.POSITION_X, 0.5f)
		.target(510)
		.ease(TweenEquations.easeInBack)
		.setCallback(moveOutCallBack).setCallbackTriggers(TweenCallback.COMPLETE)
		.start(tweenManager);
		
		isAvailable = false;
		
		//
		closeButton.setEnable(false);
		useButton.setEnable(false);
		
		//
		AssetLoader.moveSound.play(CWIGame.getSoundVolume());
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
