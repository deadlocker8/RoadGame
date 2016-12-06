package de.deadlocker8.roadgame.ui;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import de.deadlocker8.roadgame.logic.Board;
import de.deadlocker8.roadgame.logic.EdgeType;
import de.deadlocker8.roadgame.logic.Game;
import de.deadlocker8.roadgame.logic.Tile;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tools.Worker;

public class Controller
{
	@FXML private AnchorPane anchorPaneGame;
	@FXML private StackPane stackPaneCurrentTile;
	@FXML private Button buttonRotate;

	private Stage stage;
	private Image icon = new Image("de/deadlocker8/roadgame/resources/icon.png");
	private final ResourceBundle bundle = ResourceBundle.getBundle("de/deadlocker8/roadgame/main/", Locale.GERMANY);
	private GridPane grid;
	private Game game;	

	public void init(Stage stage)
	{
		this.stage = stage;

		stage.setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			public void handle(WindowEvent event)
			{
				Worker.shutdown();
				System.exit(0);
			};
		});
		
		anchorPaneGame.setStyle("-fx-border-color: #333333; -fx-border-width: 2px");
		stackPaneCurrentTile.setStyle("-fx-border-color: #333333; -fx-border-width: 2px");
		
		grid = new GridPane();
		anchorPaneGame.getChildren().add(grid);
		AnchorPane.setTopAnchor(grid, 0.0);
		AnchorPane.setRightAnchor(grid, 0.0);
		AnchorPane.setBottomAnchor(grid, 0.0);
		AnchorPane.setLeftAnchor(grid, 0.0);
		
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
					System.out.println("öhm: " +( x - (int)center.getX() )+"    "+ (y - (int)center.getY() ));
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
		System.out.println(x + "  " + y);
		game.placeTile(game.getCurrentTile(), new Point2D(x, y));
		nextTile();
	}
	
	private StackPane createStackPaneForTile(Tile tile, boolean possible, int x, int y)
	{
		StackPane stack = new StackPane();	
		
		stack.getChildren().add(new ImageView(new Image("de/deadlocker8/roadgame/resources/empty.png")));
		
		if(tile == null)
		{
			if(possible)
			{
				stack.getChildren().add(new ImageView(new Image("de/deadlocker8/roadgame/resources/border.png")));	
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
			stack.getChildren().add(new ImageView(new Image("de/deadlocker8/roadgame/resources/green.png")));	
			
			if(tile.getN().equals(EdgeType.ROAD))
			{			
				ImageView imageViewRoadNorth = new ImageView(new Image("de/deadlocker8/roadgame/resources/road.png"));					
				stack.getChildren().add(imageViewRoadNorth);
			}	
			
			if(tile.getE().equals(EdgeType.ROAD))
			{			
				ImageView imageViewRoadEast = new ImageView(new Image("de/deadlocker8/roadgame/resources/road.png"));	
				imageViewRoadEast.setRotate(90);
				stack.getChildren().add(imageViewRoadEast);
			}	
			
			if(tile.getS().equals(EdgeType.ROAD))
			{			
				ImageView imageViewRoadSouth = new ImageView(new Image("de/deadlocker8/roadgame/resources/road.png"));	
				imageViewRoadSouth.setRotate(180);
				stack.getChildren().add(imageViewRoadSouth);
			}	
			
			if(tile.getW().equals(EdgeType.ROAD))
			{			
				ImageView imageViewRoadWest = new ImageView(new Image("de/deadlocker8/roadgame/resources/road.png"));	
				imageViewRoadWest.setRotate(270);
				stack.getChildren().add(imageViewRoadWest);
			}	
		}		
		
		return stack;
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
		System.out.println(game.getBoard());
		
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