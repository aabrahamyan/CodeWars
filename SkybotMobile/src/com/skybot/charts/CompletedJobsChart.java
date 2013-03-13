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

public class CompletedJobsChart {
	// Context context;
	ArrayList<HashMap<String, String>> data;
	String key = "completed_jobs";

	public GraphicalView getChart(Object context,
			ArrayList<HashMap<String, String>> data) {

		

		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		double[] y = getValue(data);
		String[] xLabels = getLabels(data);
		GetMaxAxis getAxis = new GetMaxAxis();
		
		// double[] y = {0.2,0.3,0.4,0.5,0.9,0,0.3};
		CategorySeries series = new CategorySeries("Completed Jobs");

		for (int i = 0; i < y.length; i++) {
			series.add(y[i]);
		}

		/** Setting Labels For X Axis **/

		for (int i = 0; i < xLabels.length; i++) {
			mRenderer.addXTextLabel(i + 1, xLabels[i]);
		}
		mRenderer.setXLabels(0);

		
		double maxAxis = getAxis.getMax(y, 1);

		XYSeriesRenderer renderer = new XYSeriesRenderer();
		dataset.addSeries(series.toXYSeries());

		/** Single BarChart customisation **/
		//renderer.setDisplayChartValues(true);
		renderer.setColor(Color.parseColor("#65BDE3"));

		/** Layout Settings **/

		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.WHITE);
		mRenderer.setMarginsColor(Color.WHITE);

		mRenderer.setPanEnabled(false, false);
		mRenderer.setZoomEnabled(false, false); // Zoom disable

		mRenderer.setShowGrid(true);

		mRenderer.setChartTitle("Completed Jobs");
		mRenderer.setChartTitleTextSize(25);
		mRenderer.setLabelsColor(Color.parseColor("#ACADAA"));

		mRenderer.setBarSpacing(0.3);
		mRenderer.setLabelsTextSize(20);

		mRenderer.setInScroll(true);
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.setMargins(new int[] { 40, 40, 0, 0 });
		mRenderer.setAxesColor(Color.parseColor("#ACADAA"));
		
		
		/** Distance between y axis and labels **/

		/** X axis customisation settings **/

		mRenderer.setXAxisMax(8);
		mRenderer.setXAxisMin(0);
		mRenderer.setXLabelsPadding(20);
		mRenderer.setXLabelsColor(Color.parseColor("#ACADAA"));
		
		/** Y axis customisation Settings **/

		mRenderer.setYAxisMax(maxAxis);
		mRenderer.setYAxisMin(0);
		mRenderer.setYLabelsPadding(20);
		
		for(int i=0;i<maxAxis;i++) {
			mRenderer.setYLabelsColor(i,Color.parseColor("#ACADAA"));
		}
		
		mRenderer.setShowLegend(false);

		GraphicalView chartView = ChartFactory.getBarChartView(
				(Context) context, dataset, mRenderer, Type.DEFAULT);
		return chartView;

	}

	public double[] getValue(ArrayList<HashMap<String, String>> data) {

		double[] y = { 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < data.size(); i++) {
			y[i] = Double.parseDouble(data.get(i).get("real_value"));
			System.out.println(y[i]);
		}

		return y;
	}

	public String[] getLabels(ArrayList<HashMap<String, String>> data) {

		String[] x = { "", "", "", "", "", "", "" };

		for (int i = 0; i < data.size(); i++) {
			x[i] = data.get(i).get("label");
			System.out.println(x[i]);
		}

		return x;
	}

}
