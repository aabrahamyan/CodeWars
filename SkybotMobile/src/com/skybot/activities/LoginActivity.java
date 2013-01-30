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
		// Switching to SkybotTabLayoutActivity screen
		Intent skybottablayoutIntent = new Intent(getApplicationContext(),
				SkybotTabLayoutActivity.class);
		startActivity(skybottablayoutIntent);

		/**
		 * REGISTRATION
		 * 
		 */

		/*
		 * TextView registerScreen = (TextView)
		 * findViewById(R.id.link_to_register);
		 */

		// Listening to register new account link
		/*
		 * registerScreen.setOnClickListener(new View.OnClickListener() {
		 * 
		 * public void onClick(View v) { // Switching to Register screen Intent
		 * i = new Intent(getApplicationContext(), RegisterActivity.class);
		 * startActivity(i); } });
		 */
	}
}