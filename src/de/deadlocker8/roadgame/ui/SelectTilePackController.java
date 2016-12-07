package de.deadlocker8.roadgame.ui;

import java.io.IOException;
import java.util.ArrayList;

import de.deadlocker8.roadgame.tilepacks.TilePack;
import de.deadlocker8.roadgame.tilepacks.TilePackDefault;
import de.deadlocker8.roadgame.tilepacks.TilePackTest;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logger.LogLevel;
import logger.Logger;

public class SelectTilePackController
{
	@FXML private ScrollPane scrollPane;
	@FXML private VBox vbox;
	@FXML private Button buttonBack;

	private Stage stage;	

	public void init(Stage stage, Image icon, Controller controller)
	{
		this.stage = stage;
		
		vbox.setSpacing(10.0);
		vbox.prefWidthProperty().bind(scrollPane.widthProperty().subtract(5.0));
		vbox.setAlignment(Pos.TOP_CENTER);
		
		ArrayList<TilePack> possibleTilePacks = new ArrayList<>();
		possibleTilePacks.add(new TilePackDefault());
		possibleTilePacks.add(new TilePackTest());		
		
		for(TilePack currentPack : possibleTilePacks)
		{			
			HBox hbox = new HBox();
			Label labelName = new Label(currentPack.getName());
			labelName.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
			
			Label labelTiles = new Label("(" + currentPack.getNumberOfTiles() + " Tiles)");
			labelTiles.setStyle("-fx-font-size: 16;");
			
			Button buttonShowTiles = new Button("Show Tiles");
			buttonShowTiles.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");		
			buttonShowTiles.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event)
				{
					try
					{
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/de/deadlocker8/roadgame/ui/PossibleTilesGUI.fxml"));
						Parent root = (Parent)fxmlLoader.load();
						Stage newStage = new Stage();
						newStage.initOwner(stage);
						newStage.initModality(Modality.APPLICATION_MODAL);
						newStage.setTitle("Possible Tiles");
						newStage.setScene(new Scene(root));
						newStage.getIcons().add(icon);
						newStage.setResizable(false);
						PossibleTilesController newController = fxmlLoader.getController();
						newController.init(newStage, controller, currentPack);
						newStage.show();

					}
					catch(IOException io)
					{
						Logger.log(LogLevel.ERROR, Logger.exceptionToString(io));
					}
					
				}
			});
			
			hbox.getChildren().add(labelName);
			Region r = new Region();
			hbox.getChildren().add(r);
			HBox.setHgrow(r, Priority.ALWAYS);
			hbox.getChildren().add(labelTiles);	
			hbox.getChildren().add(buttonShowTiles);
			HBox.setMargin(buttonShowTiles, new Insets(0, 0, 0, 25));
			hbox.setStyle("-fx-border-color: #212121; -fx-border-width: 1; -fx-background-color: #CCCCCC");
			hbox.setPadding(new Insets(5));
			vbox.getChildren().add(hbox);
			
			hbox.setOnMouseClicked(new EventHandler<MouseEvent>()
			{
				@Override
				public void handle(MouseEvent event)
				{
					if(event.getButton().equals(MouseButton.PRIMARY))
					{
						controller.setTilePack(currentPack);
						stage.close();
					}				
				}
			});
		}
	}
	
	public void back()
	{
		stage.close();
	}
}