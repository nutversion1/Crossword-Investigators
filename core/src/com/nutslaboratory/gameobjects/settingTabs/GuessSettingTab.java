package com.nutslaboratory.gameobjects.settingTabs;

import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.gameobjects.WrongGuessLight;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;

public class GuessSettingTab extends SettingTab{

	Array<WrongGuessLight> wrongGuessLights = new Array<WrongGuessLight>();
	
	public GuessSettingTab(NutScreen screen, int[] values) {
		super(screen, AssetLoader.guessSettingTabTexture, values);
		
		createDecreaseButton(AssetLoader.decreaseLeftTexture, AssetLoader.decreaseLeftUnavailableTexture,
				1, 2, 0, 0);
		createIncreaseButton(AssetLoader.increaseRightTexture, AssetLoader.increaseRightUnavailableTexture,
				getWidth()-25, 2, 0, 0);
		
		removeWrongGuessLights();
		createWrongGuessLights();
	}
	
	@Override
	public void decrease(){
		super.decrease();
		
		removeWrongGuessLights();
		createWrongGuessLights();
	}
	
	@Override
	public void increase(){
		super.increase();
		
		removeWrongGuessLights();
		createWrongGuessLights();
	}
	
	@Override
	public void setCurrentIndex(int index){
		super.setCurrentIndex(index);
		
		removeWrongGuessLights();
		createWrongGuessLights();
	}
	
	private void createWrongGuessLights() {
		
		
		for(int i = 0; i < 5; i++){
			WrongGuessLight newWrongGuessLight = new WrongGuessLight(getScreen(),
					AssetLoader.wrongGuesslightBlankTexture, AssetLoader.wrongGuesslightRedTexture);
			int spaceX = (int) (newWrongGuessLight.getWidth() + 7);
			int offsetX = (int)((getWidth()/2+4) - (getCurrentIndex() * spaceX / 2));
			newWrongGuessLight.setPosition(offsetX + (i * spaceX), 17);
			addChild(newWrongGuessLight);
			wrongGuessLights.add(newWrongGuessLight);
			
			if(i+1 > getCurrentIndex()){
				newWrongGuessLight.setVisible(false);
			}
		}
	}
	
	private void removeWrongGuessLights() {
		for(NutSprite tempWrongGuessLight : wrongGuessLights){
			removeChild(tempWrongGuessLight);
		}
		
		wrongGuessLights.clear();
		
	}

}
