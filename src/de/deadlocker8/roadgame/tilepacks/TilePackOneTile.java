package de.deadlocker8.roadgame.tilepacks;

import java.util.HashMap;

import de.deadlocker8.roadgame.logic.TileType;

public class TilePackOneTile extends TilePack
{
	public TilePackOneTile()
	{
		super("Test");
		
		tiles = new HashMap<>();			
		tiles.put(TileType.ROAD_X_JUNCTION, 1);			
	}
}