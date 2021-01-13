package com.nutslaboratory.nutlibgdxgameengine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.helpers.AssetLoader;

public class NutAnimationSprite extends NutSprite {
	
	private Animation animation;
	private float animateTime;
	
	private boolean hasStarted = false;
	
	public NutAnimationSprite(NutScreen screen, Animation.PlayMode playMode, float frameDuration, TextureRegion... frames) {
		super(screen, frames[0]);
		
		animation = new Animation(frameDuration, frames);
		animation.setPlayMode(playMode);
		
		setVisible(false);
	}
	
	public NutAnimationSprite(NutScreen screen, Animation.PlayMode playMode, float frameDuration, int frameColumns, int frameRows, Texture spriteSheet) {
		super(screen, AssetLoader.blankTexture);
		
		int frameWidth = spriteSheet.getWidth() / frameColumns;
		int frameHeight = spriteSheet.getHeight() / frameRows;
		
		TextureRegion[][] temp = TextureRegion.split(spriteSheet, frameWidth, frameHeight);
		
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for(int row = 0; row < frameRows; row++){
			for(int column = 0; column < frameColumns; column++){
				frames.add(temp[row][column]);
			}
		}
		
		animation = new Animation(frameDuration, frames);
		animation.setPlayMode(playMode);
		
		setTexture(animation.getKeyFrame(animateTime));
		
		setVisible(false);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		if(hasStarted){
			animateTime += delta;
		}
		setTexture(animation.getKeyFrame(animateTime));
	}
	
	public void start(){
		hasStarted = true;
		
		setVisible(true);
		
		animateTime = 0;
	}
	
	public boolean isFinished(){
		return animation.isAnimationFinished(animateTime);
	}
	
	
	

}
