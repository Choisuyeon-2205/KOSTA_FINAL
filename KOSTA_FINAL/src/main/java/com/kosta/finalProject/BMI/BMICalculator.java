package com.kosta.finalProject.BMI;

public class BMICalculator {

	private double lowweight;
	private double normal;
	private double overweight;
	private double obbesity;
	
	public double bmicalculator(double weight, double height) {
		
		double h = height*0.01;
		double result = weight / (h*h);
		
		System.out.println("지수" + (int)result);
		
		if(result>obbesity) {
			System.out.println("비만");
		}else if(result>overweight) {
			System.out.println("과체중");
		}else if(result>normal) {
			System.out.println("정상");
		}else {
			System.out.println("저체중");
		}
		
		return result;
	}

	public void setLowweight(double lowweight) {
		this.lowweight = lowweight;
	}

	public void setNormal(double normal) {
		this.normal = normal;
	}

	public void setOverweight(double overweight) {
		this.overweight = overweight;
	}

	public void setObbesity(double obbesity) {
		this.obbesity = obbesity;
	}
	
	
}
