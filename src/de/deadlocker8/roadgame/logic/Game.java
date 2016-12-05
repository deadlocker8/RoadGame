package de.deadlocker8.roadgame.logic;

import java.util.ArrayList;

import javafx.geometry.Point2D;

public class Game
{
	private Board board;
	private PossibleTiles possibleTiles;
	private Tile currentTile;
	
	public Game()
	{
		board = new Board();
		possibleTiles = new PossibleTiles();			
	}
	
	public Board getBoard()
	{
		return board;
	}
	
	public Tile getNextTile()
	{
		return possibleTiles.getRandomTile();		
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