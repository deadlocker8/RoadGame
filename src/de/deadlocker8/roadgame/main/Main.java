package de.deadlocker8.roadgame.main;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import de.deadlocker8.roadgame.ui.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import logger.LogLevel;
import logger.Logger;

public class Main extends Application
{
	@Override
	public void start(Stage stage)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("de/deadlocker8/roadgame/ui/GUI.fxml"));
			Parent root = (Parent)loader.load();

			Scene scene = new Scene(root, 800, 600);		

			((Controller)loader.getController()).init(stage);

			stage.setResizable(true);
			stage.getIcons().add(new Image("de/deadlocker8/roadgame/resources/icon.png"));
			stage.setTitle("RoadGame");
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			Logger.log(LogLevel.ERROR, Logger.exceptionToString(e));
		}
	}

	public static void main(String[] args)
	{
		if(Arrays.asList(args).contains("debug"))
		{
			Logger.setLevel(LogLevel.ALL);
			Logger.log(LogLevel.INFO, "Running in Debug Mode");
		}
		else
		{
			Logger.setLevel(LogLevel.ERROR);
		}
		
		ResourceBundle bundle = ResourceBundle.getBundle("de/deadlocker8/roadgame/main/", Locale.GERMANY);			
		Logger.log(LogLevel.INFO, bundle.getString("app.name") + " - v" + bundle.getString("version.name") + " - (versioncode: " + bundle.getString("version.code") + ") from " + bundle.getString("version.date"));	

		launch(args);
	}
}