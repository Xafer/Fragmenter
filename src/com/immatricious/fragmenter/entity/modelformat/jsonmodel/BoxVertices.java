package com.immatricious.fragmenter.entity.modelformat.jsonmodel;

import com.immatricious.newmath.Vector3;
import com.immatricious.fragmenter.entity.modelformat.jsonmodel.Face;

public class BoxVertices {
	
	// 8 vertices of the cube
	public static final Vector3[] vertices =
	{
		new Vector3(-1,-1,-1),// a
		new Vector3(1,-1,-1),// b
		new Vector3(-1,1,-1),// c
		new Vector3(1,1,-1),// d
		new Vector3(-1,-1,1),// e
		new Vector3(1,-1,1),// f
		new Vector3(-1,1,1),// g
		new Vector3(1,1,1) // h
	};
	
	// 12 triangles of the cube
	public static final Face[] faces =
	{
		new Face(2,1,0),// z -
		new Face(2,3,1),//
		new Face(3,5,1),// x +
		new Face(3,7,5),//
		new Face(7,4,5),// z +
		new Face(7,6,4),//
		new Face(6,0,4),// x -
		new Face(6,2,0),//
		new Face(6,7,3),// y +
		new Face(3,2,6),//
		new Face(0,1,5),// y -
		new Face(5,4,0)//
	};
	
	public static final Vector3[] normals = 
	{
		new Vector3( 0, 0,-1),
		new Vector3( 0, 0,-1),
		new Vector3( 1, 0, 0),
		new Vector3( 1, 0, 0),
		new Vector3( 0, 0, 1),
		new Vector3( 0, 0, 1),
		new Vector3(-1, 0, 0),
		new Vector3(-1, 0, 0),
		new Vector3( 0, 1, 0),
		new Vector3( 0, 1, 0),
		new Vector3( 0,-1, 0),
		new Vector3( 0,-1, 0)
	};
}
