package com.immatricious.fragmenter.event.keyboard;

import com.immatricious.fragmenter.event.FragmenterEvent;

public class KeyboardEvent extends FragmenterEvent {
	
	private int key;
	private boolean state;
	
	public KeyboardEvent(int key, boolean state)
	{
		this.key = key;
		this.state = state;
	}
	
	public int getKeyCode() { return this.key; }
	public boolean getState() { return this.state; }
}
