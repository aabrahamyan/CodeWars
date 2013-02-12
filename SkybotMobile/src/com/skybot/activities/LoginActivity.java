package com.skybot.activities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.NameValuePair;

import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.connection.connection.BaseNetworkManager;
import com.skybot.connection.connection.helper.RequestCreator;
import com.skybot.connection.connection.helper.RequestHelper;
import com.skybot.util.Base64Coder;
import com.skybot.util.Constants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements ActionDelegate {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	}

	EditText username;
	EditText password;

	public void loginAction(View v) {

		// ------------------- Setting up login request here
		// ------------------//
		final String authToken = Base64Coder.encodeRandomBase64(); // "td7b4DquQScIPx9jqs0WSy07YX+AvCjRu/WzdyaCyi0=";

		username = (EditText) this.findViewById(R.id.txtUname);
		password = (EditText) this.findViewById(R.id.txtPwd);

		BaseNetworkManager baseNetworkManager = new BaseNetworkManager();

		RequestCreator creator = new RequestCreator();
		if ((username.getText().toString()).equals(password.getText()
				.toString())) {

			Map<String, String> params = creator.createAppropriateMapRequest(

			Constants.AUTH_TOKEN, authToken, Constants.USERNAME, username,
					Constants.PASSWORD, password, Constants.COMMIT, "Log In");

			// ----------------------- Construct POST DATA
			// ---------------------------//
			final RequestHelper reqHelper = new RequestHelper();
			final List<NameValuePair> paramsList = reqHelper
					.createPostDataWithKeyValuePair(params);

			baseNetworkManager.constructConnectionAndHit("Login Successful",
					"Login Request Started", paramsList, this,
					Constants.LOGIN_VIEW, Constants.LOGIN_SERVICE);

		} else {
			Toast.makeText(LoginActivity.this, "Invalid Login",
					Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public void didFinishRequestProcessing() {
		Intent skybottablayoutIntent = new Intent(getApplicationContext(),
			SkybotTabLayoutActivity.class);
		
		startActivity(skybottablayoutIntent);

	}

	@Override
	public void didFailRequestProcessing() {
		// TODO Auto-generated method stub

	}
}