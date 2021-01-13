package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutButton;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutText;

public class BackMenu extends NutSprite{

	private NutText text;
	
	private NutButton yesButton;
	private NutButton noButton;
	
	public BackMenu(NutScreen screen, BitmapFont textFont, String message) {
		super(screen, AssetLoader.backMenuTexture);
		
		createText(textFont, message);
		
		createYesButton();
		createNoButton();
	}

	private void createText(BitmapFont textFont, String message) {
		text = new NutText(getScreen(), textFont, message);
		text.setPosition(getWidth()/2 - text.getWidth()/2 ,
				getHeight()/2 - text.getHeight()/2 + 33);
		text.setColor(Color.WHITE);
		addChild(text);
		
	}

	private void createYesButton() {
		yesButton = new NutButton(getScreen(), AssetLoader.yesButtonTexture, null, null);
		yesButton.setPosition(getWidth()/2 - yesButton.getWidth()/2 - 70, 30);
		addChild(yesButton);
		yesButton.setTouchSound(AssetLoader.buttonSound);
	}
	
	private void createNoButton() {
		noButton = new NutButton(getScreen(), AssetLoader.noButtonTexture, null, null);
		noButton.setPosition(getWidth()/2 - noButton.getWidth()/2 + 70, 30);
		addChild(noButton);
		noButton.setTouchSound(AssetLoader.buttonSound);
		
	}
	
	public NutButton getYesButton(){
		return yesButton;
	}
	
	public NutButton getNoButton(){
		return noButton;
	}

}
