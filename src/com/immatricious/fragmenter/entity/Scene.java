package com.immatricious.fragmenter.entity;

import com.immatricious.fragmenter.renderer.RenderableObject;
import com.immatricious.newmath.Quaternion;
import com.immatricious.newmath.Vector3;

public class Scene extends Entity{
	
	public Scene(Vector3 position, Vector3 scale, RenderableObject parent) {
		super(position, scale, parent,new Quaternion());
	}
}
