package com.nutslaboratory.gameobjects.settingTabs;

import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;

public class ExistSettingTab extends SettingTab{

	Array<NutSprite> existPoints = new Array<NutSprite>();
	
	public ExistSettingTab(NutScreen screen, int[] values){
		super(screen, AssetLoader.existSettingTabTexture, values);
		
		createDecreaseButton(AssetLoader.decreaseDownTexture, AssetLoader.decreaseDownUnavailableTexture,
				getWidth()-30, 4, 0, -5);
		createIncreaseButton(AssetLoader.increaseUpTexture, AssetLoader.increaseUpUnavailableTexture,
				getWidth()-30, 47, 0, 5);
		
		removeExistPoints();
		createExistPoints();
	}
	
	@Override
	public void decrease(){
		super.decrease();
		
		removeExistPoints();
		createExistPoints();
	}
	
	@Override
	public void increase(){
		super.increase();
		
		removeExistPoints();
		createExistPoints();
	}
	
	@Override
	public void setCurrentIndex(int index){
		super.setCurrentIndex(index);
		
		removeExistPoints();
		createExistPoints();
	}
	
	private void createExistPoints() {
		for(int i = 0; i < 3; i++){
			NutSprite newExistPoint = new NutSprite(getScreen(), AssetLoader.existPointTexture);
			newExistPoint.setPosition(7+ (i * 40), 10);
			addChild(newExistPoint);
			existPoints.add(newExistPoint);
			
			if(i+1 > getCurrentIndex()-1){
				newExistPoint.setVisible(false);
			}
		}
		
	}
	
	private void removeExistPoints() {
		for(NutSprite tempExistPoint : existPoints){
			removeChild(tempExistPoint);
		}
		
		existPoints.clear();
		
	}

}
