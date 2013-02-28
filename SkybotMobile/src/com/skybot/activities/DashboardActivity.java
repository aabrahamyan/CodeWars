package com.skybot.activities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.adapters.ScrollItemsFragmentAdapter;

/**
 * Activity for representing Dashboard charts and statistics
 * 
 * @author gor, armenabrahamyan
 * 
 */
public class DashboardActivity extends FragmentActivity implements ActionDelegate{

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard_layout);

		/** Getting a reference to the ViewPager defined the layout file */
		ViewPager pager = (ViewPager) findViewById(R.id.pager);

		/** Getting fragment manager */
		FragmentManager fm = getSupportFragmentManager();

		/** Instantiating FragmentPagerAdapter */
		ScrollItemsFragmentAdapter pagerAdapter = new ScrollItemsFragmentAdapter(fm);

		/** Setting the pagerAdapter to the pager object */
		pager.setAdapter(pagerAdapter);

	}

	@Override
	public void didFinishRequestProcessing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void didFailRequestProcessing() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void didFinishRequestProcessing(ArrayList<HashMap<String, String>> list) {
		
	}


	}
	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) {
	 * getMenuInflater().inflate(R.menu.activity_main, menu); return true; }
	 */
