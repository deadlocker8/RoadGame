package de.deadlocker8.roadgame.ui;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.generic.IfInstruction;

import de.deadlocker8.roadgame.logic.Board;
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
		
		int width = board.getWidth();
		int height = board.getHeight();
		
		//fill outer rim with void
		for(int x = 0; x < width + 2; x++)
		{
			for(int y = 0; y < height + 2; y++)
			{
				if(x == 0 || y == 0 || x == width + 1 || y == height + 1)
				{
					Label label = new Label("X");
					if(possibleLocations != null)
					{
						//TODO x , y is alwasy positive, but possible locations can be negative --> transform coordinates
						if(isInPossibleLocations(possibleLocations, x, y))
						{
							label.setStyle("-fx-background-color: red");
						}
					}
					
					grid.add(label, y, x);
					
				}
				else
				{		
					Tile tile = board.getTile(x-1, y-1);
					if(tile != null)
					{					
						grid.add(new Label(board.getTile(x-1, y-1).toShortString()), y, x);
					}
					else
					{
						Label label = new Label("X");
						if(possibleLocations != null)
						{
							if(isInPossibleLocations(possibleLocations, x, y))
							{
								label.setStyle("-fx-background-color: red");
							}
						}
						
						grid.add(label, y, x);
					}
				}	
			}
		}		
	}
	
	private boolean isInPossibleLocations(ArrayList<Point2D> possibleLocations, int x, int y)
	{
		for(Point2D currentPoint : possibleLocations)
		{
			if((int)currentPoint.getX() == x && (int)currentPoint.getY() == y)
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
		stackPaneCurrentTile.getChildren().add(new Label(game.getCurrentTile().toShortString()));
		
		System.out.println(game.getPossibleLocations(game.getCurrentTile()));
		updateGrid(game.getBoard(), game.getPossibleLocations(game.getCurrentTile()));
	}
	
	public void rotateRight()
	{
	
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