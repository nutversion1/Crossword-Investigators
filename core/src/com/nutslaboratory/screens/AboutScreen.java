package com.nutslaboratory.screens;

import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutGame;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutSpriteLayer;
import com.nutslaboratory.nutlibgdxgameengine.NutText;

public class AboutScreen extends NutScreen {

	NutSprite aboutScene;
	
	NutText versionText;
	
	private NutSpriteLayer spriteLayer1 = new NutSpriteLayer(this, "layer1");
	
	public AboutScreen(NutGame game) {
		super(game);
		
		addSpriteLayer(spriteLayer1);
		
		createAboutScene();
		createVersionText();
		
	}

	private void createAboutScene() {
		aboutScene = new NutSprite(this, AssetLoader.aboutSceneTexture);
		spriteLayer1.addSprite(aboutScene);
		
	}
	
	private void createVersionText() {
		String versionStr = "v "+getGame().versionName +
				" (" + getGame().versionCode + ")";
		
		versionText = new NutText(this, AssetLoader.calibri20Font, versionStr);
		versionText.setPosition(12, 14);
		spriteLayer1.addSprite(versionText);
		
	}
	
	@Override
	public void pressBack() {
		getGame().setScreen(new StartScreen(getGame()));
	}

}
