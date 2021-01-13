package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutTextButton;

public class LetterButton extends NutTextButton{

	private int num;
	
	public LetterButton(NutScreen screen, TextureRegion defaultTexture,
			TextureRegion unavailableTexture, TextureRegion selectTexture,
			BitmapFont bitmapFont, String string, int num) {
		super(screen, defaultTexture, unavailableTexture, selectTexture, bitmapFont, string);
		
		this.num = num;
	}
	
	public int getNum(){
		return num;
	}

}
