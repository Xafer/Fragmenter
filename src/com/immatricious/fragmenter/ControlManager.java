package com.immatricious.fragmenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

import com.immatricious.fragmenter.event.FragmenterEvent;
import com.immatricious.fragmenter.event.FragmenterEventDispatcher;
import com.immatricious.fragmenter.event.FragmenterEventListener;
import com.immatricious.fragmenter.event.keyboard.KeyboardEvent;
import com.immatricious.fragmenter.thread.RenderThread;

public class ControlManager {
	
	private List<Integer> keysToListen;
	
	private Map<Integer,Boolean> keycodeMap;
	
	public ControlManager()
	{
		keysToListen = new ArrayList<Integer>();
		keycodeMap = new HashMap<Integer,Boolean>();
		
		FragmenterEventDispatcher.addListener(new KeyboardInputListener());
	}
	
	public boolean getState(int key)
	{
		boolean value = false;
		
		if(keycodeMap.containsKey(key))
			value = keycodeMap.get(key);
		
		return value;
	}
	
	private class KeyboardInputListener implements FragmenterEventListener
	{

		@Override
		public void trigger(FragmenterEvent e) {
			if(e instanceof KeyboardEvent)
			{
				KeyboardEvent k = (KeyboardEvent) e;
				
				int key = k.getKeyCode();
				boolean value = k.getState();
				
				keycodeMap.put(key, value);
			}
		}
		
	}
}
