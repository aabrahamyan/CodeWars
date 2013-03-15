package com.skybot.charts;
import java.util.ArrayList;
import java.util.HashMap;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;

public class TerminatedJobsChart {
	
	public GraphicalView getChart(Object context, ArrayList<HashMap<String, String>> data) {
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		ChartSettings chartSettings = new ChartSettings();
		XYSeriesRenderer renderer = new XYSeriesRenderer();	 
		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		XYSeriesRenderer renderer3 = new XYSeriesRenderer();
		GetMaxAxis getMax = new GetMaxAxis();
		
		double[] y1 = getValue(data, "real_canceled_value");
		double[] y2 = getValue(data, "real_failed_value");
		double[] y3 = getValue(data, "real_error_value");
		String[] xLabels = getLabels(data);
		
		double maxAxis = getMax.getMax(y1,y2,y3);
		
		CategorySeries series = new CategorySeries("Canceled");	
		CategorySeries series2 = new CategorySeries("Failed");
		CategorySeries series3 = new CategorySeries("Error");
		
		chartSettings.setBarChartSetting(mRenderer, maxAxis);
	
		chartSettings.addBarChartSeries(dataset, series, y1);
		chartSettings.addBarChartSeries(dataset, series2, y2);
		chartSettings.addBarChartSeries(dataset, series3, y3);
		
		chartSettings.addBarChartXLabels(mRenderer, xLabels);
		
		mRenderer.setChartTitle("Terminated Jobs");
		
		renderer.setColor( Color.parseColor("#65BDE3"));
	    renderer2.setColor(Color.parseColor("#FCCA76"));
	    renderer3.setColor(Color.parseColor("#BADC8C"));
		
	    mRenderer.setShowLegend(true);
	    mRenderer.setLegendHeight(40);
	    mRenderer.setLegendTextSize(20);	
	    
	   // mRenderer.setMargins(new int[] {30,40,30,0});
	    
	    mRenderer.setXAxisMax(8);
	    mRenderer.setXAxisMin(0);
	    
//	    mRenderer.setYLabelsAlign(Align.RIGHT);
	  
	    dataset.addSeries(series.toXYSeries());
		dataset.addSeries(series2.toXYSeries());
		dataset.addSeries(series3.toXYSeries());
	    
	    mRenderer.addSeriesRenderer(renderer);
	    mRenderer.addSeriesRenderer(renderer2);
	    mRenderer.addSeriesRenderer(renderer3);
	    
	    mRenderer.setInScroll(true);		//Setting swipe standart animation  
	    GraphicalView chartView = ChartFactory.getBarChartView((Context) context, dataset,mRenderer, Type.DEFAULT);
		return chartView;
		
	}
	
	public double[] getValue(ArrayList<HashMap<String, String>> data, String key) {
		double[] valueArray = {0,0,0,0,0,0,0};
		
		for(int i=0;i<data.size();i++) {
			valueArray[i] = Double.parseDouble(data.get(i).get(key));
		}
		return valueArray;
	}
	
	public String[] getLabels(ArrayList<HashMap<String, String>> data) {
		
		String[] x = {"","","","","","",""};
		
		for(int i=0;i<data.size();i++) {
			x[i] = data.get(i).get("label");
			System.out.println(x[i]);
		}
		
		return x;
	}
		
}
