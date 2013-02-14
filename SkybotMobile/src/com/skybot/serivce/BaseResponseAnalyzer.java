package com.skybot.serivce;

import com.skybot.util.Constants;

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
			
		}
	}
}
