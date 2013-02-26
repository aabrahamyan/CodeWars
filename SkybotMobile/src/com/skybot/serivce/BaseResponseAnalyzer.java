package com.skybot.serivce;

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
			
			ActionDelegate del = (ActionDelegate) ViewTracker.getInstance().getCurrentContext();			
			del.didFinishRequestProcessing();
			
			System.out.println(serviceName+responseData);
		}
		
		else if (serviceName.equals(Constants.JOB_SERVICE_URL)) {
			ActionDelegate del = (ActionDelegate) ViewTracker.getInstance().getCurrentContext();			
			del.didFinishRequestProcessing();			
			
		}
	}
}
