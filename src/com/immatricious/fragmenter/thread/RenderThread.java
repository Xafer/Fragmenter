package com.immatricious.fragmenter.thread;

import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import com.immatricious.fragmenter.Fragmenter;
import com.immatricious.fragmenter.entity.Scene;
import com.immatricious.fragmenter.event.FragmenterEventDispatcher;
import com.immatricious.fragmenter.event.keyboard.KeyboardEvent;
import com.immatricious.fragmenter.renderer.Renderer;
import com.immatricious.fragmenter.renderer.Time;
import com.immatricious.fragmenter.renderer.camera.Camera;
import com.immatricious.fragmenter.shader.Shader;
import com.immatricious.newmath.AxisAngle;
import com.immatricious.newmath.Vector3;

public class RenderThread extends Thread {
	
	public static final String name = "Fragmenter";
	
	private Renderer renderer;
	
	private Fragmenter frag;
	
	private Time time;
	
	public RenderThread(Renderer renderer)
	{
		this.renderer = renderer;
		time = new Time();
	}
	
	public void run()
	{
		try {
			initGL(600, 400, 0.1f, 30, Math.PI/2.1);
		} catch (LWJGLException e1) {
			e1.printStackTrace();
		}
		
		this.frag = renderer.getFragmenter();
		
		while(!this.shouldStop())
		{
			time.updateDeltaTime();
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			updateKeys();
			render();
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
		
		Fragmenter.log("The Render Thread Ended.");
		frag.setThreadsRunning(false);
	}
	
	public void updateKeys()
	{
		while(Keyboard.next())
		{
			KeyboardEvent event = new KeyboardEvent(Keyboard.getEventKey(),Keyboard.getEventKeyState());
			
			FragmenterEventDispatcher.dispatchEvent(event);
		}
	}
	
	public void render()
	{
		Camera camera = renderer.getCamera();
		
		List<Shader> globalShaders = renderer.getGlobalShaders();
		List<Scene> scenes = renderer.getScenes();
		
		Vector3 p = camera.getPosition();
		
		AxisAngle a = camera.getRotation().toAxisAngles();
		
		double deg = ((a.getTheta()/Math.PI) * 180);
		
		GL11.glPushMatrix();

		GL11.glRotated(-deg, a.getX(), a.getY(), a.getZ());
		GL11.glTranslated(-p.getX(), -p.getY(), -p.getZ());

		for(Shader shader : globalShaders)
			shader.activateShader();
			
		for(Scene scene : scenes)
		{
			scene.draw();
			GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
		}
		
		for(Shader shader : globalShaders)
			shader.releaseShader();
		
		GL11.glPopMatrix();
	}
	
	public void initGL(int width, int height,float near, float far, double fovAngle) throws LWJGLException
	{
		initDisplay(width,height);

		initSettings();
		
		renderer.setCamera(new Camera(width,height,fovAngle,near,far));
		
		Shader s = new Shader("test");
		renderer.addShader(s);
	}
	
	public void initDisplay(int width, int height) throws LWJGLException
	{
		Display.setTitle("Fragmenter");
		
		Display.setDisplayMode(new DisplayMode(width,height));
		
		Display.setResizable(true);
		
		Display.create();
	}
	
	public void initSettings()
	{
		GL11.glClearDepth(1.0f);
    	GL11.glEnable(GL11.GL_DEPTH_TEST);
    	GL11.glDepthFunc(GL11.GL_LEQUAL);
        
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
		
		GL11.glFrontFace(GL11.GL_CCW);
		
		GL11.glPolygonMode(GL11.GL_FRONT, GL11.GL_FILL);
		
		GL11.glCullFace(GL11.GL_BACK);
		
		GL11.glEnable(GL11.GL_CULL_FACE);

		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
	}
	
	public boolean shouldStop()
	{
		return Display.isCloseRequested() || Keyboard.isKeyDown(Keyboard.KEY_F) || (!renderer.getFragmenter().getThreadsRunning());
	}
	
	public boolean isKeyDown(int keycode)
	{
		if(Keyboard.isCreated())
			return Keyboard.isKeyDown(keycode);
		else
			return false;
	}
}
