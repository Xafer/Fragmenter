package com.immatricious.fragmenter.entity.modelformat.jsonmodel;

public class Face {
	private int a;
	private int b;
	private int c;
	
	public Face(int a, int b, int c)
	{
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public int getA() { return this.a; }
	public int getB() { return this.b; }
	public int getC() { return this.c; }
	
	public int[] getArray() { return new int[] {a,b,c}; }
}
