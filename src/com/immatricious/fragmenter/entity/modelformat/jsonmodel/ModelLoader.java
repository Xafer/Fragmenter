package com.immatricious.fragmenter.entity.modelformat.jsonmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.immatricious.newmath.Euler;
import com.immatricious.newmath.Quaternion;
import com.immatricious.newmath.Vector3;

public class ModelLoader {
	private static Map<Asset, List<BoxModel>> models = new HashMap<Asset, List<BoxModel>>();
	
	private static void loadAsset(Asset a)
	{
		if(a == null)
			return;
		
		JSONObject o = Asset.getJSON(a);

		List<BoxModel> boxes = new ArrayList<BoxModel>();
		
		JSONArray parts = (JSONArray) o.get("parts");
		
		for(JSONObject part : (List<JSONObject>) parts)
		{
			// Size, color, position, rotation
			JSONArray sizeJSON = (JSONArray) part.get("size");
			
			float sizex = ((Number)sizeJSON.get(0)).floatValue();
			float sizey = ((Number)sizeJSON.get(1)).floatValue();
			float sizez = ((Number)sizeJSON.get(2)).floatValue();
			
			Vector3 size = new Vector3(sizex,sizey,sizez);
			
			// Color
			JSONArray colorJSON = (JSONArray) part.get("color");

			int r = (int) Math.floor(((Number) colorJSON.get(0)).doubleValue() * 255);
			int g = (int) Math.floor(((Number) colorJSON.get(1)).doubleValue() * 255);
			int b = (int) Math.floor(((Number) colorJSON.get(2)).doubleValue() * 255);
			
			int color = (r << 16) + (g << 8) + (b);
			
			// Position
			
			JSONArray posJSON = (JSONArray) part.get("position");

			float posx = ((Number)posJSON.get(0)).floatValue();
			float posy = ((Number)posJSON.get(1)).floatValue();
			float posz = ((Number)posJSON.get(2)).floatValue();
			
			
			Vector3 position = new Vector3(-posx,-posy,-posz);
			
			// Rotation
			
			JSONArray rotJSON = (JSONArray) part.get("rotation");
			
			double rotx, roty, rotz, rotw;
			
			rotx = ((Number)rotJSON.get(0)).doubleValue();
			roty = ((Number)rotJSON.get(1)).doubleValue();
			rotz = ((Number)rotJSON.get(2)).doubleValue();
			
			Quaternion rotation = new Quaternion();
			
			if(rotJSON.size() == 3)
			{
				Euler euler = new Euler(rotx/2,roty/2,rotz/2);
				rotation = euler.toQuaternion();
			}
			else if(rotJSON.size() == 4)
			{
				rotw = ((Number)rotJSON.get(3)).doubleValue();
				rotation.set(rotx,roty,rotz,rotw);
			}
			
			
			// Assignation
			
			BoxModel box = new BoxModel(size,position,rotation,color);
			
			boxes.add(box);
		}
		
		models.put(a, boxes);
	}
	
	public static List<BoxModel> getModel(Asset a)
	{
		if(models.get(a) == null)
			loadAsset(a);

		return models.get(a);
	}
}
