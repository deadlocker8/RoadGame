package de.deadlocker8.roadgame.ui;

import de.deadlocker8.roadgame.logic.Tile;
import de.deadlocker8.roadgame.logic.TileType;
import de.deadlocker8.roadgame.tilepacks.TilePack;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PossibleTilesController
{
	@FXML private VBox vbox;
	@FXML private Button buttonBack;

	private Stage stage;	

	public void init(Stage stage, Controller controller, TilePack tilePack)
	{
		this.stage = stage;
		
		vbox.setSpacing(10.0);
		vbox.setAlignment(Pos.TOP_CENTER);
		
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
			vbox.getChildren().add(hbox);
		}
	}
	
	public void back()
	{
		stage.close();
	}
}