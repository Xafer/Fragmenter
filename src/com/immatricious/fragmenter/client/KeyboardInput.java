package com.immatricious.fragmenter.client;

public class KeyboardInput {
	private int key;
	public KeyboardInput(int key)
	{
		this.key = key;
	}
	
	public int getKey() { return this.key; }
	public void setKey(int value) { this.key = value; }
}