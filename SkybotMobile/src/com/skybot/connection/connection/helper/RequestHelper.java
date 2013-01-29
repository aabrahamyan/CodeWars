package com.skybot.connection.connection.helper;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.skybot.util.Constants;

/**
 * Supporting methods for creating HTTP request object
 * 
 * @author aabraham
 */
public class RequestHelper {

	/**
	 * Main Request Creator
	 * 
	 * @param map
	 * @return
	 */
	public String constructGetRequestString(final Map<String, String> map,
			final String serverUrl) {
		final StringBuilder builder = new StringBuilder();

		builder.append(serverUrl);

		if (map != null && !map.isEmpty()) {
			builder.append(Constants.PARAMETER_SEPARATOR);
			final Iterator<String> iter = map.keySet().iterator();
			int counter = 0;
			while (iter.hasNext()) {
				final String currentKey = iter.next();
				final String currentValue = map.get(currentKey);

				builder.append(currentKey);
				builder.append(Constants.EQUAL);
				if (currentValue != null)
					builder.append(URLEncoder.encode(currentValue));

				if (counter != map.size() - 1) {
					builder.append(Constants.PARAMETER_SEPARATOR);
				}

				counter++;
			}
		}

		return builder.toString();
	}

}
