package com.skybot.serivce;

import java.util.Map;

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
			Map<String, String> params, final String responseData) {

	}

	private void chainOfParsersResponsibilities() {

	}

}
