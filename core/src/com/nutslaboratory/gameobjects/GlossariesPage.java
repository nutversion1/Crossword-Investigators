package com.nutslaboratory.gameobjects;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nutslaboratory.cwi.CWIGame;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.helpers.SpriteAccessor;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;

public abstract class GlossariesPage extends NutSprite{
	
	
	
	private TweenManager tweenManager;
	private TweenCallback moveLeftCallBack, moveRightCallBack;
	
	private boolean isAvailable;
	
	private GlossariesTab glossariesTab;
	
	public GlossariesPage(NutScreen screen, TextureRegion texture, GlossariesTab glossariesTab) {
		super(screen, texture);
		
		this.glossariesTab = glossariesTab;
		
		isAvailable = true;
		
		setupTween();
	}

	
	
	private void setupTween() {
		Tween.registerAccessor(NutSprite.class, new SpriteAccessor());
		tweenManager = new TweenManager();
		
		moveLeftCallBack = new TweenCallback(){
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				//System.out.println("move left complete");
				isAvailable = true;
				
				glossariesTab.removeChild(glossariesTab.getOldGlossariesPage());
			}
		};
		
		moveRightCallBack = new TweenCallback(){
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				//System.out.println("move right complete");
				isAvailable = true;
				
				glossariesTab.removeChild(GlossariesPage.this);
			}
		};
	}
	
	public void moveLeft(){
		//
		Tween.to(this, SpriteAccessor.POSITION_X, 0.5f)
		.target(0)
		.ease(TweenEquations.easeOutSine)
		.setCallback(moveLeftCallBack).setCallbackTriggers(TweenCallback.COMPLETE)
		.start(tweenManager);
		
		isAvailable = false;
		
		AssetLoader.flipPaperSound.play(CWIGame.getSoundVolume());
	}
	
	public void moveRight(){
		//
		Tween.to(this, SpriteAccessor.POSITION_X, 0.5f)
		.target(480)
		.ease(TweenEquations.easeOutSine)
		.setCallback(moveRightCallBack).setCallbackTriggers(TweenCallback.COMPLETE)
		.start(tweenManager);
		
		isAvailable = false;
		
		AssetLoader.flipPaperSound.play(CWIGame.getSoundVolume());
	}
	
	@Override
	public void update(float delta){
		super.update(delta);
		
		tweenManager.update(delta);
	
		
	}
	
	public boolean isAvailable(){
		return isAvailable;
	}

}
