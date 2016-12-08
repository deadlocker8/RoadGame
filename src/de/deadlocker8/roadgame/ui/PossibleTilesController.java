package de.deadlocker8.roadgame.ui;

import de.deadlocker8.roadgame.logic.Tile;
import de.deadlocker8.roadgame.logic.TileType;
import de.deadlocker8.roadgame.tilepacks.TilePack;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PossibleTilesController
{
	@FXML private ScrollPane scrollPane;
	@FXML private FlowPane flowPane;
	@FXML private Button buttonBack;

	private Stage stage;	

	public void init(Stage stage, Controller controller, TilePack tilePack)
	{
		this.stage = stage;
		
		flowPane.setVgap(25);
	    flowPane.setHgap(25);
	    
	    flowPane.prefWidthProperty().bind(scrollPane.widthProperty());
	    flowPane.prefHeightProperty().bind(scrollPane.heightProperty());
		
		for(TileType key : tilePack.getTiles().keySet())
		{
			HBox hbox = new HBox();
			hbox.setAlignment(Pos.CENTER);
			
			Tile tile = new Tile(key);
			StackPane currentStack = controller.createStackPaneForTile(tile, false, 0, 0);
			currentStack.setMaxWidth(100.0);
			
			Label labelTimes = new Label("x" + tilePack.getTiles().get(key));
			labelTimes.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
			
			hbox.getChildren().add(currentStack);			
			hbox.getChildren().add(labelTimes);
			HBox.setMargin(labelTimes, new Insets(0, 0, 0, 25));
			flowPane.getChildren().add(hbox);
		}
	}
	
	public void back()
	{
		stage.close();
	}
}