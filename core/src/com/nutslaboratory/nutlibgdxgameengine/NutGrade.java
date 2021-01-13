package com.nutslaboratory.nutlibgdxgameengine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class NutGrade extends NutSprite{

	public enum Style{
		TOP_TO_BOTTOM, RIGHT_TO_LEFT;
	}
	private Style style;
	
	private Texture foreTexture;
	
	private float percentSize = 100f;
	
	private float foreOffsetX;
	private float foreOffsetY;
	
	
	public NutGrade(NutScreen screen, TextureRegion baseTexture, Texture foreTexture, Style style) {
		super(screen, baseTexture);
		
		this.foreTexture = foreTexture;
		this.style = style;
	}
	
	public void setPercentSize(float percentSize){
		if(percentSize < 0){
			percentSize = 0;
		}else if(percentSize > 100){
			percentSize = 100;
		}
		
		this.percentSize = percentSize;
	}
	
	public float getPercentSize(){
		return percentSize;
	}
	
	public void setForeOffsetX(float foreOffsetX){
		this.foreOffsetX = foreOffsetX;
	}
	
	public void setForeOffsetY(float foreOffsetY){
		this.foreOffsetY = foreOffsetY;
	}
	
	@Override
	public void draw(SpriteBatch batch, float xPos, float yPos){
		super.draw(batch, xPos, yPos);
		
		if(style == Style.TOP_TO_BOTTOM){
			float size = (percentSize/100f)*foreTexture.getHeight();
			
			batch.draw(foreTexture, getX()+foreOffsetX, getY()+foreOffsetY,
					0, 
					foreTexture.getHeight()-(int)size, 
					foreTexture.getWidth(),
					(int)size);
			
		}else if(style == Style.RIGHT_TO_LEFT){
			float size = (percentSize/100f)*foreTexture.getWidth();
			
			batch.draw(foreTexture, getX()+foreOffsetX, getY()+foreOffsetY,
					0, 
					0, 
					(int)size,
					foreTexture.getHeight());
		}
		
	}

}
