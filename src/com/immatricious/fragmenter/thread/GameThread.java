package com.immatricious.fragmenter.thread;

import java.util.Date;

import org.lwjgl.input.Keyboard;

import com.immatricious.fragmenter.ControlManager;
import com.immatricious.fragmenter.Fragmenter;
import com.immatricious.fragmenter.entity.Entity;
import com.immatricious.fragmenter.entity.Model;
import com.immatricious.fragmenter.entity.Scene;
import com.immatricious.fragmenter.entity.TestEntity;
import com.immatricious.fragmenter.entity.modelformat.jsonmodel.Asset;
import com.immatricious.fragmenter.entity.modelformat.jsonmodel.JSONModel;
import com.immatricious.fragmenter.renderer.RenderableObject;
import com.immatricious.fragmenter.renderer.Renderer;
import com.immatricious.fragmenter.renderer.Time;
import com.immatricious.newmath.AxisAngle;
import com.immatricious.newmath.Euler;
import com.immatricious.newmath.Quaternion;
import com.immatricious.newmath.Vector3;

public class GameThread extends Thread {
	
	private Renderer renderer;
	
	private RenderThread renderThread;
	
	private Time time;
	
	private ControlManager controlManager;
	
	public GameThread(Renderer renderer)
	{
		this.renderer = renderer;
		time = new Time();
		
		this.renderThread = this.renderer.getFragmenter().getRenderThread();
		
		controlManager = new ControlManager();
	}
	
	public void run()
	{	
		generate();
		while(!this.shouldStop())
		{
			update();
			
			updateInputs();

			time.updateDeltaTime();
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		Fragmenter.log("The Game Thread Ended.");
		
		renderer.getFragmenter().setThreadsRunning(false);
	}
	
	public boolean shouldStop()
	{
		return (!renderer.getFragmenter().getThreadsRunning()) || controlManager.getState(Keyboard.KEY_H);
	}
	
	public ControlManager getControlManager() { return this.controlManager; }
	
	//Temporary
	
	private Entity testEntity;
	
	public void update()
	{
		renderer.updateCamera(time);
		
		Quaternion q = testEntity.getRotation();
		
		double n = time.getDeltaTime();
		
		double amount = ((n > 0)?n: 0) * 0.001;
		
		Quaternion q2 = (new Euler(0,amount,0)).toQuaternion();
		
		q = q.multiplyQuaternion(q2);
		
		testEntity.setRotation(q);
	}
	
	public void updateInputs()
	{
	}
	
	public void generate()
	{
		Scene scene = new Scene(new Vector3(), new Vector3(1,1,1),null);
		
		renderer.addScene(scene);
		
		testEntity = new TestEntity(new Vector3(0,-0.1,-0.4), new Vector3(1,1,1), null, new Quaternion(), Asset.DECORATIVE_LIGHT_RED);
		
		scene.addChild(testEntity);
	}
}
