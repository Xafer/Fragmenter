package com.immatricious.fragmenter.entity.modelformat.jsonmodel;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import com.immatricious.newmath.Quaternion;
import com.immatricious.newmath.Vector3;

public class Box {
	
	private Vector3 size;
	
	private Vector3 position;
	
	private Quaternion rotation;
	
	private Color color;
	
	private float opacity;
	
	private double[] vertices;
	
	private int vboId;
	
	private FloatBuffer buffer;
	
	private boolean verticesNeedUpdate;
	
	public Box(Vector3 size, Vector3 position, Quaternion rotation, int color)
	{
		this.size = size;
		this.position = position;
		this.rotation = rotation;
		this.color = new Color(color);
		
		opacity = 1.0f;
		
		verticesNeedUpdate = true;
	}
	
	public Box()
	{
		this(new Vector3(1.0f,1.0f,1.0f), new Vector3(), new Quaternion(),0xffffff);
	}
	
	public void updateVertices()
	{
		vertices = new double[12 * 3 * 3 * 2];
		
		int index = 0;
		
		for(int i = 0; i < BoxVertices.faces.length; i++)
		{
			Face f = BoxVertices.faces[i];
			Vector3 n = BoxVertices.normals[i];
			int[] indexes = f.getArray();
			for(int j : indexes)
			{
				Vector3 vertex = BoxVertices.vertices[j];
				Vector3 v = new Vector3(vertex.getX(),vertex.getY(),vertex.getZ());
				Vector3 newPosition = new Vector3(-position.getX(),-position.getY(),-position.getZ());
				
				v.setX( v.getX() * size.getX() * 0.5f);
				v.setY( v.getY() * size.getY() * 0.5f);
				v.setZ( v.getZ() * size.getZ() * 0.5f);
				
				v = rotation.rotateVector(v);
	
				v.setX( v.getX() + newPosition.getX() );
				v.setY( v.getY() + newPosition.getY() );
				v.setZ( v.getZ() + newPosition.getZ() );
				
				Vector3 rotated = v;
				
				Vector3 normal = rotation.rotateVector(n);
				
				normal.multiplyScalar(1 / normal.length());
	
				vertices[index] = rotated.getX();
				vertices[index+1] = rotated.getY();
				vertices[index+2] = rotated.getZ();
				vertices[index+3] = normal.getX();
				vertices[index+4] = normal.getY();
				vertices[index+5] = normal.getZ();
				
				index += 6;
			}
		}
		
		int l = vertices.length;
		
		float[] verticesd = new float[l];
		
		for(int i = l-1; i >= 0; i--)
		{
			verticesd[i] = (float) vertices[i];
		}
		
		buffer = BufferUtils.createFloatBuffer(vertices.length);
		buffer.put(verticesd);
		buffer.flip();
		
		vboId = GL15.glGenBuffers();

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		
		verticesNeedUpdate = false;
	}
	
	public void draw()
	{
		if(verticesNeedUpdate)
			updateVertices();
		
		GL11.glColor4f(color.getR(), color.getG(), color.getB(),opacity);
		if(opacity < 1)GL11.glBlendFunc(GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_SRC_ALPHA);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
		
		GL15.glBindBuffer(GL15.GL_VERTEX_ARRAY_BUFFER_BINDING,vboId);
		GL11.glVertexPointer(3, GL11.GL_FLOAT, 24, 0);

		GL15.glBindBuffer(GL15.GL_NORMAL_ARRAY_BUFFER_BINDING, vboId);
		GL11.glNormalPointer(GL11.GL_FLOAT, 24, 12);
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertices.length);

		GL15.glBindBuffer(GL15.GL_VERTEX_ARRAY_BUFFER_BINDING, 0);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	public boolean verticesNeedUpdate() { return this.verticesNeedUpdate; }
	public void setVerticesNeedUpdate(boolean value) { this.verticesNeedUpdate = value; }
}
