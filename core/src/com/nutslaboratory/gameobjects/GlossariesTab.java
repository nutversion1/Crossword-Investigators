package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutButton;
import com.nutslaboratory.nutlibgdxgameengine.NutGrade;
import com.nutslaboratory.nutlibgdxgameengine.NutGrade.Style;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;
import com.nutslaboratory.nutlibgdxgameengine.NutSprite;
import com.nutslaboratory.nutlibgdxgameengine.NutText;

public class GlossariesTab extends NutSprite{
	private static final int TOTAL_GLOSSARIES_TIME = 30;
	private static final int WORDS_PER_PAGE = 10;
	
	private GameManager gameManager;
	
	private NutButton closeButton;
	private NutButton nextButton, previousButton;
	
	private int timeLeft;
	
	private NutText timeLeftText;
	
	private Array<Word> words;
	
	private Array<NutText> texts = new Array<NutText>();
	
	private NutText pageLabel;
	private int totalPages;
	private int currentPageNum;
	
	private NutGrade timeLeftGrade;
	
	private NutSprite foreground;
	
	private GlossariesPage oldGlossariesPage, currentGlossariesPage;
	
	public GlossariesTab(NutScreen screen, GameManager gameManager) {
		super(screen, AssetLoader.glossariesTabBackgroundTexture);
		
		this.gameManager = gameManager;
		
		timeLeft = TOTAL_GLOSSARIES_TIME;
		words = gameManager.getCrosswords().getShowGlossariesWords();
		totalPages = (gameManager.getTotalGlossaries() / WORDS_PER_PAGE) + 1;
		currentPageNum = 1;
		
		//
		currentGlossariesPage = createFirstGlossariesPage(0);
		oldGlossariesPage = currentGlossariesPage;
		
		createForeground();
		createTimeLeftGrade();
		createCloseButton();
		createPreviousButton();
		createNextButton();
		createPageLabel();
		
		/*
		//
		for(Word tempWord : words){
			Gdx.app.log("", tempWord.getName());
		}*/
	}
	
	private void createPageLabel() {
		pageLabel = new NutText(getScreen(), AssetLoader.charlemagne32Font, currentPageNum+"/"+totalPages);
		pageLabel.setPosition(getWidth()/2 - pageLabel.getWidth()/2, 30);
		pageLabel.setColor(Color.WHITE);
		addChild(pageLabel);
		
	}
	
	private GlossariesPage createFirstGlossariesPage(int xPosition) {
		GlossariesPage newGlossariesPage = new FirstGlossariesPage(getScreen(), AssetLoader.cluePaperTexture, this);
		newGlossariesPage.setX(xPosition);
		addChild(newGlossariesPage);
		movePageToTop(newGlossariesPage);

		return newGlossariesPage;

		
	}

	private GlossariesPage createOtherGlossariesPage(int start, int xPosition) {
		Array<Word> tempWords = new Array<Word>();
		tempWords.addAll(words, start, WORDS_PER_PAGE);
		
		GlossariesPage newGlossariesPage = new OtherGlossariesPage(getScreen(), AssetLoader.glossariesTabPaperTexture, this, tempWords);
		newGlossariesPage.setX(xPosition);
		addChild(newGlossariesPage);
		movePageToTop(newGlossariesPage);

		return newGlossariesPage;
		
		/*
		System.out.println(glossariesPages.size);
		System.out.println(words.size);
		for(Word tempWord : words){
			System.out.println(tempWord.getName());
		}
		*/
		
	}

	private void createForeground() {
		foreground = new NutSprite(getScreen(), AssetLoader.glossariesTabForegroundTexture);
		addChild(foreground);
		
	}

	private void createTimeLeftGrade() {
		timeLeftGrade = new NutGrade(getScreen(), AssetLoader.gradeBaseTexture, AssetLoader.gradeTexture,
				Style.TOP_TO_BOTTOM);
		timeLeftGrade.setPosition(440, 100);
		addChild(timeLeftGrade);
		
	}
	
	private void createNextButton() {
		nextButton = new NutButton(getScreen(), AssetLoader.nextButtonTexture,
				AssetLoader.nextButtonUnavailableTexture, AssetLoader.nextButtonTexture);
		nextButton.setTouchSound(AssetLoader.buttonSound);
		nextButton.setPosition(385, 20);
		addChild(nextButton);
		
		
	}

	private void createPreviousButton() {
		previousButton = new NutButton(getScreen(), AssetLoader.previousButtonTexture, 
				AssetLoader.previousButtonUnavailableTexture, AssetLoader.previousButtonTexture);
		previousButton.setTouchSound(AssetLoader.buttonSound);
		previousButton.setPosition(30, 20);
		previousButton.unavailable();
		addChild(previousButton);
		
		
	}

	
	
	private void createCloseButton() {
		closeButton = new NutButton(getScreen(), AssetLoader.closeTabButtonTexture, null,  AssetLoader.closeTabButtonTexture);
		closeButton.setTouchSound(AssetLoader.buttonSound);
		closeButton.setPosition(getWidth()-60, 405);
		addChild(closeButton);
		
		
	}

	public NutButton getCloseButton(){
		return closeButton;
	}
	
	public NutButton getNextButton(){
		return nextButton;
	}
	
	public NutButton getPreviousButton(){
		return previousButton;
	}
	
	public void previousPage(){
		if(currentPageNum > 1){
			
			//
			currentPageNum--;
			pageLabel.setText(currentPageNum+"/"+totalPages);
			pageLabel.setX(getWidth()/2 - pageLabel.getWidth()/2);
		
			//
			oldGlossariesPage = currentGlossariesPage;
			
			if(currentPageNum == 1){
				currentGlossariesPage = createFirstGlossariesPage(0);
			}else{
				currentGlossariesPage = createOtherGlossariesPage((currentPageNum-1)*WORDS_PER_PAGE-WORDS_PER_PAGE, 0);
			}
			
			movePageToTop(oldGlossariesPage);
			oldGlossariesPage.moveRight();
			
		}
			
		
	}
	
	public void nextPage(){
		
		if(currentPageNum < totalPages){
			
			//
			currentPageNum++;
			pageLabel.setText(currentPageNum+"/"+totalPages);
			pageLabel.setX(getWidth()/2 - pageLabel.getWidth()/2);
			
			//
			oldGlossariesPage = currentGlossariesPage;
			if(currentPageNum == 1){
				currentGlossariesPage = createFirstGlossariesPage(480);
			}else{
				currentGlossariesPage = createOtherGlossariesPage((currentPageNum-1)*WORDS_PER_PAGE-WORDS_PER_PAGE, 480);	
			}
			currentGlossariesPage.moveLeft();
			
			
			
		}
		
		
	}
	
	public void decreaseTime(int total){
		timeLeft -= total;
		
		//update time left grade
		float t = 100f/TOTAL_GLOSSARIES_TIME;
		timeLeftGrade.setPercentSize(timeLeftGrade.getPercentSize()-t);
	}
	
	public int getTimeLeft(){
		return timeLeft;
	}
	
	public Array<NutText> getTexts(){
		return texts;
	}
	
	@Override
	public void update(float delta){
		super.update(delta);
		
		//System.out.println(Gdx.graphics.getFramesPerSecond());
		
		/*
		System.out.print(getOldGlossariesPage());
		System.out.print(" , ");
		System.out.print(getCurrentGlossariesPage());
		System.out.println();
		*/
		
		
		//
		if(currentPageNum == 1){
			previousButton.unavailable();
		}else{
			previousButton.unselect();
		}
		
		if(currentPageNum == totalPages){
			nextButton.unavailable();
		}else{
			nextButton.unselect();
		}
		
		
		//
		if(!currentGlossariesPage.isAvailable() || !oldGlossariesPage.isAvailable()){
			previousButton.setEnable(false);
			nextButton.setEnable(false);
		}else{
			previousButton.setEnable(true);
			nextButton.setEnable(true);
		}
	}
	
	public void movePageToTop(GlossariesPage glossariesPage){
		moveChildToTop(glossariesPage);
		
		moveChildToTop(foreground);
		moveChildToTop(timeLeftGrade);
		moveChildToTop(closeButton);
		moveChildToTop(previousButton);
		moveChildToTop(nextButton);
		moveChildToTop(pageLabel);
	}
	
	public GlossariesPage getOldGlossariesPage(){
		return oldGlossariesPage;
	}
	
	public void setOldGlossariesPage(GlossariesPage glossariesPage){
		oldGlossariesPage = glossariesPage;
	}
	
	public GlossariesPage getCurrentGlossariesPage(){
		return currentGlossariesPage;
	}
	
	public void setCurrentGlossariesPage(GlossariesPage glossariesPage){
		currentGlossariesPage = glossariesPage;
	}
	
	

}
