package com.immatricious.fragmenter.renderer;

import java.util.Date;

import com.immatricious.fragmenter.Fragmenter;

public class Time
{
	private double ratio;//How many ms per frames
	
	private double lastDelay;
	
	private double lastTest;
	
	private double now;
	
	public Time()
	{
		this.lastTest = getTime();
		this.lastDelay = this.ratio;
	}
	
	public void updateDeltaTime()
	{
		now = getTime();
		
		this.lastDelay = now - lastTest;
		
		lastTest = now;
	}
	
	public double getTime() { return System.nanoTime()/ 1000000; }
	
	public double getDeltaTime()
	{
		double n = this.lastDelay / this.ratio;
		n = (n >= Double.MAX_VALUE)?1:n;
		return n;
	}
	public double getLastDelay() { return this.lastDelay; }
	
	public double getRatio() { return this.ratio; }
	
	public double getRealFPS()
	{
		return (1000 / this.lastDelay);
	}
}
