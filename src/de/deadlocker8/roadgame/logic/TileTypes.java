package de.deadlocker8.roadgame.logic;

public enum TileTypes
{
	ROAD_END(EdgeType.ROAD, EdgeType.GRASS, EdgeType.GRASS, EdgeType.GRASS, CenterType.EMPTY),
	ROAD_STRAIGHT(EdgeType.ROAD, EdgeType.GRASS, EdgeType.ROAD, EdgeType.GRASS, CenterType.EMPTY),
	ROAD_CURVE(EdgeType.ROAD, EdgeType.ROAD, EdgeType.GRASS, EdgeType.GRASS, CenterType.EMPTY),
	ROAD_T_JUNCTION(EdgeType.ROAD, EdgeType.ROAD, EdgeType.ROAD, EdgeType.GRASS, CenterType.EMPTY),			
	ROAD_X_JUNCTION(EdgeType.ROAD, EdgeType.ROAD, EdgeType.ROAD, EdgeType.ROAD, CenterType.EMPTY),	
	
	CASTLE_SINGLE(EdgeType.CASTLE, EdgeType.GRASS, EdgeType.GRASS, EdgeType.GRASS,CenterType.EMPTY),
	CASTLE_TWO_SINGLE_OPPOSITE(EdgeType.CASTLE, EdgeType.GRASS, EdgeType.CASTLE, EdgeType.GRASS, CenterType.EMPTY),
	CASTLE_TWO_SINGLE_NEIGHBOR(EdgeType.CASTLE, EdgeType.CASTLE, EdgeType.GRASS, EdgeType.GRASS, CenterType.EMPTY),
	CASTLE_THREE_SINGLE(EdgeType.CASTLE, EdgeType.CASTLE, EdgeType.CASTLE, EdgeType.GRASS, CenterType.EMPTY),
	CASTLE_FOUR_SINGLE(EdgeType.CASTLE, EdgeType.CASTLE, EdgeType.CASTLE, EdgeType.CASTLE, CenterType.EMPTY),
	
	CASTLE_TRIANGLE(EdgeType.CASTLE, EdgeType.CASTLE, EdgeType.GRASS, EdgeType.GRASS, CenterType.CASTLE_TRIANGLE),
	CASTLE_TUBE(EdgeType.CASTLE, EdgeType.GRASS, EdgeType.CASTLE, EdgeType.GRASS, CenterType.CASTLE),
	CASTLE_FULL(EdgeType.CASTLE, EdgeType.CASTLE, EdgeType.CASTLE, EdgeType.CASTLE, CenterType.CASTLE),
	
	CASTLE_SINGLE_ROAD_STRAIGHT(EdgeType.CASTLE, EdgeType.ROAD, EdgeType.GRASS, EdgeType.ROAD,CenterType.EMPTY),
	CASTLE_SINGLE_ROAD_CURVE_RIGHT(EdgeType.CASTLE, EdgeType.ROAD, EdgeType.ROAD, EdgeType.GRASS,CenterType.EMPTY),
	CASTLE_SINGLE_ROAD_CURVE_LEFT(EdgeType.CASTLE, EdgeType.GRASS, EdgeType.ROAD, EdgeType.ROAD,CenterType.EMPTY),
	CASTLE_SINGLE_ROAD_T_JUNCTION(EdgeType.CASTLE, EdgeType.ROAD, EdgeType.ROAD, EdgeType.ROAD,CenterType.EMPTY),
	
	CASTLE_TWO_SINGLE_OPPOSITE_ROAD_STRAIGHT(EdgeType.CASTLE, EdgeType.ROAD, EdgeType.CASTLE, EdgeType.ROAD, CenterType.EMPTY),	
	CASTLE_TWO_SINGLE_NEIGHBOR_ROAD_CURVE(EdgeType.CASTLE, EdgeType.CASTLE, EdgeType.ROAD, EdgeType.ROAD, CenterType.EMPTY),
	CASTLE_THREE_SINGLE_ROAD_END(EdgeType.CASTLE, EdgeType.CASTLE, EdgeType.CASTLE, EdgeType.ROAD, CenterType.EMPTY),
	
	CASTLE_TRIANGLE_ROAD_CURVE(EdgeType.CASTLE, EdgeType.CASTLE, EdgeType.ROAD, EdgeType.ROAD, CenterType.CASTLE_TRIANGLE),
	CASTLE_TRIANGLE_ROAD_RIGHT(EdgeType.CASTLE, EdgeType.CASTLE, EdgeType.ROAD, EdgeType.GRASS, CenterType.CASTLE_TRIANGLE),
	CASTLE_TRIANGLE_ROAD_LEFT(EdgeType.CASTLE, EdgeType.CASTLE, EdgeType.GRASS, EdgeType.ROAD, CenterType.CASTLE_TRIANGLE),
	CASTLE_TUBE_ROAD_ONE_SIDE(EdgeType.CASTLE, EdgeType.ROAD, EdgeType.CASTLE, EdgeType.GRASS, CenterType.CASTLE),
	CASTLE_TUBE_ROAD_TWO_SIDE(EdgeType.CASTLE, EdgeType.ROAD, EdgeType.CASTLE, EdgeType.ROAD, CenterType.CASTLE);
	
	
	private EdgeType N;
	private EdgeType E;
	private EdgeType S;
	private EdgeType W;
	private CenterType C;
		
	private TileTypes(EdgeType n, EdgeType e, EdgeType s, EdgeType w, CenterType c)
	{
		N = n;
		E = e;
		S = s;
		W = w;
		C = c;
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
}