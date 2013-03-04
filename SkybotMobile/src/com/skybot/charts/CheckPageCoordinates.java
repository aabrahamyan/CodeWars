package com.skybot.charts;

import org.achartengine.GraphicalView;

import android.content.Context;
import android.view.View;
	
public class CheckPageCoordinates {
	
	GraphicalView ChartView;
	public View getChart(Context context,int mCurrentPage) {
		
	
		if(mCurrentPage == 1) {
			ChartView = new CompletedJobsChart().getChart(context);
		}
		else if(mCurrentPage == 2) {
			ChartView = new TerminatedJobsChart().getChart(context);
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
