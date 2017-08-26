package aData;

import zClient.GameClient;
import entities.Player;

public class GameClientData {

	public static volatile GameClient gameClient;
	
	//Entities
	public volatile static Player players[] = new Player[general.maxUsers];
	
}
