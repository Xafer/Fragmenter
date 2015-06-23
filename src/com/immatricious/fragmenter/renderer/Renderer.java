package com.immatricious.fragmenter.renderer;

import java.util.ArrayList;
import java.util.List;

import com.immatricious.fragmenter.Fragmenter;
import com.immatricious.fragmenter.entity.Scene;
import com.immatricious.fragmenter.renderer.camera.Camera;
import com.immatricious.fragmenter.shader.Shader;

/**
 * 
 * @author Xafer
 *
 */
public class Renderer {
	
	private Fragmenter fragmenter;
	
	private List<Shader> globalShaders;
	
	/**
	 * Visuals act as a list of different planes on which to render the children. The depth buffer is cleared between the rendering of each scenes.
	 */
	private List<Scene> visuals;
	
	private List<Camera> cameras;
	
	private int mainCameraIndex;
	
	/**
	 * Generates a new Renderer object in which scenes are prepared for rendering.
	 */
	public Renderer(Fragmenter fragmenter)
	{
		this.fragmenter = fragmenter;
		
		this.visuals = new ArrayList<Scene>();
		
		this.cameras = new ArrayList<Camera>();
		
		
		
		this.mainCameraIndex = 0;
		
		globalShaders = new ArrayList<Shader>();
	}
	
	public synchronized void addScene(Scene scene)
	{
		this.visuals.add(scene);
	}
	
	public synchronized void removeScene(Scene scene)
	{
		this.visuals.remove(scene);
	}
	
	public synchronized List<Scene> getScenes() { return this.visuals; }
	
	public synchronized List<Shader> getGlobalShaders() { return this.globalShaders; }

	public synchronized void addShader(Shader shader) { this.globalShaders.add(shader); }
	public synchronized void removeShader(Shader shader) { this.globalShaders.remove(shader); }
	
	public Fragmenter getFragmenter() { return this.fragmenter; }
	
	public Camera getCamera(int index) { return this.cameras.get(index); }
	public Camera getCamera()
	{
		if(this.mainCameraIndex < this.cameras.size())
		{
		Camera camera = this.cameras.get(this.mainCameraIndex);
		return camera;
		}
		
		return null;
	}
	
	public void setCamera(Camera camera)
	{
		if(!cameras.contains(camera))
			this.cameras.add(camera);
		this.mainCameraIndex = this.cameras.indexOf(camera);
		
		camera.trigger();
	}
	
	public void addCamera(Camera camera) { this.cameras.add(camera); }
	public void removeCamera(Camera camera) { this.cameras.add(camera); }
	
	public void updateCamera(Time time)
	{
		if(this.mainCameraIndex < this.cameras.size())
		{
			this.cameras.get(this.mainCameraIndex).update(time);
		}
	}
	
}
