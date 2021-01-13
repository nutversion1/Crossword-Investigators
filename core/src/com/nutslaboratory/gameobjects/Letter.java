package com.nutslaboratory.gameobjects;

import com.badlogic.gdx.utils.Array;

public class Letter{
	
	private String name;
	private String axis;
	private int column;
	private int row;
	private Array<Integer> groups = new Array<Integer>();
	private boolean isHead = false;
	private int headGroup;
	private String headAxis;
	private boolean canLink = true;
	private boolean hasRevealed = false;
	
	
	
	public Letter(String name, String axis, int column, int row){
		this.name = name;
		this.axis = axis;
		this.column = column;
		this.row = row;
		
	}
	
	public Letter getCopy(){
		Letter copyLetter = new Letter(name, axis, column, row);
		
		//
		if(isHead){
			copyLetter.setHead(true, headGroup, headAxis);
		}
		
		//
		for(int tempGroup : getGroups()){
			copyLetter.addGroup(tempGroup);
		}
		
		//
		if(hasRevealed){
			copyLetter.reveal();
		}
		
		
		return copyLetter;
	}
	
	public String getName(){
		return name;
	}
	
	public String getString(){
		return isHead ? name+"*" : name+" ";
		
		
	}
	
	public String getGroupsString(){
		if(groups.size == 0){
			return isHead ? "   "+"*" +getHeadGroup() : "   "+" " +getHeadGroup() ;
		}else if(groups.size == 1){
			return isHead ? groups.get(0)+"  "+"*" +getHeadGroup() : groups.get(0)+"  "+" " +getHeadGroup() ;
		}else{
			return isHead ? groups.get(0)+"-"+groups.get(1)+"*" +getHeadGroup() : groups.get(0)+"-"+groups.get(1)+" "+getHeadGroup() ;
		}
		
	
	}
	
	public String getAxisString(){
		if(name.equals("-")){
			return "  ";
		}else{
			if(axis.equals(Word.HORIZONTAL_AXIS)){
				return name+"_";
			}else{
				return name+"|";
			}
		}
		
		
		
	}
	
	public void setCanLink(boolean b){
		canLink = b;
	}
	
	public boolean getCanLink(){
		return canLink;
	}
	
	public void setAxis(String axis){
		this.axis = axis;
	}
	
	public String getAxis(){
		return axis;
	}
	
	public void setColumn(int column){
		this.column = column;
	}
	
	public int getColumn(){
		return column ;
	}
	
	public void setRow(int row){
		this.row = row;
	}
	
	public int getRow(){
		return row ;
	}
	
	public void addGroup(int group){
		groups.add(group);
	}
	
	public Array<Integer> getGroups(){
		return groups;
	}
	
	public void setHead(boolean isHead, int headGroup, String headAxis){
		this.isHead = isHead;
		this.headGroup = headGroup;
		this.headAxis = headAxis;
	}
	
	public boolean isHead(){
		return isHead;
	}
	
	public int getHeadGroup(){
		return headGroup;
	}
	
	public String getHeadAxis(){
		return headAxis;
	}
	
	public void reveal(){
		hasRevealed = true;
	}
	
	public void unreveal(){
		hasRevealed = false;
	}
	
	public boolean getHasRevealed(){
		return hasRevealed;
	}
	
}
