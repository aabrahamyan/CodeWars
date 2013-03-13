package com.skybot.activities;



import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.adapters.TestFragmentAdapter;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;
/**
 * Activity for setting properties and settings of Skybot server
 * 
 * @author gor, armenabrahamyan
 * 
 */
public class SettingsActivity extends FragmentActivity implements ActionDelegate {
	
	private TestFragmentAdapter mAdapter;
	private ViewPager mPager;
	private PageIndicator mIndicator;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		
		mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.helpPager);
        mPager.setAdapter(mAdapter);

        mIndicator = (CirclePageIndicator)findViewById(R.id.helpIndicator);
        mIndicator.setViewPager(mPager);
        
        final float density = getResources().getDisplayMetrics().density;
        ((View) mIndicator).setBackgroundColor(Color.parseColor("#FFFFFF"));
        ((CirclePageIndicator) mIndicator).setRadius(5 * density);
        ((CirclePageIndicator) mIndicator).setPageColor(Color.WHITE);
        ((CirclePageIndicator) mIndicator).setFillColor(Color.parseColor("#65BDE3"));
        ((CirclePageIndicator) mIndicator).setStrokeColor(Color.GRAY);
        ((CirclePageIndicator) mIndicator).setStrokeWidth(1);
	}

	@Override
	public void didFinishRequestProcessing() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void didFinishRequestProcessing(ArrayList<HashMap<String, String>> list,String service) {
		
	}
	@Override
	public void didFailRequestProcessing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void didFinishRequestProcessing(
			ArrayList<HashMap<String, String>> list) {
		// TODO Auto-generated method stub
		
	}

}