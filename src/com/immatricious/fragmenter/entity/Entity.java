package com.immatricious.fragmenter.entity;

import org.lwjgl.opengl.GL11;

import com.immatricious.fragmenter.entity.modelformat.jsonmodel.Asset;
import com.immatricious.fragmenter.entity.modelformat.jsonmodel.JSONModel;
import com.immatricious.fragmenter.renderer.RenderableObject;
import com.immatricious.newmath.AxisAngle;
import com.immatricious.newmath.Quaternion;
import com.immatricious.newmath.Vector3;

public abstract class Entity extends RenderableObject {
	
	protected Quaternion rotation;
	
	protected Asset asset;
	
	public Entity(Vector3 position, Vector3 scale, RenderableObject parent, Quaternion rotation)
	{
		super(position,scale,parent);
		this.rotation = rotation;
	}
	
	@Override
	public void draw()
	{
		GL11.glPushMatrix();
		
		AxisAngle a = this.rotation.toAxisAngles();
		
		double deg = ((a.getTheta()/Math.PI) * 180);
		
		GL11.glTranslated(position.getX(), position.getY(), position.getZ());
		GL11.glRotated(deg,a.getX(),a.getY(),a.getZ());
		
		GL11.glPushMatrix();
		
		GL11.glScaled(scale.getX(), scale.getY(), scale.getZ());
		
		Model m = JSONModel.getModel(this.asset);
		
		if(m != null)
			m.draw();
		
		GL11.glColor3d(1,1,1);
		
		GL11.glPointSize(4);
		
		GL11.glBegin(GL11.GL_POINTS);
		
		GL11.glVertex3d(0,0,0);
		
		GL11.glEnd();
		
		if(!scaleChildren)GL11.glPopMatrix();
		
		for(RenderableObject child : children)
			child.draw();
		
		if(scaleChildren)GL11.glPopMatrix();
		
		GL11.glPopMatrix();
	}
	
	public Quaternion getRotation() { return this.rotation; }
	public void setRotation(Quaternion value) { this.rotation = value; }
	
	public void setModel( Asset asset) { this.asset = asset; }
}
