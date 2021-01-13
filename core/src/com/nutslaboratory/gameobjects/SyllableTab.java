package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutButton;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutText;

public class SyllableTab extends NutSprite {
	
	private InvestigateTab investigateTab;
	
	private NutText text;
	
	private NutButton hintButton;
	
	private SyllableHintTab syllableHintTab;
	
	public SyllableTab(NutScreen screen, InvestigateTab investigateTab) {
		super(screen, AssetLoader.syllableTabTexture);

		this.investigateTab = investigateTab;
		
		createTexts();
		createHintButton();
	}

	private void createTexts() {
		
		text = new NutText(getScreen(), AssetLoader.charlemagne20Font, "?");
		text.setPosition(getWidth()/2 - text.getWidth()/2 - 25, 30);
		text.setColor(new Color(80/255f,80/255f,80/255f,1f));
		addChild(text);
		if(investigateTab.getInvestigateWordData().getHasGuessedSyllable()){
			text.setText(investigateTab.getInvestigateWordData().getWord().getSyllable());
			text.setPosition(getWidth()/2 - text.getWidth()/2 - 25, 30);
			text.setColor(Color.BLACK);
		}
		
	}
	
	private void createHintButton() {
		hintButton = new NutButton(getScreen(), AssetLoader.hintDefaultButtonTexture, 
				AssetLoader.hintButtonUnavailableTexture, AssetLoader.hintButtonSelectTexture);
		hintButton.setTouchSound(AssetLoader.buttonSound);
		hintButton.setPosition(getWidth()-60, 30);
		addChild(hintButton);
	
		if(investigateTab.getInvestigateWordData().getHasGuessedSyllable()){
			hintButton.unavailable();
		}
	}
	
	
	public void createSyllableHintTab() {
		syllableHintTab = new SyllableHintTab(getScreen(), this);
		syllableHintTab.setPosition(510, -200);
		addChild(syllableHintTab);
	}
	
	public InvestigateTab getInvestigateTab(){
		return investigateTab;
	}
	
	public NutButton getHintButton(){
		return hintButton;
	}
	
	public NutText getText(){
		return text;
	}
	
	public SyllableHintTab getSyllableHintTab(){
		return syllableHintTab;
	}
	
	
	
	
}
