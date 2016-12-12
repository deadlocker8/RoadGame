package de.deadlocker8.roadgame.tilepacks;

import java.util.HashMap;

import de.deadlocker8.roadgame.logic.TileType;

public class TilePackTest2 extends TilePack
{
	public TilePackTest2()
	{
		super("Test 2");
		
		tiles = new HashMap<>();		
		tiles.put(TileType.CASTLE_FULL, 1);
		tiles.put(TileType.CASTLE_TRIANGLE, 1);		
	}
}