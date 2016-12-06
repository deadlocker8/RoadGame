package de.deadlocker8.roadgame.logic;

import javafx.geometry.Point2D;

public class Dimension
{
	private int minX;
	private int maxX;
	private int minY;
	private int maxY;
		
	public Dimension(int minX, int maxX, int minY, int maxY)
	{
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
	}
	
	public int getMinX()
	{
		return minX;
	}
	
	public int getMaxX()
	{
		return maxX;
	}
	
	public int getMinY()
	{
		return minY;
	}
	
	public int getMaxY()
	{
		return maxY;
	}
	
	public int getWidth()
	{
		return Math.abs(minX) + maxX + 1;
	}
	
	public int getHeight()
	{
		return Math.abs(minY) + maxY + 1;
	}
	
	public Point2D getCenterCoordinates()
	{
		return new Point2D(Math.abs(minX) + 1, Math.abs(minY) + 1);
	}

	@Override
	public String toString()
	{
		return "Dimension [minX=" + minX + ", maxX=" + maxX + ", minY=" + minY + ", maxY=" + maxY + "]";
	}
}