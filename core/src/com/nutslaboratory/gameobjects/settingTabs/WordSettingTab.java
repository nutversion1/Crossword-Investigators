package com.nutslaboratory.gameobjects.settingTabs;

import com.badlogic.gdx.graphics.Color;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutText;

public class WordSettingTab extends SettingTab {

	private NutText valueText;
	
	public WordSettingTab(NutScreen screen, int[] values) {
		super(screen, AssetLoader.wordSettingTabTexture, values);
		
		createDecreaseButton(AssetLoader.decreaseDownTexture, AssetLoader.decreaseDownUnavailableTexture,
				getWidth()-30, 4, 0, -5);
		createIncreaseButton(AssetLoader.increaseUpTexture, AssetLoader.increaseUpUnavailableTexture,
				getWidth()-30, 47, 0, 5);
		
		createValueText();
	}
	
	private void createValueText() {
		valueText = new NutText(getScreen(), AssetLoader.charlemagne32Font, String.valueOf(getValue()));
		valueText.setPosition(getWidth()/2-valueText.getWidth()/2 - 16, getHeight()/2 - 25);
		valueText.setColor(Color.BLACK);
		addChild(valueText);
		
	}
	
	@Override
	public void decrease(){
		super.decrease();
		
		valueText.setText(String.valueOf(getValue()));
		valueText.setPosition(getWidth()/2-valueText.getWidth()/2 - 16, getHeight()/2 - 25);
	}
	
	@Override
	public void increase(){
		super.increase();
		
		valueText.setText(String.valueOf(getValue()));
		valueText.setPosition(getWidth()/2-valueText.getWidth()/2 - 16, getHeight()/2 - 25);
	}
	
	@Override
	public void setCurrentIndex(int index){
		super.setCurrentIndex(index);
		
		valueText.setText(String.valueOf(getValue()));
		valueText.setPosition(getWidth()/2-valueText.getWidth()/2 - 16, getHeight()/2 - 25);
	}


}
