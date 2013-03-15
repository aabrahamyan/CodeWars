package com.skybot.charts;

import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.skybot.activities.DashboardActivity;
import com.skybot.charts.singleton.ChartSingleton;
import com.skybot.util.Constants;

import android.graphics.Color;
import android.graphics.Paint.Align;
import android.util.DisplayMetrics;
import android.util.Log;

public class ChartSettings {
	
	ChartSingleton chartSingleton = ChartSingleton.getInstance();
	float density = chartSingleton.metrics.densityDpi;

	private float titleSize = (Constants.TITLE_TEXT_SIZE*160)/density;
	private float labelSize = (Constants.LABELS_TEXT_SIZE*160)/density;
	private float xLabelsPaddingSize = (Constants.X_LABELS_PADDING*160)/density;
	private float yLabelsPaddingSize = (Constants.Y_LABELS_PADDING*160)/density;
	
	
	public void checkScreenResolution(XYMultipleSeriesRenderer mRenderer, String chartType) {
		
		Log.e("density ", " " +density);
		
		if(density == DisplayMetrics.DENSITY_XXHIGH) {
			
			setChartSizeParams(Constants.TEXT_SIZE_XXHDPI, Constants.TITLE_TEXT_SIZE_XXHDPI, mRenderer);
			
        	Log.e("Density", "XXHigh");
		}
		
		else if(density == DisplayMetrics.DENSITY_XHIGH) {
			
			setChartSizeParams(Constants.TEXT_SIZE_XHDPI,Constants.TITLE_TEXT_SIZE_XHDPI, mRenderer);
			
        	Log.e("Density", "XHigh");
		}
		
		else if(density == DisplayMetrics.DENSITY_HIGH) {
			
			mRenderer.setMargins(new int[] {40,30,0,30});
			setChartSizeParams(Constants.TEXT_SIZE_HDPI,Constants.TITLE_TEXT_SIZE_HDPI, mRenderer);
			
			
        	Log.e("Density", "High");
		}
		
		else if(density == DisplayMetrics.DENSITY_MEDIUM) {
			
			setChartSizeParams(Constants.TEXT_SIZE_MDPI,Constants.TITLE_TEXT_SIZE_MDPI, mRenderer);
			
        	Log.e("Density", "Medium");
		}
		
		else if(density == DisplayMetrics.DENSITY_LOW) {
			
			setChartSizeParams(Constants.TEXT_SIZE_LDPI,Constants.TITLE_TEXT_SIZE_LDPI, mRenderer);
        	
        	Log.e("Density", "Low");
		}
		
	}
	
	public void setBarChartSetting(XYMultipleSeriesRenderer mRenderer, double maxAxis) {
		
		checkScreenResolution(mRenderer,"");
		
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
		mRenderer.setYLabelsPadding(5);
		
		mRenderer.setYAxisMin(0);
		mRenderer.setYAxisMax(maxAxis);
		
		//mRenderer.setMargins(new int[] { 40, 30, 0, 30 });
//		mRenderer.setChartTitleTextSize(titleSize);
//		mRenderer.setLabelsTextSize(labelSize);
//		mRenderer.setXLabelsPadding(xLabelsPaddingSize);
//		mRenderer.setYLabelsPadding(yLabelsPaddingSize);
		
		mRenderer.setBarSpacing(0.3);
		
	}
	
	public void setLineChartSettings(XYMultipleSeriesRenderer mRenderer, XYSeriesRenderer renderer, double maxAxis) {
		
		checkScreenResolution(mRenderer,"");
		
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
		//mRenderer.setLabelsTextSize(20);
		
		//mRenderer.setChartTitleTextSize(20);
		mRenderer.setXLabels(0);
		
		mRenderer.setXAxisMin(0);
		mRenderer.setXAxisMax(16.5);
		mRenderer.setYAxisMin(0);
		mRenderer.setYAxisMax(maxAxis);
		
		mRenderer.setYLabelsAlign(Align.RIGHT);
		mRenderer.setYLabelsPadding(5);
		//mRenderer.setMargins(new int[] { 40, 30, 0, 30 });
		//
		mRenderer.setInScroll(true);
	}
	
	public void setChartSizeParams(int textSize, int titleTextSize, XYMultipleSeriesRenderer mRenderer){ 
		
		mRenderer.setAxisTitleTextSize(textSize);
    	mRenderer.setChartTitleTextSize(titleTextSize);
    	mRenderer.setLabelsTextSize(textSize);
    	mRenderer.setLegendTextSize(textSize);
    	
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
	
	public void addYlabelsColor(XYMultipleSeriesRenderer mRenderer, double maxAxis) {
		for(int i=0;i<maxAxis;i++) {
			mRenderer.setYLabelsColor(i,Color.parseColor("#ACADAA"));
		}
	}
	
		public void setChartResolution() {
		
	}
}
