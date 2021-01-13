package com.nutslaboratory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.cwi.CWIGame;
import com.nutslaboratory.gameobjects.Block;
import com.nutslaboratory.gameobjects.BlockData;
import com.nutslaboratory.gameobjects.CharacterHintTab;
import com.nutslaboratory.gameobjects.GameManager;
import com.nutslaboratory.gameobjects.GlossariesTab;
import com.nutslaboratory.gameobjects.GuessTab;
import com.nutslaboratory.gameobjects.InvestigateBlock;
import com.nutslaboratory.gameobjects.InvestigateTab;
import com.nutslaboratory.gameobjects.LetterButton;
import com.nutslaboratory.gameobjects.MainBlock;
import com.nutslaboratory.gameobjects.Player;
import com.nutslaboratory.gameobjects.StatusTab;
import com.nutslaboratory.gameobjects.WrongGuessLight;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutButton;
import com.nutslaboratory.nutlibgdxgameengine.NutGame;
import com.nutslaboratory.nutlibgdxgameengine.NutGrade;
import com.nutslaboratory.nutlibgdxgameengine.NutGrade.Style;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutSpriteLayer;
import com.nutslaboratory.nutlibgdxgameengine.NutText;


public class PlayScreen extends NutScreen{
	
	private static final float DECREASE_TIME_TOTAL = 1f;
	
	protected static final long LOAD_INVESTIGATE_TAB_DURATION = 800;
	protected long beforeCreateInvestigateTabTimeMillis;
	
	private int timesUpStateTimer = 5;
	private int completeGameStateTimer = 5;
	
	private final int TOTAL_WORDS;
	
	private float timer = DECREASE_TIME_TOTAL;
	
	protected Array<MainBlock> mainBlocks;
	protected Array<MainBlock> headMainBlocks;
	private Array<Array<MainBlock>> groupBlocksList;
	

	private NutText timeLeftText, completeWordText;
	
	protected StatusTab statusTab;
	
	protected GameManager gameManager;
	
	protected Player currentTurnPlayer;


	
	
	
	public enum State{
		SHOW_GLOSSARIES_STATE, MAIN_STATE, STATUS_STATE, 
		LOAD_INVESTIGATE_STATE, INVESTIGATE_STATE, GUESS_LETTER_STATE,
		HINT_CHARACTER_STATE, HINT_SYLLABLE_STATE, HINT_TYPE_STATE,
		TIMES_UP_STATE, COMPLETE_GAME_STATE,
		BACK_MENU_STATE;
	}
	
	protected State oldState;
	protected State state;
	
	
	GlossariesTab glossariesTab;
	InvestigateTab investigateTab;
	
	private Array<WrongGuessLight> wrongGuessLights;
	protected Array<NutSprite> hintPoints;
	
	
	protected NutSprite playerIcon;
	protected NutButton statusButton;
	
	private NutGrade timeLeftGrade;
	
	protected NutSpriteLayer spriteLayer1 = new NutSpriteLayer(this, "layer1");
	protected NutSpriteLayer spriteLayer2 = new NutSpriteLayer(this, "layer2");
	protected NutSpriteLayer spriteLayer3 = new NutSpriteLayer(this, "layer3");
	protected NutSpriteLayer spriteLayer4 = new NutSpriteLayer(this, "layer4");
	protected NutSpriteLayer spriteLayer5 = new NutSpriteLayer(this, "layer5");
	protected NutSpriteLayer spriteLayer6 = new NutSpriteLayer(this, "layer6");
	protected NutSpriteLayer spriteLayer7 = new NutSpriteLayer(this, "layer7");
	
	public PlayScreen(NutGame game, GameManager gameManager) {
		super(game);
		
		this.gameManager = gameManager;
		
		currentTurnPlayer = gameManager.getCurrentTurnPlayer();
		TOTAL_WORDS = gameManager.getTotalWords();
		
	}
	
	private NutSprite completeGameLabel;
	private NutSprite timesUpLabel;
	
	private boolean timesUp = false;
	
	private boolean isGuessing = false;
	
	protected boolean timeInfinite = false;
	protected boolean glossaryTimeInfinite = false;
	
	@Override
	public void show() {
		//System.out.println("current rank "+gameManager.getCurrentRank());
		
		//
		addSpriteLayer(spriteLayer1); 
		addSpriteLayer(spriteLayer2);
		addSpriteLayer(spriteLayer3);
		addSpriteLayer(spriteLayer4);
		addSpriteLayer(spriteLayer5);
		addSpriteLayer(spriteLayer6);
		addSpriteLayer(spriteLayer7);
		
		//
		createBackground();
		createMainBlocks();
		createGroupBlocksList();
		
		createWrongGuessLights();
		createHintPoints();
		
		createPlayerIcon();
		
		createStatusButton();
		
		createTimeLeftGrade();
		createTimeLeftText();
		
		createCompleteWordText();
		
		
		
		
		
		
		
		//
		if(gameManager.getRoundNum() == 1){
			//show glossary tab
			createGlossariesTab();
			
		}else{
			setState(State.MAIN_STATE);
		}
		
		//
		AssetLoader.backgroundMusic.pause();
		AssetLoader.playMusic.play();
		
	}
	
	

	private void createTimeLeftGrade() {
		//
		timeLeftGrade = new NutGrade(this, AssetLoader.sandglassBaseTexture, 
				AssetLoader.sandglassFrontTexture, Style.TOP_TO_BOTTOM);
		timeLeftGrade.setPosition(14, 542);
		timeLeftGrade.setForeOffsetX(7);
		timeLeftGrade.setForeOffsetY(12);
		spriteLayer1.addSprite(timeLeftGrade);
		
	}



	private void createTimeLeftText() {
		//
		timeLeftText = new NutText(this, AssetLoader.charlemagne32Font, ""+currentTurnPlayer.getTimeLeft());
		timeLeftText.setPosition(10, 600);
		spriteLayer1.addSprite(timeLeftText);
		timeLeftText.setVisible(false);
		
	}



	private void createCompleteWordText() {
		BitmapFont bitmapFont;
		if(TOTAL_WORDS == 10){
			bitmapFont = AssetLoader.charlemagne24Font;
		}else{
			bitmapFont = AssetLoader.charlemagne32Font;
		}
		completeWordText = new NutText(this, bitmapFont, currentTurnPlayer.getCompleteWordTotal()+"/"+TOTAL_WORDS);
		completeWordText.setPosition(357 - completeWordText.getWidth()/2, 
				585 - completeWordText.getHeight()/2);
		completeWordText.setColor(Color.BLACK);
		spriteLayer1.addSprite(completeWordText);
		
	}



	private void createGlossariesTab() {
		glossariesTab = new GlossariesTab(this, gameManager);
		spriteLayer5.addSprite(glossariesTab);

		spriteLayer5.setInfluence(true);
		
		setState(State.SHOW_GLOSSARIES_STATE);
		
	}

	private void createPlayerIcon() {
		TextureRegion texture = null;
		if(currentTurnPlayer.getPlayerName() == Player.HUMAN_1){
			texture = AssetLoader.human1IconTexture;
		}else if(currentTurnPlayer.getPlayerName() == Player.HUMAN_2){
			texture = AssetLoader.human2IconTexture;
		}else if(currentTurnPlayer.getPlayerName() == Player.HUMAN_3){
			texture = AssetLoader.human3IconTexture;
		}else if(currentTurnPlayer.getPlayerName() == Player.HUMAN_4){
			texture = AssetLoader.human4IconTexture;
		}
		
		playerIcon = new NutSprite(this, texture);
		spriteLayer1.addSprite(playerIcon);
		playerIcon.setPosition(404, 550);
		
		
	}
	
	private void createStatusButton() {
		//
		NutSprite buttonBackground = new NutSprite(this, AssetLoader.buttonBackground3Texture);
		spriteLayer1.addSprite(buttonBackground);
		buttonBackground.setPosition(405, 0);
		
		//
		statusButton = new NutButton(this, AssetLoader.statusButtonTexture,
				null, AssetLoader.statusButtonTexture);
		statusButton.setTouchSound(AssetLoader.buttonSound);
		statusButton.setPosition(425, 15);
		spriteLayer1.addSprite(statusButton);
		
	}

	private void createBackground() {
		NutSprite bg = new NutSprite(this, AssetLoader.boardBackgroundTexture);
		bg.setPosition(0, 0);
		spriteLayer1.addSprite(bg);
		
		
		
	}
	
	private void createMainBlocks() {
		mainBlocks = new Array<MainBlock>();
		headMainBlocks = new Array<MainBlock>();
		
		for(int i = 0; i < currentTurnPlayer.getBlockDatas().size; i++){
			//
			BlockData tempBlockData = currentTurnPlayer.getBlockDatas().get(i);
			
			//set new block type
			String newBlockType = null;
			if(tempBlockData.getLetter().isHead() &&
					!currentTurnPlayer.getWordDatas().get(tempBlockData.getLetter().getHeadGroup()-1).hasFinished()){
				
				newBlockType = MainBlock.BUTTON_TYPE;
			}else{
				newBlockType = MainBlock.NORMAL_TYPE;
			}
			
			//create new block
			MainBlock newMainBlock = new MainBlock(this, tempBlockData, newBlockType, State.MAIN_STATE);
			newMainBlock.setPosition(14 + (newMainBlock.getBlockData().getLetter().getColumn() * (newMainBlock.getWidth()+5)), 
					(470) - (newMainBlock.getBlockData().getLetter().getRow() * (newMainBlock.getHeight()+3)));
			
			
			spriteLayer1.addSprite(newMainBlock);
			
			//
			mainBlocks.add(newMainBlock);
			
			//head block
			if(tempBlockData.getLetter().isHead()){
				headMainBlocks.add(newMainBlock);
				
				
			}
		}
		
		//
		for(MainBlock tempHeadMainBlock : headMainBlocks){
			for(MainBlock tempMainBlock : mainBlocks){
				if(tempMainBlock.getBlockData().getLetter().getGroups().contains(tempHeadMainBlock.getBlockData().getLetter().getHeadGroup(), false)){
					if(tempMainBlock != tempHeadMainBlock){
						tempHeadMainBlock.addBodyBlock(tempMainBlock);
					}
				}
			}
		}
		
		
		
	}
	
	
	private void createGroupBlocksList() {
		groupBlocksList = new Array<Array<MainBlock>>();
		
		int currentGroup = 1;
		
		for(int i = 0; i < TOTAL_WORDS; i++){
			Array<MainBlock> innerList = new Array<MainBlock>();
			
			for(MainBlock tempBlock : mainBlocks){
				if(tempBlock.getBlockData().getLetter().getGroups().contains(currentGroup , true)){
					
					innerList.add(tempBlock);
				}
			}
			
			groupBlocksList.add(innerList);
			currentGroup++;
		}
		
		//traceAnswer();
		
		
		
		
	}
	
	
	private void traceAnswer() {
		for(int i = 0 ; i < groupBlocksList.size; i++){
			for(int j = 0; j < groupBlocksList.get(i).size; j++){
				System.out.print(groupBlocksList.get(i).get(j).getBlockData().getLetter().getName());
			}
			System.out.println();
		}	
		
	}



	public Array<Array<MainBlock>> getGroupBlocksList() {
		return groupBlocksList;
	}
	
	public void setBeforeCreateInvestigateTabTimeMillis(long value){
		beforeCreateInvestigateTabTimeMillis = value;
	}
	
	private void createWrongGuessLights() {
		wrongGuessLights = new Array<WrongGuessLight>();
		
		for(int i = 0; i < gameManager.getTotalWrongGuess(); i++){
			WrongGuessLight newWrongGuessLight = new WrongGuessLight(this, AssetLoader.wrongGuesslightBlankTexture,
					AssetLoader.wrongGuesslightRedTexture);
			float spaceX = newWrongGuessLight.getWidth() + 5;
			float offsetX = 235 - (gameManager.getTotalWrongGuess() * spaceX / 2);
			newWrongGuessLight.setPosition(offsetX + (i * spaceX), 560);
			spriteLayer1.addSprite(newWrongGuessLight);
			wrongGuessLights.add(newWrongGuessLight);
		}
	}
	
	private void createHintPoints() {
		hintPoints = new Array<NutSprite>();
		
		for(int i = 0; i < currentTurnPlayer.getHintPointsLeft(); i++){
			
			TextureRegion texture = null;
			if(i == 4 || i == 9){
				texture = AssetLoader.hintPointTiltTexture;
			}
			else{
				texture = AssetLoader.hintPointOneTexture;
			}
			
			
			NutSprite newHintPoint = new NutSprite(this, texture);
			
			if(i < 5){
				if(i == 4){
					newHintPoint.setPosition(90, 560);
				}else{
					newHintPoint.setPosition(95 + (i * 5), 560);
				}
			}else{
				if(i == 9){
					newHintPoint.setPosition(120, 560);
				}else{
					newHintPoint.setPosition(95+ (i * 5) + 5, 560);
				}
				
			}
	
			spriteLayer1.addSprite(newHintPoint);
			hintPoints.add(newHintPoint);
		}
		
	}
	
	
	
	
	
	public void setState(State state){
		//System.out.println(state);	
		
		oldState = this.state;
		
		this.state = state;
		
		
	}
	
	public void endTurn(){
		gameManager.nextTurn();
		
		getGame().setScreen(new PunctuateScreen(getGame(), gameManager));
	}

	
	
	
	
	public long createInvestiagteTab(MainBlock selectedHeadBlock) {
		//
		long beforeCreateInvestigateTabTimeMillis = System.currentTimeMillis();
		
		//
		Array<MainBlock> investigateBlocks = new Array<MainBlock>();
		
		for(MainBlock tempBlock : mainBlocks){
			int group = selectedHeadBlock.getBlockData().getLetter().getHeadGroup();
			
			if(tempBlock.getBlockData().getLetter().getGroups().contains(group, false)){
				investigateBlocks.add(tempBlock);
			}
		}
		
		//create investigate tab
		investigateTab = new InvestigateTab(this, currentTurnPlayer, selectedHeadBlock, investigateBlocks);
		investigateTab.setPosition(0, 0);
		spriteLayer2.addSprite(investigateTab);
		investigateTab.setVisible(false);
		
		//
		return beforeCreateInvestigateTabTimeMillis;
		
	}
	
	public InvestigateTab getInvestigateTab(){
		return investigateTab;
	}
	
	protected void showInvestiagteTab() {
		investigateTab.setVisible(true);
		spriteLayer2.setInfluence(true);
		
		//hide overlay
		for(InvestigateBlock tempInvestigateBlock : investigateTab.getInvestigateBlocks()){
			MainBlock tempMainBlock = tempInvestigateBlock.getMainBlock();
			spriteLayer3.removeSprite(tempMainBlock);
			spriteLayer1.addSprite(tempMainBlock);
		}
		
		spriteLayer3.setInfluence(false);
		spriteLayer3.setOverlay(false);
		
	}
	
	
	protected void createStatusTab() {
		statusTab = new StatusTab(this, gameManager);
		statusTab.setPosition(getWorldWidth()/2 - statusTab.getWidth()/2 + 500,
				getWorldHeight()/2 - statusTab.getHeight()/2);
		spriteLayer6.addSprite(statusTab);
		
		spriteLayer6.setInfluence(true);
		spriteLayer6.setOverlay(true);
		
		//
		setState(State.STATUS_STATE);
		
	}
	
	public State getState(){
		return state;
	}
	
	public State getOldState(){
		return oldState;
	}
	
	public Array<MainBlock> getMainBlocks(){
		return mainBlocks;
	}
	
	
	
	public void updateCompleteWordTotal(){
		int completeWordTotal = 0;
		
		for(int i = 0; i < groupBlocksList.size; i++){
			int currentGroup = i+1;
			
			boolean hasCompleteWord = true;
			
			for(int j = 0; j < groupBlocksList.get(i).size; j++){
				if(!groupBlocksList.get(i).get(j).getBlockData().getLetter().getHasRevealed()){
					hasCompleteWord = false;
					break;
				}
			}
			
			if(hasCompleteWord){
				completeWordTotal++;
				
			}
			
		}
		
		currentTurnPlayer.setCompleteWord(completeWordTotal);
	}
	
	public Player getCurrentTurnPlayer(){
		return currentTurnPlayer;
	}
	
	public Array<NutSprite> getHintPoints(){
		return hintPoints;
	}
	
	public Array<MainBlock> getHeadMainBlocks(){
		return headMainBlocks;
	}
	
	public Array<WrongGuessLight> getWrongGuessLights(){
		return wrongGuessLights;
	}
	
	public GameManager getGameManager(){
		return gameManager;
	}
	
	public void createCompleteGameLabel(){
		completeGameLabel = new NutSprite(this, AssetLoader.completeLabelTexture);
		completeGameLabel.setPosition(getWorldWidth()/2 - completeGameLabel.getWidth()/2,
				getWorldHeight()/2 - completeGameLabel.getHeight()/2);
		spriteLayer4.addSprite(completeGameLabel);
		
		spriteLayer4.setInfluence(true);
		spriteLayer4.setOverlay(true);
		
		
	}
	
	public void createTimesUpLabel(){
		timesUpLabel = new NutSprite(this, AssetLoader.timesUpLabelTexture);
		timesUpLabel.setPosition(getWorldWidth()/2 - timesUpLabel.getWidth()/2,
				getWorldHeight()/2 - timesUpLabel.getHeight()/2);
		spriteLayer7.addSprite(timesUpLabel);
		
		spriteLayer7.setInfluence(true);
		spriteLayer7.setOverlay(true);
	}
	
	public void removeTimesUpLabel(){
		spriteLayer7.setInfluence(false);
		spriteLayer7.setOverlay(false);
		spriteLayer7.removeSprite(timesUpLabel);
		timesUpLabel = null;
		
		
	}
	
	protected boolean hasCompleteWord() {
		boolean hasCompleteWord = true;
		for(InvestigateBlock tempInvestigateBlock : investigateTab.getInvestigateBlocks()){
			if(!tempInvestigateBlock.getBlockData().getLetter().getHasRevealed()){
				hasCompleteWord = false;
			}
		}
		
		return hasCompleteWord;
		
	}
	
	protected void checkOtherWordComplete() {
		
		for(MainBlock tempHeadMainBlock : getHeadMainBlocks()){
			if(tempHeadMainBlock.getType().equals(Block.BUTTON_TYPE)){
				if(tempHeadMainBlock.getBlockData().getLetter().getHasRevealed()){
				
					boolean hasCompleteWord = true;
					for(Block bodyBlock : tempHeadMainBlock.getBodyBlocks()){
						if(!bodyBlock.getBlockData().getLetter().getHasRevealed()){
							hasCompleteWord = false;
						}
					}
					
					if(hasCompleteWord){
						getCurrentTurnPlayer().getWordDatas().get(
								tempHeadMainBlock.getBlockData().getLetter().getHeadGroup()-1).finish();
						
						tempHeadMainBlock.finish();
						
					}
				}
			}
		}
		
	}
	
	public boolean hasTimesUp(){
		return timesUp;
	}
	
	public boolean hasFinish() {
		if(currentTurnPlayer.getCompleteWordTotal() == getGameManager().getTotalWords()){
			return true;
		}
		
		return false;
		
	}
	
	protected void showGuessTab(InvestigateBlock investigateBlock){
		investigateTab.createGuessTab(investigateBlock);
		
		setState(State.GUESS_LETTER_STATE);
		
		//
		investigateBlock.select();
		
		//
		investigateTab.removeChild(investigateTab.getGuessTab());
		spriteLayer3.addSprite(investigateTab.getGuessTab());
		
		investigateTab.removeChild(investigateBlock);
		spriteLayer3.addSprite(investigateBlock);
		
		spriteLayer3.setInfluence(true);
		spriteLayer3.setOverlay(true);
	}
	
	protected void showCharacterHintTab(){
		investigateTab.getCharacterTab().createCharacterHintTab();
		
		investigateTab.getCharacterTab().getHintButton().select();
		
		setState(PlayScreen.State.HINT_CHARACTER_STATE);
		
		//
		investigateTab.removeChild(investigateTab.getCharacterTab());
		spriteLayer3.addSprite(investigateTab.getCharacterTab());
		
		spriteLayer3.setInfluence(true);
		spriteLayer3.setOverlay(true);
	}
	
	protected void showSyllableHintTab(){
		investigateTab.getSyllableTab().createSyllableHintTab();
		
		investigateTab.getSyllableTab().getHintButton().select();
		
		setState(PlayScreen.State.HINT_SYLLABLE_STATE);
	
		//
		investigateTab.removeChild(investigateTab.getSyllableTab());
		spriteLayer3.addSprite(investigateTab.getSyllableTab());
		
		spriteLayer3.setInfluence(true);
		spriteLayer3.setOverlay(true);
	}
	
	protected void showTypeHintTab(){
		investigateTab.getTypeTab().createTypeHintTab();
		
		investigateTab.getTypeTab().getHintButton().select();
		
		setState(PlayScreen.State.HINT_TYPE_STATE);
		
		//
		investigateTab.removeChild(investigateTab.getTypeTab());
		spriteLayer3.addSprite(investigateTab.getTypeTab());
		
		spriteLayer3.setInfluence(true);
		spriteLayer3.setOverlay(true);
	}
	
	
	protected void guessLetter(){
		
		
		//
		if(getCurrentTurnPlayer().getWrongGuess() < gameManager.getTotalWrongGuess()){
			
			isGuessing = true;
			
			LetterButton selectedLetterButton = investigateTab.getGuessTab().getSelectedLetterButton();
			InvestigateBlock selectedInvestigateBlock = investigateTab.getGuessTab().getSelectedInvestigateBlock();
			
			selectedLetterButton.unavailable();
			
			//correct
			if(selectedLetterButton.getText().getText().toLowerCase().equals(selectedInvestigateBlock.getBlockData().getLetter().getName().toLowerCase())){
				selectedInvestigateBlock.getBlockData().getLetter().reveal();
				selectedInvestigateBlock.finish();
				
				selectedInvestigateBlock.getMainBlock().getBlockData().getLetter().reveal();
				selectedInvestigateBlock.getMainBlock().showLetterText();
		
				
				getCurrentTurnPlayer().completeBlock();
				updateCompleteWordTotal();
				
				selectedInvestigateBlock.getGuessEffectAnimation().start();
				
				AssetLoader.magicSound.play(CWIGame.getSoundVolume());
				
				investigateTab.getGuessTab().moveOut();
				
				checkOtherWordComplete();
				
				investigateTab.getGuessTab().getHelpText().setText("correct!");
				investigateTab.getGuessTab().getHelpText().setX(investigateTab.getGuessTab().getWidth()/2-investigateTab.getGuessTab().getHelpText().getWidth()/2);
				
				
			//wrong
			}else{
				
				isGuessing = false;
				
				selectedInvestigateBlock.getBlockData().getGuessedLetterBooleans().set(selectedLetterButton.getNum(), true);
				
				getCurrentTurnPlayer().wrongGuess();
				getWrongGuessLights().get(
						investigateTab.getCurrentTurnPlayer().getWrongGuess()-1).red();
				
				AssetLoader.wrongSound.play(CWIGame.getSoundVolume());
				
				investigateTab.getGuessTab().getHelpText().setText("select a letter for a guess");
				investigateTab.getGuessTab().getHelpText().setX(investigateTab.getGuessTab().getWidth()/2-investigateTab.getGuessTab().getHelpText().getWidth()/2);
			}
			
			//
			investigateTab.getGuessTab().getGuessButton().unavailable();
			
			
			
			
		}
	}
	
	protected void useCharacterHint(){
		if(getCurrentTurnPlayer().getHintPointsLeft() > 0){
			investigateTab.getCharacterTab().getCharacterHintTab().useHint();
		
			spriteLayer1.removeSprite(hintPoints.pop());
			
			CharacterHintTab characterHintTab = investigateTab.getCharacterTab().getCharacterHintTab();
			characterHintTab.getHelpText().setText("select a letter for a hint");
			characterHintTab.getHelpText().setX(characterHintTab.getWidth()/2-characterHintTab.getHelpText().getWidth()/2);
			
			AssetLoader.hintSound.play(CWIGame.getSoundVolume());
		}
	}
	
	protected void useTypeHint(){
		if(getCurrentTurnPlayer().getHintPointsLeft() > 0){
			investigateTab.getTypeTab().getTypeHintTab().useHint();
			investigateTab.getTypeTab().getTypeHintTab().moveOut();
		
			spriteLayer1.removeSprite(hintPoints.pop());
			
			AssetLoader.hintSound.play(CWIGame.getSoundVolume());
		}
	}
	
	protected void useSyllableHint() {
		if(getCurrentTurnPlayer().getHintPointsLeft() > 0){
			investigateTab.getSyllableTab().getSyllableHintTab().useHint();
			investigateTab.getSyllableTab().getSyllableHintTab().moveOut();
		
			spriteLayer1.removeSprite(hintPoints.pop());
			
			AssetLoader.hintSound.play(CWIGame.getSoundVolume());
		}
		
	}
	
	protected void closeInvestigateTab() {
		spriteLayer2.removeSprite(investigateTab);
		
		spriteLayer2.setInfluence(false);
		
		//unselect main's blocks
		for(MainBlock tempMainBlock : mainBlocks){
			if(tempMainBlock.getState().equals(Block.SELECT_STATE)){
				tempMainBlock.unselect();
			}
		}
		
		//
		setState(PlayScreen.State.MAIN_STATE);
		
	}
	
	protected void selectHeadMainBlock(MainBlock headMainBlock){
		//
		headMainBlock.select();
		
		for(Block tempBodyBlock : headMainBlock.getBodyBlocks()){
			tempBodyBlock.select();
		}
		
		//
		setBeforeCreateInvestigateTabTimeMillis(createInvestiagteTab(headMainBlock));
		setState(PlayScreen.State.LOAD_INVESTIGATE_STATE);
		
		
		//show overlay
		spriteLayer1.removeSprite(headMainBlock);
		spriteLayer3.addSprite(headMainBlock);
		
		for(Block tempBodyBlock : headMainBlock.getBodyBlocks()){
			spriteLayer1.removeSprite(tempBodyBlock);
			spriteLayer3.addSprite(tempBodyBlock);
		}
		
		spriteLayer3.setInfluence(true);
		spriteLayer3.setOverlay(true);
	}
	
	protected void selectGuessTabLetter(LetterButton letterButton){
		if(investigateTab.getGuessTab().getSelectedLetterButton() != null){
			if(!investigateTab.getGuessTab().getSelectedLetterButton().
					getState().equals(LetterButton.UNAVAILABLE_STATE)){
				investigateTab.getGuessTab().getSelectedLetterButton().unselect();
			}
		}
		
		investigateTab.getGuessTab().selectLetterButton(letterButton);
		investigateTab.getGuessTab().getSelectedLetterButton().select();
		
		GuessTab guessTab = investigateTab.getGuessTab();
		guessTab.getHelpText().setText("guess : " + letterButton.getText().getText() + " ?");
		guessTab.getHelpText().setX(guessTab.getWidth()/2-guessTab.getHelpText().getWidth()/2);
	}
	
	protected void selectCharacterHintTabLetter(LetterButton letterButton){
		if(investigateTab.getCharacterTab().getCharacterHintTab().getSelectedLetterButton() != null){
			if(!investigateTab.getCharacterTab().getCharacterHintTab().getSelectedLetterButton().
					getState().equals(LetterButton.UNAVAILABLE_STATE)){
				investigateTab.getCharacterTab().getCharacterHintTab().getSelectedLetterButton().unselect();
			}
		}
		
		investigateTab.getCharacterTab().getCharacterHintTab().selectLetterButton(letterButton);
		investigateTab.getCharacterTab().getCharacterHintTab().getSelectedLetterButton().select();
		
		CharacterHintTab characterHintTab = investigateTab.getCharacterTab().getCharacterHintTab();
		characterHintTab.getHelpText().setText("hint : " + letterButton.getText().getText() + " ?");
		characterHintTab.getHelpText().setX(characterHintTab.getWidth()/2-characterHintTab.getHelpText().getWidth()/2);
	}
	
	@Override
	public void update(float delta){
		super.update(delta);
		
		
		//update text
		timeLeftText.setText(""+currentTurnPlayer.getTimeLeft());
		
		updateCompleteWordText();
		
		//state
		switch(state){
		
		case STATUS_STATE:
			if(statusTab.hasMoveOut()){
				spriteLayer6.removeSprite(statusTab);
				spriteLayer6.setInfluence(false);
				spriteLayer6.setOverlay(false);
				
				setState(getOldState());
			}
			break;
		case LOAD_INVESTIGATE_STATE:
			long totalCreateInvestigateTabTimeMillis = System.currentTimeMillis() - beforeCreateInvestigateTabTimeMillis;
			
			if(investigateTab != null && totalCreateInvestigateTabTimeMillis >= LOAD_INVESTIGATE_TAB_DURATION){
				//System.out.println(totalCreateInvestigateTabTimeMillis);
				
				setState(State.INVESTIGATE_STATE);
				showInvestiagteTab();
			}
			break;
		case GUESS_LETTER_STATE:
			if(investigateTab.getGuessTab().hasMoveOut()){
		
				//remove guess tab
				spriteLayer3.setInfluence(false);
				spriteLayer3.setOverlay(false);
				spriteLayer3.removeSprite(investigateTab.getGuessTab());
				
				investigateTab.getGuessTab().getSelectedInvestigateBlock().unselect();
				
				spriteLayer3.removeSprite(investigateTab.getGuessTab().getSelectedInvestigateBlock());
				investigateTab.addChild(investigateTab.getGuessTab().getSelectedInvestigateBlock());
				
				//has just completed word
				if(hasCompleteWord()){
					investigateTab.getInvestigateWordData().finish();
					investigateTab.getInvestigateBlocks().first().getMainBlock().finish();
					investigateTab.getInvestigateBlocks().first().getMainBlock().unavailable();
					
					
					
					//close investigate tab
					spriteLayer2.setInfluence(false);
					
					spriteLayer2.removeSprite(investigateTab);
					
					for(MainBlock tempMainBlock : mainBlocks){
						if(tempMainBlock.getState().equals(Block.SELECT_STATE)){
							tempMainBlock.unselect();
						}
					}
					
					//has just finished
					if(hasFinish()){
						setState(State.COMPLETE_GAME_STATE);
						
						currentTurnPlayer.finish(getGameManager().getCurrentRank());
						
						createCompleteGameLabel();
						
						AssetLoader.playMusic.stop();
						AssetLoader.completeSound.play(CWIGame.getSoundVolume());
						
						//Gdx.app.log("", "complete");
					}
					//not yet finish
					else{
						setState(PlayScreen.State.MAIN_STATE);
					}
				}
				//not completed word
				else{
					setState(PlayScreen.State.INVESTIGATE_STATE);
				}
				
				//
				isGuessing = false;
			}
			break;
		case HINT_CHARACTER_STATE:
			//
			if(investigateTab.getCharacterTab().getCharacterHintTab().hasMoveOut()){
				setState(PlayScreen.State.INVESTIGATE_STATE);
				
				investigateTab.getCharacterTab().removeChild(investigateTab.getCharacterTab().getCharacterHintTab());
				
				//
				spriteLayer3.setInfluence(false);
				spriteLayer3.setOverlay(false);
				
				spriteLayer3.removeSprite(investigateTab.getCharacterTab());
				investigateTab.addChild(investigateTab.getCharacterTab());
			}
			break;
		case HINT_SYLLABLE_STATE:
			if(investigateTab.getSyllableTab().getSyllableHintTab().hasMoveOut()){
				setState(PlayScreen.State.INVESTIGATE_STATE);
				
				investigateTab.getSyllableTab().removeChild(investigateTab.getSyllableTab().getSyllableHintTab());
				
				//
				spriteLayer3.setInfluence(false);
				spriteLayer3.setOverlay(false);
				
				spriteLayer3.removeSprite(investigateTab.getSyllableTab());
				investigateTab.addChild(investigateTab.getSyllableTab());
			}
			break;
		case HINT_TYPE_STATE:
			if(investigateTab.getTypeTab().getTypeHintTab().hasMoveOut()){
				setState(PlayScreen.State.INVESTIGATE_STATE);
				
				investigateTab.getTypeTab().removeChild(investigateTab.getTypeTab().getTypeHintTab());
				
				//
				spriteLayer3.setInfluence(false);
				spriteLayer3.setOverlay(false);
				
				spriteLayer3.removeSprite(investigateTab.getTypeTab());
				investigateTab.addChild(investigateTab.getTypeTab());
			}
			break;
		}
		
		//time
		timer -= delta;
		if(timer <= 0){
			timer = DECREASE_TIME_TOTAL;
			
			//
			if(state != State.SHOW_GLOSSARIES_STATE){
				if(!timeInfinite && !timesUp && !currentTurnPlayer.hasFinished()){
					currentTurnPlayer.decreaseTime(1);
					
					if(currentTurnPlayer.getTimeLeft() == 0){
						timesUp = true;
						
						Gdx.app.log("", "times up");
					}
					
					//update sandglass
					float total = 100f/gameManager.getTotalTime();
					timeLeftGrade.setPercentSize(timeLeftGrade.getPercentSize()-total);
				
				}	
			}
			
			//
			if(state == State.SHOW_GLOSSARIES_STATE){
				//
				if(!glossaryTimeInfinite){
					glossariesTab.decreaseTime(1);
					if(glossariesTab.getTimeLeft() == 0){
						removeGlossaryTab();
					}
				}
				
				
			}
			
			//
			if(state == State.TIMES_UP_STATE){
				timesUpStateTimer--;
				if(timesUpStateTimer == 0){
					endTurn();
				}
			}
			
			//	
			if(state == State.COMPLETE_GAME_STATE){
				completeGameStateTimer--;
				if(completeGameStateTimer == 0){
					completeGameStateTimer = 5;
					
					endTurn();
				}
			}	
		}
		
		//go to times up state
		if(state != State.TIMES_UP_STATE){
			if(timesUp && !isGuessing && !currentTurnPlayer.hasFinished()){
				setState(State.TIMES_UP_STATE);
				createTimesUpLabel();
				
				AssetLoader.playMusic.stop();
				AssetLoader.bellSound.play(CWIGame.getSoundVolume());
			}
		}

		
		
	}
	
	private void updateCompleteWordText() {
		completeWordText.setText(currentTurnPlayer.getCompleteWordTotal()+"/"+TOTAL_WORDS);
		completeWordText.setPosition(357 - completeWordText.getWidth()/2, 
				585 - completeWordText.getHeight()/2);
		if(currentTurnPlayer.getCompleteWordTotal() == TOTAL_WORDS){
			completeWordText.setColor(Color.FOREST);
		}
		
	}



	@Override
	public void doEvents() {
		//buttons
		switch(state){
		case COMPLETE_GAME_STATE:
			break;
		case GUESS_LETTER_STATE:
			//
			if(investigateTab.getGuessTab().getCloseButton().isClicked()){
				investigateTab.getGuessTab().moveOut();
				
				
			}
			
			//
			for(LetterButton tempLetterButton : investigateTab.getGuessTab().getLetterButtons()){
				if(tempLetterButton.isClicked()){
					selectGuessTabLetter(tempLetterButton);
					
					
					
					break;
				}
			}
			
			//guess button
			if(investigateTab.getGuessTab().getGuessButton().isClicked()){
				guessLetter();
				
				
			}
			
			break;
		case HINT_CHARACTER_STATE:
			//
			if(investigateTab.getCharacterTab().getCharacterHintTab().getCloseButton().isClicked()){
				investigateTab.getCharacterTab().getCharacterHintTab().moveOut();
			}
			
			//
			for(LetterButton tempLetterButton : investigateTab.getCharacterTab().getCharacterHintTab().getLetterButtons()){
				if(tempLetterButton.isClicked()){
					selectCharacterHintTabLetter(tempLetterButton);
					
					
					
					break;
				}
			}
			
			//
			//use button
			if(investigateTab.getCharacterTab().getCharacterHintTab().getUseButton().isClicked()){
				useCharacterHint();
				
				
			}
			
			break;
		case HINT_SYLLABLE_STATE:
			
			if(investigateTab.getSyllableTab().getSyllableHintTab().getCloseButton().isClicked()){
				investigateTab.getSyllableTab().getSyllableHintTab().moveOut();
				
				
			}
			else if(investigateTab.getSyllableTab().getSyllableHintTab().getUseButton().isClicked()){
				useSyllableHint();
				
				
			}
			
			break;
		case HINT_TYPE_STATE:
			if(investigateTab.getTypeTab().getTypeHintTab().getCloseButton().isClicked()){
				investigateTab.getTypeTab().getTypeHintTab().moveOut();
				
				
			}
			else if(investigateTab.getTypeTab().getTypeHintTab().getUseButton().isClicked()){
				useTypeHint();
				
				
			}
			
			break;
		case INVESTIGATE_STATE:
			
			if(investigateTab.getCloseButton().isClicked()){
				closeInvestigateTab();
				
				
			}
			
			else if(investigateTab.getCharacterTab().getHintButton().isClicked()){
				showCharacterHintTab();
				
				
			}
			else if(investigateTab.getSyllableTab().getHintButton().isClicked()){
				showSyllableHintTab();
				
				
			}
			else if(investigateTab.getTypeTab().getHintButton().isClicked()){
				showTypeHintTab();
				
				
			}
			
			
			for(InvestigateBlock tempInvestigateBlock : investigateTab.getInvestigateBlocks()){
				if(tempInvestigateBlock.isClicked()){
					//System.out.println("show guess tab ");
					showGuessTab(tempInvestigateBlock);
					
					
					break;
				}
			}
			
			break;
		case LOAD_INVESTIGATE_STATE:
			break;
		case MAIN_STATE:
			
			//
			if(statusButton.isClicked()){
				createStatusTab();
				
				
			}
			
			//
			for(MainBlock tempHeadMainBlock : headMainBlocks){
				
				if(tempHeadMainBlock.isClicked()){
					
					//
					selectHeadMainBlock(tempHeadMainBlock);
					
					
					
					break;
				}
			}
			
			break;
		case SHOW_GLOSSARIES_STATE:
			if(glossariesTab.getCloseButton().isClicked()){
				removeGlossaryTab();
				
				
			}
			else if(glossariesTab.getPreviousButton().isClicked()){
				glossariesTab.previousPage();
				
			}
			else if(glossariesTab.getNextButton().isClicked()){
				glossariesTab.nextPage();
				
				
			}
				
			break;
		case STATUS_STATE:
			if(statusTab.getEndTurnButton().isClicked()){
				endTurn();
				
				
			}
			else if(statusTab.getCloseButton().isClicked()){
				statusTab.moveOut();
				
				
			}
			
			break;
		case TIMES_UP_STATE:
			break;
		default:
			break;
		
		}
	}



	protected void removeGlossaryTab() {
		spriteLayer5.removeSprite(glossariesTab);
		spriteLayer5.setInfluence(false);
		
		setState(PlayScreen.State.MAIN_STATE);
		
	}

	

	
}
	
