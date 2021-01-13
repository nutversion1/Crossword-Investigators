package com.nutslaboratory.nutlibgdxgameengine;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class NutButton extends NutSprite {
	
	public static final String DEFAULT_STATE = "default_state";
	public static final String UNAVAILABLE_STATE = "unavailable_state";
	public static final String SELECT_STATE = "select_state";

	private TextureRegion defaultTexture, unavailableTexture, selectTexture;
	
	protected String state = DEFAULT_STATE;
	private String name = "";
	
	private Sound touchSound;
	
	private NutScreen screen;
	
	private boolean isEnabled = true;
	private boolean isPressing = false;
	private boolean isClicked = false;
	
	public NutButton(NutScreen screen, TextureRegion defaultTexture, TextureRegion unavailableTexture, TextureRegion selectTexture) {
		super(screen, defaultTexture);
		
		this.screen = screen;
		
		this.defaultTexture = defaultTexture;
		this.unavailableTexture = unavailableTexture;
		this.selectTexture = selectTexture;
		
	}
	
	protected void setState(String state){
		if(this.state.equals(state)){
			return;
		}
		
		this.state = state;
		
		if(state.equals(DEFAULT_STATE)){
			setTexture(defaultTexture);
		}else if(state.equals(UNAVAILABLE_STATE)){
			setTexture(unavailableTexture);
		}else if(state.equals(SELECT_STATE)){
			setTexture(selectTexture);
		}
		
	}
	
	public String getState(){
		return state;
	}
	
	public void select(){
		setState(SELECT_STATE);
	}
	
	public void unselect(){
		setState(DEFAULT_STATE);
	}
	
	public void unavailable(){
		setState(UNAVAILABLE_STATE);
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setEnable(boolean b){
		isEnabled = b;
	}
	
	public void update(float delta){
		super.update(delta);
	}
	
	public void pressDown(){
		isPressing = true;
		
		//play sound
    	if(touchSound != null){
    		touchSound.play(NutGame.getSoundVolume());
    	}
    	
    	
    	//set position
    	setPosition(getX()+5, getY()-5);
    	
	}
	
	public void pressUp(){
		isPressing = false;
		
		setPosition(getX()-5, getY()+5);
	}
	
	public void click(){
		isClicked = true;
	}
	
	public void unclick(){
		isClicked = false;
	}
	
	public boolean isClicked(){
		return isClicked;
	}
	
	public boolean isEnabled(){
		return isEnabled;
	}
	
	public boolean isPressing(){
		return isPressing;
	}
	
	public boolean checkLayer() {
		//find top influence sprite layer
		NutSpriteLayer topInfluenceSpriteLayer = null;
		for(int i = getScreen().getSpriteLayers().size-1; i >= 0; i--){
			NutSpriteLayer tempSpriteLayer = getScreen().getSpriteLayers().get(i);
			
			if(tempSpriteLayer.isInfluence()){
				topInfluenceSpriteLayer = tempSpriteLayer;
				break;
			}
				 
		}
		
		
		//don't have influence sprite layer
		if(topInfluenceSpriteLayer == null){
			return true;
		//have influence sprite layer
		}else{
			//get most parent of the sprite
			NutSprite tempSprite = this;
			while(tempSprite.getParent() != null){
				tempSprite = tempSprite.getParent();
			}
			
			//loop each layer
			for(int i = getScreen().getSpriteLayers().size-1; i >= 0; i--){
				NutSpriteLayer tempSpriteLayer = getScreen().getSpriteLayers().get(i);
				
				//check that this layer have this sprite
				if(tempSpriteLayer.getSprites().contains(tempSprite, false)){
					return true;
				}
				
				//pass top influence sprite layer
				if(tempSpriteLayer == topInfluenceSpriteLayer){
					return false;
				}
			}
			
			
		}
		
		return false;

	}

	public void setTouchSound(Sound sound){
		touchSound = sound;
	}

}
