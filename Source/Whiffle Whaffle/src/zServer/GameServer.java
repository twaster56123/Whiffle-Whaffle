package zServer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import aData.GameServerData;
import aData.general;
import entities.Player;

public class GameServer extends GameServerHandler{

	static DatagramSocket sock;
	static byte[] buffer;
	static DatagramPacket inc;
	
	public GameServer(){
	
		try{
			
			sock = new DatagramSocket(GameServerData.ServerPort);
			buffer = new byte[GameServerData.bufferSize];
			inc = new DatagramPacket(buffer, buffer.length);
			
		}catch(Exception ex){
			System.err.println(ex.getLocalizedMessage());
		}
		
	}
	
	public void send(String text, InetAddress returnAddress, int returnPort){
		
		try{
			DatagramPacket dp = new DatagramPacket(text.getBytes() , text.getBytes().length , returnAddress , returnPort);
			sock.send(dp);
		}catch(Exception ex){}
		
	}
	
	public static DatagramPacket getInput(){
		
		try{
			sock.receive(inc);	
			return(inc);	
		}catch(Exception ex){}
		return(null);
		
	}
	
	public void run(){
		process();
	}
	
}
