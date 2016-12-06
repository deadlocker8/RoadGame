package de.deadlocker8.roadgame.logic;

import java.util.ArrayList;
import java.util.Random;

public class PossibleTiles
{
	private ArrayList<Tile> possibleTiles;

	public PossibleTiles()
	{
		possibleTiles = new ArrayList<>();
		//curve
		possibleTiles.add(new Tile(EdgeType.ROAD, EdgeType.ROAD, EdgeType.GRASS, EdgeType.GRASS));
		//straight
		possibleTiles.add(new Tile(EdgeType.ROAD, EdgeType.GRASS, EdgeType.ROAD, EdgeType.GRASS));
		//T-tile
		possibleTiles.add(new Tile(EdgeType.ROAD, EdgeType.ROAD, EdgeType.ROAD, EdgeType.GRASS));		
	}

	public ArrayList<Tile> getPossibleTiles()
	{
		return possibleTiles;
	}	
	
	public Tile getRandomTile()
	{
		Random random = new Random();
		int index = random.nextInt(possibleTiles.size());
		Tile tile = possibleTiles.get(index);	
		
		//random rotation
		int rotate = random.nextInt(3);
		for(int i = 0; i < rotate; i++)
		{
			tile.rotateRight();
		}
	
		return tile;
	}
}