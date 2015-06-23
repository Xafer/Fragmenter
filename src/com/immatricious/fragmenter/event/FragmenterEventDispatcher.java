package com.immatricious.fragmenter.event;

import java.util.ArrayList;
import java.util.List;

public class FragmenterEventDispatcher {
	private static List<FragmenterEventListener> listeners = new ArrayList<FragmenterEventListener>();
	
	public static void dispatchEvent(FragmenterEvent event)
	{
		for(FragmenterEventListener listener : FragmenterEventDispatcher.listeners)
		{
			listener.trigger(event);
		}
	}
	
	public static void addListener(FragmenterEventListener listener) { listeners.add(listener); }
	public static void removeListener(FragmenterEventListener listener) { listeners.remove(listener); }
}
