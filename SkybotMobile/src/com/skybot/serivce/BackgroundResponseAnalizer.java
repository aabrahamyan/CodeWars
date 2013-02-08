package com.skybot.serivce;

import java.util.Map;

/**
 * Executes new thread for parsing response data
 * @author aabraham
 *
 */
public class BackgroundResponseAnalizer extends Thread {

	private String serviceName;
	private String responseData;
	private Map<String, String> params;

	/**
	 * 
	 * @param serviceName
	 * @param responseData
	 * @param params
	 */
	public BackgroundResponseAnalizer(final String serviceName,
			final String responseData, final Map<String, String> params) {

		this.serviceName = serviceName;
		this.responseData = responseData;
		this.params = params;
	}

	@Override
	public void run() {
		super.run();

		BaseResponseAnalyzer.analyze(serviceName, params, responseData);
	}

}
