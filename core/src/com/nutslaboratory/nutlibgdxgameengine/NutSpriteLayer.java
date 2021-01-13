package com.nutslaboratory.nutlibgdxgameengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class NutSpriteLayer {
	private Array<NutSprite> sprites = new Array<NutSprite>();
	
	private boolean isInfluence = false;
	private boolean isOverlay = false;
	
	private NutScreen screen;
	
	private String tag = "";
	

	
	public NutSpriteLayer(NutScreen screen, String tag){
		this.screen = screen;
		this.tag = tag;
	
	}
	
	public void addSprite(NutSprite sprite){
		sprites.add(sprite);
	}
	
	public void removeSprite(NutSprite sprite){
		sprites.removeValue(sprite, true);
		
	}
	
	public void moveSpriteToTop(NutSprite sprite){
		if(sprites.contains(sprite, true)){
			sprites.removeValue(sprite, true);
			sprites.add(sprite);
		}
	}
	
	public void showSprites(){
		System.out.println("show sprites "+sprites.size+":");
		for(NutSprite sprite : sprites){
			System.out.println(sprite.getClass());
		}
		System.out.println("****************");
	}
	
	public void updateSprites(float delta){
		for(NutSprite tempSprite : sprites){
			tempSprite.update(delta);
		}
	}
	
	public void drawOverlay(){
		//
		if(isOverlay){
			screen.getShaperRenderer().setProjectionMatrix(screen.getCamera().projection);
			screen.getShaperRenderer().setTransformMatrix(screen.getCamera().view);
			
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			
			screen.getShaperRenderer().begin(ShapeRenderer.ShapeType.Filled);
			
			screen.getShaperRenderer().setColor(0, 0, 0, 0.6f);
			screen.getShaperRenderer().rect(0, 0, screen.getWorldWidth(), screen.getWorldHeight());
			
			screen.getShaperRenderer().end();
			
			Gdx.gl.glDisable(GL20.GL_BLEND);
		}
	}
	
	public void drawSprites(SpriteBatch spriteBatch){
		
		
		
		//
		spriteBatch.setProjectionMatrix(screen.getCamera().projection);
		spriteBatch.setTransformMatrix(screen.getCamera().view);
		spriteBatch.begin();
		
		for(NutSprite tempSprite : sprites){
			if(tempSprite.getVisible()){
				tempSprite.draw(spriteBatch, tempSprite.getX(), tempSprite.getY());
			}
		}
		
		spriteBatch.end();
		
	}
	
	public void drawDebug(){
		//
		
		screen.getShaperRenderer().setProjectionMatrix(screen.getCamera().projection);
		screen.getShaperRenderer().setTransformMatrix(screen.getCamera().view);
		screen.getShaperRenderer().setColor(Color.WHITE);
		screen.getShaperRenderer().begin(ShapeRenderer.ShapeType.Line);
		
		
		for(NutSprite tempSprite : sprites){
			if(tempSprite.getVisible()){
				tempSprite.drawDebug(screen.getShaperRenderer());
			}
		}
		
		
		screen.getShaperRenderer().end();
		
	}
	
	public Array<NutSprite> getSprites(){
		return sprites;
	}
	
	public void setInfluence(boolean b){
		isInfluence = b;
	}
	
	public boolean isInfluence(){
		return isInfluence;
	}
	
	public void setOverlay(boolean b){
		isOverlay = b;
	}
	
	public boolean isOverlay(){
		return isOverlay;
	}
	
	public String getTag(){
		return tag;
	}
}
