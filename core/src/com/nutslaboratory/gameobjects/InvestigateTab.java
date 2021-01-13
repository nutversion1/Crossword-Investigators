package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutButton;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.screens.PlayScreen.State;

public class InvestigateTab extends NutSprite{
	
	private NutButton closeButton;
	private Player currentTurnPlayer;
	private WordData investigateWordData;
	private Array<InvestigateBlock> investigateBlocks = new Array<InvestigateBlock>();
	private String axis;
	
	private MainBlock selectedHeadMainBlock;
	
	private GuessTab guessTab;
	
	private CharacterTab characterTab;
	private SyllableTab syllableTab;
	private TypeTab typeTab;
	
	public InvestigateTab(NutScreen screen, Player currentTurnPlayer, MainBlock selectedHeadMainBlock, Array<MainBlock> mainBlocks) {
		super(screen, AssetLoader.investigateTabTexture);
		
		this.currentTurnPlayer = currentTurnPlayer;
		this.investigateWordData = currentTurnPlayer.getWordDatas().get(selectedHeadMainBlock.getBlockData().getLetter().getHeadGroup()-1);
		this.axis = selectedHeadMainBlock.getBlockData().getLetter().getHeadAxis();
		this.selectedHeadMainBlock = selectedHeadMainBlock;
		
		//System.out.println("Investigate word data: "+investigateWordData.getWord().getName());
		//System.out.println(axis);
		
		//
		createInvestigateBlocks(mainBlocks);
		createCloseButton();
		
		createCharacterTab();
		createSyllableTab();
		createTypeTab();
		
		
	}
	
	private void createCharacterTab() {
		characterTab = new CharacterTab(getScreen(), this);
		characterTab.setPosition(80, 330);
		addChild(characterTab);
	}
	
	private void createSyllableTab() {
		syllableTab = new SyllableTab(getScreen(), this);
		syllableTab.setPosition(80, 220);
		addChild(syllableTab);
	}
	
	private void createTypeTab() {
		typeTab = new TypeTab(getScreen(), this);
		typeTab.setPosition(240, 220);
		addChild(typeTab);
	}

	private void createInvestigateBlocks(Array<MainBlock> mainBlocks) {
		for(int i = 0; i < mainBlocks.size; i++){
			//
			MainBlock tempMainBlock = mainBlocks.get(i);
			
			//set new block type
			String newInvestigateBlockType = null;
			if(!tempMainBlock.getBlockData().getLetter().getHasRevealed()){
				newInvestigateBlockType = InvestigateBlock.BUTTON_TYPE;
			}else{
				newInvestigateBlockType = InvestigateBlock.NORMAL_TYPE;
			}
			
			//create new investigate block
			InvestigateBlock newInvestigateBlock = new InvestigateBlock(getScreen(), tempMainBlock.getBlockData(), newInvestigateBlockType, State.INVESTIGATE_STATE, tempMainBlock);
			
			
			
			if(axis == Word.HORIZONTAL_AXIS){
				newInvestigateBlock.setPosition(i * newInvestigateBlock.getWidth()+10, (640 - newInvestigateBlock.getHeight() -125));
			}else{
				newInvestigateBlock.setPosition(15, (640 - newInvestigateBlock.getHeight() -115) - (i * newInvestigateBlock.getHeight()));
			}
			
			addChild(newInvestigateBlock);
			
			investigateBlocks.add(newInvestigateBlock);
			
		}
		
	}

	private void createCloseButton() {
		//
		NutSprite buttonBackground = new NutSprite(getScreen(), AssetLoader.buttonBackground2Texture);
		addChild(buttonBackground);
		buttonBackground.setPosition(405, 5);
		
		//
		closeButton = new NutButton(getScreen(), AssetLoader.closeTabButtonTexture, null, AssetLoader.closeTabButtonTexture);
		closeButton.setTouchSound(AssetLoader.buttonSound);
		closeButton.setPosition(425, 15);
		addChild(closeButton);
		
		
	}

	
	
	
	public void createGuessTab(InvestigateBlock selectedBlock){
		guessTab = new GuessTab(getScreen(), selectedBlock, this);
		guessTab.setPosition(475, 10);
		addChild(guessTab);
		
		
	}
	
	
	
	public NutButton getCloseButton(){
		return closeButton;
	}
	
	public GuessTab getGuessTab(){
		return guessTab;
	}
	
	public CharacterTab getCharacterTab(){
		return characterTab;
	}
	
	public SyllableTab getSyllableTab(){
		return syllableTab;
	}
	
	public TypeTab getTypeTab(){
		return typeTab;
	}
	
	public Array<InvestigateBlock> getInvestigateBlocks(){
		return investigateBlocks;
	}
	
	
	public WordData getInvestigateWordData(){
		return investigateWordData;
	}
	
	
	public Player getCurrentTurnPlayer(){
		return currentTurnPlayer;
	}
	
	
	
	

}
