package com.nutslaboratory.nutlibgdxgameengine;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.nutslaboratory.helpers.SpriteAccessor;

public class NutMovingSprite extends NutSprite {
	
	private TweenManager tweenManager;
	private TweenCallback moveInCallBack, moveOutCallBack;
	
	
	private float originalX;
	private float originalY;
	private float targetX;
	private float targetY;
	
	private float duration;
	
	public NutMovingSprite(NutScreen screen, TextureRegion texture) {
		super(screen, texture);
	
		setupTween();
	}
	
	private void setupTween() {
		//
		Tween.registerAccessor(NutSprite.class, new SpriteAccessor());
		tweenManager = new TweenManager();
		
		
		moveInCallBack = new TweenCallback(){
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				//System.out.println("move in complete");
				moveOut();
			}
		};
		
		moveOutCallBack = new TweenCallback(){
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				//System.out.println("move out complete");
				moveIn();
			}
		};
		
		
		
	}
	
	private void moveIn(){
		Tween.to(this, SpriteAccessor.POSITION_XY, duration+MathUtils.random(-0.5f, 0.5f))
		.target(targetX+MathUtils.random(-0.5f, 0.5f), targetY+MathUtils.random(-0.5f, 0.5f))
		.ease(TweenEquations.easeInBack)
		.setCallback(moveInCallBack).setCallbackTriggers(TweenCallback.COMPLETE)
		.start(tweenManager);
	}
	
	private void moveOut(){
		Tween.to(this, SpriteAccessor.POSITION_XY, duration+MathUtils.random(-0.5f, 0.5f))
		.target(originalX+MathUtils.random(-0.5f, 0.5f), originalY+MathUtils.random(-0.5f, 0.5f))
		.ease(TweenEquations.easeInOutBack)
		.setCallback(moveOutCallBack).setCallbackTriggers(TweenCallback.COMPLETE)
		.start(tweenManager);
	}
	
	public void moveTo(float targetX, float targetY, float duration){
		this.targetX = targetX;
		this.targetY = targetY;
		originalX = getX();
		originalY = getY();
		
		this.duration = duration;
	}
	
	public void startTween(){
		moveIn();
	}
	
	@Override
	public void update(float delta){
		super.update(delta);
		
		if(tweenManager != null){
			tweenManager.update(delta);
		}
	}

}
