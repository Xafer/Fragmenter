package com.immatricious.fragmenter.entity.modelformat.jsonmodel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.immatricious.newmath.Vector3;

public enum Asset {
	TEST_A("test_a.json"),
	TEST_B("test_b.json"),
	CONCRETE_DOOR("concrete_door.json"),
	CONCRETE_DOORFRAME("concrete_doorframe.json"),
	CONCRETE_FLOOR("concrete_floor.json"),
	CONCRETE_WALL("concrete_wall.json"),
	CONCRETE_WINDOW("concrete_window.json"),
	DECORATIVE_CANDLE_NIGHT("decorative_candle_night.json"),
	DECORATIVE_HALL("decorative_hall.json"),
	DECORATIVE_LIGHT_RED("decorative_light_red.json"),
	DECORATIVE_LIGHT_YELLOW("decorative_light_yellow.json"),
	DECORATIVE_PUDDLE1("decorative_puddle1.json"),
	DECORATIVE_SHRINE_PILLAR("decorative_shrine_pillar.json"),
	DECORATIVE_TABLE_ROUND("decorative_table_round.json"),
	WOOD_DOOR("wood_door.json"),
	WOOD_DOORFRAME("wood_doorframe.json"),
	WOOD_FLOOR("wood_floor.json"),
	WOOD_WALL("wood_wall.json"),
	WOOD_WINDOW("wood_window.json");
	
	private final String fileName;
	
	private Asset(String fileName)
	{
		this.fileName = fileName;
	}
	
	public String getFileName() { return this.fileName; }

	public static Asset getRandomAsset()
	{
		Asset[] assets = Asset.values();
		int index = (int) Math.floor(Math.random() * assets.length);
		Asset a = assets[index];
		
		return a;
	}
	
	public static String getRandomRoomFamily()
	{
		String family = "";
		
		int a = (int) Math.floor(Math.random() * 2);
		switch(a)
		{
		case 0:
			family = "wood";
			break;
		case 1:
			family = "concrete";
			break;
		}
		
		return family;
	}
	
	public static String getFamily(Asset a)
	{
		String family = null;
		
		switch(a)
		{
		case TEST_A:
		case TEST_B:
			family = "test";
			break;
		case CONCRETE_DOOR:
		case CONCRETE_DOORFRAME:
		case CONCRETE_FLOOR:
		case CONCRETE_WALL:
		case CONCRETE_WINDOW:
			family = "concrete";
			break;
		case DECORATIVE_CANDLE_NIGHT:
		case DECORATIVE_HALL:
		case DECORATIVE_LIGHT_RED:
		case DECORATIVE_LIGHT_YELLOW:
		case DECORATIVE_PUDDLE1:
		case DECORATIVE_SHRINE_PILLAR:
		case DECORATIVE_TABLE_ROUND:
			family = "decorative";
			break;
		case WOOD_DOOR:
		case WOOD_DOORFRAME:
		case WOOD_FLOOR:
		case WOOD_WALL:
		case WOOD_WINDOW:
			family = "wood";
			break;
		}
		
		return family;
	}
	
	public static String getType(Asset a)
	{
		String type = null;
		
		switch(a)
		{
		case TEST_A:
			type = "a";
			break;
		case TEST_B:
			type = "b";
			break;
		case CONCRETE_DOOR:
		case WOOD_DOOR:
			type = "door";
			break;
		case CONCRETE_DOORFRAME:
		case WOOD_DOORFRAME:
			type = "doorframe";
			break;
		case CONCRETE_FLOOR:
		case WOOD_FLOOR:
			type = "floor";
			break;
		case CONCRETE_WALL:
		case WOOD_WALL:
			type = "wall";
			break;
		case CONCRETE_WINDOW:
		case WOOD_WINDOW:
			type = "window";
			break;
		case DECORATIVE_CANDLE_NIGHT:
			type = "candle_night";
			break;
		case DECORATIVE_HALL:
			type = "hall";
			break;
		case DECORATIVE_LIGHT_RED:
			type = "light_red";
			break;
		case DECORATIVE_LIGHT_YELLOW:
			type = "light_yellow";
			break;
		case DECORATIVE_PUDDLE1:
			type = "puddle_1";
			break;
		case DECORATIVE_SHRINE_PILLAR:
			type = "shrine_pillar";
			break;
		case DECORATIVE_TABLE_ROUND:
			type = "table_round";
			break;
		}
		
		return type;
	}
	
	public static Vector3 getHitboxFromType(String type)
	{
		Vector3 hitbox = new Vector3();
		
		switch(type)
		{
		case "test":
			hitbox.set(0,0,0);
			break;
		case "door":
			hitbox.set(0.3f,3f,1.35f);
			break;
		case "doorframe":
			hitbox.set(0.3f,3f,1.35f);
			break;
		case "floor":
			hitbox.set(0.3f,3f,1.35f);
			break;
		case "wall":
			hitbox.set(0.3f,3f,1.35f);
			break;
		case "window":
			hitbox.set(0.3f,3f,1.35f);
			break;
		case "candle_night":
			hitbox.set(0.3f,3f,1.35f);
			break;
		case "hall":
			hitbox.set(0.3f,3f,1.35f);
			break;
		case "light_red":
			hitbox.set(0.3f,3f,1.35f);
			break;
		case "light_yellow":
			hitbox.set(0.3f,3f,1.35f);
			break;
		case "puddle_1":
			hitbox.set(0.3f,3f,1.35f);
			break;
		case "table_round":
			hitbox.set(0.9f,3f,0.9f);
			break;
		}
		
		return hitbox;
	}
	
	public static Asset getAssetFromTypeAndFamily(String type, String family)
	{
		Asset a = null;
		
		for(Asset asset : Asset.values())
		{
			if(	Asset.getType(asset).equals(type) &&
				Asset.getFamily(asset).equals(family))
			{
				a = asset;
				break;
			}
		}
		
		return a;
	}
	
	public static JSONObject getJSON(Asset a)
	{
		if(a == null)
			return null;
		
		JSONObject model;
		JSONParser parser = new JSONParser();
		byte[] encoded = null;
		
		String fileLocation = "src/assets/"+ a.getFileName();
		
		try {
			encoded = Files.readAllBytes(Paths.get(fileLocation));
		} catch (IOException e) {
			System.out.println("Could not load " + fileLocation);
		}

		String modelString = new String(encoded);
		
		try {
			model = (JSONObject)parser.parse(modelString);
			return model;
		} catch (ParseException e) {
			System.out.println(e);
		}
		
		return null;
	}
}
