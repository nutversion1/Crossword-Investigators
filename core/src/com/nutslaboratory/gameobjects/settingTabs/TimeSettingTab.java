package com.nutslaboratory.gameobjects.settingTabs;

import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;

public class TimeSettingTab extends SettingTab{

	Array<NutSprite> timePoints = new Array<NutSprite>();
	
	public TimeSettingTab(NutScreen screen, int[] values) {
		super(screen, AssetLoader.timeSettingTabTexture, values);
		
		createDecreaseButton(AssetLoader.decreaseDownTexture, AssetLoader.decreaseDownUnavailableTexture,
				getWidth()-30, 7, 0, -5);
		createIncreaseButton(AssetLoader.increaseUpTexture, AssetLoader.increaseUpUnavailableTexture,
				getWidth()-30, 50, 0, 5);
		
		removeTimePoints();
		createTimePoints();
	}
	
	@Override
	public void decrease(){
		super.decrease();
		
		removeTimePoints();
		createTimePoints();
	}
	
	@Override
	public void increase(){
		super.increase();
		
		removeTimePoints();
		createTimePoints();
	}
	
	@Override
	public void setCurrentIndex(int index){
		super.setCurrentIndex(index);
		
		removeTimePoints();
		createTimePoints();
	}
	
	private void createTimePoints() {
		for(int i = 0; i < 5; i++){
			NutSprite newTimePoint = new NutSprite(getScreen(), AssetLoader.timePointTexture);
			newTimePoint.setPosition(56+ (i * 23), 13);
			addChild(newTimePoint);
			timePoints.add(newTimePoint);
			
			if(i+1 > getCurrentIndex()){
				newTimePoint.setVisible(false);
			}
		}
		
	}
	
	private void removeTimePoints() {
		for(NutSprite tempTimePoint : timePoints){
			removeChild(tempTimePoint);
		}
		
		timePoints.clear();
		
	}

}
