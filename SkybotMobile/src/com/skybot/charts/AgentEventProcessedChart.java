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


public class AgentEventProcessedChart {
	
	public GraphicalView getChart(Object context, ArrayList<HashMap<String, String>> data) {
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); 
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		XYSeriesRenderer renderer = new XYSeriesRenderer();	 
		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		XYSeriesRenderer renderer3 = new XYSeriesRenderer();
		XYSeriesRenderer renderer4 = new XYSeriesRenderer();
		ChartSettings chartSettings = new ChartSettings();
		
		CategorySeries series = new CategorySeries("Manual");
		CategorySeries series2 = new CategorySeries("File");
		CategorySeries series3 = new CategorySeries("Directory");
		CategorySeries series4 = new CategorySeries("Process");
		
		double[] y1 = getValue(data,"manual_events");
		double[] y2 = getValue(data, "file_events");
		double[] y3 = getValue(data, "directory_events");
		double[] y4 = getValue(data, "process_events");
		String[] xLabels = getLabels(data,"label");
		
		GetMaxAxis getMax = new GetMaxAxis();
		double maxAxis = getMax.getMax(y1,y2,y3,y4);
		
		chartSettings.setBarChartSetting(mRenderer, maxAxis);
			
		chartSettings.addBarChartSeries(dataset, series, y1);
		chartSettings.addBarChartSeries(dataset, series2, y2);
		chartSettings.addBarChartSeries(dataset, series3, y3);
		chartSettings.addBarChartSeries(dataset, series4, y4);
		
		chartSettings.addBarChartXLabels(mRenderer, xLabels);

	    mRenderer.setChartTitle("Agent Events Processed");
	   // mRenderer.setMargins(new int[] { 40, 40, 0, 0 });
		
	    mRenderer.setShowLegend(true);
	    mRenderer.setLabelsTextSize(20); 
	    
	    mRenderer.setMargins(new int[] {30,40,30,0});
	    
	    mRenderer.setLegendHeight(40);
	    mRenderer.setLegendTextSize(20);
	    
	    mRenderer.setXAxisMax(8);
	    mRenderer.setXAxisMin(0);
	    
	    renderer.setChartValuesTextSize(20);
	    renderer.setColor( Color.parseColor("#65BDE3"));
	    
	    renderer2.setChartValuesTextSize(20);
	    renderer2.setColor(Color.parseColor("#FCCA76"));
	    
	    renderer3.setChartValuesTextSize(20);
	    renderer3.setColor(Color.parseColor("#BADC8C"));
	    
	    renderer4.setChartValuesTextSize(20);
	    renderer4.setColor(Color.parseColor("#C0C0C0"));
	    
	    dataset.addSeries(series.toXYSeries());
		dataset.addSeries(series2.toXYSeries());
		dataset.addSeries(series3.toXYSeries());
		dataset.addSeries(series4.toXYSeries());
	    
	    mRenderer.addSeriesRenderer(renderer);
	    mRenderer.addSeriesRenderer(renderer2);
	    mRenderer.addSeriesRenderer(renderer3);
	    mRenderer.addSeriesRenderer(renderer4);
	    
	    
	    GraphicalView chartView = ChartFactory.getBarChartView((Context) context, dataset,mRenderer, Type.STACKED);
		return chartView;
		
	}
	
	public double[] getValue(ArrayList<HashMap<String, String>> data,String key) {
		
		double[] y = {0,0,0,0,0,0,0};
		for(int i=0;i<data.size();i++) {
			y[i] =Double.parseDouble(data.get(i).get(key)) ;
			System.out.println(y[i]);
		}
		
		return y;
	}

	public String[] getLabels(ArrayList<HashMap<String, String>> data, String key) {
	
	String[] x = {"","","","","","",""};
	
	for(int i=0;i<data.size();i++) {
		x[i] = data.get(i).get(key);
		System.out.println(x[i]);
	}
	
	return x;
	}
	
}
