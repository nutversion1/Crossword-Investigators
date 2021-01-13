package com.nutslaboratory.nutlibgdxgameengine;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class NutTextButton extends NutButton{

	private NutText text; 
	String string;
	BitmapFont bitmapFont;
	
	public NutTextButton(NutScreen screen, TextureRegion defaultTexture, TextureRegion unavailableTexture, TextureRegion selectTexture,
			BitmapFont bitmapFont, String string) {
		super(screen, defaultTexture, unavailableTexture, selectTexture);
		
		this.bitmapFont = bitmapFont;
		this.string = string;

		
		text = new NutText(screen, bitmapFont, string);
		text.setText(string);
		addChild(text);
		text.setPosition(getWidth()/2 - text.getWidth()/2, getHeight()/2 - text.getHeight()/2);
		
		
	}

	public NutText getText(){
		return text;
	}

}
