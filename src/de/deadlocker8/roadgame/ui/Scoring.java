package de.deadlocker8.roadgame.ui;

import de.deadlocker8.roadgame.logic.Board;
import de.deadlocker8.roadgame.logic.CenterType;
import de.deadlocker8.roadgame.logic.EdgeType;
import de.deadlocker8.roadgame.logic.ScorePosition;
import de.deadlocker8.roadgame.logic.Tile;
import de.deadlocker8.roadgame.logic.TileType;
import de.deadlocker8.roadgame.tilepacks.TilePackOneTile;
import javafx.geometry.Point2D;

public class Scoring
{
	private Tile tileToScore;
	private Tile tile;
	private Board board;
	private int x;
	private int y;
	private int points;

	public Scoring(Tile tile, Board board)
	{
		this.tile = tile;
		this.board = board;
		Point2D position = tile.getPosition();
		this.x = (int)position.getX();
		this.y = (int)position.getY();
		points = 1;
		tileToScore = tile;
		this.tile = tile;
	}
	
	private void setTile(Tile nextTile)
	{
		this.tile = nextTile;
		this.x = (int)nextTile.getPosition().getX();
		this.y = (int)nextTile.getPosition().getY();
	}

	public int getScoreForTile(ScorePosition scorePosition)
	{
		switch(scorePosition)
		{
			case CENTER:
				scoreCenter();
				break;
			case NORTH:
				scoreEdge(tile.getN(), scorePosition);
				break;
			case EAST:
				scoreEdge(tile.getE(), scorePosition);
				break;
			case SOUTH:
				scoreEdge(tile.getS(), scorePosition);
				break;
			case WEST:
				scoreEdge(tile.getW(), scorePosition);
				break;
			default:
				break;
		}

		return points;
	}

	private void scoreEdge(EdgeType edgeType, ScorePosition scorePosition)
	{
		switch(edgeType)
		{
			case CASTLE:
				scoreCastle(scorePosition);
				break;
			case CASTLE_END:
				scoreCastle(scorePosition);
				break;
			// TODO
			case GRASS:
				break;
			case ROAD:
				scoreRoad(scorePosition);
				break;
			default:
				break;
		}
	}

	private void scoreCenter()
	{
		switch(tile.getC())
		{
			case CASTLE:
				scoreCastle(null);
				break;
			case CASTLE_TRIANGLE:
				scoreCastle(null);
				break;
			case CHURCH:
				scoreChurch();
				break;
			case JUNCTION:
				scoreRoad(null);
				break;
			case ROAD:
				scoreRoad(null);
				break;
			default:
				break;
		}
	}

	private void scoreChurch()
	{
		// North
		points = board.containsTileAtPosition(x, y - 1) ? points += 1 : points;

		// North-East
		points = board.containsTileAtPosition(x + 1, y - 1) ? points += 1 : points;

		// East
		points = board.containsTileAtPosition(x + 1, y) ? points += 1 : points;

		// South-East
		points = board.containsTileAtPosition(x + 1, y + 1) ? points += 1 : points;

		// South
		points = board.containsTileAtPosition(x, y + 1) ? points += 1 : points;

		// South-West
		points = board.containsTileAtPosition(x - 1, y + 1) ? points += 1 : points;

		// West
		points = board.containsTileAtPosition(x - 1, y) ? points += 1 : points;

		// North-West
		points = board.containsTileAtPosition(x - 1, y - 1) ? points += 1 : points;
	}

	private void scoreRoad(ScorePosition scorePosition)
	{		
		switch(scorePosition)
		{
			case NORTH:
				if(board.containsTileAtPosition(x, y - 1))
				{
					Tile nextTile = board.getTile(x, y - 1);
					if(nextTile.getS().equals(tile.getN()))
					{
						if(nextTile.getC().equals(CenterType.EMPTY))
						{
							if(nextTile.getN().equals(EdgeType.ROAD))
							{
								setTile(nextTile);
								scoreRoad(ScorePosition.NORTH);
								return;
							}

							if(nextTile.getE().equals(EdgeType.ROAD))
							{
								setTile(nextTile);
								scoreRoad(ScorePosition.EAST);
								return;
							}

							if(nextTile.getW().equals(EdgeType.ROAD))
							{
								setTile(nextTile);
								scoreRoad(ScorePosition.WEST);
								return;
							}
						}
						else
						{							
							points++;
						}
					}
					else
					{
						points++;
					}
				}
				break;
			case EAST:
				if(board.containsTileAtPosition(x + 1, y))
				{
					Tile nextTile = board.getTile(x + 1, y);
					if(nextTile.getW().equals(tile.getE()))
					{
						if(nextTile.getC().equals(CenterType.EMPTY))
						{
							if(nextTile.getN().equals(EdgeType.ROAD))
							{
								setTile(nextTile);
								scoreRoad(ScorePosition.NORTH);
								return;
							}

							if(nextTile.getE().equals(EdgeType.ROAD))
							{
								setTile(nextTile);
								scoreRoad(ScorePosition.EAST);
								return;
							}

							if(nextTile.getS().equals(EdgeType.ROAD))
							{
								setTile(nextTile);
								scoreRoad(ScorePosition.SOUTH);
								return;
							}
						}
						else
						{
							points++;
						}
					}
					else
					{
						points++;
					}
				}
				break;
			case SOUTH:
				if(board.containsTileAtPosition(x, y + 1))
				{
					Tile nextTile = board.getTile(x, y + 1);
					if(nextTile.getN().equals(tile.getS()))
					{
						if(nextTile.getC().equals(CenterType.EMPTY))
						{
							if(nextTile.getE().equals(EdgeType.ROAD))
							{
								setTile(nextTile);
								scoreRoad(ScorePosition.EAST);
								return;
							}

							if(nextTile.getS().equals(EdgeType.ROAD))
							{
								setTile(nextTile);
								scoreRoad(ScorePosition.SOUTH);
								return;
							}

							if(nextTile.getW().equals(EdgeType.ROAD))
							{
								setTile(nextTile);
								scoreRoad(ScorePosition.WEST);
								return;
							}
						}
						else
						{
							points++;
						}
					}
					else
					{
						points++;
					}
				}
				break;
			case WEST:
				if(board.containsTileAtPosition(x - 1, y))
				{
					Tile nextTile = board.getTile(x - 1, y);
					if(nextTile.getN().equals(tile.getS()))
					{
						if(nextTile.getC().equals(CenterType.EMPTY))
						{
							if(nextTile.getE().equals(EdgeType.ROAD))
							{
								setTile(nextTile);
								scoreRoad(ScorePosition.EAST);
								return;
							}

							if(nextTile.getS().equals(EdgeType.ROAD))
							{
								setTile(nextTile);
								scoreRoad(ScorePosition.SOUTH);
								return;
							}

							if(nextTile.getN().equals(EdgeType.ROAD))
							{
								setTile(nextTile);
								scoreRoad(ScorePosition.NORTH);
								return;
							}
						}
						else
						{
							points++;
						}
					}
					else
					{
						points++;
					}
				}
				break;
			default:
				break;
		}
	}

	private void scoreCastle(ScorePosition scorePosition)
	{

	}

	public static void main(String[] args)
	{
		Board b = new Board(new TilePackOneTile());
		Scoring s = new Scoring(b.getTile(0, 0), b);

		Tile t = new Tile(TileType.ROAD_STRAIGHT);
		t.setPosition(new Point2D(0, -1));
		if(!t.getN().equals(EdgeType.ROAD))
		{
			t.rotateRight();
		}

		b.addTile(t);
		System.out.println(b.getTile(0, 0));
		System.out.println(s.getScoreForTile(ScorePosition.NORTH));
	}
}