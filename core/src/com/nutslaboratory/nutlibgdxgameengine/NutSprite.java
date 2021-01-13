package com.nutslaboratory.nutlibgdxgameengine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class NutSprite {
	protected TextureRegion texture;
	private float x = 0;
	private float y = 0;
	private float width;
	private float height;
	private boolean visible = true;
	private String tag = "none";
	
	private Array<NutSprite> children = new Array<NutSprite>();
	private NutSprite parent;
	
	private NutSpriteLayer layer;
	
	private Rectangle collisionRect;
	private float collisionRectOffsetX;
	private float collisionRectOffsetY;
	
	private NutScreen screen;
	
	public NutSprite(NutScreen screen, TextureRegion texture){
		this.screen = screen;
		
		this.texture = texture;
		this.width = texture.getRegionWidth();
		this.height = texture.getRegionHeight();
		
		collisionRect = new Rectangle(x,y,width,height);
	}
	
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void setTexture(TextureRegion texture){
		this.texture = texture;
		
		this.width = texture.getRegionWidth();
		this.height = texture.getRegionHeight();
	}
	
	public TextureRegion getTexture(){
		return texture;
	}
	
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}
	
	public boolean getVisible(){
		return visible;
	}
	
	public void setWidth(float width){
		this.width = width;
	}
	
	public float getWidth(){
		return width;
	}
	
	public void setHeight(float height){
		this.height = height;
	}
	
	public float getHeight(){
		return height;
	}
	
	public void setCollisionRect(float offsetX, float offsetY, float width, float height){
		collisionRectOffsetX = offsetX;
		collisionRectOffsetY = offsetY;
		
		collisionRect.set(0, 0, width, height);
	}
	
	public Rectangle getCollisionRect(){
		return collisionRect;
	}
	
	private void updateCollisionRect(){
		collisionRect.setCenter((getGlobalX()+width/2) + collisionRectOffsetX,
				(getGlobalY()+height/2) + collisionRectOffsetY);
	}
	
	public void update(float delta) {
		
		//update child
		for(NutSprite child : children){
			child.update(delta);
		}
		
		//update collision rect
		updateCollisionRect();
	}
	
	public void draw(SpriteBatch spriteBatch, float xPos, float yPos) {
		
		//draw this sprite
		spriteBatch.draw(texture, xPos, yPos, width, height);
		
		//draw child
		for(NutSprite child : children){
			if(child.getVisible()){
				child.draw(spriteBatch, getGlobalX()+child.getX(), getGlobalY()+child.getY());
			}
		}
	}
	
	public void drawDebug(ShapeRenderer shapeRenderer){
		//draw this sprite
		shapeRenderer.rect(getCollisionRect().x,
					getCollisionRect().y,
					getCollisionRect().width,
					getCollisionRect().height);
		
		
		//draw child
		for(NutSprite child : children){
			if(child.getVisible()){
				child.drawDebug(shapeRenderer);
			}
		}
	}
	
	public boolean isCollidePoint(float pointX, float pointY){
		return pointX >= collisionRect.x && pointX <= collisionRect.x+collisionRect.width &&
				pointY >= collisionRect.y && pointY <= collisionRect.y+collisionRect.height;
		
	}
	
	public void addChild(NutSprite child){
		children.add(child);
		
		child.parent = this;
	}
	
	
	public void removeChild(NutSprite child){
		children.removeValue(child, true);
		
		child.parent = null;
	}
	
	public void moveChildToTop(NutSprite sprite){
		if(children.contains(sprite, true)){
			children.removeValue(sprite, true);
			children.add(sprite);
		}
	}
	
	public NutSprite getParent(){
		return parent;
	}
	
	public Array<NutSprite> getChildren(){
		return children;
	}
	
	public void setTag(String tag){
		this.tag = tag;
	}
	
	public String getTag(){
		return tag;
	}
	
	public NutScreen getScreen(){
		return screen;
	}
	
	public float getGlobalX(){
		if(parent == null){
			return getX();
		}else{
			float totalX = 0;
			
			NutSprite tempSprite = this;
			do{
				totalX += tempSprite.getX();
				
				tempSprite = tempSprite.getParent();
				
			}while(tempSprite != null);
			
			return totalX;
		}
		
		 
	}
	
	public float getGlobalY(){
		if(parent == null){
			return getY();
		}else{
			float totalY = 0;
			
			NutSprite tempSprite = this;
			do{
				totalY += tempSprite.getY();
				
				tempSprite = tempSprite.getParent();
				
			}while(tempSprite != null);
			
			return totalY;
		}
	}
	
	
	
	
	
}
