package com.skybot.serivce;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;

import android.util.Log;

import com.skybot.activities.delegate.ActionDelegate;
import com.skybot.util.Constants;
import com.skybot.util.ViewTracker;

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
	 */
	public static synchronized void analyze(final String serviceName,
			final String urlWithParams, final String responseData) {

		if (serviceName.equals(Constants.LOGIN_SERVICE)) {

			ActionDelegate del = (ActionDelegate) ViewTracker.getInstance()
					.getCurrentContext();
			del.didFinishRequestProcessing();
		}

		else if (serviceName.equals(Constants.JOB_SERVICE_URL)) {

			ActionDelegate del = (ActionDelegate) ViewTracker.getInstance()
					.getCurrentContext();
			del.didFinishRequestProcessing();
		}

		else if (serviceName.equals(Constants.JOBHISTORY_SERVICE_URL)) {

			String responseString = "";
			responseString = responseData.replace("{totalResultsReturned:",
					"{\"totalResultsReturned\":");
			responseString = responseString.replace("totalResultsAvailable:",
					"\"totalResultsAvailable\":");

			responseString = responseString.replace("firstResultPosition:",
					"\"firstResultPosition\":");
			responseString = responseString.replace("maxId:", "\"maxId\":");

			responseString = responseString.replace("timestamp:",
					"\"timestamp\":");

			responseString = responseString.replace("items:", "\"items\":");
			System.out.println(responseString);

			try {
				final JSONParser jParser = new JSONParser();
				JSONObject jObject = (JSONObject) jParser.parse(responseString);

				final ContainerFactory containerFactory = new ContainerFactory() {
					public List<?> creatArrayContainer() {
						return new LinkedList<Object>();
					}

					public Map<?, ?> createObjectContainer() {
						return new LinkedHashMap<Object, Object>();
					}
				};
				try {
					Map<?, ?> json = (Map<?, ?>) jParser.parse(responseString,
							containerFactory);
					Iterator<?> iter = json.entrySet().iterator();
					System.out
							.println("==+++**+++HERE++++**++iterate result+++**+++HERE++++**+++==");
					while (iter.hasNext()) {
						Map.Entry entry = (Map.Entry) iter.next();
						System.out.println(entry.getKey() + "=>"
								+ entry.getValue());

						ActionDelegate del = (ActionDelegate) ViewTracker
								.getInstance().getCurrentContext();
						del.didFinishRequestProcessing(json);

					}

				} catch (Exception pe) {
					pe.printStackTrace();
				}
				;

			}

			catch (Exception e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}

		}

	}
}
