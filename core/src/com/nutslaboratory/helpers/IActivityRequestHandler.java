package com.nutslaboratory.helpers;

public interface IActivityRequestHandler {
	
	public int getVersionCode();
	public String getVersionName();
	
	//for google analytics
	public void setTrackerScreenName(String path);
	
	//for admob ads
	public void showAds(boolean show);

	 
}
