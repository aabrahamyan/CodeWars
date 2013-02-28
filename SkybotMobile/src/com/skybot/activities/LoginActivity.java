package com.skybot.activities;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.connection.connection.BaseNetworkManager;
import com.skybot.connection.connection.helper.RequestCreator;
import com.skybot.connection.connection.helper.RequestHelper;
import com.skybot.util.Base64Coder;
import com.skybot.util.Constants;
import com.skybot.util.Util;
import com.skybot.util.ViewTracker;

public class LoginActivity extends Activity implements ActionDelegate {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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

		// ------------------- Setting up login request here
		// ------------------//
		final String authToken = Base64Coder.encodeRandomBase64(); // "td7b4DquQScIPx9jqs0WSy07YX+AvCjRu/WzdyaCyi0=";

		BaseNetworkManager baseNetworkManager = new BaseNetworkManager();

		RequestCreator creator = new RequestCreator();
		if ((username.getText().toString()).equals(password.getText()
				.toString())) {

			Map<String, String> params = creator.createAppropriateMapRequest(

			Constants.AUTH_TOKEN, authToken, Constants.USERNAME, Constants.ADMIN,
					Constants.PASSWORD, Constants.ADMIN, Constants.COMMIT, "Log In");

			// ----------------------- Construct POST DATA
			// ---------------------------//
			final RequestHelper reqHelper = new RequestHelper();
			final List<NameValuePair> paramsList = reqHelper
					.createPostDataWithKeyValuePair(params);

			baseNetworkManager.constructConnectionAndHitPOST(
					"Login Successful", "Login Request Started", paramsList,

					this, Constants.LOGIN_VIEW, Constants.LOGIN_SERVICE);
		} 
	

		else {

			/*
			 * Toast.makeText(LoginActivity.this, "Invalid Login",
			 * Toast.LENGTH_LONG).show();
			 */
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

		startActivity(skybottablayoutIntent);
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