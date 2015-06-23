package com.immatricious.fragmenter;

import org.lwjgl.LWJGLException;

import com.immatricious.fragmenter.entity.Entity;
import com.immatricious.fragmenter.entity.Model;
import com.immatricious.fragmenter.entity.Scene;
import com.immatricious.fragmenter.entity.TestEntity;
import com.immatricious.fragmenter.entity.modelformat.jsonmodel.Asset;
import com.immatricious.fragmenter.entity.modelformat.jsonmodel.JSONModel;
import com.immatricious.fragmenter.entity.modelformat.jsonmodel.ModelLoader;
import com.immatricious.fragmenter.renderer.Renderer;
import com.immatricious.fragmenter.thread.GameThread;
import com.immatricious.fragmenter.thread.RenderThread;
import com.immatricious.newmath.Vector3;

public class Fragmenter {
	
	public static final String name = "Fragmenter";
	
	private Renderer renderer;
	
	private Thread renderThread;
	private Thread gameThread;
	
	private boolean threadsRunning;
	
	public Fragmenter()
	{
		this.renderer = new Renderer(this);
		
		start();
	}
	
	public void start()
	{
		
		this.threadsRunning = true;

		renderThread = new RenderThread(this.renderer);
		gameThread = new GameThread(this.renderer);

		renderThread.start();
		gameThread.start();
	}
	
	//
	//Access functions
	//
	
	public Renderer getRenderer() { return this.renderer; }
	public RenderThread getRenderThread() { return (RenderThread) this.renderThread; }
	public GameThread getGameThread() { return (GameThread) this.gameThread; }
	
	public boolean getThreadsRunning() { return this.threadsRunning; }
	public void setThreadsRunning(boolean value) { this.threadsRunning = value; }
	
	////////////////////
	//Static functions//
	////////////////////
	
	/**
	 * Messages logged with the fragmenter have a prefix.
	 * @param log
	 */
	public static void log(String log)
	{
		String base = "[FRAGMENTER] : ";
		System.out.println(base + log);
	}
}
