package de.deadlocker8.roadgame.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import de.deadlocker8.roadgame.logic.Board;
import de.deadlocker8.roadgame.logic.EdgeType;
import de.deadlocker8.roadgame.logic.Game;
import de.deadlocker8.roadgame.logic.Textures;
import de.deadlocker8.roadgame.logic.Tile;
import de.deadlocker8.roadgame.tilepacks.TilePack;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import logger.LogLevel;
import logger.Logger;

public class Controller
{
	@FXML private AnchorPane anchorPaneGame;
	@FXML private StackPane stackPaneCurrentTile;
	@FXML private Button buttonRotate;
	@FXML private Label labelTilesRemaining;
	
	private Stage stage;
	private Image icon = new Image("de/deadlocker8/roadgame/resources/icon.png");
	private final ResourceBundle bundle = ResourceBundle.getBundle("de/deadlocker8/roadgame/main/", Locale.GERMANY);
	private GridPane grid;
	private Game game;
	private Textures textures;
	private ZoomableScrollPane scrollPane;
	private StackPane stackPanePlaceHolder;
	
	public void init(Stage stage)
	{
		this.stage = stage;
		textures = new Textures();

		anchorPaneGame.setStyle("-fx-border-color: #333333; -fx-border-width: 2px");
		stackPaneCurrentTile.setStyle("-fx-border-color: #333333; -fx-border-width: 2px");
		
		labelTilesRemaining.setText("0");
	
		grid = new GridPane();
		grid.setFocusTraversable(false);	
		
		scrollPane = new ZoomableScrollPane(grid);		
		scrollPane.setPannable(true);		
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);		
		anchorPaneGame.getChildren().add(scrollPane);
		AnchorPane.setTopAnchor(scrollPane, 0.0);
		AnchorPane.setRightAnchor(scrollPane, 0.0);
		AnchorPane.setBottomAnchor(scrollPane, 0.0);
		AnchorPane.setLeftAnchor(scrollPane, 0.0);		
		
		anchorPaneGame.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				if(event.getButton().equals(MouseButton.SECONDARY))
				{
					rotateRight();
				}				
			}
		});
		
		buttonRotate.setDisable(true);
		Label labelPlaceHolder = new Label("Please select Tilepack first.");
		labelPlaceHolder.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		stackPanePlaceHolder = new StackPane();
		stackPanePlaceHolder.getChildren().add(labelPlaceHolder);
		anchorPaneGame.getChildren().add(stackPanePlaceHolder);
		AnchorPane.setTopAnchor(stackPanePlaceHolder, 0.0);
		AnchorPane.setRightAnchor(stackPanePlaceHolder, 0.0);
		AnchorPane.setBottomAnchor(stackPanePlaceHolder, 0.0);
		AnchorPane.setLeftAnchor(stackPanePlaceHolder, 0.0);
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

	public StackPane createStackPaneForTile(Tile tile, boolean possible, int x, int y)
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

	public ImageView getImageForEdge(EdgeType edgeType)
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
	
	public ImageView getImageForCenter(Tile tile)
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
			case ROAD:
				ImageView ivRoad = new ImageView(textures.getImageCenterRoad());				
				
				if(tile.getE().equals(EdgeType.CASTLE))
				{				
					ivRoad.setRotate(90);
				}
				
				if(tile.getS().equals(EdgeType.CASTLE))
				{					
					ivRoad.setRotate(180);
				}
				
				if(tile.getW().equals(EdgeType.CASTLE))
				{					
					ivRoad.setRotate(270);
				}
				return ivRoad;
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
		Tile nextTile = game.getNextTile();
		if(nextTile == null)		
		{			
			game.setCurrentTile(null);
			stackPaneCurrentTile.getChildren().clear();			
			updateGrid(game.getBoard(), null);
			return;
		}
			
		game.setCurrentTile(nextTile);

		stackPaneCurrentTile.getChildren().clear();
		stackPaneCurrentTile.getChildren().add(createStackPaneForTile(game.getCurrentTile(), false, 0, 0));
		
		labelTilesRemaining.setText(String.valueOf(game.getBoard().getTilePack().getNumberOfTiles()));

		updateGrid(game.getBoard(), game.getPossibleLocations(game.getCurrentTile()));			
	}

	public void rotateRight()
	{
		if(game != null && game.getCurrentTile() != null)
		{
			game.getCurrentTile().rotateRight();
			stackPaneCurrentTile.getChildren().clear();
			stackPaneCurrentTile.getChildren().add(createStackPaneForTile(game.getCurrentTile(), false, 0, 0));
	
			updateGrid(game.getBoard(), game.getPossibleLocations(game.getCurrentTile()));
		}
	}
	
	public void reset()
	{
		init(stage);
	}
	
	public void selectTilePack()
	{
		try
		{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/de/deadlocker8/roadgame/ui/SelectTilePackGUI.fxml"));
			Parent root = (Parent)fxmlLoader.load();
			Stage newStage = new Stage();
			newStage.initOwner(stage);
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setTitle("Select Tilepack");
			newStage.setScene(new Scene(root));
			newStage.getIcons().add(icon);
			newStage.setResizable(false);
			SelectTilePackController newController = fxmlLoader.getController();
			newController.init(newStage, icon, this);
			newStage.show();

		}
		catch(IOException io)
		{
			Logger.log(LogLevel.ERROR, Logger.exceptionToString(io));
		}
	}
	
	public void setTilePack(TilePack tilePack)
	{
		buttonRotate.setDisable(false);
		if(anchorPaneGame.getChildren().contains(stackPanePlaceHolder))
		{
			anchorPaneGame.getChildren().remove(stackPanePlaceHolder);
		}
		
		game = new Game(tilePack);
		
		updateGrid(game.getBoard(), null);

		nextTile();
	}

	public void about()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About " + bundle.getString("app.name"));
		alert.setHeaderText(bundle.getString("app.name"));
		alert.setContentText("Version:     " + bundle.getString("version.name") + "\r\nDate:         " + bundle.getString("version.date") + "\r\nAuthor:      Robert Goldmann\r\n");
		Stage dialogStage = (Stage)alert.getDialogPane().getScene().getWindow();
		dialogStage.getIcons().add(icon);
		dialogStage.centerOnScreen();
		alert.showAndWait();
	}
}