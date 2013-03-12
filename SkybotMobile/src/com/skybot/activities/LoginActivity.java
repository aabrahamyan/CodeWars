package com.skybot.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.connection.connection.BaseNetworkManager;
import com.skybot.connection.connection.helper.RequestCreator;
import com.skybot.connection.connection.helper.RequestHelper;
import com.skybot.util.Base64Coder;
import com.skybot.util.Constants;
import com.skybot.util.Util;
import com.skybot.util.ViewTracker;

public class LoginActivity extends Activity implements ActionDelegate {
	public static String authToken;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.login);
		init();
	}

	@Override
	protected void onResume() {
		super.onResume();

		ViewTracker.getInstance().setCurrentContext(this);
		ViewTracker.getInstance().setCurrentViewName(Constants.LOGIN_VIEW);
	}

	EditText username;
	EditText password;

	/***** Share Preferences */

	private void init() {

		username = (EditText) this.findViewById(R.id.txtUname);
		password = (EditText) this.findViewById(R.id.txtPwd);

		readPerson();
	}

	private void readPerson() {
		username.setText(Util.readString(this, Util.LOGIN, null));
		password.setText(Util.readString(this, Util.PASSWORD, null));

	}

	public void loginAction(View v) {
		
		// ------------------- Setting up login request here -------------- //
		authToken = Base64Coder.encodeRandomBase64();

		BaseNetworkManager baseNetworkManager = new BaseNetworkManager();

		RequestCreator creator = new RequestCreator();
		// TODO: Gor es icnh es gre, ova ase vor partadira username-@ passi het
		// nuyne lni ???
		// if ((username.getText().toString()).equals(password.getText()
		// .toString())) {
		final String uname = username.getText().toString().trim();
		final String pass = password.getText().toString().trim();

		if (!uname.isEmpty() && !pass.isEmpty()) {
			Util.showOrHideActivityIndicator(LoginActivity.this, 0,
					"Logging into Skybot...");
			
			Map<String, String> params = creator.createAppropriateMapRequest(

			Constants.AUTH_TOKEN, authToken, Constants.USERNAME,
					Constants.ADMIN, Constants.PASSWORD, Constants.ADMIN,
					Constants.COMMIT, "Log In");

			// ----------------------- Construct POST DATA
			// ---------------------------//
			final RequestHelper reqHelper = new RequestHelper();
			final List<NameValuePair> paramsList = reqHelper
					.createPostDataWithKeyValuePair(params);

			baseNetworkManager.constructConnectionAndHitPOST(
					"Login Successful", "Login Request Started", paramsList,

					this, Constants.LOGIN_VIEW, Constants.LOGIN_SERVICE);
		} else {
			Toast.makeText(LoginActivity.this,
					"Username or Password can not be empty", Toast.LENGTH_LONG)
					.show();
		}

	}

	@Override
	public void didFinishRequestProcessing() {

		/***** Share Preferences Save */
		String nameText = username.getText().toString();
		String surnameText = password.getText().toString();

		if (nameText != null)
			Util.writeString(this, Util.LOGIN, nameText);
		if (surnameText != null)
			Util.writeString(this, Util.PASSWORD, surnameText);

		Intent skybottablayoutIntent = new Intent(getApplicationContext(),
				SkybotTabLayoutActivity.class);

		Util.showOrHideActivityIndicator(LoginActivity.this, 1,
				"Logging into Skybot...");

		startActivity(skybottablayoutIntent);
	}

	@Override
	public void didFinishRequestProcessing(
			ArrayList<HashMap<String, String>> list, String service) {

		Util.showOrHideActivityIndicator(LoginActivity.this, 1,
				"Logging into Skybot...");
	}

	@Override
	public void didFailRequestProcessing() {
		Util.showOrHideActivityIndicator(LoginActivity.this, 1,
				"Logging into Skybot...");

	}

	@Override
	public void didFinishRequestProcessing(
			ArrayList<HashMap<String, String>> list) {
		// TODO Auto-generated method stub

	}

}