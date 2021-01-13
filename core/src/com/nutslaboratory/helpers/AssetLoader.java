package com.nutslaboratory.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	private static AssetManager assetManager;
	
	private static TextureAtlas textureAtlas;
	
	public static TextureRegion openBackgroundTexture;
	public static TextureRegion punctuateBackgroundTexture; 
	public static TextureRegion playButtonTexture;
	public static TextureRegion tutorialButtonTexture;
	public static TextureRegion aboutButtonTexture;
	public static TextureRegion setupBackgroundTexture;
	public static TextureRegion boardBackgroundTexture;
	public static TextureRegion statusTabTexture;
	public static TextureRegion investigateTabTexture;
	public static TextureRegion glossariesTabTexture;
	public static TextureRegion nextButtonTexture;
	public static TextureRegion nextButtonUnavailableTexture;
	public static TextureRegion previousButtonTexture;
	public static TextureRegion previousButtonUnavailableTexture;
	public static TextureRegion closeTabButtonTexture;
	public static TextureRegion startButtonTexture;
	public static TextureRegion startButtonUnavailableTexture;
	public static TextureRegion endTurnButtonTexture;
	public static TextureRegion decreaseButtonTexture;
	public static TextureRegion increaseButtonTexture;
	public static TextureRegion defaultButtonBlockTexture;
	public static TextureRegion selectButtonBlockTexture;
	public static TextureRegion defaultNormalBlockTexture;
	public static TextureRegion selectNormalBlockTexture;
	public static TextureRegion defaultHumanPlayerButtonTexture;
	public static TextureRegion unavailableHumanPlayerButtonTexture;
	public static TextureRegion defaultComputerPlayerButtonTexture;
	public static TextureRegion unavailableComputerPlayerButtonTexture;
	public static TextureRegion currentTurnPlayerMarkerTexture;
	public static TextureRegion defaultUseHintButtonTexture;
	public static TextureRegion unavailableUseHintButtonTexture;
	
	public static TextureRegion hintPointOneTexture;
	public static TextureRegion hintPointTiltTexture;
	
	public static TextureRegion decreaseLeftTexture;
	public static TextureRegion decreaseLeftUnavailableTexture;
	public static TextureRegion decreaseDownTexture;
	public static TextureRegion decreaseDownUnavailableTexture;
	public static TextureRegion increaseRightTexture;
	public static TextureRegion increaseRightUnavailableTexture;
	public static TextureRegion increaseUpTexture;
	public static TextureRegion increaseUpUnavailableTexture;
	
	public static TextureRegion soundSettingButtonTexture;
	public static TextureRegion defaultButtonTexture;
	public static TextureRegion helpButtonTexture;
	
	public static TextureRegion showPointTexture;
	public static TextureRegion existPointTexture;
	public static TextureRegion timePointTexture;
	
	public static TextureRegion player1NoneTexture;
	public static TextureRegion player1HumanTexture;
	public static TextureRegion player1RobotTexture;
	public static TextureRegion player2NoneTexture;
	public static TextureRegion player2HumanTexture;
	public static TextureRegion player2RobotTexture;
	public static TextureRegion player3NoneTexture;
	public static TextureRegion player3HumanTexture;
	public static TextureRegion player3RobotTexture;
	public static TextureRegion player4NoneTexture;
	public static TextureRegion player4HumanTexture;
	public static TextureRegion player4RobotTexture;
	
	public static TextureRegion robotThinkATexture;
	public static TextureRegion robotThinkBTexture;
	
	public static TextureRegion helpSettingATexture;
	public static TextureRegion helpSettingBTexture;
	public static TextureRegion helpSettingCTexture;
	
	public static TextureRegion leftButtonTexture;
	public static TextureRegion leftButtonUnavailableTexture;
	public static TextureRegion rightButtonTexture;
	public static TextureRegion rightButtonUnavailableTexture;
	public static TextureRegion exitButtonTexture;
	
	public static TextureRegion human1IconTexture;
	public static TextureRegion human2IconTexture;
	public static TextureRegion human3IconTexture;
	public static TextureRegion human4IconTexture;
	public static TextureRegion robot1IconTexture;
	public static TextureRegion robot2IconTexture;
	public static TextureRegion robot3IconTexture;
	public static TextureRegion robot4IconTexture;
	
	public static TextureRegion player1LabelTexture;
	public static TextureRegion player2LabelTexture;
	public static TextureRegion player3LabelTexture;
	public static TextureRegion player4LabelTexture;
	public static TextureRegion robot1LabelTexture;
	public static TextureRegion robot2LabelTexture;
	public static TextureRegion robot3LabelTexture;
	public static TextureRegion robot4LabelTexture;
	
	public static TextureRegion winFirstIconTexture;
	public static TextureRegion winSecondIconTexture;
	public static TextureRegion winThirdIconTexture;
	public static TextureRegion winFourthIconTexture;
	
	public static TextureRegion soundSettingBackgroundTexture;
	
	public static TextureRegion soundSliderBaseTexture;
	public static Texture soundSliderTexture;
	public static TextureRegion soundKnobTexture;
	
	public static TextureRegion gradeBaseTexture;
	public static Texture gradeTexture;
	
	public static TextureRegion sandglassBaseTexture;
	public static Texture sandglassFrontTexture;
	
	
	public static TextureRegion glossariesTabBackgroundTexture;
	public static TextureRegion glossariesTabForegroundTexture;
	public static TextureRegion glossariesTabPaperTexture;
	
	public static TextureRegion characterTabTexture;
	public static TextureRegion syllableTabTexture;
	public static TextureRegion typeTabTexture;
	
	public static TextureRegion letterButtonTexture;
	public static TextureRegion letterButtonUnavailableTexture;
	public static TextureRegion letterButtonSelectTexture;
	
	public static TextureRegion computer1BackgroundTexture;
	public static TextureRegion computer2BackgroundTexture;
	public static TextureRegion computer3BackgroundTexture;
	public static TextureRegion computer4BackgroundTexture;
	
	public static TextureRegion blankTexture;
	
	public static TextureRegion insideWordMarkTexture;
	public static TextureRegion outsideWordMarkTexture;
	
	public static TextureRegion wrongGuesslightBlankTexture;
	public static TextureRegion wrongGuesslightRedTexture;
	
	public static TextureRegion backMenuTexture;
	public static TextureRegion yesButtonTexture;
	public static TextureRegion noButtonTexture;
	
	public static TextureRegion cluePaperTexture;
	
	public static Texture guessEffectAnimationTexture;
	
	public static TextureRegion hintBoxTexture;
	public static TextureRegion hintBoxOKButtonTexture;
	public static TextureRegion hintArrowUpTexture;
	public static TextureRegion hintArrowDownTexture;
	public static TextureRegion hintArrowLeftTexture;
	public static TextureRegion hintArrowRightTexture;
	public static TextureRegion hintFingerUpTexture;
	public static TextureRegion hintFingerDownTexture;
	public static TextureRegion hintFingerLeftTexture;
	public static TextureRegion hintFingerRightTexture;
	
	public static TextureRegion buttonBackground1Texture;
	public static TextureRegion buttonBackground2Texture;
	public static TextureRegion buttonBackground3Texture;
	
	public static TextureRegion borderHorTexture;
	public static TextureRegion borderVerTexture;
	
	public static TextureRegion guessLetterTabTexture;
	public static TextureRegion smallKeyboardTabTexture;
	
	public static TextureRegion statusButtonTexture;
	
	public static TextureRegion warningLabelTexture;
	
	public static TextureRegion timesUpLabelTexture;
	public static TextureRegion completeLabelTexture;
	
	public static TextureRegion magnifierTexture;
	public static TextureRegion titleTexture;
	
	public static TextureRegion hintDefaultButtonTexture;
	public static TextureRegion hintButtonUnavailableTexture;
	public static TextureRegion hintButtonSelectTexture;
	
	public static TextureRegion defaultUseButtonTexture;
	public static TextureRegion useButtonUnavailableTexture;
	
	public static TextureRegion wordSettingTabTexture;
	public static TextureRegion showSettingTabTexture;
	public static TextureRegion existSettingTabTexture;
	public static TextureRegion timeSettingTabTexture;
	public static TextureRegion guessSettingTabTexture;
	public static TextureRegion hintSettingTabTexture;
	
	public static TextureRegion playerSettingTabTexture;
	
	public static TextureRegion aboutSceneTexture;
	
	public static BitmapFont charlemagne14Font;
	public static BitmapFont charlemagne16Font;
	public static BitmapFont charlemagne18Font;
	public static BitmapFont charlemagne20Font;
	public static BitmapFont charlemagne24Font;
	public static BitmapFont charlemagne32Font;
	public static BitmapFont calibri18Font;
	public static BitmapFont calibri20Font;
	
	public static Music backgroundMusic;
	public static Music playMusic;
	public static Sound buttonSound;
	public static Sound flipPaperSound;
	public static Sound completeSound;
	public static Sound bellSound;
	public static Sound robotSound;
	public static Sound touchSound;
	public static Sound magicSound;
	public static Sound hintSound;
	public static Sound wrongSound;
	public static Sound moveSound;
	
	
	
	public static void loadAssets(){
		//System.out.println("load assets");
		
		//
		assetManager = new AssetManager();
		
		//
		assetManager.load("game_assets.pack", TextureAtlas.class);
		
		assetManager.load("sound_slider.png", Texture.class);
		assetManager.load("grade.png", Texture.class);
		assetManager.load("sandglass_front.png", Texture.class);
		
		assetManager.load("guess_effect_animation.png", Texture.class);
		
		assetManager.load("sounds/Spy Glass.ogg", Music.class);
		assetManager.load("sounds/Mining by Moonlight.ogg", Music.class);
		assetManager.load("sounds/click.ogg", Sound.class);
		assetManager.load("sounds/flip_paper.ogg", Sound.class);
		assetManager.load("sounds/complete.ogg", Sound.class);
		assetManager.load("sounds/bell.ogg", Sound.class);
		assetManager.load("sounds/robot.ogg", Sound.class);
		assetManager.load("sounds/beep.ogg", Sound.class);
		assetManager.load("sounds/magic.ogg", Sound.class);
		assetManager.load("sounds/hint.ogg", Sound.class);
		assetManager.load("sounds/wrong.ogg", Sound.class);
		assetManager.load("sounds/move.ogg", Sound.class);
		
	
	}
	
	public static void getAssets(){
		//System.out.println("get assets");
		
		//
		textureAtlas = assetManager.get("game_assets.pack");
		
		for(Texture texture : textureAtlas.getTextures()){
			//Gdx.app.log("", ""+texture);
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		//
		openBackgroundTexture = textureAtlas.findRegion("open_background");
		boardBackgroundTexture = textureAtlas.findRegion("board_background");
		setupBackgroundTexture = textureAtlas.findRegion("setup_background");
		punctuateBackgroundTexture = textureAtlas.findRegion("punctuate_background");
		
		playButtonTexture = textureAtlas.findRegion("play_button");
		tutorialButtonTexture = textureAtlas.findRegion("tutorial_button");
		aboutButtonTexture = textureAtlas.findRegion("about_button");
		statusTabTexture = textureAtlas.findRegion("status_tab");
		investigateTabTexture = textureAtlas.findRegion("investigate_tab");
		glossariesTabTexture = textureAtlas.findRegion("glossary_tab");
		nextButtonTexture = textureAtlas.findRegion("next_button");
		nextButtonUnavailableTexture = textureAtlas.findRegion("next_button_unavailable");
		previousButtonTexture = textureAtlas.findRegion("previous_button");
		previousButtonUnavailableTexture = textureAtlas.findRegion("previous_button_unavailable");
		closeTabButtonTexture = textureAtlas.findRegion("close_tab_button");
		endTurnButtonTexture = textureAtlas.findRegion("end_turn_button");
		decreaseButtonTexture = textureAtlas.findRegion("decrease_button");
		increaseButtonTexture = textureAtlas.findRegion("increase_button");
		defaultButtonBlockTexture = textureAtlas.findRegion("button_block_default");
		selectButtonBlockTexture = textureAtlas.findRegion("button_block_select");
		defaultNormalBlockTexture = textureAtlas.findRegion("available_block");
		selectNormalBlockTexture = textureAtlas.findRegion("block_select");
		defaultHumanPlayerButtonTexture = textureAtlas.findRegion("human_player_button_default");
		unavailableHumanPlayerButtonTexture = textureAtlas.findRegion("human_player_button_unavailable");
		defaultComputerPlayerButtonTexture = textureAtlas.findRegion("computer_player_button_default");
		unavailableComputerPlayerButtonTexture = textureAtlas.findRegion("computer_player_button_unavailable");
		currentTurnPlayerMarkerTexture = textureAtlas.findRegion("current_turn_player_marker");
		defaultUseHintButtonTexture = textureAtlas.findRegion("use_hint_button_default"); ;
		unavailableUseHintButtonTexture = textureAtlas.findRegion("use_hint_button_unavailable");
		
		hintPointOneTexture = textureAtlas.findRegion("hint_point_one");
		hintPointTiltTexture = textureAtlas.findRegion("hint_point_tilt");
		
		decreaseLeftTexture = textureAtlas.findRegion("decrease_left");
		decreaseLeftUnavailableTexture = textureAtlas.findRegion("decrease_left_unavailable");
		decreaseDownTexture = textureAtlas.findRegion("decrease_down");
		decreaseDownUnavailableTexture = textureAtlas.findRegion("decrease_down_unavailable");
		increaseRightTexture = textureAtlas.findRegion("increase_right");
		increaseRightUnavailableTexture = textureAtlas.findRegion("increase_right_unavailable");
		increaseUpTexture = textureAtlas.findRegion("increase_up");
		increaseUpUnavailableTexture = textureAtlas.findRegion("increase_up_unavailable");
		
		soundSettingButtonTexture = textureAtlas.findRegion("sound_setting_button"); 
		defaultButtonTexture = textureAtlas.findRegion("default_button");
		startButtonTexture = textureAtlas.findRegion("start_button");
		startButtonUnavailableTexture = textureAtlas.findRegion("start_button_unavailable");
		helpButtonTexture = textureAtlas.findRegion("help_button"); 
		
		showPointTexture = textureAtlas.findRegion("show_point");
		existPointTexture = textureAtlas.findRegion("exist_point");
		timePointTexture = textureAtlas.findRegion("time_point");
		
		player1NoneTexture = textureAtlas.findRegion("player_1_none");
		player1HumanTexture = textureAtlas.findRegion("player_1_human");
		player1RobotTexture = textureAtlas.findRegion("player_1_robot");
		player2NoneTexture = textureAtlas.findRegion("player_2_none");
		player2HumanTexture = textureAtlas.findRegion("player_2_human");
		player2RobotTexture = textureAtlas.findRegion("player_2_robot");
		player3NoneTexture = textureAtlas.findRegion("player_3_none");
		player3HumanTexture = textureAtlas.findRegion("player_3_human");
		player3RobotTexture = textureAtlas.findRegion("player_3_robot");
		player4NoneTexture = textureAtlas.findRegion("player_4_none");
		player4HumanTexture = textureAtlas.findRegion("player_4_human");
		player4RobotTexture = textureAtlas.findRegion("player_4_robot");
		
		robotThinkATexture = textureAtlas.findRegion("robot_think_a");
		robotThinkBTexture = textureAtlas.findRegion("robot_think_b");
		
		helpSettingATexture = textureAtlas.findRegion("help_setting_a");
		helpSettingBTexture = textureAtlas.findRegion("help_setting_b");
		helpSettingCTexture = textureAtlas.findRegion("help_setting_c");
		
		leftButtonTexture = textureAtlas.findRegion("left_button");
		leftButtonUnavailableTexture = textureAtlas.findRegion("left_button_unavailable");
		rightButtonTexture = textureAtlas.findRegion("right_button");
		rightButtonUnavailableTexture = textureAtlas.findRegion("right_button_unavailable");
		exitButtonTexture = textureAtlas.findRegion("exit_button");
		
		human1IconTexture = textureAtlas.findRegion("human_1_icon");
		human2IconTexture = textureAtlas.findRegion("human_2_icon");
		human3IconTexture = textureAtlas.findRegion("human_3_icon");
		human4IconTexture = textureAtlas.findRegion("human_4_icon");
		robot1IconTexture = textureAtlas.findRegion("robot_1_icon");
		robot2IconTexture = textureAtlas.findRegion("robot_2_icon");
		robot3IconTexture = textureAtlas.findRegion("robot_3_icon");
		robot4IconTexture = textureAtlas.findRegion("robot_4_icon");
		
		player1LabelTexture = textureAtlas.findRegion("human1_label");
		player2LabelTexture = textureAtlas.findRegion("human2_label");
		player3LabelTexture = textureAtlas.findRegion("human3_label");
		player4LabelTexture = textureAtlas.findRegion("human4_label");
		robot1LabelTexture = textureAtlas.findRegion("com1_label");
		robot2LabelTexture = textureAtlas.findRegion("com2_label");
		robot3LabelTexture = textureAtlas.findRegion("com3_label");
		robot4LabelTexture = textureAtlas.findRegion("com4_label");
		
		winFirstIconTexture = textureAtlas.findRegion("win_first_icon");
		winSecondIconTexture = textureAtlas.findRegion("win_second_icon");
		winThirdIconTexture = textureAtlas.findRegion("win_third_icon");
		winFourthIconTexture = textureAtlas.findRegion("win_fourth_icon");
		
		soundSettingBackgroundTexture = textureAtlas.findRegion("sound_setting_background");
		
		soundSliderBaseTexture = textureAtlas.findRegion("sound_slider_base");
		soundSliderTexture = assetManager.get("sound_slider.png");
		soundSliderTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		soundKnobTexture = textureAtlas.findRegion("sound_knob");
		
		gradeBaseTexture  = textureAtlas.findRegion("grade_base");
		gradeTexture  = assetManager.get("grade.png");
		gradeTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		sandglassBaseTexture = textureAtlas.findRegion("sandglass_base");
		sandglassFrontTexture = assetManager.get("sandglass_front.png");
		sandglassFrontTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		glossariesTabBackgroundTexture = textureAtlas.findRegion("glossaries_tab_background");
		glossariesTabForegroundTexture  = textureAtlas.findRegion("glossaries_tab_foreground");
		glossariesTabPaperTexture  = textureAtlas.findRegion("glossaries_tab_paper");
		
		characterTabTexture = textureAtlas.findRegion("character_tab");
		syllableTabTexture = textureAtlas.findRegion("syllable_tab");
		typeTabTexture = textureAtlas.findRegion("type_tab");
		
		letterButtonTexture = textureAtlas.findRegion("letter_button");
		letterButtonUnavailableTexture = textureAtlas.findRegion("letter_button_unavailable");
		letterButtonSelectTexture = textureAtlas.findRegion("letter_button_select");
		
		computer1BackgroundTexture = textureAtlas.findRegion("computer_1_background");
		computer2BackgroundTexture = textureAtlas.findRegion("computer_2_background");
		computer3BackgroundTexture = textureAtlas.findRegion("computer_3_background");
		computer4BackgroundTexture = textureAtlas.findRegion("computer_4_background");
		
		blankTexture = textureAtlas.findRegion("blank_texture");
		
		insideWordMarkTexture = textureAtlas.findRegion("inside_word_mark");
		outsideWordMarkTexture = textureAtlas.findRegion("outside_word_mark");
		
		wrongGuesslightBlankTexture = textureAtlas.findRegion("wrong_guess_light_blank");
		wrongGuesslightRedTexture = textureAtlas.findRegion("wrong_guess_light_red");
		
		backMenuTexture = textureAtlas.findRegion("back_menu");
		yesButtonTexture = textureAtlas.findRegion("yes_button");
		noButtonTexture = textureAtlas.findRegion("no_button");

		
		cluePaperTexture = textureAtlas.findRegion("clue_paper");
		
		guessEffectAnimationTexture = assetManager.get("guess_effect_animation.png");
		guessEffectAnimationTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		hintBoxTexture = textureAtlas.findRegion("hint_box");
		hintBoxOKButtonTexture = textureAtlas.findRegion("hintbox_ok_button");;
		hintArrowUpTexture = textureAtlas.findRegion("hint_arrow_up");
		hintArrowDownTexture = textureAtlas.findRegion("hint_arrow_down");
		hintArrowLeftTexture = textureAtlas.findRegion("hint_arrow_left");
		hintArrowRightTexture = textureAtlas.findRegion("hint_arrow_right");
		hintFingerUpTexture = textureAtlas.findRegion("hint_finger_up");
		hintFingerDownTexture = textureAtlas.findRegion("hint_finger_down");
		hintFingerLeftTexture = textureAtlas.findRegion("hint_finger_left");
		hintFingerRightTexture = textureAtlas.findRegion("hint_finger_right");
		
		buttonBackground1Texture = textureAtlas.findRegion("button_back1");
		buttonBackground2Texture = textureAtlas.findRegion("button_back2");
		buttonBackground3Texture = textureAtlas.findRegion("button_back3");
		
		borderHorTexture = textureAtlas.findRegion("border_hor");
		borderVerTexture = textureAtlas.findRegion("border_ver");
		
		guessLetterTabTexture = textureAtlas.findRegion("guess_letter_tab");
		smallKeyboardTabTexture = textureAtlas.findRegion("small_keyboard_tab");
		
		statusButtonTexture = textureAtlas.findRegion("status_button");
		
		warningLabelTexture = textureAtlas.findRegion("warning_label");
		
		timesUpLabelTexture = textureAtlas.findRegion("times_up_label");
		completeLabelTexture = textureAtlas.findRegion("complete_label");
		
		magnifierTexture = textureAtlas.findRegion("magnifier");
		titleTexture = textureAtlas.findRegion("title");
		
		hintDefaultButtonTexture = textureAtlas.findRegion("hint_default_button");
		hintButtonUnavailableTexture = textureAtlas.findRegion("hint_button_unavailable");
		hintButtonSelectTexture = textureAtlas.findRegion("hint_button_select");
		
		defaultUseButtonTexture = textureAtlas.findRegion("use_button_default");
		useButtonUnavailableTexture = textureAtlas.findRegion("use_button_unavailable");
		
		wordSettingTabTexture = textureAtlas.findRegion("word_setting_tab");
		showSettingTabTexture = textureAtlas.findRegion("show_setting_tab");
		existSettingTabTexture = textureAtlas.findRegion("exist_setting_tab");
		timeSettingTabTexture = textureAtlas.findRegion("time_setting_tab");
		guessSettingTabTexture = textureAtlas.findRegion("guess_setting_tab");
		hintSettingTabTexture = textureAtlas.findRegion("hint_setting_tab");
		
		playerSettingTabTexture = textureAtlas.findRegion("player_setting_tab"); 
		
		aboutSceneTexture = textureAtlas.findRegion("about_scene"); 

		charlemagne14Font = new BitmapFont(Gdx.files.internal("font/charlemagne14font.fnt"), 
				textureAtlas.findRegion("charlemagne14font"));
		charlemagne16Font = new BitmapFont(Gdx.files.internal("font/charlemagne16font.fnt"), 
				textureAtlas.findRegion("charlemagne16font"));
		charlemagne18Font = new BitmapFont(Gdx.files.internal("font/charlemagne18font.fnt"), 
				textureAtlas.findRegion("charlemagne18font"));
		charlemagne20Font = new BitmapFont(Gdx.files.internal("font/charlemagne20font.fnt"), 
				textureAtlas.findRegion("charlemagne20font"));
		charlemagne24Font = new BitmapFont(Gdx.files.internal("font/charlemagne24font.fnt"), 
				textureAtlas.findRegion("charlemagne24font"));
		charlemagne32Font = new BitmapFont(Gdx.files.internal("font/charlemagne32font.fnt"), 
				textureAtlas.findRegion("charlemagne32font"));
		calibri18Font = new BitmapFont(Gdx.files.internal("font/calibri18font.fnt"), 
				textureAtlas.findRegion("calibri18font"));
		calibri20Font = new BitmapFont(Gdx.files.internal("font/calibri20font.fnt"), 
				textureAtlas.findRegion("calibri20font"));
		
		
		backgroundMusic = assetManager.get("sounds/Spy Glass.ogg");
		playMusic = assetManager.get("sounds/Mining by Moonlight.ogg");
		buttonSound = assetManager.get("sounds/click.ogg");
		flipPaperSound = assetManager.get("sounds/flip_paper.ogg");
		completeSound = assetManager.get("sounds/complete.ogg");
		bellSound = assetManager.get("sounds/bell.ogg");
		robotSound = assetManager.get("sounds/robot.ogg");
		touchSound = assetManager.get("sounds/beep.ogg");
		magicSound = assetManager.get("sounds/magic.ogg");
		hintSound = assetManager.get("sounds/hint.ogg");
		wrongSound = assetManager.get("sounds/wrong.ogg");
		moveSound = assetManager.get("sounds/move.ogg");
		
	}
	
	public static void dispose(){
		//System.out.println("dispose assets");
		
		assetManager.clear();
		
		charlemagne14Font.dispose();
		charlemagne16Font.dispose();
		charlemagne18Font.dispose();
		charlemagne20Font.dispose();
		charlemagne24Font.dispose();
		charlemagne32Font.dispose();
		calibri18Font.dispose();
		calibri20Font.dispose();
	}
	
	public static AssetManager getAssetManager(){
		return assetManager;
		
	}
}
