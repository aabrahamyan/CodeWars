package com.skybot.activities;



import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;
import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.adapters.TestFragmentAdapter;
import com.skybot.serivce.parser.dataholder.DataHolder;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;
/**
 * Activity for setting properties and settings of Skybot server
 * 
 * @author gor, armenabrahamyan
 * 
 */
public class HelpActivity extends FragmentActivity implements ActionDelegate {	
	
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
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
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
	@SuppressWarnings("deprecation")
	@Override
	public void onBackPressed() {
		showDialog(10);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 10:
			// Create out AlterDialog
			Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Do you want to log out?");
			builder.setCancelable(true);
			builder.setPositiveButton("Yes", new OkOnClickListener());
			builder.setNegativeButton("No", new CancelOnClickListener());
			AlertDialog dialog = builder.create();
			dialog.show();
		}
		return super.onCreateDialog(id);
	}

	private final class CancelOnClickListener implements
			DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {

		}
	}

	private final class OkOnClickListener implements
			DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {			
			JobsActivity.getActivity().signOutRequest();
			HelpActivity.this.finish();
			DataHolder.getInstance().emptyDataSet();
			Toast.makeText(getApplicationContext(), "Log out",
					Toast.LENGTH_LONG).show();
		}
	}
}