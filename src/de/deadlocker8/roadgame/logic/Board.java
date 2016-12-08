package de.deadlocker8.roadgame.logic;

import java.util.ArrayList;

import de.deadlocker8.roadgame.tilepacks.TilePack;
import javafx.geometry.Point2D;

public class Board
{
	private TilePack tilePack;
	private ArrayList<Tile> tiles;

	public Board(TilePack tilePack)
	{	
		this.tilePack = tilePack;
		this.tiles = new ArrayList<>();			
		initBoard();
	}
	
	private void initBoard()
	{		
		TileType tileType = getRandomTile();
		if(tileType != null)
		{	
			Tile startTile = new Tile(tileType);
			startTile.setPosition(new Point2D(0, 0));
			
			tiles.add(startTile);	
		}	
	}
	
	public TilePack getTilePack()
	{
		return tilePack;
	}

	public ArrayList<Tile> getTiles()
	{
		return tiles;
	}
	
	public Tile getTile(int x, int y)
	{
		for(Tile currentTile : tiles)
		{
			if((int)currentTile.getPosition().getX() == x && (int)currentTile.getPosition().getY() == y)
			{
				return currentTile;
			}
		}
		
		return null;
	}
	
	public TileType getRandomTile()
	{
		return tilePack.getRandomTile();
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
			if(isCrossCheckValid(playerTile, x, y-1))
			{
				freeEdges.add(new Point2D(x, y-1));
			}			
		}
		
		//East
		if(!containsTileAtPosition(x+1, y))
		{
			if(isCrossCheckValid(playerTile, x+1, y))
			{
				freeEdges.add(new Point2D(x+1, y));
			}		
		}
				
		//South
		if(!containsTileAtPosition(x, y+1))
		{	
			if(isCrossCheckValid(playerTile, x, y+1))
			{						
				freeEdges.add(new Point2D(x, y+1));
			}			
		}
		
		//West
		if(!containsTileAtPosition(x-1, y))
		{
			if(isCrossCheckValid(playerTile, x-1, y))
			{
				freeEdges.add(new Point2D(x-1, y));
			}		
		}
		
		return freeEdges;
	}
	
	private boolean isCrossCheckValid(Tile tile, int x, int y)
	{
		//North
		if(containsTileAtPosition(x, y-1))
		{			
			if(!tile.getN().equals(getTile(x, y-1).getS()))
			{
				return false;
			}			
		}
		
		//East
		if(containsTileAtPosition(x+1, y))
		{
			if(!tile.getE().equals(getTile(x+1, y).getW()))
			{
				return false;
			}	
		}
				
		//South
		if(containsTileAtPosition(x, y+1))
		{
			if(!tile.getS().equals(getTile(x, y+1).getN()))
			{
				return false;
			}	
		}
		
		//West
		if(containsTileAtPosition(x-1, y))
		{
			if(!tile.getW().equals(getTile(x-1, y).getE()))
			{
				return false;
			}	
		}
		
		return true;
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

	public boolean tileCanBePlaced(Tile tile)
	{
		if(getPossibleLocations(tile).size() == 0)
		{
			return false;
		}
		
		tile.rotateRight();
		if(getPossibleLocations(tile).size() == 0)
		{
			tile.rotateLeft();
			return false;
		}
		
		tile.rotateRight();
		if(getPossibleLocations(tile).size() == 0)
		{
			tile.rotateLeft();
			tile.rotateLeft();
			return false;
		}
		
		tile.rotateRight();
		if(getPossibleLocations(tile).size() == 0)
		{
			tile.rotateRight();
			return false;
		}
		
		tile.rotateRight();
		return true;
	}
	
	public Dimension getDimension()
	{
		int minX = 0;
		int maxX = 0;
		int minY = 0;
		int maxY = 0;
		
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
			
			if((int)currentTile.getPosition().getY() < minY)
			{
				minY = (int)currentTile.getPosition().getY();
			}
			
			if((int)currentTile.getPosition().getY() > maxY)
			{
				maxY = (int)currentTile.getPosition().getY();
			}
		}	
		
		return new Dimension(minX, maxX, minY, maxY);		
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