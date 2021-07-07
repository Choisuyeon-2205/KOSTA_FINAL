package com.kosta.finalProject.BMI;

//@Component
public class BMICalculator {

	int group;
	
	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public double bmicalculator(double weight, double height) {
		
		double h = height*0.01;
		double result = weight / (h*h);
		
		if(result>=25.0) {
			this.group = 1;
		}else if(result >= 23.0 && result < 25.0) {
			this.group = 2;
		}else if(result >= 18.5 && result < 23.0) {
			this.group = 3;
		}else {
			this.group = 4;
		}
		
		return result;
	}


	
}
