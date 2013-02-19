package com.skybot.charts;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;

public class CompletedJobsChart {
	//Context context;
	public GraphicalView getChart(Object context) {
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		
		double[] y = {0.2,0.3,0.4,0.5,0.9,0,0.3};
		CategorySeries series = new CategorySeries("Completed Jobs");
		for(int i=0; i<y.length;i++) {
			series.add(y[i]);
		}
		
		for(int i=0;i<y.length;i++) {
	    	
	    	mRenderer.addXTextLabel(i+1,"2" + "-" +i );
	    	
	    }
		mRenderer.setXLabels(0);
		XYSeriesRenderer renderer = new XYSeriesRenderer();
	    renderer.setDisplayChartValues(true);
	    renderer.setColor(Color.parseColor("#65BDE3"));
		dataset.addSeries(series.toXYSeries());
		
		/** Layout Settings**/
		
		mRenderer.setAxesColor(Color.BLACK);
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.WHITE);
		mRenderer.setMarginsColor(Color.WHITE);
	    mRenderer.setPanEnabled(false, false);
	    mRenderer.setZoomEnabled(false,false); //Zoom disable
	    mRenderer.setBarSpacing(0.3); // Distance between bar groups
	    mRenderer.setShowGrid(true); 
	    mRenderer.setInScroll(true);
	    mRenderer.setChartTitle("Completed Jobs");
	    mRenderer.setChartTitleTextSize(35);
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.setBarSpacing(0.5);
		mRenderer.setLabelsTextSize(20);
		/** X axis settings**/

		//mRenderer.setXLabels(15);
		mRenderer.setXAxisMax(8);
		mRenderer.setXAxisMin(0);
		
		/** Y axis Settings**/
		
		mRenderer.setYAxisMax(1);
		mRenderer.setYAxisMin(0);
	    GraphicalView chartView = ChartFactory.getBarChartView((Context) context, dataset, mRenderer,Type.DEFAULT);
	    
		return chartView;
		
	}
}
