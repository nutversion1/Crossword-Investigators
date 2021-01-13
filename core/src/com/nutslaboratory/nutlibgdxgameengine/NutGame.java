package com.nutslaboratory.nutlibgdxgameengine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.nutslaboratory.helpers.IActivityRequestHandler;

public abstract class NutGame extends Game{

	private int worldWidth;
	public int worldHeight;
	
	private static float musicVolume = 1f;
	private static float soundVolume = 1f;
	
	private boolean showShapeRenderer;
	
	//
	public boolean isAndroid;
	
	public IActivityRequestHandler handler;
	
	public int versionCode = 0;
	public String versionName = "none";
	
	public NutGame(int worldWidth, int worldHeight, IActivityRequestHandler IARH){
		this.worldWidth = worldWidth;
		this.worldHeight = worldHeight;
		
		handler = IARH;
		
		
	}
	
	@Override
	public void create(){
		
		//Interfacing-with-platform-specific-code
		switch(Gdx.app.getType()){
		case Android:
			isAndroid = true;
			break;
		case Applet:
			break;
		case Desktop:
			break;
		case HeadlessDesktop:
			break;
		case WebGL:
			break;
		case iOS:
			break;
		default:
			break;
		}
		
		//only Android
		if(isAndroid){
			versionCode = handler.getVersionCode();
			versionName = handler.getVersionName();
		}
	}
	
	public int getWorldWidth(){
		return worldWidth;
	}
	
	public int getWorldHeight(){
		return worldHeight;
	}
	
	public static void setMusicVolume(float volume){
		musicVolume = volume;
	}
	
	public static float getMusicVolume(){
		return musicVolume;
	}
	
	public static void setSoundVolume(float volume){
		soundVolume = volume;
	}
	
	public static float getSoundVolume(){
		return soundVolume;
	}
	
	public void setShowShapeRenderer(boolean b){
		showShapeRenderer = b;
	}
	
	public boolean getShowShapeRenderer(){
		return showShapeRenderer;
	}
	
	@Override
	public void setScreen(Screen screen){
		super.setScreen(screen);
		
		//tracking screen (google analytics)
		if(isAndroid){
			handler.setTrackerScreenName(screen.getClass().getName());
		}
	}
}
