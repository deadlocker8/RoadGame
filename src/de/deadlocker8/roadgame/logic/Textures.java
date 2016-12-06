package de.deadlocker8.roadgame.logic;

import javafx.scene.image.Image;

public class Textures
{
	private Image imageEmpty = new Image("de/deadlocker8/roadgame/resources/empty.png");
	private Image imageBorder= new Image("de/deadlocker8/roadgame/resources/border.png");
	private Image imageGrass = new Image("de/deadlocker8/roadgame/resources/grass.png");
	private Image imageRoad = new Image("de/deadlocker8/roadgame/resources/road.png");
	
	public Textures()
	{
		
	}

	public Image getImageEmpty()
	{
		return imageEmpty;
	}

	public Image getImageBorder()
	{
		return imageBorder;
	}

	public Image getImageGrass()
	{
		return imageGrass;
	}

	public Image getImageRoad()
	{
		return imageRoad;
	}
}