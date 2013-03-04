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
import com.skybot.connection.connection.BaseNetworkManager;
import com.skybot.connection.connection.helper.RequestCreator;
import com.skybot.util.Constants;


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
		getDashBoardResponse();
		/** Setting the pagerAdapter to the pager object */
		pager.setAdapter(pagerAdapter);

	}
	
	private void getDashBoardResponse() {
		
		String system_time = Long.toString(System.currentTimeMillis());
		RequestCreator creator = new RequestCreator();
		BaseNetworkManager baseNetworkManager = new BaseNetworkManager();
		
		Map <String,String> dashboard_params = creator.createAppropriateMapRequest(Constants.DATE,system_time);
		
//		final RequestHelper helper = new RequestHelper();
		//String urlStringWithParams = helper.constructGetRequestString(dashboard_params, Constants.SERVER_URL, Constants.DASHBOARD_SERVICE_URL);
		
		StringBuilder stringBuilder = new StringBuilder(Constants.SERVER_URL);
		
		stringBuilder.append(Constants.RIGHT_SLASH)
					 .append(Constants.DASHBOARD_SERVICE)
					 .append(Constants.RIGHT_SLASH)
					 .append(Constants.COMPLETED_JOBS_ID)
					 .append(Constants.RIGHT_SLASH)
					 .append(Constants.DASHBOARD_SERVICE_URL)
					 .append("?")
					 .append(Constants.DATE)
					 .append("=")
					 .append(system_time);
		
		String stringWithUrlAndParams = stringBuilder.toString();
		
		baseNetworkManager.constructConnectionAndHitGET("Chart Request is successful", "Chart Request Started", stringWithUrlAndParams, 
				this, Constants.DASHBOARD_VIEW, Constants.DASHBOARD_SERVICE);
		
		
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
