package com.nutslaboratory.screens;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncResult;
import com.badlogic.gdx.utils.async.AsyncTask;
import com.nutslaboratory.cwi.CWIGame;
import com.nutslaboratory.gameobjects.BackMenu;
import com.nutslaboratory.gameobjects.GameManager;
import com.nutslaboratory.gameobjects.Player;
import com.nutslaboratory.gameobjects.PlayerSettingButton;
import com.nutslaboratory.gameobjects.TutorialMessage;
import com.nutslaboratory.gameobjects.settingTabs.ExistSettingTab;
import com.nutslaboratory.gameobjects.settingTabs.GuessSettingTab;
import com.nutslaboratory.gameobjects.settingTabs.HintSettingTab;
import com.nutslaboratory.gameobjects.settingTabs.ShowSettingTab;
import com.nutslaboratory.gameobjects.settingTabs.TimeSettingTab;
import com.nutslaboratory.gameobjects.settingTabs.WordSettingTab;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutButton;
import com.nutslaboratory.nutlibgdxgameengine.NutGame;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutSpriteLayer;
import com.nutslaboratory.nutlibgdxgameengine.NutText;

public class MainMenuScreen extends NutScreen{
	
	private static String PLAYER_1_TYPE_DEFAULT = Player.HUMAN_PLAYER_TYPE;
	private static String PLAYER_2_TYPE_DEFAULT = Player.COMPUTER_PLAYER_TYPE;
	private static String PLAYER_3_TYPE_DEFAULT = Player.NONE_PLAYER_TYPE;
	private static String PLAYER_4_TYPE_DEFAULT = Player.NONE_PLAYER_TYPE;
	
	private static int WORD_CURRENT_INDEX_DEFAULT = 3;
	private static int SHOW_CURRENT_INDEX_DEFAULT = 1;
	private static int EXIST_CURRENT_INDEX_DEFAULT = 3;
	private static int TIME_CURRENT_INDEX_DEFAULT = 3;
	private static int GUESS_CURRENT_INDEX_DEFAULT = 3;
	private static int HINT_CURRENT_INDEX_DEFAULT = 6;
	
	private static final int[] wordsValues = {1,2,3,4,5,6,7,8,9,10};
	private static final int[] showValues = {30,50,80,100,120};
	private static final int[] existValues = {0,1,2,3};
	private static final int[] timeValues = {40,60,80,100,120};
	private static final int[] guessValues = {1,2,3,4,5};
	private static final int[] hintValues = {0,1,2,3,4,5,6,7,8,9,10};
	
	private int wordCurrentIndex;
	private int showCurrentIndex;
	private int existCurrentIndex;
	private int timeCurrentIndex;
	private int guessCurrentIndex;
	private int hintCurrentIndex;
	
	private String player1Type, player2Type, player3Type, player4Type;
	
	private NutSprite background;
	
	private NutButton soundSettingButton, defaultButton, startButton, helpButton;
	
	private PlayerSettingButton player1Button, player2Button, player3Button, player4Button;
	
	WordSettingTab wordSettingTab;
	ShowSettingTab showSettingTab;
	ExistSettingTab existSettingTab;
	TimeSettingTab timeSettingTab;
	GuessSettingTab guessSettingTab;
	HintSettingTab hintSettingTab;
	
	private NutSprite warningLabel;
	
	private GameManager gameManager;
	
	
	
	protected BackMenu backMenu;
	
	private NutSpriteLayer spriteLayer1 = new NutSpriteLayer(this, "layer1");
	private NutSpriteLayer spriteLayer2 = new NutSpriteLayer(this, "layer2");
	private NutSpriteLayer spriteLayer3 = new NutSpriteLayer(this, "layer3");
	
	public enum State{
		MAIN_STATE, LOADING_STATE, BACK_MENU_STATE
	}
	
	private State state;
	private State oldState;
	
	AsyncResult<Void> task;
	
	public MainMenuScreen(NutGame game) {
		super(game);
		
	}
	
	private void saveSettings(){
		//System.out.println("save setting");
		
		//
		wordCurrentIndex = wordSettingTab.getCurrentIndex();
		showCurrentIndex = showSettingTab.getCurrentIndex();
		existCurrentIndex = existSettingTab.getCurrentIndex();
		timeCurrentIndex = timeSettingTab.getCurrentIndex();
		guessCurrentIndex = guessSettingTab.getCurrentIndex();
		hintCurrentIndex = hintSettingTab.getCurrentIndex();
		
		//
		CWIGame.getPlayPreferences().putInteger("wordCurrentIndex", wordCurrentIndex);
		CWIGame.getPlayPreferences().putInteger("showCurrentIndex", showCurrentIndex);
		CWIGame.getPlayPreferences().putInteger("existCurrentIndex", existCurrentIndex);
		CWIGame.getPlayPreferences().putInteger("timeCurrentIndex", timeCurrentIndex);
		CWIGame.getPlayPreferences().putInteger("guessCurrentIndex", guessCurrentIndex);
		CWIGame.getPlayPreferences().putInteger("hintCurrentIndex", hintCurrentIndex);
		CWIGame.getPlayPreferences().putString("player1Type", player1Type);
		CWIGame.getPlayPreferences().putString("player2Type", player2Type);
		CWIGame.getPlayPreferences().putString("player3Type", player3Type);
		CWIGame.getPlayPreferences().putString("player4Type", player4Type);
		
		//
		CWIGame.getPlayPreferences().flush();
		
		
	}
	
	private void setDefault(){
		//
		wordCurrentIndex = WORD_CURRENT_INDEX_DEFAULT;
		showCurrentIndex = SHOW_CURRENT_INDEX_DEFAULT;
		existCurrentIndex = EXIST_CURRENT_INDEX_DEFAULT;
		timeCurrentIndex = TIME_CURRENT_INDEX_DEFAULT;
		guessCurrentIndex = GUESS_CURRENT_INDEX_DEFAULT;
		hintCurrentIndex = HINT_CURRENT_INDEX_DEFAULT;
		
		wordSettingTab.setCurrentIndex(wordCurrentIndex);
		showSettingTab.setCurrentIndex(showCurrentIndex);
		existSettingTab.setCurrentIndex(existCurrentIndex);
		timeSettingTab.setCurrentIndex(timeCurrentIndex);
		guessSettingTab.setCurrentIndex(guessCurrentIndex);
		hintSettingTab.setCurrentIndex(hintCurrentIndex);
		
		
		//
		player1Type = PLAYER_1_TYPE_DEFAULT;
		player2Type = PLAYER_2_TYPE_DEFAULT;
		player3Type = PLAYER_3_TYPE_DEFAULT;
		player4Type = PLAYER_4_TYPE_DEFAULT;
		
		player1Button.setType(player1Type);
		player2Button.setType(player2Type);
		player3Button.setType(player3Type);
		player4Button.setType(player4Type);
		
		//show/hide warning label
		if(getTotalPlayers() < 2){
			warningLabel.setVisible(true);
			startButton.unavailable();
		}else{
			warningLabel.setVisible(false);
			startButton.unselect();
		}
		
		//
		saveSettings();
	}

	@Override
	public void show() {
		//
		addSpriteLayer(spriteLayer1);
		addSpriteLayer(spriteLayer2);
		addSpriteLayer(spriteLayer3);
		
		//
		wordCurrentIndex = CWIGame.getPlayPreferences().getInteger("wordCurrentIndex", WORD_CURRENT_INDEX_DEFAULT);
		showCurrentIndex = CWIGame.getPlayPreferences().getInteger("showCurrentIndex", SHOW_CURRENT_INDEX_DEFAULT);
		existCurrentIndex = CWIGame.getPlayPreferences().getInteger("existCurrentIndex", EXIST_CURRENT_INDEX_DEFAULT);
		timeCurrentIndex = CWIGame.getPlayPreferences().getInteger("timeCurrentIndex", TIME_CURRENT_INDEX_DEFAULT);
		guessCurrentIndex = CWIGame.getPlayPreferences().getInteger("guessCurrentIndex", GUESS_CURRENT_INDEX_DEFAULT);
		hintCurrentIndex = CWIGame.getPlayPreferences().getInteger("hintCurrentIndex", HINT_CURRENT_INDEX_DEFAULT);
		
		player1Type = CWIGame.getPlayPreferences().getString("player1Type", PLAYER_1_TYPE_DEFAULT);
		player2Type = CWIGame.getPlayPreferences().getString("player2Type", PLAYER_2_TYPE_DEFAULT);
		player3Type = CWIGame.getPlayPreferences().getString("player3Type", PLAYER_3_TYPE_DEFAULT);
		player4Type = CWIGame.getPlayPreferences().getString("player4Type", PLAYER_4_TYPE_DEFAULT);
		
		/*
		System.out.println(player1Type);
		System.out.println(player2Type);
		System.out.println(player3Type);
		System.out.println(player4Type);
		*/
		
	
		//
		createBackground();
	
		createSettingTabs();
		
		createSoundSettingButton();
		createDefaultButton();
		createStartButton();
		createHelpButton();
		createPlayerButtons();
		
		createWarningLabel();
		
		//set state
		setState(State.MAIN_STATE);
		
		//tutorial checking
		if(((CWIGame)getGame()).hasPassTutorial()){
			//Gdx.app.log("", "has pass tutorial");
		}else{
			//Gdx.app.log("", "hasn't pass tutorial");
			
			createBackMenu();
			setState(State.BACK_MENU_STATE);
			
		}
		
		//music
		if(!AssetLoader.backgroundMusic.isPlaying()){
			AssetLoader.playMusic.stop();
			AssetLoader.backgroundMusic.play();
		}
		
	}
	
	private void createWarningLabel() {
		warningLabel = new NutSprite(this, AssetLoader.warningLabelTexture);
		warningLabel.setPosition(250, 220);
		spriteLayer1.addSprite(warningLabel);
		
		//show/hide warning label
		if(getTotalPlayers() < 2){
			warningLabel.setVisible(true);
			startButton.unavailable();
		}else{
			warningLabel.setVisible(false);
			startButton.unselect();
		}
		
	}

	
	
	private void createPlayerButtons() {
		//
		NutSprite playerSettingTab = new NutSprite(this, AssetLoader.playerSettingTabTexture);
		playerSettingTab.setPosition(15, 194);
		spriteLayer1.addSprite(playerSettingTab);
		
		//
		player1Button = new PlayerSettingButton(this, AssetLoader.player1NoneTexture,
				AssetLoader.player1HumanTexture,
				AssetLoader.player1RobotTexture,
				1);
		spriteLayer1.addSprite(player1Button);
		player1Button.setPosition(20, 312);
		player1Button.setType(player1Type);
		
		
		player2Button = new PlayerSettingButton(this, AssetLoader.player2NoneTexture,
				AssetLoader.player2HumanTexture,
				AssetLoader.player2RobotTexture,
				2);
		spriteLayer1.addSprite(player2Button);
		player2Button.setPosition(136, 312);
		player2Button.setType(player2Type);
		
		player3Button = new PlayerSettingButton(this, AssetLoader.player3NoneTexture,
				AssetLoader.player3HumanTexture,
				AssetLoader.player3RobotTexture,
				3);
		spriteLayer1.addSprite(player3Button);
		player3Button.setPosition(20, 202);
		player3Button.setType(player3Type);
		
		player4Button = new PlayerSettingButton(this, AssetLoader.player4NoneTexture,
				AssetLoader.player4HumanTexture,
				AssetLoader.player4RobotTexture,
				4);
		spriteLayer1.addSprite(player4Button);
		player4Button.setPosition(136, 202);
		player4Button.setType(player4Type);
		
	}
	
	private void createBackground() {
		background = new NutSprite(this, AssetLoader.setupBackgroundTexture);
		spriteLayer1.addSprite(background);
	}
	
	
	private void createSoundSettingButton() {
		soundSettingButton = new NutButton(this, AssetLoader.soundSettingButtonTexture, 
				null, AssetLoader.soundSettingButtonTexture);
		soundSettingButton.setTouchSound(AssetLoader.buttonSound);
	 	soundSettingButton.setPosition(15, 70);
	 	spriteLayer1.addSprite(soundSettingButton);
		
	}
	
	private void createDefaultButton() {
		defaultButton = new NutButton(this, AssetLoader.defaultButtonTexture, 
				null, AssetLoader.defaultButtonTexture);
		defaultButton.setTouchSound(AssetLoader.buttonSound);
		defaultButton.setPosition(110, 90);
		spriteLayer1.addSprite(defaultButton);
		
	}
	
	private void createStartButton() {
		startButton = new NutButton(this, AssetLoader.startButtonTexture,
				AssetLoader.startButtonUnavailableTexture, AssetLoader.startButtonTexture);
		startButton.setTouchSound(AssetLoader.buttonSound);
		startButton.setPosition(240, 90);
		spriteLayer1.addSprite(startButton);
	}
	
	private void createHelpButton() {
		helpButton = new NutButton(this, AssetLoader.helpButtonTexture, 
				null, AssetLoader.helpButtonTexture);
		helpButton.setTouchSound(AssetLoader.buttonSound);
		helpButton.setPosition(370, 70);
		spriteLayer1.addSprite(helpButton);
		
		
	}


	private void createSettingTabs() {
		//
		wordSettingTab = new WordSettingTab(this, wordsValues);
		wordSettingTab.setPosition(20, 530);
		spriteLayer1.addSprite(wordSettingTab);
		
		showSettingTab = new ShowSettingTab(this, showValues);
		showSettingTab.setPosition(158, 530);
		spriteLayer1.addSprite(showSettingTab);
		
		existSettingTab = new ExistSettingTab(this, existValues);
		existSettingTab.setPosition(295, 530);
		spriteLayer1.addSprite(existSettingTab);
		
		timeSettingTab = new TimeSettingTab(this, timeValues);
		timeSettingTab.setPosition(20, 430);
		spriteLayer1.addSprite(timeSettingTab);
		
		guessSettingTab = new GuessSettingTab(this, guessValues);
		guessSettingTab.setPosition(260, 430);
		spriteLayer1.addSprite(guessSettingTab);
		
		hintSettingTab = new HintSettingTab(this, hintValues);
		hintSettingTab.setPosition(295, 330);
		spriteLayer1.addSprite(hintSettingTab);
		
		//
		wordSettingTab.setCurrentIndex(wordCurrentIndex);
		showSettingTab.setCurrentIndex(showCurrentIndex);
		existSettingTab.setCurrentIndex(existCurrentIndex);
		timeSettingTab.setCurrentIndex(timeCurrentIndex);
		guessSettingTab.setCurrentIndex(guessCurrentIndex);
		hintSettingTab.setCurrentIndex(hintCurrentIndex);
	}

	public void setState(State state){
		//System.out.println(state);	
		
		oldState = this.state;
		
		this.state = state;
		
		
	}
	
	private int getTotalPlayers(){
		int totalPlayers = 0;
		
		Array<String> playerTypes = new Array<String>();
		playerTypes.add(player1Type);
		playerTypes.add(player2Type);
		playerTypes.add(player3Type);
		playerTypes.add(player4Type);
		
		for(int i = 0; i < playerTypes.size; i++){
			if(playerTypes.get(i).equals(Player.NONE_PLAYER_TYPE)){
				continue;
			}
			
			totalPlayers++;
		}
		
		return totalPlayers;
	}
	
	
	
	
	
	@Override
	public void update(float delta){
		super.update(delta);
		
		// checks to see if the task is done.  If not draw the load screen
	    if(task != null && task.isDone()) {
	    	//System.out.println("task done");
	    	startGame();
	    }
	}
	
	@Override
	public void doEvents() {
		//
		
	    switch(state){
		case LOADING_STATE:
			break;
		case MAIN_STATE:
			//
			if(soundSettingButton.isClicked()){
				getGame().setScreen(new SoundSettingScreen(getGame()));
			}
			else if(defaultButton.isClicked()){
				setDefault();
			}
			else if(startButton.isClicked()){
				if(getTotalPlayers() > 1){
					//show loading text
					NutText loadingLabel = new NutText(this, AssetLoader.charlemagne32Font, "Loading...");
					loadingLabel.setPosition(150, 350);
					spriteLayer2.addSprite(loadingLabel);
					
					//
					spriteLayer2.setInfluence(true);
					spriteLayer2.setOverlay(true);
					
					//
					setState(State.LOADING_STATE);
					
					 //create our async task that runs our async method
					AsyncExecutor asyncExecutor = new AsyncExecutor(1);
					
					task = asyncExecutor.submit(new AsyncTask<Void>() {
				        public Void call() {
				        	startGame();
				            return null;
				        } 
				    });
				}
			}
			else if(helpButton.isClicked()){
				getGame().setScreen(new HelpSettingScreen(getGame()));
				
				
			}
			
			//
			else if(wordSettingTab.getDecreaseButton().isClicked()){
				wordSettingTab.decrease();
				saveSettings();
			}
			else if(wordSettingTab.getIncreaseButton().isClicked()){
				wordSettingTab.increase();
				saveSettings();
			}
			
			else if(showSettingTab.getDecreaseButton().isClicked()){
				showSettingTab.decrease();
				saveSettings();
			}
			else if(showSettingTab.getIncreaseButton().isClicked()){
				showSettingTab.increase();
				saveSettings();
			}
			
			else if(existSettingTab.getDecreaseButton().isClicked()){
				existSettingTab.decrease();
				saveSettings();
			}
			else if(existSettingTab.getIncreaseButton().isClicked()){
				existSettingTab.increase();
				saveSettings();
			}
			
			else if(timeSettingTab.getDecreaseButton().isClicked()){
				timeSettingTab.decrease();
				saveSettings();
			}
			else if(timeSettingTab.getIncreaseButton().isClicked()){
				timeSettingTab.increase();
				saveSettings();
			}
			
			else if(guessSettingTab.getDecreaseButton().isClicked()){
				guessSettingTab.decrease();
				saveSettings();
			}
			else if(guessSettingTab.getIncreaseButton().isClicked()){
				guessSettingTab.increase();
				saveSettings();
			}
			
			else if(hintSettingTab.getDecreaseButton().isClicked()){
				hintSettingTab.decrease();
				saveSettings();
			}
			else if(hintSettingTab.getIncreaseButton().isClicked()){
				hintSettingTab.increase();
				saveSettings();
			}
			
			break;
				
		case BACK_MENU_STATE:
			if(backMenu.getYesButton().isClicked()){
				Array<String> playerTypes = new Array<String>();
				playerTypes.add(Player.HUMAN_PLAYER_TYPE);
				playerTypes.add(Player.COMPUTER_PLAYER_TYPE);
				
				int totalWords = 2;
				int totalGlossaries = 20;
				int totalTime = 60;
				int totalHint = 5;
				int totalWrongGuess = 3;
				int totalExistLetter = 3;
				
				GameManager gameManager = new GameManager(GameManager.TUTORIAL_GAME_TYPE,
						totalWords,
						totalGlossaries, 
						totalTime, 
						totalHint, 
						totalWrongGuess,
						totalExistLetter,
						playerTypes,
						new TutorialMessage());
				
				getGame().setScreen(new TutorialPunctuateScreen(getGame(), gameManager, TutorialPunctuateScreen.EXIT_TO_MAIN_MENU_SCREEN));
				
			}
			else if(backMenu.getNoButton().isClicked()){
				removeBackMenu();
				setState(State.MAIN_STATE);
				
				
				((CWIGame)getGame()).passTutorial();
				
				
			}
			
			break;
		default:
			break;
	   
	    }
		
	}

	private void startGame() {
		//
		Array<String> playerTypes = new Array<String>();
		playerTypes.add(player1Type);
		playerTypes.add(player2Type);
		playerTypes.add(player3Type);
		playerTypes.add(player4Type);
		
		//
		gameManager = new GameManager(GameManager.REAL_GAME_TYPE,
				wordSettingTab.getValue(),
				showSettingTab.getValue(), 
				timeSettingTab.getValue(), 
				hintSettingTab.getValue(), 
				guessSettingTab.getValue(),
				existSettingTab.getValue(),
				playerTypes,
				null);
		
		getGame().setScreen(new PunctuateScreen(getGame(), gameManager));
		
	}
	
	public void createBackMenu(){
		//
		backMenu = new BackMenu(this, AssetLoader.charlemagne24Font, "Tutorial First ?");
		backMenu.setPosition(getWorldWidth()/2 - backMenu.getWidth()/2,
				getWorldHeight()/2 - backMenu.getHeight()/2);
		spriteLayer3.addSprite(backMenu);
		
		//
		spriteLayer3.setInfluence(true);
		spriteLayer3.setOverlay(true);
		
	}
	
	public void removeBackMenu(){
		spriteLayer3.removeSprite(backMenu);
		
		//
		spriteLayer3.setInfluence(false);
		spriteLayer3.setOverlay(false);
	}

	@Override
	public void pressBack(){
		if(state.equals(State.MAIN_STATE)){
			getGame().setScreen(new StartScreen(getGame()));	
		}
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		super.touchDown(screenX, screenY, pointer, button);
	    
	    switch(state){
		
		case MAIN_STATE:
			
			//touch player's buttons
			if(player1Button.isCollidePoint(getTouchX(), getTouchY())){
				player1Button.nextType();
				
				player1Type = player1Button.getPlayerType();
				//System.out.println(player1Type);
				saveSettings();
				
				AssetLoader.touchSound.play(CWIGame.getSoundVolume());
				
			}
			else if(player2Button.isCollidePoint(getTouchX(), getTouchY())){
				player2Button.nextType();
				
				player2Type = player2Button.getPlayerType();
				//System.out.println(player2Type);
				saveSettings();
				
				AssetLoader.touchSound.play(CWIGame.getSoundVolume());
				
			}
			else if(player3Button.isCollidePoint(getTouchX(), getTouchY())){
				player3Button.nextType();
				
				player3Type = player3Button.getPlayerType();
				//System.out.println(player3Type);
				saveSettings();
				
				AssetLoader.touchSound.play(CWIGame.getSoundVolume());
				
			}
			else if(player4Button.isCollidePoint(getTouchX(), getTouchY())){
				player4Button.nextType();
				
				player4Type = player4Button.getPlayerType();
				//System.out.println(player4Type);
				saveSettings();
				
				AssetLoader.touchSound.play(CWIGame.getSoundVolume());
				
			}
			
			//show/hide warning label
			if(getTotalPlayers() < 2){
				warningLabel.setVisible(true);
				startButton.unavailable();
			}else{
				warningLabel.setVisible(false);
				startButton.unselect();
			}
			
			break;
		case LOADING_STATE:
			break;
		default:
			break;
	    }
		
		
		return false;
	}
}
