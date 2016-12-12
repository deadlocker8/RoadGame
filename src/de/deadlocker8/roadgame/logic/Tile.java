package de.deadlocker8.roadgame.logic;

import javafx.geometry.Point2D;

public class Tile
{
	private TileType type;
	private EdgeType N;
	private EdgeType E;
	private EdgeType S;
	private EdgeType W;	
	private CenterType C;
	private Point2D position;
	//TODO use
	private boolean marked;
	
	public Tile(TileType type, int x, int y)
	{		
		this.type = type;
		this.N = type.getN();
		this.E = type.getE();
		this.S = type.getS();
		this.W = type.getW();
		this.C = type.getC();
		this.position = new Point2D(x, y);
	}
	
	public Tile(TileType type)
	{		
		this.type = type;
		this.N = type.getN();
		this.E = type.getE();
		this.S = type.getS();
		this.W = type.getW();
		this.C = type.getC();
		this.position = null;
	}	

	public EdgeType getN()
	{
		return N;
	}

	public EdgeType getE()
	{
		return E;
	}

	public EdgeType getS()
	{
		return S;
	}

	public EdgeType getW()
	{
		return W;
	}	
	
	public CenterType getC()
	{
		return C;
	}
	
	public TileType getType()
	{
		return type;
	}

	public Point2D getPosition()
	{
		return position;
	}

	public void setPosition(Point2D position)
	{
		this.position = position;
	}

	public void rotateRight()
	{
		EdgeType temp = N;
		N = W;
		W = S;
		S = E;
		E = temp;
	}
	
	public void rotateLeft()
	{
		EdgeType temp = N;
		N = E;
		E = S;
		S = W;
		W = temp;
	}

	@Override
	public String toString()
	{
		return "Tile [N=" + N + ", E=" + E + ", S=" + S + ", W=" + W + ", position=" + position + "]";
	}
	
	public String toShortString()
	{
		String result = "";
		
		
		if(N.equals(EdgeType.ROAD))
		{
			result += "N";
		}
		
		if(E.equals(EdgeType.ROAD))
		{
			result += "E";
		}
		
		if(S.equals(EdgeType.ROAD))
		{
			result += "S";
		}
		
		if(W.equals(EdgeType.ROAD))
		{
			result += "W";
		}
				
		return result;
	}
}