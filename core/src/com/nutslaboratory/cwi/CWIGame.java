//CWI: CrossWords Investigator by nutvers1.0n started project at 28/11/2015

package com.nutslaboratory.cwi;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.helpers.IActivityRequestHandler;
import com.nutslaboratory.nutlibgdxgameengine.NutGame;
import com.nutslaboratory.screens.LoadingScreen;

public class CWIGame extends NutGame {
	
	public static final int WORLD_WIDTH = 480;
	public static final int WORLD_HEIGHT = 640;
	
	private static final float MUSIC_VOLUME_DEFAULT = 1f;
	private static final float SOUND_VOLUME_DEFAULT = 1f;
	
	private static Preferences gamePreferences;
	private static Preferences soundPreferences;
	private static Preferences playPreferences;
	
	private boolean hasPassedTutorial;
	
	
	
	public CWIGame(IActivityRequestHandler IARH) {
		super(WORLD_WIDTH, WORLD_HEIGHT, IARH);
		
	}
	
	@Override
	public void create() {
		super.create();
		
		//set log level
		Gdx.app.setLogLevel(Application.LOG_NONE);
		
		//setShowShapeRenderer(true);
		
		//create preferences 
		gamePreferences = Gdx.app.getPreferences("gameSettings");
		soundPreferences = Gdx.app.getPreferences("soundSettings");
		playPreferences = Gdx.app.getPreferences("playSettings");
		
		
		//check version  (android only)
		if(isAndroid){
			int lastVersionCode = gamePreferences.getInteger("lastVersionCode", 1);
			
			Gdx.app.debug("test", ("last version: "+ lastVersionCode));
			Gdx.app.debug("test", ("current version: "+ versionCode));
			
			//user version is not last version
			if(versionCode > lastVersionCode){
				//code when update to next version
			}
			
			gamePreferences.putInteger("lastVersionCode", versionCode);
			gamePreferences.flush();
		}
		
		//set sound & music
		NutGame.setMusicVolume(soundPreferences.getFloat("musicVolume", MUSIC_VOLUME_DEFAULT)); 
		NutGame.setSoundVolume(soundPreferences.getFloat("soundVolume", SOUND_VOLUME_DEFAULT));
		
		//go to loading screen
		setScreen(new LoadingScreen(this));
		
	}
	
	@Override
	public void dispose(){
		//System.out.println("dispose");
		
		AssetLoader.dispose();
	}
	
	public static void saveSoundSettings(){
		soundPreferences.putFloat("musicVolume", NutGame.getMusicVolume());
		soundPreferences.putFloat("soundVolume", NutGame.getSoundVolume());
		
		soundPreferences.flush();
		
	}
	
	public void passTutorial(){
		gamePreferences.putBoolean("hasPassedTutorial", true);
	
		gamePreferences.flush();
	}
	
	public boolean hasPassTutorial(){
		hasPassedTutorial = gamePreferences.getBoolean("hasPassedTutorial", false);
		
		return hasPassedTutorial;
	}
	
	public static Preferences getPlayPreferences(){
		return playPreferences;
	}

}
