package com.nutslaboratory.nutlibgdxgameengine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class NutText extends NutSprite{
	
	
	private BitmapFont bitmapFont;
	private String text;
	private Color color;
	private GlyphLayout layout;
	
	private int widthLimit = 0;
	
	public NutText(NutScreen screen, BitmapFont bitmapFont, String text) {
		super(screen, new TextureRegion(new Texture(0, 0,Pixmap.Format.RGB565)));
		
		
		this.bitmapFont = bitmapFont;
		this.text = text;
		
		//
		layout = new GlyphLayout();
		layout.setText(bitmapFont, text);
		
		setWidth(layout.width);
		setHeight(layout.height);
		
		//setTexture(new TextureRegion(new Texture((int)getWidth(), (int)getHeight(), Pixmap.Format.Alpha)));
		setTexture(new TextureRegion(new Texture((int)getWidth(), (int)getHeight(), Pixmap.Format.RGB565)));
		
		
		setColor(Color.WHITE);
	}
	
	public NutText(NutScreen screen, BitmapFont bitmapFont, String text, int width, int height) {
		super(screen, new TextureRegion(new Texture(0, 0,Pixmap.Format.RGB565)));
		
		
		
		
		this.bitmapFont = bitmapFont;
		this.text = text;
		
		//
		layout = new GlyphLayout();
		layout.setText(bitmapFont, text);
		
		setWidth(width);
		setHeight(height);
		
		//setTexture(new TextureRegion(new Texture((int)getWidth(), (int)getHeight(), Pixmap.Format.Alpha)));
		setTexture(new TextureRegion(new Texture((int)getWidth(), (int)getHeight(), Pixmap.Format.RGB565)));
		
		setColor(Color.WHITE);
		
	}
	

	
	@Override
	public void draw(SpriteBatch spriteBatch, float xPos, float yPos){
		//super.draw(spriteBatch, xPos, yPos);
		
		bitmapFont.setColor(color);
		
		bitmapFont.draw(spriteBatch, text, xPos+((getWidth()-layout.width)/2), 
				(yPos+getHeight())-((getHeight()-layout.height)/2));
		
		
	}
	
	public void setText(String text){
		this.text = text;
		
		//
		layout.setText(bitmapFont, this.text);
		setWidth(layout.width);
		setHeight(layout.height);
	}
	
	private String replaceLast(String string, String from, String to) {
	     int lastIndex = string.lastIndexOf(from);
	     if (lastIndex < 0) return string;
	     String tail = string.substring(lastIndex).replaceFirst(from, to);
	     return string.substring(0, lastIndex) + tail;
	}
	
	public void setWidthLimit(int widthLimit){
		this.widthLimit = widthLimit;
		
		if(widthLimit != 0){
			//
			
			String newString = "";
			
			String[] words = text.split(" ");
			
			String lineString = "";
			
			int remainder = 0;
			for(int i = 0; i < words.length; i++){
				String tempWord = words[i];
				
				//Gdx.app.log("", tempWord);
				
				lineString += tempWord;
				lineString += " ";
				
				
				if(i == words.length-1){
					if(lineString.length() + remainder <= widthLimit){
						newString += lineString;
						break;
					}
				}
				
				if(lineString.length() + remainder > widthLimit){
					lineString = replaceLast(lineString, tempWord, "");
					//Gdx.app.log("", tempWord);
			
					newString += lineString;
					newString += "\n";
					newString += tempWord;
					newString += " ";
					
					remainder = tempWord.length()+1;
					
					lineString = "";
				}
			}
			
			this.text = newString;
		}
		
		setText(text);
	}
	
	public String getText(){
		return text;
	}
	
	public void setColor(Color color){
		this.color = color;
		
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setFont(BitmapFont bitmapFont){
		this.bitmapFont = bitmapFont;
	}
}
