package com.skybot.charts;

import java.util.ArrayList;
import java.util.HashMap;

import org.achartengine.GraphicalView;

import android.content.Context;
import android.view.View;
	
public class CheckPageCoordinates {
	
	GraphicalView ChartView;
	ArrayList<HashMap<String, String>> data;
	
	public View getChart(Context context,int mCurrentPage,ArrayList<HashMap<String, String>> data) {
		
		this.data = data;
		
		if(mCurrentPage == 1) {
			ChartView = new CompletedJobsChart().getChart(context,data);
		}
		else if(mCurrentPage == 2) {
			ChartView = new TerminatedJobsChart().getChart(context,data);
		}
		else if(mCurrentPage == 3) {
			ChartView = new SubmittedJobsChart().getChart(context);
		}
		else if(mCurrentPage == 4){
			ChartView = new StartedJobsChart().getChart(context);
		}
		else if (mCurrentPage == 5) {
			ChartView = new EndedJobsChart().getChart(context);
		}
		else {
			ChartView = new AgentEventProcessedChart().getChart(context);
		}
		return ChartView;
	}
}
