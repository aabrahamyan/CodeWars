package com.skybot.activities;

<<<<<<< HEAD
import java.util.Map;
=======
import java.util.ArrayList;
import java.util.HashMap;
>>>>>>> 130305a93ee53b4c3b03c9f484596fa48e5723f2

import com.skybot.activities.delegate.ActionDelegate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisterActivity extends Activity implements ActionDelegate {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set View to register.xml
		setContentView(R.layout.register);

		TextView loginScreen = (TextView) findViewById(R.id.link_to_login);

		// Listening to Login Screen link
		loginScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// Switching to Login Screen/closing register screen
				finish();
			}
		});
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
	
	@Override
	public void didFinishRequestProcessing(Map<?, ?>json) {
		// TODO Auto-generated method stub

		}
	
}