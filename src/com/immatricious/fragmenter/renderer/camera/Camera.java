package com.immatricious.fragmenter.renderer.camera;

import org.lwjgl.opengl.GL11;

import com.immatricious.fragmenter.entity.Entity;
import com.immatricious.fragmenter.renderer.RenderableObject;
import com.immatricious.fragmenter.renderer.Time;
import com.immatricious.newmath.Quaternion;
import com.immatricious.newmath.Vector3;

public abstract class Camera extends Entity{
	
	private int width;
	private int height;
	double fovAngle;
	double near;
	double far;
	
	public Camera(Vector3 position, Vector3 scale, RenderableObject parent,Quaternion rotation) {
		super(position, scale, parent, rotation);
		// TODO Auto-generated constructor stub
	}
	
	public Camera(int width, int height, double fovAngle, double near, double far)
	{
		super(new Vector3(), new Vector3(1,1,1),null,new Quaternion());
		
		set(width,height,fovAngle,near,far);
	}
	
	public void update(Time time)
	{
		
	}
	
	public void set(int width, int height, double fovAngle, double near, double far)
	{
		this.width = width;
		this.height = height;
		this.fovAngle = fovAngle;
		this.near = near;
		this.far = far;
	}
	
	public void trigger()
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		
	    double fov  = Math.tan(fovAngle)/4;
	    double aspectRatio = width / height;
	    
	    fov /= aspectRatio;
	    
	    double b = fov/2;
	    
	    GL11.glFrustum(	-b * near * aspectRatio,
				b * near * aspectRatio,
				-b * near,
				b * near,
				near,
				far);
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}

}
