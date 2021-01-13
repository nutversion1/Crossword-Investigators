package com.nutslaboratory.gameobjects.settingTabs;

import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;

public class ShowSettingTab extends SettingTab {
	
	Array<NutSprite> showPoints = new Array<NutSprite>();
	
	public ShowSettingTab(NutScreen screen, int[] values) {
		super(screen, AssetLoader.showSettingTabTexture, values);
		
		createDecreaseButton(AssetLoader.decreaseDownTexture, AssetLoader.decreaseDownUnavailableTexture,
				getWidth()-30, 4, 0, -5);
		createIncreaseButton(AssetLoader.increaseUpTexture, AssetLoader.increaseUpUnavailableTexture,
				getWidth()-30, 47, 0, 5);
		
		removeShowPoints();
		createShowPoints();
	}
	
	@Override
	public void decrease(){
		super.decrease();
		
		removeShowPoints();
		createShowPoints();
	}
	
	@Override
	public void increase(){
		super.increase();
		
		removeShowPoints();
		createShowPoints();
	}
	
	@Override
	public void setCurrentIndex(int index){
		super.setCurrentIndex(index);
		
		removeShowPoints();
		createShowPoints();
	}
	
	private void createShowPoints() {
		for(int i = 0; i < 5; i++){
			NutSprite newShowPoint = new NutSprite(getScreen(), AssetLoader.showPointTexture);
			newShowPoint.setPosition(8+ (i * 16), 8);
			addChild(newShowPoint);
			showPoints.add(newShowPoint);
			
			if(i+1 > getCurrentIndex()){
				newShowPoint.setVisible(false);
			}
		}
		
	}
	
	private void removeShowPoints() {
		for(NutSprite tempShowPoint : showPoints){
			removeChild(tempShowPoint);
		}
		
		showPoints.clear();
		
	}

}
