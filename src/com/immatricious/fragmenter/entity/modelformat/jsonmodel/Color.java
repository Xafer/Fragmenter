package com.immatricious.fragmenter.entity.modelformat.jsonmodel;

public class Color {
	private int color;
	
	public Color(int color)
	{
		this.color = color;
	}
	
	public float getR()
	{
		float r = ((color >> 16) & 0xff );
		return r/256;
	}
	
	public float getG()
	{
		float g = ((color >> 8) & 0xff);
		return g/256;
	}
	
	public float getB()
	{
		float g = ((color) & 0xff);
		return g/256;
	}
	
	public Color clone()
	{
		Color c = new Color(this.color);
		
		return c;
	}
}
