package com.nutslaboratory.gameobjects.settingTabs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;

public class HintSettingTab extends SettingTab {

	Array<NutSprite> hintPoints = new Array<NutSprite>();
	
	public HintSettingTab(NutScreen screen, int[] values) {
		super(screen, AssetLoader.hintSettingTabTexture, values);
		
		createDecreaseButton(AssetLoader.decreaseDownTexture, AssetLoader.decreaseDownUnavailableTexture,
				getWidth()-30, 4, 0, -5);
		createIncreaseButton(AssetLoader.increaseUpTexture, AssetLoader.increaseUpUnavailableTexture,
				getWidth()-30, 47, 0, 5);
		
		removeHintPoints();
		createHintPoints();
	}
	
	@Override
	public void decrease(){
		super.decrease();
		
		removeHintPoints();
		createHintPoints();
	}
	
	@Override
	public void increase(){
		super.increase();
		
		removeHintPoints();
		createHintPoints();
	}
	
	@Override
	public void setCurrentIndex(int index){
		super.setCurrentIndex(index);
		
		removeHintPoints();
		createHintPoints();
	}
	
	private void createHintPoints() {
		
		for(int i = 0; i < 10; i++){
			
			TextureRegion texture = null;
			if(i == 4 || i == 9){
				texture = AssetLoader.hintPointTiltTexture;
			}
			else{
				texture = AssetLoader.hintPointOneTexture;
			}
			
			
			NutSprite newHintPoint = new NutSprite(getScreen(), texture);
			
			if(i < 5){
				if(i == 4){
					newHintPoint.setPosition(29, 15);
				}else{
					newHintPoint.setPosition(34+ (i * 5), 15);
				}
			}else{
				if(i == 9){
					newHintPoint.setPosition(65, 15);
				}else{
					newHintPoint.setPosition(34+ (i * 5) + 10, 15);
				}
				
			}
			
			
			addChild(newHintPoint);
			hintPoints.add(newHintPoint);
			
			if(i+1 > getCurrentIndex()-1){
				newHintPoint.setVisible(false);
			}
		}
		
	}
	
	private void removeHintPoints() {
		for(NutSprite tempHintPoint : hintPoints){
			removeChild(tempHintPoint);
		}
		
		hintPoints.clear();
		
	}
}
