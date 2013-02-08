package com.skybot.activities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.NameValuePair;

import com.skybot.connection.connection.BaseNetworkManager;
import com.skybot.connection.connection.helper.RequestCreator;
import com.skybot.connection.connection.helper.RequestHelper;
import com.skybot.util.Base64Coder;
import com.skybot.util.Constants;

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

	public void loginAction(View v) {

		// Intent skybottablayoutIntent = new
		// Intent(getApplicationContext(),SkybotTabLayoutActivity.class);
		// startActivity(skybottablayoutIntent);

		// ------------------- Setting up login request here
		// ------------------//
		final String authToken = Base64Coder.encodeRandomBase64(); //"td7b4DquQScIPx9jqs0WSy07YX+AvCjRu/WzdyaCyi0=";
		final String username = "admin";
		final String password = "admin";
		
		BaseNetworkManager baseNetworkManager = new BaseNetworkManager();

		RequestCreator creator = new RequestCreator();
		Map<String, String> params = creator.createAppropriateMapRequest(
				Constants.AUTH_TOKEN, authToken, Constants.USERNAME, username,
				Constants.PASSWORD, password, Constants.COMMIT, "Log In");
		
		//----------------------- Construct POST DATA ---------------------------//			
		final RequestHelper reqHelper = new RequestHelper();
		final List<NameValuePair> paramsList = reqHelper.createPostDataWithKeyValuePair(params);

		baseNetworkManager.constructConnectionAndHit("Login Successful",
				"Login Request Started", paramsList, this, Constants.LOGIN_VIEW,
				Constants.LOGIN_SERVICE);
	}
}