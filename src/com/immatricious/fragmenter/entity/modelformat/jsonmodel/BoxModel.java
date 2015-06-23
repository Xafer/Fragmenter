package com.immatricious.fragmenter.entity.modelformat.jsonmodel;

import com.immatricious.newmath.Quaternion;
import com.immatricious.newmath.Vector3;


public class BoxModel {
	
	private Vector3 size;
	
	private Vector3 position;
	
	private Quaternion rotation;
	
	private int color;
	
	public BoxModel(Vector3 size, Vector3 position, Quaternion rotation, int color)
	{
		this.size = size;
		this.position = position;
		this.rotation = rotation;
		this.color = color;
	}
	
	public Vector3 getSize()
	{
		return new Vector3(size.getX(),size.getY(),size.getZ());
	}
	
	public Vector3 getPosition()
	{
		return new Vector3(position.getX(),position.getY(),position.getZ());
	}
	
	public Quaternion getRotation()
	{
		return new Quaternion(rotation.getX(),rotation.getY(),rotation.getZ(),rotation.getW());
	}
	
	public int getColor()
	{
		return this.color;
	}
}
