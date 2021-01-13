package com.nutslaboratory.gameobjects.settingTabs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutButton;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutText;

public abstract class SettingTab extends NutSprite{
	
	protected NutButton decreaseButton, increaseButton;;
	private NutText debugText;
	private int minIndex = 1;
	private int maxIndex;
	private int currentIndex = 1;
	private int value;
	
	private int[] values;
	
	private String name;
	
	public SettingTab(NutScreen screen, TextureRegion texture, int[] values) {
		super(screen, texture);
		
		this.values = values;
		maxIndex = values.length;
		value = values[currentIndex-1];
		
		createDebugText();
		
		setValue();
	}
	
	private void createDebugText() {
		debugText = new NutText(getScreen(), AssetLoader.calibri20Font, currentIndex + " = " + value);
		debugText.setPosition(0, -15);
		addChild(debugText);
		debugText.setVisible(false);
		
	}

	protected void createDecreaseButton(TextureRegion defTexture, TextureRegion unaTexture, 
			float x, float y, float offsetX, float offsetY) {
		decreaseButton = new NutButton(getScreen(), defTexture, unaTexture, null);
		decreaseButton.setPosition(x, y);
		addChild(decreaseButton);
		
		decreaseButton.setTouchSound(AssetLoader.buttonSound);
		
		decreaseButton.setCollisionRect(offsetX, offsetY, 
				decreaseButton.getWidth()+20, decreaseButton.getHeight()+20);
		
	}
	
	
	protected void createIncreaseButton(TextureRegion defTexture, TextureRegion unaTexture,
			float x, float y, float offsetX, float offsetY){
		increaseButton = new NutButton(getScreen(), defTexture, unaTexture, null);
		increaseButton.setPosition(x, y);
		addChild(increaseButton);
		
		increaseButton.setTouchSound(AssetLoader.buttonSound);
		
		increaseButton.setCollisionRect(offsetX, offsetY, 
				increaseButton.getWidth()+20, increaseButton.getHeight()+20);
	}
	
	public void decrease(){
		if(currentIndex > minIndex){
			currentIndex--;
			setValue();
			setDebugText(currentIndex + " = " + value);
			
			
		}
		
		
	}
	
	public void increase(){
		if(currentIndex < maxIndex){
			currentIndex++;
			setValue();
			setDebugText(currentIndex + " = " + value);
			
			
		}
		
	}
	
	public void setCurrentIndex(int index){
		currentIndex = index;
		
		setValue();
		
		setDebugText(currentIndex + " = " + value);
	}
	
	public int getCurrentIndex(){
		return currentIndex;
	}
	
	public int getValue(){
		return value;
	}
	
	private void setValue(){
		value = values[currentIndex-1];
		
		updateButtonState();
	}
	
	
	
	private void updateButtonState() {
		if(decreaseButton == null || increaseButton == null){
			return;
		}
		
		decreaseButton.unselect();
		increaseButton.unselect();
		
		if(currentIndex == minIndex){
			decreaseButton.unavailable();
		}
		else if(currentIndex == maxIndex){
			increaseButton.unavailable();
		}
		
	}

	private void setDebugText(String string){
		debugText.setText(string);
	}
	
	public NutButton getDecreaseButton(){
		return decreaseButton;
	}
	
	public NutButton getIncreaseButton(){
		return increaseButton;
	}

	

}
