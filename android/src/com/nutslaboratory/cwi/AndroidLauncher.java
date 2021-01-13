package com.nutslaboratory.cwi;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.nutslaboratory.helpers.IActivityRequestHandler;

public class AndroidLauncher extends AndroidApplication implements IActivityRequestHandler{
	Tracker globalTracker;
	
	private static final String AD_UNIT_ID = "ca-app-pub-4073897813234505/3598593273";
	
	
	private View gameView;
	private AdView adView;

	private final int SHOW_ADS = 1;
	private final int HIDE_ADS = 0;
	
	
	protected Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
				case SHOW_ADS:
				{
					adView.setVisibility(View.VISIBLE);
					break;
				}
				case HIDE_ADS:
				{
					adView.setVisibility(View.GONE);
					break;
				}
			}
		}
	};

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		//initialize(new CWIGame(this), config);
		
		//keep screen on
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		//google analytics
		GoogleAnalytics analytics = GoogleAnalytics.getInstance(this); 
		globalTracker = analytics.newTracker(R.xml.global_tracker);
		
		
		//set up view for ads
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		RelativeLayout layout = new RelativeLayout(this);
		
		gameView = initializeForView(new CWIGame(this), config);
		layout.addView(gameView);

		//Add the AdMob view
		RelativeLayout.LayoutParams adParams = 
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
						RelativeLayout.LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

		adView = new AdView(this);
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId(AD_UNIT_ID);
		
		startAdvertising();
		
		layout.addView(adView, adParams);

		
		//set content view
		setContentView(layout);
		
		 
	}

	@Override
	public int getVersionCode() {
		int versionCode = 0;
		
		try {
			PackageInfo pInfo;
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			
			versionCode  = pInfo.versionCode;
			
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		
		return versionCode;
	}
	
	@Override
	public String getVersionName() {
		String versionName = null;
		
		try {
			PackageInfo pInfo;
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			
			versionName  = pInfo.versionName;
			
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		
		return versionName;
	}
	
	@Override
	public void onStart(){
		super.onStart();
		
		//google analytics
		GoogleAnalytics.getInstance(this).reportActivityStart(this);
	}
	
	@Override
	public void onStop(){
		super.onStop();
		
		//google analytics
		GoogleAnalytics.getInstance(this).reportActivityStop(this);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		
		if (adView != null){
			adView.resume();
		}
		
		
		//return focus to game view (for back button)
		gameView.requestFocus();
		gameView.requestFocusFromTouch();
		
		
	}

	@Override
	public void onPause() {
		if (adView != null){
			adView.pause();
		}
		
		super.onPause();
	}

	@Override
	public void onDestroy() {
		if (adView != null){
			adView.destroy();
		}
		
		super.onDestroy();
	}
	
	@Override
	public void setTrackerScreenName(String path) {
		//google analytics
		globalTracker.setScreenName(path);
		globalTracker.send(new HitBuilders.AppViewBuilder().build());
	}
	
	
	@Override
	public void showAds(boolean show) {
		handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
	}
	
	private void startAdvertising() {
		AdRequest adRequest = new AdRequest.Builder()
		//.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		//.addTestDevice("52FBF75CB5F3555800F0ED1745B8C008")
		.build();
		
		adView.loadAd(adRequest);
	}

	
}
