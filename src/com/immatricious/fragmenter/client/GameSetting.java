package com.immatricious.fragmenter.client;

import java.util.ArrayList;
import java.util.List;

public class GameSetting {
	
	public static List<Integer> keyboardInputs = new ArrayList<Integer>();
	
	
	public static void setKeyboardInputs(int[] keys)
	{
		for(int key : keys)
		{
			GameSetting.keyboardInputs.add(key);
		}
	}
	
	public static List<Integer> getKeyboardInputs() { return keyboardInputs; }
}
