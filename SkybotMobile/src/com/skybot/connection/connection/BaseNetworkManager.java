package com.skybot.connection.connection;

import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.skybot.connection.connection.helper.RequestHelper;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 
 * @author aabraham
 * 
 */
public class BaseNetworkManager {

	// Default Constructor
	public BaseNetworkManager() {
	
	}


	public void constructConnectionAndHit(final String successMessage,
			final String startingMessage, final Map<String, String> map,
			final Object managerObject, final String classString,
			final String serverUrl) {

		Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

				switch (msg.what) {
				case HttpConnection.DID_START:
					Log.d("Request", startingMessage);
					break;
				case HttpConnection.DID_SUCCEED:
					Log.d("Response Recieved", msg.obj.toString());
					try {
						parseJSONAndConstructConnection(msg.obj.toString(),
								managerObject, classString);
					} catch (final JSONException e) {
						System.err.println("HANDLE EXCEPTION PROPERLY");
					}
					break;
				case HttpConnection.DID_ERROR:
					Exception ex = (Exception) msg.obj;
					Log.d("Exception occured while hitting response", "Crashed");
					break;
				}
			}
		};

		final HttpConnection connection = new HttpConnection(handler);
		final String reqUrl = new RequestHelper().constructGetRequestString(
				map, serverUrl);
		
		connection.get(reqUrl);

	}

	/**
	 * Check if error is returned in JSON
	 * 
	 * @param jObject
	 * @throws JSONException
	 */
	private String isErrorReturned(JSONObject jObject) throws JSONException {

		if (jObject != null) {
			final Iterator<String> iter = jObject.keys();

			if (iter.hasNext()) {

				String yocKey = iter.next();
				JSONObject tempObject = jObject.getJSONObject(yocKey);
				Boolean isError;
				String errorString;
				// errorString = tempObject.getString("error_message");
				isError = tempObject.has("error_message");

				if (isError) {
					errorString = tempObject.getString("error_message");
					return errorString;
				}

				return null;
			}
		}

		return "Unknown error";
	}

	/**
	 * Parse JSON response and construct second request to get HTML response
	 * 
	 * @param responseJSON
	 * @throws JSONException
	 */
	private void parseJSONAndConstructConnection(final String jsonString,
			final Object managerObject, final String classString)
			throws JSONException {

		// Parsing JSON
		RequestHelper reqhelper = new RequestHelper();
		JSONParser jParser = new JSONParser();
		//TODO: Parse here !!!		

		// TODO: Creating request with Map params & Server URL
		String requeststring = reqhelper.constructGetRequestString(null, null);
		

		Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

				switch (msg.what) {
				case HttpConnection.DID_START:
					break;
				case HttpConnection.DID_SUCCEED:
					Log.d("Response Recieved", msg.obj.toString());
					chainOfResponsibilities(msg.obj.toString(), classString,
							managerObject, null);
					break;
				case HttpConnection.DID_ERROR:
					Exception ex = (Exception) msg.obj;					
					Log.d("Exception occured while hitting response", "Crashed");
					break;
				}
			}
		};

		// Sending second request to get HTML
		final HttpConnection connection = new HttpConnection(handler);

		connection.get(requeststring);
	}

	/**
	 * @param responseHtml
	 */
	private void chainOfResponsibilities(String responseHtml,
			final String classString, Object managerObject, String requestType) {

		if (responseHtml != null) {

			// TODO: Implement HTTP response handling implement error case
			// handling logic instead of the following hotfixes
		}

	}

	
}
