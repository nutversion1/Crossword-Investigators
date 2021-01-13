package com.nutslaboratory.screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutButton;
import com.nutslaboratory.nutlibgdxgameengine.NutGame;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutSpriteLayer;
import com.nutslaboratory.nutlibgdxgameengine.NutText;

public class HelpSettingScreen extends NutScreen implements InputProcessor{
	
	private Array<NutSprite> pages;
	
	private NutSprite page1, page2, page3;
	
	private int currentPageIndex;
	
	private NutButton previousButton, nextButton;
	
	private NutText pageLabel;
	
	private Array<NutText> texts =  new Array<NutText>();
	
	private NutSpriteLayer spriteLayer1 = new NutSpriteLayer(this, "layer1");
	
	public HelpSettingScreen(NutGame game) {
		super(game);
		
	
	}
	
	@Override
	public void show(){
		addSpriteLayer(spriteLayer1);
		
		page1 = new NutSprite(this, AssetLoader.helpSettingATexture);
		spriteLayer1.addSprite(page1);
		page1.setVisible(true);
		page2 = new NutSprite(this, AssetLoader.helpSettingBTexture);
		spriteLayer1.addSprite(page2);
		page2.setVisible(false);
		page3 = new NutSprite(this, AssetLoader.helpSettingCTexture);
		spriteLayer1.addSprite(page3);
		page3.setVisible(false);
		

		pages = new Array<NutSprite>();
		pages.add(page1);
		pages.add(page2);
		pages.add(page3);
		
		//
		previousButton = new NutButton(this, AssetLoader.leftButtonTexture, 
				AssetLoader.leftButtonUnavailableTexture, AssetLoader.leftButtonTexture);
		previousButton.setTouchSound(AssetLoader.buttonSound);
		spriteLayer1.addSprite(previousButton);
		previousButton.setPosition(15, 70);
		previousButton.unavailable();
		
		
		
		
		nextButton = new NutButton(this, AssetLoader.rightButtonTexture, 
				AssetLoader.rightButtonUnavailableTexture, AssetLoader.rightButtonTexture);
		nextButton.setTouchSound(AssetLoader.buttonSound);
		spriteLayer1.addSprite(nextButton);
		nextButton.setPosition(370, 70);
	
		
		//
		createPageLabel();
		
		//
		setPage();
		
		
	}
	
	private void createPageLabel() {
		pageLabel = new NutText(this, AssetLoader.charlemagne32Font, (currentPageIndex+1)+"/"+pages.size);
		pageLabel.setPosition(getWorldWidth()/2 - pageLabel.getWidth()/2, 100);
		pageLabel.setColor(Color.WHITE);
		spriteLayer1.addSprite(pageLabel);
		
	}
	
	

	private void setPage() {
		for(int i = 0; i < pages.size; i++){
			if(i == currentPageIndex){
				pages.get(i).setVisible(true);
			}else{
				pages.get(i).setVisible(false);
			}
		}
		
		//
		pageLabel.setText((currentPageIndex+1)+"/"+pages.size);
		pageLabel.setX(getWorldWidth()/2 - pageLabel.getWidth()/2);
		
		//set buttons state
		if(currentPageIndex == 0){
			previousButton.unavailable();
		}else if(currentPageIndex == pages.size-1){
			nextButton.unavailable();
		}else{
			previousButton.unselect();
			nextButton.unselect();
		}
		
		//
		removeTexts();
		createTexts();
		
		
	}
	
	private void removeTexts() {
		for(NutText tempText : texts){
			spriteLayer1.removeSprite(tempText);
		}
		
		texts.clear();
		
	}

	private void createTexts() {
		
		if(currentPageIndex == 0){
			NutText text1 = new NutText(this, AssetLoader.charlemagne14Font, 
					"Number of Words");
			text1.setColor(Color.BLACK);
			text1.setPosition(238, 550);
			spriteLayer1.addSprite(text1);
			texts.add(text1);
			
			NutText text2 = new NutText(this, AssetLoader.charlemagne14Font, 
					"Number of possible words" + "\n" +
					"shown before starting to" + "\n" +
					"play (the more words, " + "\n" +
					"the harder it is)");
			text2.setColor(Color.BLACK);
			text2.setPosition(195, 405);
			spriteLayer1.addSprite(text2);
			texts.add(text2);
			
			NutText text3 = new NutText(this, AssetLoader.charlemagne14Font, 
					"Letters given " + "\n" +
					"(the more letter, " + "\n" +
					"the easier it is)");
			text3.setColor(Color.BLACK);
			text3.setPosition(260, 266);
			spriteLayer1.addSprite(text3);
			texts.add(text3);
		}
		
		else if(currentPageIndex == 1){
			NutText text1 = new NutText(this, AssetLoader.charlemagne14Font, 
					"Time limit");
			text1.setColor(Color.BLACK);
			text1.setPosition(310, 545);
			spriteLayer1.addSprite(text1);
			texts.add(text1);
			
			NutText text2 = new NutText(this, AssetLoader.charlemagne14Font, 
					"Number of" + "\n" +
					"incorrect guess " + "\n" +
					"allowed");
			text2.setColor(Color.BLACK);
			text2.setPosition(280, 380);
			spriteLayer1.addSprite(text2);
			texts.add(text2);
			
			NutText text3 = new NutText(this, AssetLoader.charlemagne14Font, 
					"Number of hints" + "\n" +
					"allowed");
			text3.setColor(Color.BLACK);
			text3.setPosition(280, 250);
			spriteLayer1.addSprite(text3);
			texts.add(text3);
		}
		
		else if(currentPageIndex == 2){
			NutText text1 = new NutText(this, AssetLoader.charlemagne14Font, 
					"Choose whether to play with " + "\n" +
					"computer or human" + "\n" +
					"(requires at least 2 players)");
			text1.setColor(Color.BLACK);
			text1.setPosition(110, 497);
			spriteLayer1.addSprite(text1);
			texts.add(text1);
			
		}
		
		
		
	}

	public void update(float delta){
		super.update(delta);
		
		
	}
	
	@Override
	public void doEvents(){
		if(previousButton.isClicked()){
			if (currentPageIndex > 0){
				currentPageIndex--;
				
				setPage();
			}
			
			
		}else if(nextButton.isClicked()){
			if (currentPageIndex < pages.size-1){
				currentPageIndex++;
				
				setPage();
			}
			
		}
	}
	
	@Override
	public void pressBack() {
		getGame().setScreen(new MainMenuScreen(getGame()));
	}

}
