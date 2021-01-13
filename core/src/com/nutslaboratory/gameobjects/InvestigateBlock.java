package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutAnimationSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.screens.PlayScreen.State;

public class InvestigateBlock extends Block {

	private MainBlock mainBlock;
	
	private NutAnimationSprite guessEffectAnimation;
	
	public InvestigateBlock(NutScreen screen, BlockData blockData, String type, State blockMode, MainBlock mainBlock) {
		super(screen, blockData, type, blockMode);

		this.mainBlock = mainBlock;
		
		createGuessEffectAnimation();
		
		
	}
	
	private void createGuessEffectAnimation() {
		guessEffectAnimation = new NutAnimationSprite(getScreen(), PlayMode.NORMAL, 0.06f, 3, 3,
				AssetLoader.guessEffectAnimationTexture);
		guessEffectAnimation.setPosition(-guessEffectAnimation.getWidth()/2 +30, 
				-guessEffectAnimation.getHeight()/2 +30);
		addChild(guessEffectAnimation);
	}
	
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		if(guessEffectAnimation != null){
			if(guessEffectAnimation.isFinished()){
				removeChild(guessEffectAnimation);
			}
		}
		
	}
	
	public MainBlock getMainBlock(){
		return mainBlock;
	}
	
	public NutAnimationSprite getGuessEffectAnimation(){
		return guessEffectAnimation;
	}
	
	

}
