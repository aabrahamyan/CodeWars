package com.skybot.serivce;

import java.util.ArrayList;
import java.util.HashMap;


import android.util.Log;

import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.util.Constants;
import com.skybot.util.ViewTracker;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Synchronized instance which evaluates response data and passes to the
 * structural parsing
 * 
 * @author aabraham
 * 
 */
public class BaseResponseAnalyzer {

	/**
	 * Handles responses and chains parsing responsibilities
	 * 
	 * @param serviceName
	 * @param map
	 * @param responseData
	 * @throws ParseException
	 */
	public static synchronized void analyze(final String serviceName,
			final String urlWithParams, final String responseData)
			 {

		if (serviceName.equals(Constants.LOGIN_SERVICE)) {

			ActionDelegate del = (ActionDelegate) ViewTracker.getInstance()
					.getCurrentContext();
			del.didFinishRequestProcessing();
		}

		else if (serviceName.equals(Constants.JOB_SERVICE_URL)) {			

			String responseString = "";
			responseString = responseData.replace("maxId:", "\"maxId\":");
			responseString = responseString.replace("timestamp:",
					"\"timestamp\":");
			responseString = responseString.replace("growler_message:",
					"\"growler_message\":");
			responseString = responseString.replace("items:", "\"items\":");

			try {
				JSONParser jParser = new JSONParser();
				JSONObject jObject = (JSONObject) jParser.parse(responseString);
				System.out.println("parsed: " + jObject.toString());					
				
				ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();				
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("maxId", jObject.get("maxId").toString());
				map.put("timestamp", jObject.get("timestamp").toString());
				map.put("growler_message", jObject.get("growler_message").toString());				
				
				list.add(map);
				ActionDelegate del = (ActionDelegate) ViewTracker.getInstance()
						.getCurrentContext();
				del.didFinishRequestProcessing(list);
				
			} catch (Exception e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}

		}

		else if (serviceName.equals(Constants.JOBHISTORY_SERVICE_URL)) {
			ActionDelegate del = (ActionDelegate) ViewTracker.getInstance()
					.getCurrentContext();
			del.didFinishRequestProcessing();

		}
	}

}
