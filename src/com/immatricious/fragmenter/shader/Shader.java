package com.immatricious.fragmenter.shader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;

public class Shader {
	
	private String vertex;
	private String fragment;
	
	private int vertexShaderId;
	private int fragmentShaderId;
	
	private String shaderName;
	
	private int program;
	
	private boolean isUsable;
	
	public Shader(String shName)
	{
		this.shaderName = shName;
		this.program = 0;
		this.isUsable = false;
		
		loadShaders(shName);
		
		initiateShaders();
	}
	
	private void loadShaders(String shName)
	{
		String basePath = "src/shaders/";
		
		byte[] encodedVertex = null;
		byte[] encodedFragment = null;
		
		try
		{
			encodedVertex = Files.readAllBytes(Paths.get(basePath + shName + ".vsh"));
			encodedFragment = Files.readAllBytes(Paths.get(basePath + shName + ".fsh"));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		vertex = new String(encodedVertex);
		fragment = new String(encodedFragment);
	}
	
	private void initiateShaders()
	{
		try
		{
		vertexShaderId = createShader(vertex, ARBVertexShader.GL_VERTEX_SHADER_ARB);
		fragmentShaderId = createShader(fragment, ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
		}
		catch(Exception e)
		{
			System.out.println(e);
			return;
		}
		finally
		{
			if(vertexShaderId == 0 || fragmentShaderId == 0)
				return;
		}
		
		program = ARBShaderObjects.glCreateProgramObjectARB();
		
		if(program == 0)
			return;

		ARBShaderObjects.glAttachObjectARB(program, vertexShaderId);
		ARBShaderObjects.glAttachObjectARB(program, fragmentShaderId);
		
		ARBShaderObjects.glLinkProgramARB(program);
		
		if(ARBShaderObjects.glGetObjectParameterfARB(program, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE)
		{
			System.err.println("OI M8 it fails!");
			return;
		}
		
		ARBShaderObjects.glValidateProgramARB(program);
		if(ARBShaderObjects.glGetObjectParameteriARB(program, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE)
		{
			System.out.println("OI M8 it fails v2!");
			return;
		}
		
		this.isUsable = true;
	}
	
	private int createShader(String shaderString, int shaderType) throws Exception
	{
		int shader = 0;
		try
		{
			shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType);
			
			if(shader == 0)
				return 0;
			
			ARBShaderObjects.glShaderSourceARB(shader, shaderString);
			ARBShaderObjects.glCompileShaderARB(shader);
			
			if(ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE)
				throw new RuntimeException("Error creating Shader.");
				
			return shader;
		}
		catch(Exception e)
		{
			ARBShaderObjects.glDeleteObjectARB(shader);
			throw e;
		}
	}
	
	public int getVertexShaderId()
	{
		return this.vertexShaderId;
	}

	public int getFragmentShaderId()
	{
		return this.fragmentShaderId;
	}
	
	public void activateShader()
	{
		if(this.isUsable)
			ARBShaderObjects.glUseProgramObjectARB(program);
	}
	
	public void releaseShader()
	{
		if(this.isUsable)
			ARBShaderObjects.glUseProgramObjectARB(0);
	}
}
