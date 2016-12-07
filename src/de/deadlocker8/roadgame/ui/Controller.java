package de.deadlocker8.roadgame.ui;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import de.deadlocker8.roadgame.logic.Board;
import de.deadlocker8.roadgame.logic.EdgeType;
import de.deadlocker8.roadgame.logic.Game;
import de.deadlocker8.roadgame.logic.Textures;
import de.deadlocker8.roadgame.logic.Tile;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Controller
{
	@FXML private AnchorPane anchorPaneGame;
	@FXML private StackPane stackPaneCurrentTile;
	@FXML private Button buttonRotate;
	@FXML private ScrollPane scrollPane;

	private Image icon = new Image("de/deadlocker8/roadgame/resources/icon.png");
	private final ResourceBundle bundle = ResourceBundle.getBundle("de/deadlocker8/roadgame/main/", Locale.GERMANY);
	private GridPane grid;
	private Game game;
	private Textures textures;

	public void init(Stage stage)
	{
		textures = new Textures();

		anchorPaneGame.setStyle("-fx-border-color: #333333; -fx-border-width: 2px");
		stackPaneCurrentTile.setStyle("-fx-border-color: #333333; -fx-border-width: 2px");

		grid = new GridPane();
		scrollPane.setContent(grid);

		game = new Game();

		updateGrid(game.getBoard(), null);

		nextTile();
	}

	private void updateGrid(Board board, ArrayList<Point2D> possibleLocations)
	{
		grid.getChildren().clear();

		int width = board.getDimension().getWidth();
		int height = board.getDimension().getHeight();

		Point2D center = board.getDimension().getCenterCoordinates();

		for(int x = 0; x < width + 2; x++)
		{
			for(int y = 0; y < height + 2; y++)
			{
				if(x == 0 || y == 0 || x == width + 1 || y == height + 1)
				{
					if(possibleLocations != null)
					{
						if(isInPossibleLocations(possibleLocations, center, x, y))
						{
							grid.add(createStackPaneForTile(null, true, -((int)center.getX() - x), -((int)center.getY() - y)), x, y);
						}
						else
						{
							grid.add(createStackPaneForTile(null, false, 0, 0), x, y);
						}
					}
					else
					{
						grid.add(createStackPaneForTile(null, false, 0, 0), x, y);
					}
				}
				else
				{
					Tile tile = board.getTile(x - (int)center.getX(), y - (int)center.getY());
					if(tile != null)
					{
						grid.add(createStackPaneForTile(tile, false, 0, 0), x, y);
					}
					else
					{
						if(possibleLocations != null)
						{
							if(isInPossibleLocations(possibleLocations, center, x, y))
							{
								grid.add(createStackPaneForTile(null, true, x - (int)center.getX(), y - (int)center.getY()), x, y);
							}
							else
							{
								grid.add(createStackPaneForTile(null, false, 0, 0), x, y);
							}
						}
						else
						{
							grid.add(createStackPaneForTile(null, false, 0, 0), x, y);
						}
					}
				}
			}
		}
	}

	private void placeTile(int x, int y)
	{
		game.placeTile(game.getCurrentTile(), new Point2D(x, y));
		nextTile();
	}

	private StackPane createStackPaneForTile(Tile tile, boolean possible, int x, int y)
	{
		StackPane stack = new StackPane();

		stack.getChildren().add(new ImageView(textures.getImageEmpty()));

		if(tile == null)
		{
			if(possible)
			{
				stack.getChildren().add(new ImageView(textures.getImageBorder()));
				stack.setOnMouseClicked(new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent event)
					{
						if(event.getButton().equals(MouseButton.PRIMARY))
						{
							placeTile(x, y);
						}
					}
				});
			}
		}
		else
		{
			stack.getChildren().add(new ImageView(textures.getImageGrass()));
			
			//North
			stack.getChildren().add(getImageForEdge(tile.getN()));	

			//East
			ImageView imageViewEast = getImageForEdge(tile.getE());
			imageViewEast.setRotate(90);
			stack.getChildren().add(imageViewEast);			

			//South
			ImageView imageViewSouth = getImageForEdge(tile.getS());
			imageViewSouth.setRotate(180);
			stack.getChildren().add(imageViewSouth);			

			//West			
			ImageView imageViewWest = getImageForEdge(tile.getW());
			imageViewWest.setRotate(270);
			stack.getChildren().add(imageViewWest);	
			
			//Center					
			ImageView imageViewCenter = getImageForCenter(tile);
			stack.getChildren().add(imageViewCenter);	
		}

		stack.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1px;");
		return stack;
	}

	private ImageView getImageForEdge(EdgeType edgeType)
	{
		switch(edgeType)
		{
			case GRASS:
				return new ImageView(textures.getImageEmpty());
			case ROAD:
				return new ImageView(textures.getImageRoad());			
			case CASTLE:
				return new ImageView(textures.getImageCastle());			
			default:
				return new ImageView(textures.getImageEmpty());
		}
	}
	
	private ImageView getImageForCenter(Tile tile)
	{		
		switch(tile.getC())
		{
			case EMPTY:
				return new ImageView(textures.getImageEmpty());
			case CASTLE:
				return new ImageView(textures.getImageCenterCastle());			
			case CASTLE_TRIANGLE:
				ImageView iv = new ImageView(textures.getImageCenterCastleTriangle());			
				if(tile.getN().equals(EdgeType.CASTLE))
				{
					if(tile.getW().equals(EdgeType.CASTLE))
					{
						//North and West
						iv.setRotate(270);
					}
				}
				
				if(tile.getS().equals(EdgeType.CASTLE))
				{					
					if(tile.getE().equals(EdgeType.CASTLE))
					{
						//South and East
						iv.setRotate(90);
					}
					else
					{
						//South and West
						iv.setRotate(180);
					}
				}
				
				return iv;
			case CHURCH:
				return new ImageView(textures.getImageCenterChurch());
			default:
				return new ImageView(textures.getImageEmpty());
		}
	}

	private boolean isInPossibleLocations(ArrayList<Point2D> possibleLocations, Point2D center, int x, int y)
	{
		for(Point2D currentPoint : possibleLocations)
		{
			int currentX = (int)center.getX() + (int)currentPoint.getX();
			int currentY = (int)center.getY() + (int)currentPoint.getY();
			if(currentX == x && currentY == y)
			{
				return true;
			}
		}

		return false;
	}

	private void nextTile()
	{
		game.setCurrentTile(game.getNextTile());

		stackPaneCurrentTile.getChildren().clear();
		stackPaneCurrentTile.getChildren().add(createStackPaneForTile(game.getCurrentTile(), false, 0, 0));

		updateGrid(game.getBoard(), game.getPossibleLocations(game.getCurrentTile()));
	}

	public void rotateRight()
	{
		game.getCurrentTile().rotateRight();
		stackPaneCurrentTile.getChildren().clear();
		stackPaneCurrentTile.getChildren().add(createStackPaneForTile(game.getCurrentTile(), false, 0, 0));

		updateGrid(game.getBoard(), game.getPossibleLocations(game.getCurrentTile()));
	}

	public void about()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Über " + bundle.getString("app.name"));
		alert.setHeaderText(bundle.getString("app.name"));
		alert.setContentText("Version:     " + bundle.getString("version.name") + "\r\nDatum:      " + bundle.getString("version.date") + "\r\nAutor:        Robert Goldmann\r\n");
		Stage dialogStage = (Stage)alert.getDialogPane().getScene().getWindow();
		dialogStage.getIcons().add(icon);
		dialogStage.centerOnScreen();
		alert.showAndWait();
	}
}