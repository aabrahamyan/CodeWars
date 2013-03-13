package com.skybot.charts;

import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.graphics.Color;

public class ChartSettings {

	public void setBarChartSetting(XYMultipleSeriesRenderer mRenderer, double maxAxis) {
		
		mRenderer.setApplyBackgroundColor(true); 
		mRenderer.setBackgroundColor(Color.WHITE); 
		mRenderer.setMarginsColor(Color.WHITE); 
		
		mRenderer.setPanEnabled(false, false);
		mRenderer.setZoomEnabled(false, false); // Zoom disable
		
		mRenderer.setShowGrid(true);
		mRenderer.setChartTitleTextSize(25);
		mRenderer.setLabelsColor(Color.parseColor("#ACADAA"));
		mRenderer.setAxesColor(Color.parseColor("#ACADAA"));
		mRenderer.setBarSpacing(0.3);
		mRenderer.setLabelsTextSize(20);
		mRenderer.setInScroll(true);
		
		mRenderer.setXLabelsPadding(20);
		mRenderer.setXLabelsColor(Color.parseColor("#ACADAA"));
		mRenderer.setXLabels(0);
		mRenderer.setYAxisMax(maxAxis);
		mRenderer.setYAxisMin(0);
		mRenderer.setYLabelsPadding(20);
		
		mRenderer.setShowLegend(false);
	}
	
	public void setLineChartSettings(XYMultipleSeriesRenderer mRenderer, XYSeriesRenderer renderer, double maxAxis) {
		
		renderer.setPointStyle(PointStyle.CIRCLE);
		renderer.setColor(Color.parseColor("#65BDE3"));
		renderer.setFillPoints(true);
		renderer.setLineWidth(5);
		
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.WHITE);
		mRenderer.setMarginsColor(Color.WHITE);
		mRenderer.setPointSize(10);
		mRenderer.setPanEnabled(false);
		mRenderer.setZoomEnabled(false);
		mRenderer.setShowGridX(true);
		mRenderer.setShowLegend(false); 
		mRenderer.setLabelsTextSize(20);
		
		mRenderer.setChartTitleTextSize(20);
		mRenderer.setXLabels(0);
		
		mRenderer.setXAxisMin(0);
		mRenderer.setXAxisMax(17);
		mRenderer.setYAxisMax(maxAxis);
		
		mRenderer.setYLabelsPadding(20);
		mRenderer.setInScroll(true);
	}
	
	
	
	public void addBarChartSeries(XYMultipleSeriesDataset dataset, CategorySeries series, double[] value ) {

		for (int i = 0; i < value.length; i++) {
			series.add(value[i]);
		}
		
		//dataset.addSeries(series.toXYSeries());
	}
	
	public void addLineChartSeries(TimeSeries series, double[] values) {
		int[] x = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		
		for(int i=0; i<values.length;i++) {
			series.add(x[i],values[i]);
		}
		
	}
	
	public void addBarChartXLabels(XYMultipleSeriesRenderer mRenderer,String[] xLabels) {
		for (int i = 0; i < xLabels.length; i++) {
			mRenderer.addXTextLabel(i + 1, xLabels[i]);
		}
	}
	
	public void addLineChartXLabels(XYMultipleSeriesRenderer mRenderer, double[] values, String[] xLabels) {
		for(int i=0;i<values.length;i++) {
			if(i%2 == 0) {
				mRenderer.addXTextLabel(i+1,xLabels[i] );
			}
	    	
	}
	}
	
	public void addYLabels() {
		
	}
	
	public void addYlabelsColor(XYMultipleSeriesRenderer mRenderer, double maxAxis) {
		for(int i=0;i<maxAxis;i++) {
			mRenderer.setYLabelsColor(i,Color.parseColor("#ACADAA"));
		}
	}
	
		public void setChartResolution() {
		
	}
}
