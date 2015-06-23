package com.immatricious.fragmenter;

/**
 * Fragmenter is a rendering test aimed towards rendering techniques based on fragmenting.
 * @author Xafer
 *
 */
public class Main {
	
	private static Fragmenter fragmenter;

	public static void main(String[] args) {
		Fragmenter.log("booted.");
		
		Main.fragmenter = new Fragmenter();
	}
	
	public static Fragmenter getFragmenter(){ return Main.fragmenter; }
}
