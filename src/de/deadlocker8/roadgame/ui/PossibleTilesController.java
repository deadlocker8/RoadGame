package de.deadlocker8.roadgame.ui;

import de.deadlocker8.roadgame.logic.Tile;
import de.deadlocker8.roadgame.logic.TileType;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PossibleTilesController
{
	@FXML private VBox vbox;
	@FXML private Button buttonBack;

	private Stage stage;	

	public void init(Stage stage, Controller controller)
	{
		this.stage = stage;
		
		vbox.setSpacing(10.0);
		vbox.setAlignment(Pos.CENTER);
		
		for(TileType currentType : TileType.values())
		{
			Tile tile = new Tile(currentType);
			StackPane currentStack = controller.createStackPaneForTile(tile, false, 0, 0);
			currentStack.setMaxWidth(100.0);
			vbox.getChildren().add(currentStack);
		}
	}
	
	public void back()
	{
		stage.close();
	}
}