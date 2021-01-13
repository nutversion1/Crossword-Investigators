package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutButton;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutText;
import com.nutslaboratory.screens.PlayScreen;

public abstract class Block extends NutButton{
	public static final String BUTTON_TYPE = "button_type";
	public static final String NORMAL_TYPE = "normal_type";
	
	protected TextureRegion defaultButtonTexture, defaultNormalTexture, selectButtonTexture, selectNormalTexture;
	
	protected BlockData blockData;
	
	protected String type;
	
	protected NutText letterText;
	
	protected NutText headSymbolText;
	
	protected PlayScreen.State blockMode;
	
	protected Array<Block> bodyBlocks = new Array<Block>();
	
	public Block(NutScreen screen, BlockData blockData, String type, PlayScreen.State blockMode){
		super(screen, AssetLoader.defaultNormalBlockTexture, AssetLoader.defaultNormalBlockTexture,
				AssetLoader.defaultNormalBlockTexture);
		
		//
		this.blockData = blockData;
		this.type = type;
		this.blockMode = blockMode;
		
		//
		defaultButtonTexture = AssetLoader.defaultButtonBlockTexture;
		defaultNormalTexture = AssetLoader.defaultNormalBlockTexture;
		selectButtonTexture = AssetLoader.selectButtonBlockTexture;
		selectNormalTexture = AssetLoader.selectNormalBlockTexture;
		
		//
		createLetterText();
		
		//
		if(blockData.getLetter().isHead()){
			//createHeadSymbolText();
		}
		
		//
		setType(type);
		setState(DEFAULT_STATE);
		
		//
		if(type.equals(NORMAL_TYPE)){
			setEnable(false);
		}
		
		
		//
		setTouchSound(AssetLoader.buttonSound);
		
		//
		setCollisionRect(0, 0, getWidth()-10, getHeight()-10);
		
	}
	
	private void createLetterText() {
		letterText = new NutText(getScreen(), AssetLoader.charlemagne24Font, ""+blockData.getLetter().getName().toUpperCase());
		letterText.setPosition(getWidth()/2 - letterText.getWidth()/2,
				getHeight()/2 - letterText.getHeight()/2 + 2);
		letterText.setColor(Color.BLACK);	
		addChild(letterText);
		
		if(blockData.getLetter().getHasRevealed()){
			showLetterText();
		}else{
			hideLetterText();
		}
		
	}

	private void createHeadSymbolText() {
		//headSymbolText = new NutText(AssetLoader.charlemagne14Font, String.valueOf(getBlockData().getLetter().getHeadGroup()));
		headSymbolText = new NutText(getScreen(), AssetLoader.charlemagne24Font, "*");
		headSymbolText.setPosition(getWidth()/2 - headSymbolText.getWidth()/2,
				getHeight()/2 - headSymbolText.getHeight()/2);
		headSymbolText.setColor(Color.BLACK);	
		addChild(headSymbolText);
		headSymbolText.setVisible(true);
		
	}
	
	private void setType(String type){
		this.type = type;
		
		if(type.equals(BUTTON_TYPE)){
			setTexture(defaultButtonTexture);
			
			letterText.setY(getHeight()/2 - letterText.getHeight()/2 + 9);
		}else{
			setTexture(defaultNormalTexture);
			
			letterText.setY(getHeight()/2 - letterText.getHeight()/2 + 2);
		}
	}
	
	
	@Override
	protected void setState(String state){
		if(this.state.equals(state)){
			return;
		}
		
		this.state = state;
		
		if(type.equals(BUTTON_TYPE)){
			if(state.equals(DEFAULT_STATE)){
				setTexture(defaultButtonTexture);
			}else if(state.equals(SELECT_STATE)){
				setTexture(selectButtonTexture);
			}
		}else{
			if(state.equals(DEFAULT_STATE)){
				setTexture(defaultNormalTexture);
			}else if(state.equals(SELECT_STATE)){
				setTexture(selectNormalTexture);
			}
		}
		
	}
	
	public BlockData getBlockData(){
		return blockData;
	}
	
	public String getType(){
		return type;
	}
	
	public void finish(){
		setType(NORMAL_TYPE);
		setEnable(false);
		showLetterText();
		
	}
	
	public void showLetterText(){
		letterText.setVisible(true);
	}
	
	public void hideLetterText(){
		letterText.setVisible(false);
	}
	
	public void addBodyBlock(Block bodyBlock){
		bodyBlocks.add(bodyBlock);
	}
	
	public Array<Block> getBodyBlocks(){
		return bodyBlocks;
	}
	
	
	
}