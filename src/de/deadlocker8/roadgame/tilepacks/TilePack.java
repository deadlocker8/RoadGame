package de.deadlocker8.roadgame.tilepacks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import de.deadlocker8.roadgame.logic.Tile;
import de.deadlocker8.roadgame.logic.TileType;

public class TilePack
{	
	protected String name;
	protected HashMap<TileType, Integer> tiles;	
	
	protected TilePack(String name)
	{
		this.name = name;
	}		
	
	public String getName()
	{
		return name;
	}
	
	public HashMap<TileType, Integer> getTiles()
	{
		return tiles;
	}
	
	public int getNumberOfTiles()
	{
		int numberOfTiles = 0;
		for(TileType key : tiles.keySet())
		{
			numberOfTiles += tiles.get(key);
		}
		
		return numberOfTiles;
	}
	
	public TileType getRandomTile()
	{		
		if(tiles.size() > 0)
		{
			Random random = new Random();
			int index = random.nextInt(tiles.size());
			List<TileType> keysAsArray = new ArrayList<>(tiles.keySet());		
			TileType tileType = keysAsArray.get(index);		
			
			tiles.put(tileType, tiles.get(tileType) -1);
			if(tiles.get(tileType) <= 0)
			{
				tiles.remove(tileType);
			}				
		
			Tile tile = new Tile(tileType);
			
			//random rotation
			int rotate = random.nextInt(3);
			for(int i = 0; i < rotate; i++)
			{
				tile.rotateRight();
			}
		
			return tileType;
		}
		else
		{
			return null;
		}
	}	
}