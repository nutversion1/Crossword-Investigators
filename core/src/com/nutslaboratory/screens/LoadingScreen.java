package com.nutslaboratory.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutGame;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSpriteLayer;
import com.nutslaboratory.nutlibgdxgameengine.NutText;

public class LoadingScreen extends NutScreen{
	
	//
	private float progress;
	
	private BitmapFont bitmapFont;
	
	private NutText loadingLabel;
	private NutText progressLabel;
	
	private NutSpriteLayer spriteLayer1 = new NutSpriteLayer(this, "layer1");

	public LoadingScreen(NutGame game) {
		super(game);
		
		setBackgroundColor(new Color(0/255f,0/255f,0/255f,1f));
		
		addSpriteLayer(spriteLayer1);
		
		bitmapFont = new BitmapFont();
		
		createProgressLabel();
		
		
	}
	
	
	private void createProgressLabel() {
		progressLabel = new NutText(this, bitmapFont, "0%");
		spriteLayer1.addSprite(progressLabel);
		progressLabel.setVisible(false);
		
	}

	@Override
	public void show(){
		AssetLoader.loadAssets();
	}
	
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		
		//finish loading
		if(AssetLoader.getAssetManager().update()){
			AssetLoader.getAssets();
			getGame().setScreen(new StartScreen(getGame()));
			dispose();
		//still loading
		}else{
			progress = AssetLoader.getAssetManager().getProgress();
		}
		
		//update progress label's text
		String progressText = ((int)(progress*100)) + "%";
		progressLabel.setText(progressText);
		progressLabel.setPosition(getWorldWidth()/2 - progressLabel.getWidth()/2, 
				getWorldHeight()/2 - progressLabel.getHeight()/2);
		
	}
	
	@Override
	public void dispose(){
		bitmapFont.dispose();
	}
	
	

}
