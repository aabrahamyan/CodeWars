package com.skybot.charts;

import java.util.Currency;
import org.achartengine.GraphicalView;
import android.content.Context;
import android.view.View;
	
public class CheckPageCoordinates {
	TerminatedJobsChart termChart = new TerminatedJobsChart();
	CompletedJobsChart compChart = new CompletedJobsChart();
	SubmittedJobsChart subChart = new SubmittedJobsChart();
	StartedJobsChart startChart = new StartedJobsChart();
	
	GraphicalView ChartView;
	public View getChart(Context context,int mCurrentPage) {
		
	
		if(mCurrentPage == 1) {
			ChartView = compChart.getChart(context);
		}
		
		else if(mCurrentPage == 2) {
			ChartView = termChart.getChart(context);
		}
		
		else if(mCurrentPage == 3) {
			ChartView = subChart.getChart(context);
		}
		else {
			ChartView = startChart.getChart(context);
		}
		return ChartView;
	}
}
