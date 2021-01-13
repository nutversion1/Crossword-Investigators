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

public class TypeHintTab extends NutSprite{
	
	private NutButton useButton;
	private NutButton closeButton;

	private TypeTab typeTab;
	
	private TweenManager tweenManager;
	private TweenCallback moveInCallBack, moveOutCallBack;
	private boolean isAvailable;
	
	private boolean hasMoveOut = false;
	
	private NutText helpText;
	
	public TypeHintTab(NutScreen screen, TypeTab typeTab) {
		super(screen, AssetLoader.smallKeyboardTabTexture);

		
		this.typeTab = typeTab;
		
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
		typeTab.getInvestigateTab().getCurrentTurnPlayer().useHint(1);
		
		//
		typeTab.getInvestigateTab().getInvestigateWordData().setHasGuessedType(true);
		String typeName = "";
		for(int i = 0; i < typeTab.getInvestigateTab().getInvestigateWordData().getWord().getType().length; i++){
			String tempType = typeTab.getInvestigateTab().getInvestigateWordData().getWord().getType()[i];
			
			if(i == typeTab.getInvestigateTab().getInvestigateWordData().getWord().getType().length-1){
				typeName += tempType;
			}else{
				typeName += tempType + ", ";
			}
		}	
		
		typeTab.getText().setText(typeName);
		typeTab.getText().setWidthLimit(15);
		typeTab.getText().setPosition(typeTab.getWidth()/2 - typeTab.getText().getWidth()/2 - 30,
				typeTab.getHeight()/2 - typeTab.getText().getHeight()/2 - 15);
		typeTab.getText().setColor(Color.BLACK);
		
		
		//
		typeTab.getHintButton().unavailable();
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
				if(typeTab.getHintButton().getState().equals(NutButton.SELECT_STATE)){
					typeTab.getHintButton().unselect();
				}
			}
		};
		
	}
	
	public void moveIn(){
		Tween.to(this, SpriteAccessor.POSITION_X, 0.5f)
		.target(-50)
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
		.target(350)
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
