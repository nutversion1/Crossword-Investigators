package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutButton;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutText;

public class CharacterTab extends NutSprite {
	
	private InvestigateTab investigateTab;
	
	private Array<NutText> texts = new Array<NutText>();
	
	private NutButton hintButton;
	
	private CharacterHintTab characterHintTab;
	
	public CharacterTab(NutScreen screen, InvestigateTab investigateTab) {
		super(screen, AssetLoader.characterTabTexture);

		this.investigateTab = investigateTab;
		
		createTexts();
		createHintButton();
		
		
		
	}

	private void createTexts() {
		
		String[] characterNames = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", 
				"w", "x", "y", "z"}; 
		
		
		
		int column = 0;
		int row = 0;
		
		for(int i = 0; i < characterNames.length; i++){
			String newCharacterName = characterNames[i].toUpperCase();
			
			NutText newText = new NutText(getScreen(), AssetLoader.charlemagne18Font, newCharacterName, 20,20);
			newText.setPosition(20 + (column*(newText.getWidth()+3)), 45-(row*25));
			newText.setColor(Color.BLACK);
			addChild(newText);
			texts.add(newText);
			
			
			column++;
			if(column >= 13){
				column = 0;
				row++;
			}
			
			
			
			//
			String guessedStatus = investigateTab.getInvestigateWordData().getGuessedCharacterStrings().get(i);
			if(guessedStatus.contains(WordData.NOT_GUESSED)){
				//newText.setColor(Color.WHITE);
			}else if(guessedStatus.contains(WordData.INSIDE_WORD)){
				//newText.setColor(Color.GREEN);
				
				createInsideWordMark(newText);
				
		
			}else if(guessedStatus.contains(WordData.OUTSIDE_WORD)){
				//newText.setColor(Color.RED);
				
				createOutsideWordMark(newText);
			}
			
			
		}
		
		
		
	}
	
	private void createHintButton() {
		hintButton = new NutButton(getScreen(), AssetLoader.hintDefaultButtonTexture,
				AssetLoader.hintButtonUnavailableTexture, AssetLoader.hintButtonSelectTexture);
		hintButton.setTouchSound(AssetLoader.buttonSound);
		hintButton.setPosition(getWidth()-60, 40);
		addChild(hintButton);
		
		//set hint button to unavailable (if guessed all)
		boolean hasGuessedAll = true;
		
		for(String guessedStatus : investigateTab.getInvestigateWordData().getGuessedCharacterStrings()){
			if(guessedStatus.equals(WordData.NOT_GUESSED)){
				hasGuessedAll = false;
				break;
			}
		}
		
		if(hasGuessedAll){
			hintButton.unavailable();
		}
	}
	
	
	public void createCharacterHintTab(){
		characterHintTab = new CharacterHintTab(getScreen(), this, investigateTab);
		characterHintTab.setPosition(395, -320);
		addChild(characterHintTab);
	}
	
	public Array<NutText> getTexts(){
		return texts;
	}
	
	public NutButton getHintButton(){
		return hintButton;
	}
	
	public void createInsideWordMark(NutText text){
		NutSprite mark = new NutSprite(getScreen(), AssetLoader.insideWordMarkTexture);
		mark.setPosition(text.getX(), text.getY()-5);
		addChild(mark);
		
		
		moveChildToTop(text);
	}
	
	public void createOutsideWordMark(NutText text){
		NutSprite mark = new NutSprite(getScreen(), AssetLoader.outsideWordMarkTexture);
		mark.setPosition(text.getX()-3, text.getY()-5);
		addChild(mark);
		
		
		moveChildToTop(text);
	}
	
	public CharacterHintTab getCharacterHintTab(){
		return characterHintTab;
	}
	
	
}
