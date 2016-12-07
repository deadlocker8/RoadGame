package de.deadlocker8.roadgame.logic;

import java.util.ArrayList;

import de.deadlocker8.roadgame.tilepacks.TilePack;
import javafx.geometry.Point2D;

public class Game
{	
	private Board board;	
	private Tile currentTile;
	
	public Game(TilePack tilePack)
	{
		board = new Board(tilePack);				
	}
	
	public Board getBoard()
	{
		return board;
	}
	
	public Tile getNextTile()
	{
		TileType type = board.getRandomTile();
		if(type == null)
		{
			return null;
		}
		
		return new Tile(type);	
	}
	
	public void setCurrentTile(Tile currentTile)
	{
		this.currentTile = currentTile;
	}

	public Tile getCurrentTile()
	{
		return currentTile;
	}

	public ArrayList<Point2D> getPossibleLocations(Tile tile)
	{
		return board.getPossibleLocations(tile);
	}
	
	public void placeTile(Tile tile, Point2D position)
	{
		tile.setPosition(position);
		board.addTile(tile);
	}	
}