package com.skybot.connection.connection;

import java.util.List;
import org.apache.http.NameValuePair;
import com.skybot.util.Constants;
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

	/**
	 * Main HTTP Service requests method
	 * 
	 * @param successMessage
	 * @param startingMessage
	 * @param paramsList
	 * @param managerObject
	 * @param classString
	 * @param serviceName
	 */
	public void constructConnectionAndHit(final String successMessage,
			final String startingMessage, final List<NameValuePair> paramsList,
			final Object managerObject, final String classString,
			final String serviceName) {

		final Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

				switch (msg.what) {
				case HttpConnection.DID_START:
					Log.d("Request", startingMessage);
					break;
				case HttpConnection.DID_SUCCEED:
					Log.d("Response Recieved", msg.obj.toString());
					chainOfResponsibilities(msg.obj.toString(), classString,
							managerObject, serviceName);
					break;
				case HttpConnection.DID_ERROR:
					Exception ex = (Exception) msg.obj;
					Log.d("Exception occured while hitting response",
							ex.getMessage());
					break;
				}
			}
		};

		final HttpConnection connection = new HttpConnection(handler);
		final StringBuilder builder = new StringBuilder();

		// ----------------------- Construct Service Url
		// -------------------------//
		builder.append(Constants.SERVER_URL);
		builder.append(Constants.RIGHT_SLASH);
		builder.append(serviceName);
		final String httpRequestUrl = builder.toString();

		connection.post(httpRequestUrl, paramsList);

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
