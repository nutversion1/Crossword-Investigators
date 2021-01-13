package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutButton;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutText;

public class TypeTab extends NutSprite{

	private InvestigateTab investigateTab;
	
	private NutText text;
	
	private NutButton hintButton;
	
	private TypeHintTab typeHintTab;
	
	public TypeTab(NutScreen screen, InvestigateTab investigateTab) {
		super(screen, AssetLoader.typeTabTexture);
		
		this.investigateTab = investigateTab;
		
		createTexts();
		createHintButton();
	}

	private void createTexts() {
		
		text = new NutText(getScreen(), AssetLoader.charlemagne14Font, "???");
		text.setWidthLimit(15);
		text.setPosition(getWidth()/2 - text.getWidth()/2 - 30, 
				getHeight()/2 - text.getHeight()/2 - 15);
		text.setColor(new Color(80/255f,80/255f,80/255f,1f));
		addChild(text);
		if(investigateTab.getInvestigateWordData().getHasGuessedType()){
			String typeName = "";
			for(int i = 0; i < investigateTab.getInvestigateWordData().getWord().getType().length; i++){
				String tempType = investigateTab.getInvestigateWordData().getWord().getType()[i];
				
				if(i == investigateTab.getInvestigateWordData().getWord().getType().length-1){
					typeName += tempType;
				}else{
					typeName += tempType + ", ";
				}
			}	
			text.setText(typeName);
			text.setPosition(getWidth()/2 - text.getWidth()/2 - 30, 
					getHeight()/2 - text.getHeight()/2 - 15);
			text.setColor(Color.BLACK);
			
		}
		
		
	}
	
	private void createHintButton() {
		hintButton = new NutButton(getScreen(), AssetLoader.hintDefaultButtonTexture,
				AssetLoader.hintButtonUnavailableTexture, AssetLoader.hintButtonSelectTexture);
		hintButton.setTouchSound(AssetLoader.buttonSound);
		hintButton.setPosition(getWidth()-60, 30);
		addChild(hintButton);
		
		if(investigateTab.getInvestigateWordData().getHasGuessedType()){
			hintButton.unavailable();
		}
	}
	
	public void createTypeHintTab() {
		typeHintTab = new TypeHintTab(getScreen(), this);
		typeHintTab.setPosition(350, -200);
		addChild(typeHintTab);
		
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
	
	public TypeHintTab getTypeHintTab(){
		return typeHintTab;
	}
	
	
}

