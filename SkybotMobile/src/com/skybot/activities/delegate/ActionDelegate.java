package com.skybot.activities.delegate;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	public void didFinishRequestProcessing(
			ArrayList<HashMap<String, String>> list);

	public void didFinishRequestProcessing(
			ArrayList<HashMap<String, String>> list, String service);

	public void didFailRequestProcessing();

}
