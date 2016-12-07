package de.deadlocker8.roadgame.logic;

public enum TileTypes
{
	ROAD_END(EdgeType.ROAD, EdgeType.GRASS, EdgeType.GRASS, EdgeType.GRASS),
	ROAD_STRAIGHT(EdgeType.ROAD, EdgeType.GRASS, EdgeType.ROAD, EdgeType.GRASS),
	ROAD_CURVE(EdgeType.ROAD, EdgeType.ROAD, EdgeType.GRASS, EdgeType.GRASS),
	ROAD_T_JUNCTION(EdgeType.ROAD, EdgeType.ROAD, EdgeType.ROAD, EdgeType.GRASS),			
	ROAD_X_JUNCTION(EdgeType.ROAD, EdgeType.ROAD, EdgeType.ROAD, EdgeType.ROAD);			
	
	private EdgeType N;
	private EdgeType E;
	private EdgeType S;
	private EdgeType W;
	
	private TileTypes(EdgeType n, EdgeType e, EdgeType s, EdgeType w)
	{
		N = n;
		E = e;
		S = s;
		W = w;
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
}