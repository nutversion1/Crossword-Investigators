package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.utils.Array;

public class Word {
	public static final String HORIZONTAL_AXIS = "horizontal_axis";
	public static final String VERTICAL_AXIS = "vertical_axis";
	
	private String name;
	private String syllable;
	private String[] type;
	
	public Word(String name, String syllable, String[] type){
		this.name = name;
		this.syllable = syllable;
		this.type = type;
	}
	
	public String getName(){
		return name;
	}
	
	public String getSyllable(){
		return syllable;
	}
	
	public String[] getType(){
		return type;
	}
	
	public String getString(){
		//create string for type(array to string)
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < type.length; i++) {
			stringBuilder.append(type[i]);
			
			if(i+1 < type.length){
				stringBuilder.append("-");
			}
		}
		String typeString = stringBuilder.toString();
		
		//return string
		return name+" , "+syllable+" , "+typeString;
	}
	
	public Array<Integer> getLetterIndexs(Letter letter){
		Array<Integer> letterIndexs = new Array<Integer>();
		
		int fromIndex = 0;
		while(fromIndex <= getName().length()){
			int letterIndex = getName().indexOf(letter.getName(), fromIndex);
			
			if(letterIndex == -1){
				break;
			}else{
				letterIndexs.add(letterIndex);
				
				fromIndex = letterIndex+1;
			}
		}
		
		return letterIndexs;
	}
}
