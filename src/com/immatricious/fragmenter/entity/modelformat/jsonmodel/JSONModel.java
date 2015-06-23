package com.immatricious.fragmenter.entity.modelformat.jsonmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import com.immatricious.fragmenter.entity.Model;
import com.immatricious.newmath.Vector3;

public class JSONModel implements Model{
	
	public static Map<Asset,JSONModel> models = new HashMap<Asset,JSONModel>();
	
	private Asset type;
	
	private List<Box> boxes;
	
	private JSONModel(Asset a)
	{
		boxes = new ArrayList<Box>();
		assignModel(a);
		
		if(a != null)
		{
			System.out.println(a.toString() + " has been loaded.");
		}
	}
	
	private void assignModel(Asset a)
	{
		if(a == null)
			return;
		
		this.type = a;
		List<BoxModel> b = ModelLoader.getModel(a);
		for(BoxModel boxModel : b)
		{
			Box box = new Box(boxModel.getSize(), boxModel.getPosition(), boxModel.getRotation(), boxModel.getColor());
			boxes.add(box); 
			box.setVerticesNeedUpdate(true);
		}
	}
	
	public static JSONModel getModel(Asset a)
	{
		JSONModel model;
		
		if(models.get(a) == null)
		{
			model = new JSONModel(a);
			models.put(a, model);
		}
		else
		{
			model = models.get(a);
		}
		
		return model;
	}
	
	public void updateVertices()
	{
		for(Box b : boxes)
		{
			b.updateVertices();
		}
	}
	
	public Asset getModelType() { return this.type; }
	
	public void draw()
	{
		for(Box b : boxes)
		{
			b.draw();
		}
	}
}
