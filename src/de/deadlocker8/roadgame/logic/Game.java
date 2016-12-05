package de.deadlocker8.roadgame.logic;

import java.util.ArrayList;

import javafx.geometry.Point2D;

public class Game
{
	private Board board;
	private PossibleTiles possibleTiles;
	
	public Game()
	{
		board = new Board();
		possibleTiles = new PossibleTiles();	
		System.out.println(board);
	}
	
	public Tile getNextTile()
	{
		return possibleTiles.getRandomTile();		
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
	
	public static void main(String[] args)
	{
		Game g = new Game();
		Tile t = g.getNextTile();
		System.out.println(t);
		System.out.println(g.getPossibleLocations(t));
		System.out.println(g.board.getWidth());
		System.out.println(g.board.getHeight());
		
		g.placeTile(t, g.getPossibleLocations(t).get(0));
		System.out.println(g.board);
		
		System.out.println(g.board.getWidth());
		System.out.println(g.board.getHeight());
	}
}