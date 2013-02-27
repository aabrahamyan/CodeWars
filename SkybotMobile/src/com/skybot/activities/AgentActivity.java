package com.skybot.activities;

import java.util.ArrayList;
import java.util.HashMap;

import com.skybot.activities.delegate.ActionDelegate;

import android.app.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Activity lifecycle for Skybot Agents
 * 
 * @author gor, armenabrahamyan
 * 
 */

public class AgentActivity extends Activity implements ActionDelegate {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agent_layout);
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
