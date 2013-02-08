package com.skybot.connection.connection.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * 
 * @author aabraham
 * 
 */
public class RequestCreator {

	/**
	 * Construct Parameters Map
	 * 
	 * @param objects
	 * @return
	 */
	public Map<String, String> createAppropriateMapRequest(Object... objects) {

		final Map<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < objects.length; i += 2) {

			final String currentKey = (String) objects[i];
			final String currentValue = (String) objects[i + 1];

			if (!map.containsKey(currentKey)) {
				map.put(currentKey, currentValue);
			}
		}

		return map;
	}

	

}
