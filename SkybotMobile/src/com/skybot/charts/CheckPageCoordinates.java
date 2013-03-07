package com.skybot.charts;

import java.util.ArrayList;
import java.util.HashMap;

import org.achartengine.GraphicalView;

import com.skybot.charts.singleton.ChartSingleton;

import android.content.Context;
import android.view.View;
	
public class CheckPageCoordinates {
	
	GraphicalView ChartView;
	ArrayList<HashMap<String, String>> data;
	ChartSingleton chartSingleton = ChartSingleton.getInstance();
	
	public View getChart(Context context,int mCurrentPage,ArrayList<HashMap<String, String>> data) {
		
		this.data = data;
		
		if(mCurrentPage == 1) {
			ChartView = new CompletedJobsChart().getChart(context,chartSingleton.completedArayList);
		}
		else if(mCurrentPage == 2) {
			ChartView = new TerminatedJobsChart().getChart(context,chartSingleton.terminatedArrayLIst);
		}
		else if(mCurrentPage == 3) {
			ChartView = new SubmittedJobsChart().getChart(context,chartSingleton.submittedArrayList);
		}
		else if(mCurrentPage == 4){
			ChartView = new AgentEventProcessedChart().getChart(context,chartSingleton.agentEventProcessedArrayList);
		}
//		else if (mCurrentPage == 5) {
//			ChartView = new EndedJobsChart().getChart(context);
//		}
//		else {
//			ChartView = new AgentEventProcessedChart().getChart(context);
//		}
		return ChartView;
	}
}
