package com.nutslaboratory.nutlibgdxgameengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class NutScreen extends InputAdapter implements Screen {
	
	private int worldWidth;
	private int worldHeight;
	
	private Viewport viewport;
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;
	private ShapeRenderer shapeRenderer;
	
	private Color backgroundColor = Color.BLACK;
	
	private Array<NutSpriteLayer> spriteLayers = new Array<NutSpriteLayer>();
	
	private int touchX, touchY;
	
	private NutGame game;
	
	private BitmapFont bitmapFont = new BitmapFont();
	
	private Array<NutButton> buttons = new Array<NutButton>(); 

	
	public NutScreen(NutGame game){	
		this.game = game;
		
		this.worldWidth= game.getWorldWidth();
		this.worldHeight = game.getWorldHeight();
		
		camera = new OrthographicCamera();
		camera.position.set(worldWidth/2, worldHeight/2, 0);
		camera.update();
		viewport = new FitViewport(worldWidth, worldHeight, camera);
		
		spriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		
		
		//
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
		
		//
		setBackgroundColor(new Color(38/255f,0/255f,0/255f,1f));
	}

	@Override
	public void show() {
		//Gdx.app.log("NutScreen", "show");
		
		
		
	}

	@Override
	public void render(float delta) {
		//reset total draw calls
		//spriteBatch.totalRenderCalls = 0;

		
		
		//Gdx.app.log("NutScreen", "render");
		
		//clear
		clearScreen();
		
		//update
		for(NutSpriteLayer tempSpriteLayer : spriteLayers){
			tempSpriteLayer.updateSprites(delta);
		}
		
		update(delta);
		
		//draw
		for(NutSpriteLayer tempSpriteLayer : spriteLayers){
			if(tempSpriteLayer.isOverlay()){
				tempSpriteLayer.drawOverlay();
			}
			
			tempSpriteLayer.drawSprites(spriteBatch);
		}
		//
		spriteBatch.setProjectionMatrix(camera.projection);
		spriteBatch.setTransformMatrix(camera.view);
		spriteBatch.begin();
		
		draw(spriteBatch);
		
		//show fps
		//bitmapFont.draw(spriteBatch, ""+Gdx.graphics.getFramesPerSecond(), 10, 15);
		
		spriteBatch.end();
		
		//draw debug
		if(getGame().getShowShapeRenderer()){
			for(NutSpriteLayer tempSpriteLayer : spriteLayers){
				if(tempSpriteLayer.isOverlay()){
					tempSpriteLayer.drawOverlay();
				}
				
				tempSpriteLayer.drawDebug();
			}
		}
		
		//
		Vector2 touchVector2 = new Vector2();
		getViewport().unproject(touchVector2.set(Gdx.input.getX(), Gdx.input.getY()));
	    touchX = (int)touchVector2.x;
	    touchY = (int)touchVector2.y;
	    
	    //show touch coordinates
	    //System.out.println(touchX +", "+touchY);
		
	    
	    //show total draw calls
	 	//int calls = spriteBatch.totalRenderCalls;
	 	//System.out.println(calls);
	}

	
	@Override
	public void resize(int width, int height) {
		//Gdx.app.log("NutScreen", "resize");
		
		viewport.update(width, height);
		
	}

	@Override
	public void pause() {
		//Gdx.app.log("NutScreen", "pause");
		
	}

	@Override
	public void resume() {
		//Gdx.app.log("NutScreen", "resume");
		
	}

	@Override
	public void hide() {
		//Gdx.app.log("NutScreen", "hide");
		
	}

	@Override
	public void dispose() {
		//Gdx.app.log("NutScreen", "dispose");
		
	}
	
	private void clearScreen() {
		Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
	}
	
	public void update(float delta) {
		//Gdx.app.log("", buttons.size+"");
	}
	
	public void draw(SpriteBatch spriteBatch) {
		
	}
	
	public void drawDebug(ShapeRenderer shapeRenderer) {
	
	}
	
	public void setBackgroundColor(Color color){
		backgroundColor = color;
	}
	
	public int getWorldWidth(){
		return worldWidth;
	}
	
	public int getWorldHeight(){
		return worldHeight;
	}
	
	public Viewport getViewport(){
		return viewport;
	}
	
	public OrthographicCamera getCamera(){
		return camera;
	}
	
	public SpriteBatch getSpriteBatch(){
		return spriteBatch;
	}
	
	public ShapeRenderer getShaperRenderer(){
		return shapeRenderer;
	}
	
	public int getTouchX(){
		return touchX;
	}
	
	public int getTouchY(){
		return touchY;
	}
	
	public NutGame getGame(){
		return game;
	}
	
	public void addSpriteLayer(NutSpriteLayer spriteLayer){
		spriteLayers.add(spriteLayer);
	}
	
	public Array<NutSpriteLayer> getSpriteLayers(){
		return spriteLayers;
	}
	
	//
	private void clearButtons(){
		 buttons.clear();
	}
   
	
	//incomplete
	private void getButtons(){
		
		
		//
		for(NutSpriteLayer tempSpriteLayer : spriteLayers){
			for(NutSprite tempSprite : tempSpriteLayer.getSprites()){
				
				
				if(NutButton.class.isAssignableFrom(tempSprite.getClass())){
					buttons.add((NutButton)tempSprite);
				}
				
				for(NutSprite tempChild : tempSprite.getChildren()){
					if(NutButton.class.isAssignableFrom(tempChild.getClass())){
						buttons.add((NutButton)tempChild);
					}
					
					for(NutSprite tempChild2 : tempChild.getChildren()){
						if(NutButton.class.isAssignableFrom(tempChild2.getClass())){
							buttons.add((NutButton)tempChild2);
						}
					}
				}
			}
		}
		
		
		
	}

	
	//input processor
	
	public void doEvents(){
		
	}
	
	public void pressBack() {
		//System.out.println("back");
		
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.BACK || keycode == Keys.SPACE){
			pressBack();
			
			return true;
		}

		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//prevent multitouch
		if(pointer > 0){
			return false;
		}
		
		
		//
		Vector2 touchVector2 = new Vector2();
		getViewport().unproject(touchVector2.set(screenX, screenY));
	    touchX = (int)touchVector2.x;
	    touchY = (int)touchVector2.y;
	    
	    
	    //
	    getButtons();
	    
	    //
	    for(NutButton tempButton : buttons){
	    	if(tempButton.isCollidePoint(touchX, touchY)){
	    		if(tempButton.checkLayer()){
	    			if(tempButton.isEnabled()){
		    			if(tempButton.getState().equals(NutButton.DEFAULT_STATE)){
		    				tempButton.pressDown();
		    			}
	    			}
	    		}
			}
			
	    }
	    
	    //
	    clearButtons();
	    
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector2 touchVector2 = new Vector2();
		getViewport().unproject(touchVector2.set(screenX, screenY));
	    touchX = (int)touchVector2.x;
	    touchY = (int)touchVector2.y;
	    
	    //
	    getButtons();
	    
	    //
	    for(NutButton tempButton : buttons){
	    	if(tempButton.isPressing()){
				tempButton.pressUp();
				tempButton.click();
			}
	    }
	    
	    //
	    doEvents();
	    
	    //
	    for(NutButton tempButton : buttons){
	    	tempButton.unclick();
	    }
	    
	    //
	    clearButtons();
	    
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector2 touchVector2 = new Vector2();
		getViewport().unproject(touchVector2.set(screenX, screenY));
	    touchX = (int)touchVector2.x;
	    touchY = (int)touchVector2.y;
	    
		return false;
	}

	
	
	
	
	
}
