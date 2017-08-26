package aData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;

import zClient.GameClient;
import zServer.GameServer;
import entities.Player;

public class GameServerData {
	
	//Entities
	public volatile static Player players[] = new Player[general.maxUsers];
	
	//Info
	public static volatile String username="";
	public static volatile String password="";
	
	public static volatile GameServer gameServer;
	
	//Server
	public static volatile int ServerPort=6969;
	public static volatile int bufferSize=30;
	public static volatile String ip;
	
	//
	public static volatile InetAddress out[] = new InetAddress[general.maxUsers];
	public static volatile int port[] = new int[general.maxUsers];
	
	//rip data hello server optimization??? pls make change
	public static volatile String lastUsername[][] = new String[general.maxUsers][general.maxUsers];
	public static volatile String lastMap[][] = new String[general.maxUsers][general.maxUsers];
	public static volatile int lastCharacter[][] = new int[general.maxUsers][general.maxUsers];
	public static volatile int lastHealth[][] = new int[general.maxUsers][general.maxUsers];
	public static volatile int lastMaxHealth[][] = new int[general.maxUsers][general.maxUsers];
	public static volatile float lastX[][] = new float[general.maxUsers][general.maxUsers];
	public static volatile float lastY[][] = new float[general.maxUsers][general.maxUsers];
	public static volatile double lastAnim[][] = new double[general.maxUsers][general.maxUsers];
	
	public static void getRouterIP(){
		try{
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			System.out.println("External Server Ip: "+in.readLine());
			System.out.println("Internal Server Ip: "+InetAddress.getLocalHost().getHostAddress());
		}catch(Exception ex){
			try{
				System.out.println("External Server Ip: No IPAdress found");
				System.out.println("Internal Server Ip: "+InetAddress.getLocalHost().getHostAddress());
			}catch(Exception ex1){
				System.out.println("External Server Ip: No IPAddress found");
				System.out.println("Internal Server Ip: No IPAddress found");
			}
		}
	}
	
}
