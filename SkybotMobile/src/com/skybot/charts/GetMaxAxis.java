package com.skybot.charts;

import android.util.Log;

public class GetMaxAxis {
	
	public double getMax(double[] y, int status) {
		double maxValue = 0;
		double maxAxis = 0;
		
		maxValue = y[0];
		for(int i=0; i<y.length;i++) { 
			if(y[i] > maxValue) { 
				maxValue = y[i];
			}
		}
		
		if(status == 0) {
			maxAxis = maxValue;
		}
		else {
			maxAxis = checkIfZero(maxValue);
		}
		
		Log.e("max", " " + maxAxis);
	
		return maxAxis;
	}
	
	public double getMax(double[] y1, double[] y2, double[] y3) {
		
		double maxY1 = getMax(y1,0);
		double maxY2 = getMax(y2,0);
		double maxY3 = getMax(y3,0);
		
		double[] maxY = {maxY1,maxY2,maxY3};
		double maxValue = getMax(maxY,0);
		
		double maxAxis = checkIfZero(maxValue);
		
		return maxAxis;
	}
	
	public double getMax(double[] y1, double[] y2, double[] y3, double[] y4) {
		
		double maxY1 = getMax(y1,0);
		double maxY2 = getMax(y2,0);
		double maxY3 = getMax(y3,0);
		double maxY4 = getMax(y4,0);
		
		double[] maxY = {maxY1,maxY2,maxY3,maxY4};
		double maxValue = getMax(maxY,0);
		
		double maxAxis = checkIfZero(maxValue);
		
		return maxAxis;
	}
	
	public double checkIfZero(double maxValue) {
		
		if(maxValue == 0) {
			maxValue = 1;
		}
		else if(maxValue >= 10 && maxValue < 30) {
			maxValue = maxValue + 10;
		}
		else if (maxValue >= 30) {
			maxValue = maxValue + 15;
		}
		else  {
			maxValue = maxValue + 0.5;
		}
		return maxValue;
	}
	
}

