package com.immatricious.fragmenter.entity;

import com.immatricious.fragmenter.entity.modelformat.jsonmodel.Asset;
import com.immatricious.fragmenter.renderer.RenderableObject;
import com.immatricious.newmath.Quaternion;
import com.immatricious.newmath.Vector3;

public class TestEntity extends Entity{

	public TestEntity(Vector3 position, Vector3 scale, RenderableObject parent, Quaternion rotation, Asset asset) {
		super(position, scale, parent, rotation);
		this.setModel(asset);
	}
	
	public TestEntity(Asset asset)
	{
		this(new Vector3(), new Vector3(1,1,1), null, new Quaternion(), asset);
	}
	
}
