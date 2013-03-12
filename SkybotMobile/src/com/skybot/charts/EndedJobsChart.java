package com.skybot.charts;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;

public class EndedJobsChart {
	String key = "ended_jobs";
	public GraphicalView getChart(Object context) {
		
		
		XYMultipleSeriesDataset dataset= new XYMultipleSeriesDataset();
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		TimeSeries series = new TimeSeries("Jobs Started");
		
		int[] x = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		double[] values = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		
		for(int i=0; i<values.length;i++) {
			series.add(x[i],values[i]);
		}
		
		GetMaxAxis getAxis = new GetMaxAxis();
		double maxAxis = getAxis.getMax(values,1);
		
		/**   Line Customization   **/
		
		renderer.setPointStyle(PointStyle.CIRCLE);
		renderer.setColor(Color.parseColor("#65BDE3"));
		renderer.setFillPoints(true);
		renderer.setLineWidth(5);
		
		/**Layout Customisation**/
		
		mRenderer.addSeriesRenderer(renderer);
		
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.WHITE);
		mRenderer.setMarginsColor(Color.WHITE);
		
		mRenderer.setPointSize(10);
		
		mRenderer.setPanEnabled(false);
		mRenderer.setZoomEnabled(false);
		
		mRenderer.setShowGridX(true);
		mRenderer.setShowLegend(false); //Disable legend in Chart
		mRenderer.setLabelsTextSize(20);
		
		mRenderer.setChartTitle("Ended Jobs");
		mRenderer.setChartTitleTextSize(20);
		
		mRenderer.setXAxisMin(0);
		mRenderer.setXAxisMax(17);
		mRenderer.setYAxisMax(maxAxis+1); //y max +1 Important
		mRenderer.setYLabelsPadding(-15);		
		dataset.addSeries(series);
		
		/** Layout Customisation**/
		mRenderer.setInScroll(true); //Disable fetch animation
		
		GraphicalView chartView = ChartFactory.getLineChartView((Context) context, dataset, mRenderer);
		return chartView;
		
	}
	
}
