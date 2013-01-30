package com.skybot.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class LoginActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	}

	public void onClick(View v) {		
		
		Intent skybottablayoutIntent = new Intent(getApplicationContext(),SkybotTabLayoutActivity.class);		
		startActivity(skybottablayoutIntent);
		
	}
}