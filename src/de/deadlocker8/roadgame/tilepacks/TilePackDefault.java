package de.deadlocker8.roadgame.tilepacks;

import java.util.HashMap;

import de.deadlocker8.roadgame.logic.TileType;

public class TilePackDefault extends TilePack
{
	public TilePackDefault()
	{
		super("Default");
		
		tiles = new HashMap<>();		
		tiles.put(TileType.ROAD_END, 1);
		tiles.put(TileType.ROAD_STRAIGHT, 1);
		tiles.put(TileType.ROAD_CURVE, 1);
		tiles.put(TileType.ROAD_T_JUNCTION, 1);
		tiles.put(TileType.ROAD_X_JUNCTION, 1);
		tiles.put(TileType.ROAD_END_CHURCH, 1);
		tiles.put(TileType.ROAD_STRAIGHT_CHURCH, 1);
		tiles.put(TileType.ROAD_CURVE_CHURCH, 1);
		tiles.put(TileType.ROAD_T_JUNCTION_CHURCH, 1);
		tiles.put(TileType.ROAD_X_JUNCTION_CHURCH, 1);
		tiles.put(TileType.CASTLE_SINGLE, 1);
		tiles.put(TileType.CASTLE_TWO_SINGLE_OPPOSITE, 1);
		tiles.put(TileType.CASTLE_TWO_SINGLE_NEIGHBOR, 1);
		tiles.put(TileType.CASTLE_THREE_SINGLE, 1);
		tiles.put(TileType.CASTLE_FOUR_SINGLE, 1);
		tiles.put(TileType.CASTLE_TRIANGLE, 1);
		tiles.put(TileType.CASTLE_TUBE, 1);
		tiles.put(TileType.CASTLE_FULL, 1);
		tiles.put(TileType.CASTLE_U, 1);
		tiles.put(TileType.CASTLE_SINGLE_ROAD_STRAIGHT, 1);
		tiles.put(TileType.CASTLE_SINGLE_ROAD_CURVE_RIGHT, 1);
		tiles.put(TileType.CASTLE_SINGLE_ROAD_CURVE_LEFT, 1);
		tiles.put(TileType.CASTLE_SINGLE_ROAD_T_JUNCTION, 1);
		tiles.put(TileType.CASTLE_SINGLE_ROAD_END_IN_CASTLE, 1);
		tiles.put(TileType.CASTLE_SINGLE_ROAD_CURVE_RIGHT_END_IN_CASTLE, 1);
		tiles.put(TileType.CASTLE_SINGLE_ROAD_CURVE_LEFT_END_IN_CASTLE, 1);
		tiles.put(TileType.CASTLE_TWO_SINGLE_OPPOSITE_ROAD_STRAIGHT, 1);
		tiles.put(TileType.CASTLE_TWO_SINGLE_NEIGHBOR_ROAD_CURVE, 1);
		tiles.put(TileType.CASTLE_THREE_SINGLE_ROAD_END, 1);
		tiles.put(TileType.CASTLE_TRIANGLE_ROAD_CURVE, 1);
		tiles.put(TileType.CASTLE_TRIANGLE_ROAD_RIGHT, 1);
		tiles.put(TileType.CASTLE_TRIANGLE_ROAD_LEFT, 1);
		tiles.put(TileType.CASTLE_TUBE_ROAD_ONE_SIDE, 1);
		tiles.put(TileType.CASTLE_TUBE_ROAD_TWO_SIDE, 1);
		tiles.put(TileType.CASTLE_U_ROAD_END, 1);
	}
}