package com.skybot.charts;

import java.util.Currency;

import org.achartengine.GraphicalView;

import android.content.Context;
import android.view.View;
	
public class CheckPageCoordinates {
	TerminatedJobsChart tjChart = new TerminatedJobsChart();
	CompletedJobsChart cjChart = new CompletedJobsChart();
	GraphicalView ChartView;
	public View getChart(Context context,int mCurrentPage) {
		
		if(mCurrentPage%2 == 0) {
			ChartView = tjChart.getChart(context);
		}
		else {
			ChartView = cjChart.getChart(context);
		}
		return ChartView;
	}
}
