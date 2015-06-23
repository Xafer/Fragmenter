package com.immatricious.fragmenter.renderer;

import java.util.ArrayList;
import java.util.List;

import com.immatricious.newmath.Vector3;

public abstract class RenderableObject implements Renderable{
	protected Vector3 position;
	protected Vector3 scale;
	protected List<RenderableObject> children;
	protected RenderableObject parent;
	protected boolean scaleChildren;
	
	public RenderableObject(Vector3 position, Vector3 scale, RenderableObject parent)
	{
		this.position = position;
		this.scale = scale;
		this.children = new ArrayList<RenderableObject>();
		this.parent = parent;
		this.scaleChildren = false;
	}
	
	public void setPosition(Vector3 position)
	{
		this.position.setX(position.getX());
		this.position.setY(position.getY());
		this.position.setZ(position.getZ());
	}

	public Vector3 getPosition() { return new Vector3(position); }
	public Vector3 getScale() { return new Vector3(scale); }
	public void setChildScaling(boolean value) { this.scaleChildren = value; }
	
	public void setScale(Vector3 scale)
	{
		this.scale.setX(scale.getX());
		this.scale.setY(scale.getY());
		this.scale.setZ(scale.getZ());
	}
	
	public void draw(){}
	
	public void addChild(RenderableObject object)
	{
		if(!children.contains(object))
		{
			this.children.add(object);
			object.setParent(this);
		}
	}
	public void addChildren(List<RenderableObject> objects)
	{
		this.children.addAll(objects);
		
		for(RenderableObject object : objects)
			object.setParent(this);
	}
	
	public void removeChild(RenderableObject object)
	{
		if(children.contains(object))
		{
			this.children.remove(object);
			object.removeParent();
		}
	}
	
	public void removeChildren(List<RenderableObject> objects)
	{
		for(RenderableObject object : objects)
		{
			if(this.children.contains(object))
			{
				this.children.remove(object);
				object.removeParent();
			}
		}
	}
	
	public void setParent(RenderableObject parent)
	{
		if(this.parent != null)
			this.parent.removeChild(this);
		
		this.parent = parent;
	}
	
	public void removeParent()
	{
		if(this.parent != null)
			this.parent.removeChild(this);
		
		this.parent = null;
	}
}
