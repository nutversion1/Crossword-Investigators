package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutText;

public class OtherGlossariesPage extends GlossariesPage{

	private Array<Word> words;
	private Array<NutText> wordTexts;
	
	public OtherGlossariesPage(NutScreen screen, TextureRegion texture, GlossariesTab glossariesTab, Array<Word> words) {
		super(screen, texture, glossariesTab);
		
		this.words = words;
		
		createWordTexts();
		
	}
	
	private void createWordTexts() {
		wordTexts = new Array<NutText>();
		
		for(int row = 0; row < words.size; row++){
			Word tempWord = words.get(row);
			
			//
			NutText newWordText = new NutText(getScreen(), AssetLoader.charlemagne18Font, tempWord.getName().toUpperCase());
			newWordText.setColor(Color.BLACK);
			newWordText.setPosition(90-newWordText.getWidth()/2, 430-(row*35));
			addChild(newWordText);
			wordTexts.add(newWordText);
			
			//
			NutText newSyllableText = new NutText(getScreen(),AssetLoader.charlemagne18Font, tempWord.getSyllable());
			newSyllableText.setColor(Color.BLACK);
			newSyllableText.setPosition(215-newSyllableText.getWidth()/2, 430-(row*35));
			addChild(newSyllableText);
			wordTexts.add(newSyllableText);
			
			//
			String typeName = "";
			
			for(int j = 0; j < tempWord.getType().length; j++){
				String tempType = tempWord.getType()[j];
				
				if(j == tempWord.getType().length-1){
					typeName += tempType;
				}else{
					typeName += tempType + ", ";
				}
				
			}
			
			NutText newTypeText = new NutText(getScreen(),AssetLoader.charlemagne18Font, typeName.toUpperCase());
			newTypeText.setColor(Color.BLACK);
			newTypeText.setPosition(345-newTypeText.getWidth()/2, 430-(row*35));
			addChild(newTypeText);
			wordTexts.add(newTypeText);
				
			
		}
		
	}

}
