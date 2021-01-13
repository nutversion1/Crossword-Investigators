package com.nutslaboratory.gameobjects;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.helpers.SpriteAccessor;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;

public class HintIndicator extends NutSprite{

	public final static int ARROW_TYPE = 0;
	public final static int FINGER_TYPE = 1;
	
	public final static int UP_DIR = 0;
	public final static int DOWN_DIR = 1;
	public final static int LEFT_DIR = 2;
	public final static int RIGHT_DIR = 3;
	
	private int type;
	private int dir;
	private boolean canMove;
	
	private float oldX;
	private float oldY;
	
	private TweenManager tweenManager;
	private TweenCallback moveInCallBack, moveOutCallBack;
	
	public HintIndicator(NutScreen screen, int type, int dir, boolean canMove, float x, float y) {
		super(screen, AssetLoader.blankTexture);
		
		this.type = type;
		this.dir = dir;
		this.canMove = canMove;
		
		if(type == ARROW_TYPE){
			if(dir == UP_DIR){
				setTexture(AssetLoader.hintArrowUpTexture);
			}else if(dir == DOWN_DIR){
				setTexture(AssetLoader.hintArrowDownTexture);
			}else if(dir == LEFT_DIR){
				setTexture(AssetLoader.hintArrowLeftTexture);
			}else if(dir == RIGHT_DIR){
				setTexture(AssetLoader.hintArrowRightTexture);
			}
			
		}else{
			if(dir == UP_DIR){
				setTexture(AssetLoader.hintFingerUpTexture);
			}else if(dir == DOWN_DIR){
				setTexture(AssetLoader.hintFingerDownTexture);
			}else if(dir == LEFT_DIR){
				setTexture(AssetLoader.hintFingerLeftTexture);
			}else if(dir == RIGHT_DIR){
				setTexture(AssetLoader.hintFingerRightTexture);
			}
		}
		
		setPosition(x, y);
		
		if(canMove){
			oldX = x;
			oldY = y;
			setupTween();
			moveIn();
		}
	}
	
	private void setupTween() {
		Tween.registerAccessor(NutSprite.class, new SpriteAccessor());
		tweenManager = new TweenManager();
		
		
		moveInCallBack = new TweenCallback(){
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				//System.out.println("move in complete");
				
				moveOut();
			}
		};
		
		moveOutCallBack = new TweenCallback(){
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				//System.out.println("move out complete");
				
				moveIn();
			}
		};
		
	}
	
	public void moveIn(){
		int moveType = 0;
		float targetPos = 0;
		
		if(dir == UP_DIR){
			moveType = SpriteAccessor.POSITION_Y;
			targetPos = oldY + 10f;
		}else if(dir == DOWN_DIR){
			moveType = SpriteAccessor.POSITION_Y;
			targetPos = oldY - 10f;
		}else if(dir == LEFT_DIR){
			moveType = SpriteAccessor.POSITION_X;
			targetPos = oldX - 10f;
		}else if(dir == RIGHT_DIR){
			moveType = SpriteAccessor.POSITION_X;
			targetPos = oldX + 10f;
		}
		
		
		Tween.to(this, moveType, 0.5f)
		.target(targetPos)
		.setCallback(moveInCallBack).setCallbackTriggers(TweenCallback.COMPLETE)
		.start(tweenManager);
	}
	
	public void moveOut(){
		int moveType = 0;
		float targetPos = 0;
		
		if(dir == UP_DIR){
			moveType = SpriteAccessor.POSITION_Y;
			targetPos = oldY;
		}else if(dir == DOWN_DIR){
			moveType = SpriteAccessor.POSITION_Y;
			targetPos = oldY;
		}else if(dir == LEFT_DIR){
			moveType = SpriteAccessor.POSITION_X;
			targetPos = oldX;
		}else if(dir == RIGHT_DIR){
			moveType = SpriteAccessor.POSITION_X;
			targetPos = oldX;
		}
		
		Tween.to(this, moveType, 0.5f)
		.target(targetPos)
		.setCallback(moveOutCallBack).setCallbackTriggers(TweenCallback.COMPLETE)
		.start(tweenManager);
	}
	
	@Override
	public void update(float delta){
		super.update(delta);
		
		if(canMove){
			tweenManager.update(delta);
		}
	}

}
