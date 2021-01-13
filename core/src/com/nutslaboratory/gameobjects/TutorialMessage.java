package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.nutslaboratory.helpers.AssetLoader;
import com.nutslaboratory.nutlibgdxgameengine.NutScreen;



public class TutorialMessage {
	
	private int currentHintStep = 1;
	
	
	
	
	public void TutorialMesssage(){
		
	}
	
	public void nextHintStep(){
		currentHintStep++;
		
		//Gdx.app.log("", "current hint step : "+currentHintStep);
	}
	
	public int getCurrentHintStep(){
		return currentHintStep;
	}
	
	
	public HintBox getNewHintBox(NutScreen screen) {
		//
		HintBox hintBox = null;
		
		switch(currentHintStep){
		case 1:
			hintBox = createNewHintBox(screen, 
					"Welcome to our tutorial. This is a word guessing game." + 
					" You can play this with your friends or the computer. " + 
					"This game can have 2 to 4 players. " + 
					"Who can guess all the words is the winners.",
					20, 200, true, HintBox.BIG_SIZE);
	
			break;
			
		case 2:
			hintBox = createNewHintBox(screen,
					"If there's only you and the computer." +
					"You have a chance to play first." +
					" Touch the \"start\" button to play.",
					80, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 230, 140);
			break;
			
		case 3:
			hintBox = createNewHintBox(screen,
					"In the first round, everybody has one sets of clues. " +
					"The clues will tell all the words possible." +
					" There will be the right answers among words in the clues.", 
					 20, 380, true, HintBox.MEDIUM_SIZE);
			break;
		case 4:
			hintBox = createNewHintBox(screen, 
					"Touch on \"next\" button for the next page.", 
					60, 100, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 400, 80);
			break;
		case 5:
			hintBox = createNewHintBox(screen, 
					"Touch on \"next\" button again for another next page.", 
					60, 100, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 400, 80);
			break;
		case 6:
			hintBox = createNewHintBox(screen, 
					"Touch on \"previous\" button to turn to the previous page.",
					100, 100, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 40, 80);
			break;
		case 7:
			hintBox = createNewHintBox(screen, 
					"When you start playing, there will be time limit for you to look at the clues.",
					70, 120, true, HintBox.SMALL_SIZE);
			hintBox.createHintArrow(HintIndicator.RIGHT_DIR, true, 360, 300);
			break;
		case 8:
			hintBox = createNewHintBox(screen,
					"When you finish looking at the clues, touch on" +
					" \"close\" button to enter the main scene.", 
					70, 120, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 430, 450);
			break;
		case 9:
			hintBox = createNewHintBox(screen, 
					"When you start playing, there will be a time limit for each turn.", 
					50, 30, true, HintBox.SMALL_SIZE);
			hintBox.createHintArrow(HintIndicator.UP_DIR, true, 10, 470);
			break;
		case 10:
			hintBox = createNewHintBox(screen,
					"This is the number of hints you can use for each turn.", 
					50, 30, true, HintBox.SMALL_SIZE);
			hintBox.createHintArrow(HintIndicator.UP_DIR, true, 90, 470);
			break;
		case 11:
			hintBox = createNewHintBox(screen, 
					"This is the number of incorrect guess you can make for each turn.",
					50, 30, true, HintBox.SMALL_SIZE);
			hintBox.createHintArrow(HintIndicator.UP_DIR, true, 200, 470);
			break;
		case 12:
			hintBox = createNewHintBox(screen, 
					"This is the number of words that you guess correctly.", 
					50, 30, true, HintBox.SMALL_SIZE);
			hintBox.createHintArrow(HintIndicator.UP_DIR, true, 330, 470);
			break;
		case 13:
			hintBox = createNewHintBox(screen, 
					"In the main scene, you have choose the word you want to make a guess." +
					" There're two words right now.", 
					50, 30, true, HintBox.SMALL_SIZE);
			break;
		case 14:
			hintBox = createNewHintBox(screen, 
					"This word", 
					50, 30, true, HintBox.SMALL_SIZE);
			hintBox.createBorderVer(185, 183);
			break;
		case 15:
			hintBox = createNewHintBox(screen, 
					"and this word", 
					50, 30, true, HintBox.SMALL_SIZE);
			hintBox.createBorderHor(67, 185);
			break;
		case 16:
			hintBox = createNewHintBox(screen, 
					"You can choose the word you want to make a guess by touching" +
					" on the first letter of that word.", 
					50, 30, true, HintBox.SMALL_SIZE);
			hintBox.createHintArrow(HintIndicator.DOWN_DIR, true, 180, 410);
			hintBox.createHintArrow(HintIndicator.DOWN_DIR, true, 65, 240);
			break;
		case 17:
			hintBox = createNewHintBox(screen, 
					"Touch here to guess this word", 
					50, 30, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 190, 410);
			break;
		case 18:
			hintBox = createNewHintBox(screen, 
					"When you finish choosing a word, you'll enter investigating scene. " +
					"In this scene, you can choose to make a guess or use a hint.", 
					60, 50, true, HintBox.SMALL_SIZE);
			break;
		case 19:
			hintBox = createNewHintBox(screen, 
					"To make a guess, touch on the button that has no letter.", 
					60, 50, true, HintBox.SMALL_SIZE);
			hintBox.createHintArrow(HintIndicator.LEFT_DIR, true, 70, 470);
			hintBox.createHintArrow(HintIndicator.LEFT_DIR, true, 70, 360);
			break;
		case 20:
			hintBox = createNewHintBox(screen, 
					"You can use a hint, which will give you more information about the word." +
					" There're 3 types of hints.", 
					60, 50, true, HintBox.SMALL_SIZE);
			hintBox.createHintArrow(HintIndicator.UP_DIR, true, 403, 300);
			hintBox.createHintArrow(HintIndicator.UP_DIR, true, 174, 180);
			hintBox.createHintArrow(HintIndicator.UP_DIR, true, 407, 180);
			break;
		case 21:
			hintBox = createNewHintBox(screen, 
					"Let's start with letters. Touch here to make a guess for this letter.",
					60, 50, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.LEFT_DIR, true, 60, 370);
			break;
		case 22:
			hintBox = createNewHintBox(screen, 
					"Now let's select the letter \"o\" ",
					120, 410, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 265, 95);
			break;
		case 23:
			hintBox = createNewHintBox(screen,
					"Touch on the green button to make a guess",
					120, 410, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 77);
			break;
		case 24:
			hintBox = createNewHintBox(screen, 
					"You've made the right guess!",
					60, 50, true, HintBox.SMALL_SIZE);
			hintBox.createHintArrow(HintIndicator.LEFT_DIR, true, 60, 355);
			break;
		case 25:
			hintBox = createNewHintBox(screen, 
					"Now, touch on this button to guess this letter.",
					60, 50, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.LEFT_DIR, true, 60, 480);
			break;
		case 26:
			hintBox = createNewHintBox(screen, 
					"This word probably be the word \"good\". Let's guess it's the \"g\".", 
					120, 410, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 300, 130);
			break;
		case 27:
			hintBox = createNewHintBox(screen, 
					"Touch on the green button to make a guess",
					120, 410, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 77);
			break;
		case 28:
			hintBox = createNewHintBox(screen,
					"You've made the incorrect guess! When you make a incorrect guess, " +
					"the number of incorrect guess will go up. When it reach the maximum," +
					" you won't be able to make another guess.",
					70, 280, true, HintBox.MEDIUM_SIZE);
			hintBox.createHintArrow(HintIndicator.UP_DIR, true, 200, 470);
			break;
		case 29:
			hintBox = createNewHintBox(screen,
					"You can continue guessing. However, this might be difficult. " +
					"Let's use a hint to make things easier. " +
					"Touch on \"close\" button to close this tab.",
					70, 390, false, HintBox.MEDIUM_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 130);
			break;
		case 30:
			hintBox = createNewHintBox(screen, 
					"Touch on this \"hint\" button. It will give you a hint on which" +
					" part of speech the word is.", 
					60, 50, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 280);
			break;
		case 31:
			hintBox = createNewHintBox(screen, 
					"Touch on the green button to use this hint", 
					120, 410, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 220, 90);
			break;
		case 32:
			hintBox = createNewHintBox(screen, 
					"Part of speech of this word are \"noun\" and \"adjective\"",
					60, 50, true, HintBox.SMALL_SIZE);
			hintBox.createHintArrow(HintIndicator.UP_DIR, true, 290, 175);
			break;
		case 33:
			hintBox = createNewHintBox(screen, 
					"When you use hint, the number of hints you can use will decrease." +
					" When it reaches zero, you won't be able to use another hint in this turn.",
					30, 80, true, HintBox.MEDIUM_SIZE);
			hintBox.createHintArrow(HintIndicator.UP_DIR, true, 90, 470);
			break;
		case 34:
			hintBox = createNewHintBox(screen,
					"Next, touch on this \"hint\" button. It will give you a hint on the number" +
							" of syllables this word has.",
					60, 50, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 180, 290);
			break;
		case 35:
			hintBox = createNewHintBox(screen, 
					"Touch on the green button to use this hint.", 
					120, 410, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 210, 90);
			break;
		case 36:
			hintBox = createNewHintBox(screen, 
					"This word has one syllable.", 
					60, 50, true, HintBox.SMALL_SIZE);
			hintBox.createHintArrow(HintIndicator.UP_DIR, true, 105, 175);
			break;
		case 37:
			hintBox = createNewHintBox(screen, 
					"Next, touch on this \"hint\" button. It will tell you whether this word" +
					" has the letter you have selected or not.",
					60, 50, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 410);
			break;
		case 38:
			hintBox = createNewHintBox(screen,
					"Let's see if there's the letter \"f\" in this word.", 
					150, 480, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 265, 130);
			break;
		case 39:
			hintBox = createNewHintBox(screen, 
					"Touch on the green button to use this hint.", 
					150, 480, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 77);
			break;
		case 40:
			hintBox = createNewHintBox(screen, 
					"This word doesn't contain the letter \"f\".",
					140, 480, true, HintBox.SMALL_SIZE);
			hintBox.createHintArrow(HintIndicator.UP_DIR, true, 192, 300);
			break;
		case 41:
			hintBox = createNewHintBox(screen, 
					"Let's try \"w\".", 
					140, 480, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 230, 60);
			break;
		case 42:
			hintBox = createNewHintBox(screen, 
					"Touch on the green button to use this hint.", 
					150, 480, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 77);
			break;
		case 43:
			hintBox = createNewHintBox(screen, 
					"This word contains the letter \"w\".",
					140, 480, true, HintBox.SMALL_SIZE);
			hintBox.createHintArrow(HintIndicator.UP_DIR, true, 285, 270);
			break;
		case 44:
			hintBox = createNewHintBox(screen, 
					"Now you know that this word is \"wood\". Close this tab.", 
					150, 480, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 130);
			break;
		case 45:
			hintBox = createNewHintBox(screen, 
					"Touch on this button to make a guess for this word.", 
					60, 50, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.LEFT_DIR, true, 60, 480);
			break;
		case 46:
			hintBox = createNewHintBox(screen,
					"Select \"w\"",
					120, 410, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 230, 60);
			break;
		case 47:
			hintBox = createNewHintBox(screen, 
					"Touch on the green button to use this hint.",
					120, 410, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 77);
			break;
		case 48:
			hintBox = createNewHintBox(screen, 
					"Now you've made a correct word guess!",
					50, 30, true, HintBox.SMALL_SIZE);
			hintBox.createHintArrow(HintIndicator.UP_DIR, true, 330, 470);
			break;
		case 49:
			hintBox = createNewHintBox(screen, 
					"When you've made a correct word guess, you cannot select that word again.", 
					50, 30, true, HintBox.SMALL_SIZE);
			hintBox.createHintArrow(HintIndicator.DOWN_DIR, true, 180, 410);
			break;
		case 50:
			hintBox = createNewHintBox(screen, 
					"Touch on this button to guess the other word.", 
					50, 30, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 80, 230);
			break;
		case 51:
			hintBox = createNewHintBox(screen, 
					"Touch on the \"hint\" button to see how many syllable this word has.",
					60, 50, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 185, 290);
			break;
		case 52:
			hintBox = createNewHintBox(screen,
					"Touch on the green button to use this hint.",
					120, 410, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 220, 90);
			break;
		case 53:
			hintBox = createNewHintBox(screen, 
					"This word has 3 syllables. Next, touch on this button to guess this letter.", 
					60, 50, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 75, 510);
			break;
		case 54:
			hintBox = createNewHintBox(screen, 
					"Let's try the \"a\".", 
					150, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 90, 130);
			break;
		case 55:
			hintBox = createNewHintBox(screen, 
					"Touch on the green button to make a guess.", 
					150, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 77);
			break;
		case 56:
			hintBox = createNewHintBox(screen, 
					"You've made the right guess! Touch on this button to guess this letter.", 
					150, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 180, 510);
			break;
		case 57:
			hintBox = createNewHintBox(screen,
					"Let's try the \"a\".",
					150, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 95, 130);
			break;
		case 58:
			hintBox = createNewHintBox(screen, 
					"Touch on the green button to make a guess.", 
					150, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 77);
			break;
		case 59:
			hintBox = createNewHintBox(screen, 
					"You've made the incorrect guess! Let's try the \"e\".", 
					150, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 235, 130);
			break;
		case 60:
			hintBox = createNewHintBox(screen, 
					"Touch on the green button to make a guess.", 
					150, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 77);
			break;
		case 61:
			hintBox = createNewHintBox(screen, 
					"You've made the incorrect guess! Close this tab.", 
					150, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 410, 130);
			break;
		case 62:
			hintBox = createNewHintBox(screen,
					"There's nothing you can do now. The number of hints you can use" +
					" are finished and the number of incorrect guess has reached" +
					" its maximum. This turn is finished.",
					30, 80, true, HintBox.MEDIUM_SIZE);
			hintBox.createHintArrow(HintIndicator.UP_DIR, true, 90, 470);
			hintBox.createHintArrow(HintIndicator.UP_DIR, true, 200, 470);
			break;
		case 63:
			hintBox = createNewHintBox(screen, 
					"Touch on this button to go back to the main scene.",
					60, 50, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 430, 60);
			break;
		case 64:
			hintBox = createNewHintBox(screen, 
					"Touch on \"status\" button to open status menu.",
					50, 30, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 430, 60);
			break;
		case 65:
			hintBox = createNewHintBox(screen, 
					"You can see your status here. There's an \"end turn\" button, " +
					"touch on the button to finish this turn.",
					90, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 220, 160);
			break;
			
	
		case 66:
			hintBox = createNewHintBox(screen, 
					"Now it's the other player's turn. Touch on \"start\" button" +
					" to let the computer plays.",
					80, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 230, 140);
			break;
		case 67:
			hintBox = createNewHintBox(screen, 
					"Now it's the 2nd round. It's your turn now. Touch on the \"start\" button" +
					" to play.",
					80, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 230, 140);
			break;
		case 68:
			hintBox = createNewHintBox(screen, 
					"Choose the word the you haven't finished guessing from the last round.", 
					50, 30, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 80, 230);
			break;
		case 69:
			hintBox = createNewHintBox(screen, 
					"This probably be the word \"radio\". Let's see if there're the" +
					" letter \"i\" and \"o\" in this word.", 
					60, 50, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 410);
			break;
		case 70:
			hintBox = createNewHintBox(screen, 
					"Select the letter \"i\".", 
					120, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 375, 130);
			break;
		case 71:
			hintBox = createNewHintBox(screen, 
					"Touch on the green button to use this hint.", 
					120, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 77);
			break;
		case 72:
			hintBox = createNewHintBox(screen, 
					"There is the letter \"i\" in this word. Let's try the letter \"o\".", 
					120, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 270, 95);
			break;
		case 73:
			hintBox = createNewHintBox(screen, 
					"Touch on the green button to use this hint.", 
					120, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 77);
			break;
		case 74:
			hintBox = createNewHintBox(screen, 
					"There is the letter \"o\" in this word. Now you know this word " +
					"is \"radio\". Close this tab.",
					120, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 137);
			break;
		case 75:
			hintBox = createNewHintBox(screen, 
					"Touch this button.",
					50, 60, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 180, 510);
			break;
		case 76:
			hintBox = createNewHintBox(screen, 
					"Select the letter \"i\".", 
					120, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 375, 130);
			break;
		case 77:
			hintBox = createNewHintBox(screen,
					"Touch on the green button to make a guess.", 
					120, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 77);
			break;
		case 78:
			hintBox = createNewHintBox(screen, 
					"Touch this button.", 
					50, 60, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 230, 510);
			break;
		case 79:
			hintBox = createNewHintBox(screen, 
					"Select the letter \"o\".", 
					120, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 270, 95);
			break;
		case 80:
			hintBox = createNewHintBox(screen, 
					"Touch on the green button to make a guess.", 
					120, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 420, 77);
			break;
		case 81:
			hintBox = createNewHintBox(screen, 
					"Now you've made all the correct word guess.    But you're not the winner yet." +
					" You have to wait for the other player to finish his turn. If the computer" +
					" have made all the correct guess too, The game is draw.",
					30, 200, true, HintBox.BIG_SIZE);
			break;
		case 82:
			hintBox = createNewHintBox(screen,
					"Touch on \"start\" button so the computer" +
					" can start his turn.",
					80, 220, false, HintBox.SMALL_SIZE);
			hintBox.createHintFinger(HintIndicator.DOWN_DIR, true, 230, 140);
			break;
		case 83:
			hintBox = createNewHintBox(screen, 
					"The computer hasn't made all the correct word guess, " +
					"so now you're the winner. Congratulations! Now you've" +
					" finished the tutorial!",
					50, 200, true, HintBox.MEDIUM_SIZE);
			break;
		}
		
		
		return hintBox;
		
		
		
	}
	
	private HintBox createNewHintBox(NutScreen screen, String message, int x ,int y, boolean hasOkButton, int[] size) {
		HintBox hintBox = new HintBox(screen, AssetLoader.calibri18Font, message, hasOkButton, size);
		hintBox.setPosition(x, y);	
		hintBox.getText().setColor(Color.BLUE);
		
		return hintBox;
	}
	
	
}
