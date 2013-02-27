package com.skybot.activities;


import java.util.ArrayList;
import java.util.HashMap;

import com.skybot.activities.delegate.ActionDelegate;

import android.app.Activity;
import android.os.Bundle;

/**
 * Activity for setting properties and settings of Skybot server
 * 
 * @author gor, armenabrahamyan
 * 
 */
public class SettingsActivity extends Activity implements ActionDelegate {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_layout);
	}

	@Override
	public void didFinishRequestProcessing() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void didFinishRequestProcessing(ArrayList<HashMap<String, String>> list) {
		
	}
	@Override
	public void didFailRequestProcessing() {
		// TODO Auto-generated method stub
		
	}
}