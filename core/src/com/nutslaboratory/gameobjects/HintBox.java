package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutButton;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutText;

public class HintBox extends NutSprite{
	
	public static final int[] BIG_SIZE = {430, 177};
	public static final int[] MEDIUM_SIZE = {380, 157};
	public static final int[] SMALL_SIZE = {318, 131};
	
	private NutText text;
	private NutButton okButton;
	
	public HintBox(NutScreen screen, BitmapFont bitmapFont, String message, boolean hasOkButton, int[] size) {
		super(screen, AssetLoader.hintBoxTexture);
		
		//
		text = new NutText(screen, bitmapFont, message);
		addChild(text);
		
		//
		if(hasOkButton){
			createOkButton();
		}
		
		//
		setBoxSize(size);
	}
	
	private void setBoxSize(int[] size){
		setWidth(size[0]);
		setHeight(size[1]);
		
		if(size == BIG_SIZE){
			text.setWidthLimit(48);
			text.setPosition(50, getHeight()-getText().getHeight() - 30);
		}
		
		else if(size == MEDIUM_SIZE){
			text.setWidthLimit(40);
			text.setPosition(40, getHeight()-getText().getHeight() - 30);
		}
		
		else if(size == SMALL_SIZE){
			text.setWidthLimit(35);
			text.setPosition(35, getHeight()-getText().getHeight() - 30);
		}
		
		
		if(okButton != null){
			okButton.setPosition(getWidth()-30, -10);
		}
		
	}
	
	public NutText getText(){
		return text;
	}
	
	private void createOkButton() {
		okButton = new NutButton(getScreen(), AssetLoader.hintBoxOKButtonTexture,
				null, AssetLoader.hintBoxOKButtonTexture);
		okButton.setPosition(getWidth()-30, -10);
		okButton.setTouchSound(AssetLoader.buttonSound);
		addChild(okButton);
		
	}

	public NutButton getOkButton(){
		return okButton;
	}
	
	public void createHintArrow(int dir, boolean canMove, float x, float y){
		HintIndicator hintArrow = new HintIndicator(getScreen(), HintIndicator.ARROW_TYPE, 
				dir, canMove, x-getGlobalX(), y-getGlobalY());
		addChild(hintArrow);
	}
	
	public void createHintFinger(int dir, boolean canMove, float x, float y){
		HintIndicator hintFinger = new HintIndicator(getScreen(), HintIndicator.FINGER_TYPE, 
				dir, canMove, x-getGlobalX(), y-getGlobalY());
		addChild(hintFinger);
	}
	
	public void createBorderHor(float x, float y){
		NutSprite border = new NutSprite(getScreen(), AssetLoader.borderHorTexture);
		border.setPosition(x-getGlobalX(), y-getGlobalY());
		addChild(border);
	}
	
	public void createBorderVer(float x, float y){
		NutSprite border = new NutSprite(getScreen(), AssetLoader.borderVerTexture);
		border.setPosition(x-getGlobalX(), y-getGlobalY());
		addChild(border);
	}
	
	

}
