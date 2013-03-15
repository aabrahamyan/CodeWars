package com.skybot.charts;

import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.skybot.activities.DashboardActivity;
import com.skybot.util.Constants;

import android.graphics.Color;
import android.graphics.Paint.Align;
import android.util.DisplayMetrics;
import android.util.Log;

public class ChartSettings {
	
	DashboardActivity da = new DashboardActivity();
	
	float density = da.metrics.densityDpi;

	private float titleSize = (Constants.TITLE_TEXT_SIZE*density)/160;
	private float labelSize = (Constants.LABELS_TEXT_SIZE*density)/160;
	private float xLabelsPaddingSize = (Constants.X_LABELS_PADDING*density)/160;
	private float yLabelsPaddingSize = (Constants.Y_LABELS_PADDING*density)/160;
	
	
	public void checkScreenResolution(XYMultipleSeriesRenderer mRenderer) {
		
		Log.e("density ", " " +density);
		
		if(density == DisplayMetrics.DENSITY_XXHIGH) {
			
			mRenderer.setMargins(new int[] { 40, 90, 25, 10 });
        	mRenderer.setAxisTitleTextSize(Constants.TEXT_SIZE_XXHDPI);
        	mRenderer.setChartTitleTextSize(Constants.TEXT_SIZE_XXHDPI);
        	mRenderer.setLabelsTextSize(Constants.TEXT_SIZE_XXHDPI);
        	mRenderer.setLegendTextSize(Constants.TEXT_SIZE_XXHDPI);
        	Log.e("Density", "XXHigh");
		}
		
		else if(density == DisplayMetrics.DENSITY_XHIGH) {
			
			mRenderer.setMargins(new int[] { 40, 90, 25, 10 });
        	mRenderer.setAxisTitleTextSize(Constants.TEXT_SIZE_XHDPI);
        	mRenderer.setChartTitleTextSize(Constants.TEXT_SIZE_XHDPI);
        	mRenderer.setLabelsTextSize(Constants.TEXT_SIZE_XHDPI);
        	mRenderer.setLegendTextSize(Constants.TEXT_SIZE_XHDPI);
        	Log.e("Density", "XHigh");
		}
		
		else if(density == DisplayMetrics.DENSITY_HIGH) {
			
			mRenderer.setMargins(new int[] { 30, 50, 20, 10 });
        	mRenderer.setAxisTitleTextSize(Constants.TEXT_SIZE_HDPI);
        	mRenderer.setChartTitleTextSize(Constants.TEXT_SIZE_HDPI);
        	mRenderer.setLabelsTextSize(Constants.TEXT_SIZE_HDPI);
        	mRenderer.setLegendTextSize(Constants.TEXT_SIZE_HDPI);
        	Log.e("Density", "High");
		}
		
		else if(density == DisplayMetrics.DENSITY_MEDIUM) {
			
			mRenderer.setMargins(new int[] { 30, 50, 20, 10 });
        	mRenderer.setAxisTitleTextSize(Constants.TEXT_SIZE_MDPI);
        	mRenderer.setChartTitleTextSize(Constants.TEXT_SIZE_MDPI);
        	mRenderer.setLabelsTextSize(Constants.TEXT_SIZE_MDPI);
        	mRenderer.setLegendTextSize(Constants.TEXT_SIZE_MDPI);
        	Log.e("Density", "Medium");
		}
		
		else {
			
			mRenderer.setMargins(new int[] { 30, 50, 20, 10 });
        	mRenderer.setAxisTitleTextSize(Constants.TEXT_SIZE_LDPI);
        	mRenderer.setChartTitleTextSize(Constants.TEXT_SIZE_LDPI);
        	mRenderer.setLabelsTextSize(Constants.TEXT_SIZE_LDPI);
        	mRenderer.setLegendTextSize(Constants.TEXT_SIZE_LDPI);
        	
        	Log.e("Density", "Low");
		}
		
	}
	
	public void setBarChartSetting(XYMultipleSeriesRenderer mRenderer, double maxAxis) {
		
		checkScreenResolution(mRenderer);
		
		mRenderer.setApplyBackgroundColor(true); 
		mRenderer.setBackgroundColor(Color.WHITE); 
		mRenderer.setMarginsColor(Color.WHITE); 
		
		mRenderer.setPanEnabled(false, false);
		mRenderer.setZoomEnabled(false, false); // Zoom disable
		mRenderer.setShowGrid(true);
		mRenderer.setXLabels(0);
		
		mRenderer.setLabelsColor(Color.parseColor("#ACADAA"));
		mRenderer.setAxesColor(Color.parseColor("#ACADAA"));
		mRenderer.setXLabelsColor(Color.parseColor("#ACADAA"));
		
		mRenderer.setInScroll(true);
		mRenderer.setShowLegend(false);
		mRenderer.setYLabelsAlign(Align.RIGHT);
		mRenderer.setYAxisMin(0);
		mRenderer.setYAxisMax(maxAxis);
		
//		mRenderer.setChartTitleTextSize(titleSize);
//		mRenderer.setLabelsTextSize(labelSize);
//		mRenderer.setXLabelsPadding(xLabelsPaddingSize);
//		mRenderer.setYLabelsPadding(yLabelsPaddingSize);
		
		mRenderer.setBarSpacing(0.3);
		
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
		
		mRenderer.setPanEnabled(false, false);
		mRenderer.setZoomEnabled(false, false); // Zoom disable
		
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
