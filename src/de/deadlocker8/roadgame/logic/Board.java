package de.deadlocker8.roadgame.logic;

import java.util.ArrayList;

import javafx.geometry.Point2D;

public class Board
{
	private ArrayList<Tile> tiles;
	private PossibleTiles possibleTiles;

	public Board()
	{	
		this.tiles = new ArrayList<>();	
		this.possibleTiles = new PossibleTiles();
		initBoard();
	}
	
	private void initBoard()
	{		
		Tile startTile = possibleTiles.getRandomTile();
		startTile.setPosition(new Point2D(0, 0));
		
		tiles.add(startTile);	
	}

	public ArrayList<Tile> getTiles()
	{
		return tiles;
	}
	
	public boolean containsTileAtPosition(int x, int y)
	{
		for(Tile currentTile : tiles)
		{
			if((int)currentTile.getPosition().getX() == x && (int)currentTile.getPosition().getY() == y)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<Point2D> getFreeEdges(Tile tile, Tile playerTile)
	{
		ArrayList<Point2D> freeEdges = new ArrayList<>();
		
		int x = (int)tile.getPosition().getX();
		int y = (int)tile.getPosition().getY();
		
		//North
		if(!containsTileAtPosition(x, y-1))
		{	
			if(tile.getN().equals(playerTile.getS()))
			{
				freeEdges.add(new Point2D(x, y-1));
			}
		}
		
		//East
		if(!containsTileAtPosition(x+1, y))
		{
			if(tile.getE().equals(playerTile.getW()))
			{

			}freeEdges.add(new Point2D(x+1, y));
		}
				
		//South
		if(!containsTileAtPosition(x, y+1))
		{
			if(tile.getS().equals(playerTile.getN()))
			{
				freeEdges.add(new Point2D(x, y+1));
			}
		}
		
		//West
		if(!containsTileAtPosition(x-1, y))
		{
			if(tile.getW().equals(playerTile.getE()))
			{
				freeEdges.add(new Point2D(x-1, y));
			}
		}
		
		return freeEdges;
	}
	
	public ArrayList<Point2D> getPossibleLocations(Tile tile)
	{
		ArrayList<Point2D> possibleLocations = new ArrayList<>();
		
		for(Tile currentTile : tiles)
		{
			possibleLocations.addAll(getFreeEdges(currentTile, tile));
		}		
		
		return possibleLocations;
	}
	
	public int getWidth()
	{
		int minX = 0;
		int maxX = 0;
		
		for(Tile currentTile : tiles)
		{
			if((int)currentTile.getPosition().getX() < minX)
			{
				minX = (int)currentTile.getPosition().getX();
			}
			
			if((int)currentTile.getPosition().getX() > maxX)
			{
				maxX = (int)currentTile.getPosition().getX();
			}
		}	
		
		return Math.abs(minX) + maxX + 1;		
	}
	
	public int getHeight()
	{
		int minY = 0;
		int maxY = 0;
		
		for(Tile currentTile : tiles)
		{
			if((int)currentTile.getPosition().getY() < minY)
			{
				minY = (int)currentTile.getPosition().getY();
			}
			
			if((int)currentTile.getPosition().getX() > maxY)
			{
				maxY = (int)currentTile.getPosition().getY();
			}
		}		
		
		return Math.abs(minY) + maxY + 1;		
	}
	
	public void addTile(Tile tile)
	{
		tiles.add(tile);
	}

	@Override
	public String toString()
	{
		return "Board [tiles=" + tiles + "]";
	}
}