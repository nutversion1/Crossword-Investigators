package com.nutslaboratory.screens;

import com.badlogic.gdx.math.MathUtils;
import com.nutslaboratory.cwi.CWIGame;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutGame;
import com.nutslaboratory.nutlibgdxgameengine.NutGrade;
import com.nutslaboratory.nutlibgdxgameengine.NutGrade.Style;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutSpriteLayer;

public class SoundSettingScreen extends NutScreen{

	
	private static final int MIN_KNOB_X_POS = 80;
	private static final int MAX_KNOB_X_POS = 370;
	
	private NutSprite background;
	
	private NutGrade musicSlider, soundSlider;
	
	private NutSprite musicKnob, soundKnob;
	
	private boolean hasTouchMusicKnob = false;
	private boolean hasTouchSoundKnob = false;
	
	private NutSpriteLayer spriteLayer1 = new NutSpriteLayer(this, "layer1");
	
	public SoundSettingScreen(NutGame game) {
		super(game);
		
	
		
	}
	
	@Override
	public void show(){
		addSpriteLayer(spriteLayer1);
		
		createBackground();
		createMusicSlider();
		createSoundSlider();
		
	}
	
	private void createMusicSlider() {
		//create slider
		musicSlider = new NutGrade(this, AssetLoader.soundSliderBaseTexture, AssetLoader.soundSliderTexture,
				Style.RIGHT_TO_LEFT);
		musicSlider.setPosition(80, 390);
		spriteLayer1.addSprite(musicSlider);
		
		//create knob
		musicKnob = new NutSprite(this, AssetLoader.soundKnobTexture);
		musicKnob.setPosition(((NutGame.getMusicVolume()*100) / 100 * 290) +(MAX_KNOB_X_POS-290), 
				385);
		musicKnob.setCollisionRect(0, 0, musicKnob.getWidth()+20, musicKnob.getHeight()+20);
		spriteLayer1.addSprite(musicKnob);
		
		//calculate percent size of music slider grade
		float percentSize = (290 - (MAX_KNOB_X_POS - musicKnob.getX())) / 290 * 100;
		musicSlider.setPercentSize(percentSize);
		
	}
	
	private void createSoundSlider() {
		//create slider
		soundSlider = new NutGrade(this, AssetLoader.soundSliderBaseTexture, AssetLoader.soundSliderTexture,
				Style.RIGHT_TO_LEFT);
		soundSlider.setPosition(80, 295);
		spriteLayer1.addSprite(soundSlider);
		
		//create knob
		soundKnob = new NutSprite(this, AssetLoader.soundKnobTexture);
		soundKnob.setPosition(((NutGame.getSoundVolume()*100) / 100 * 290) +(MAX_KNOB_X_POS-290), 
				290);
		soundKnob.setCollisionRect(0, 0, musicKnob.getWidth()+20, musicKnob.getHeight()+20);
		spriteLayer1.addSprite(soundKnob);
		
		//calculate percent size of sound slider grade
		float percentSize = (290 - (MAX_KNOB_X_POS - soundKnob.getX())) / 290 * 100;
		soundSlider.setPercentSize(percentSize);
		
	}

	private void createBackground() {
		background = new NutSprite(this, AssetLoader.soundSettingBackgroundTexture);
		spriteLayer1.addSprite(background);
		
	}

	
	@Override
	public void update(float delta){
		super.update(delta);
		
		if(hasTouchMusicKnob){
			//set knob position
			musicKnob.setPosition(MathUtils.clamp(getTouchX()-musicKnob.getWidth()/2, MIN_KNOB_X_POS, MAX_KNOB_X_POS),
					musicKnob.getY());
			
			//calculate percent size of music slider grade
			float percentSize = (290 - (MAX_KNOB_X_POS - musicKnob.getX())) / 290 * 100;
			musicSlider.setPercentSize(percentSize);
			
			//set volume
			NutGame.setMusicVolume(percentSize/100f);
			AssetLoader.backgroundMusic.setVolume((CWIGame.getMusicVolume()));
			AssetLoader.playMusic.setVolume((CWIGame.getMusicVolume()));
			
		}
		if(hasTouchSoundKnob){
			//set knob position
			soundKnob.setPosition(MathUtils.clamp(getTouchX()-soundKnob.getWidth()/2, MIN_KNOB_X_POS, MAX_KNOB_X_POS),
					soundKnob.getY());
			
			//calculate percent size of sound slider grade
			float percentSize = (290 - (MAX_KNOB_X_POS - soundKnob.getX())) / 290 * 100;
			soundSlider.setPercentSize(percentSize);
			
			//set volume
			NutGame.setSoundVolume(percentSize/100f);
		}
	}
	
	@Override
	public void pressBack() {
		getGame().setScreen(new MainMenuScreen(getGame()));
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		super.touchDown(screenX, screenY, pointer, button);
		
		if(musicKnob.isCollidePoint(getTouchX(), getTouchY())){
			hasTouchMusicKnob = true;
		}
		
		if(soundKnob.isCollidePoint(getTouchX(), getTouchY())){
			hasTouchSoundKnob = true;
		}
		
		return false;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		super.touchUp(screenX, screenY, pointer, button);
		
		hasTouchMusicKnob = false;
		hasTouchSoundKnob = false;
	    
		//save preference
		CWIGame.saveSoundSettings();
		
		return false;
	}

}
