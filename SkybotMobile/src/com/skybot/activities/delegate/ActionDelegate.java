package com.skybot.activities.delegate;

/**
 * Provides callback methods for activities to render or to act on ready
 * situations
 * 
 * @author aabraham
 * 
 */
public interface ActionDelegate {

	// --------------------- Service Response Delegates -------------//
	public void didFinishRequestProcessing();

	public void didFailRequestProcessing();

}
