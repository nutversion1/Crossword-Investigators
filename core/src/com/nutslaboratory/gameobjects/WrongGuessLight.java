package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;

public class WrongGuessLight extends NutSprite{
	
	public static final int BLANK_STATE = 0;
	public static final int RED_STATE = 1;
	
	private TextureRegion blankTexture;
	private TextureRegion redTexture;
	
	private int state;
	
	public WrongGuessLight(NutScreen screen, TextureRegion blankTexture, TextureRegion redTexture) {
		super(screen, blankTexture);

		this.blankTexture = blankTexture;
		this.redTexture = redTexture;
		
		state = BLANK_STATE;
	}
	
	public void red(){
		setTexture(redTexture);
		
		state = RED_STATE;
	}
	
	public int getState(){
		return state;
	}
	

}
