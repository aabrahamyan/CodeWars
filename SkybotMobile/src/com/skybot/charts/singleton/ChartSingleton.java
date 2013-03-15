package com.skybot.charts.singleton;

import java.util.ArrayList;
import java.util.HashMap;

import android.util.DisplayMetrics;

public class ChartSingleton {
	private static ChartSingleton chartSingleton = null;
	
	public ArrayList<HashMap<String, String>> completedArayList;
	public ArrayList<HashMap<String, String>> terminatedArrayLIst;
	public ArrayList<HashMap<String, String>> submittedArrayList;
	public ArrayList<HashMap<String, String>> agentEventProcessedArrayList;
	public DisplayMetrics metrics;
	
	public int charts_reg_counter = 0;
	
	private ChartSingleton() {
		
	}
	
	public static synchronized ChartSingleton getInstance() {
	    if (chartSingleton == null) {
	    	chartSingleton = new ChartSingleton();
	    }
	    return chartSingleton;
	  }
}
