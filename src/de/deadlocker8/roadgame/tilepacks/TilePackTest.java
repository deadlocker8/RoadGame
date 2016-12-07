package de.deadlocker8.roadgame.tilepacks;

import java.util.HashMap;

import de.deadlocker8.roadgame.logic.TileType;

public class TilePackTest extends TilePack
{
	public TilePackTest()
	{
		super("Test");
		
		tiles = new HashMap<>();		
		tiles.put(TileType.ROAD_END, 1);
		tiles.put(TileType.ROAD_STRAIGHT, 1);
		tiles.put(TileType.ROAD_CURVE, 3);		
	}
}