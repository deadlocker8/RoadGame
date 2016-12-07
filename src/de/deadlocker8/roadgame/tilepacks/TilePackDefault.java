package de.deadlocker8.roadgame.tilepacks;

import java.util.HashMap;

import de.deadlocker8.roadgame.logic.TileType;

public class TilePackDefault extends TilePack
{
	public TilePackDefault()
	{
		super("Default");
		
		tiles = new HashMap<>();	
		
		tiles.put(TileType.ROAD_STRAIGHT, 8);
		tiles.put(TileType.ROAD_CURVE, 9);
		tiles.put(TileType.ROAD_T_JUNCTION, 4);
		tiles.put(TileType.ROAD_X_JUNCTION, 1);
		tiles.put(TileType.ROAD_END_CHURCH, 2);		
		tiles.put(TileType.CASTLE_SINGLE, 5);
		tiles.put(TileType.CASTLE_TWO_SINGLE_OPPOSITE, 3);
		tiles.put(TileType.CASTLE_TWO_SINGLE_NEIGHBOR, 2);	
		tiles.put(TileType.CASTLE_TRIANGLE, 5);
		tiles.put(TileType.CASTLE_TUBE, 3);
		tiles.put(TileType.CASTLE_FULL, 1);
		tiles.put(TileType.CASTLE_U, 4);
		tiles.put(TileType.CASTLE_SINGLE_ROAD_STRAIGHT, 4);
		tiles.put(TileType.CASTLE_SINGLE_ROAD_CURVE_RIGHT, 3);
		tiles.put(TileType.CASTLE_SINGLE_ROAD_CURVE_LEFT, 3);
		tiles.put(TileType.CASTLE_SINGLE_ROAD_T_JUNCTION, 3);		
		tiles.put(TileType.CASTLE_TRIANGLE_ROAD_CURVE, 5);		
		tiles.put(TileType.CASTLE_U_ROAD_END, 3);
		tiles.put(TileType.CHURCH, 4);
	}
}