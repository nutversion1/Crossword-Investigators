package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;

public class PlayerSettingButton extends NutSprite{
	
	
	
	private String playerType;
	private int playerNum;
	
	private TextureRegion noneTexture, humanTexture, robotTexture;
	
	public PlayerSettingButton(NutScreen screen, TextureRegion noneTexture, TextureRegion humanTexture, TextureRegion robotTexture, int playerNum) {
		super(screen, noneTexture);
		
		this.noneTexture = noneTexture;
		this.humanTexture = humanTexture;
		this.robotTexture = robotTexture;
		
		this.playerNum = playerNum;
		
		setType(Player.NONE_PLAYER_TYPE);
	}
	
	public void setType(String playerType){
		this.playerType = playerType;
		
		if(playerType.equals(Player.NONE_PLAYER_TYPE)){
			setTexture(noneTexture);
		}else if(playerType.equals(Player.HUMAN_PLAYER_TYPE)){
			setTexture(humanTexture);
		}else if(playerType.equals(Player.COMPUTER_PLAYER_TYPE)){
			setTexture(robotTexture);
		}
		
		//System.out.println(playerType);
		
	}
	
	

	public void nextType(){
		if(playerType.equals(Player.NONE_PLAYER_TYPE)){
			setType(Player.HUMAN_PLAYER_TYPE);
		}else if(playerType.equals(Player.HUMAN_PLAYER_TYPE)){
			setType(Player.COMPUTER_PLAYER_TYPE);
		}else if(playerType.equals(Player.COMPUTER_PLAYER_TYPE)){
			setType(Player.NONE_PLAYER_TYPE);
		}
	}
	
	public String getPlayerType(){
		return playerType;
	}
	
	public int getPlayerNum(){
		return playerNum;
	}

}
