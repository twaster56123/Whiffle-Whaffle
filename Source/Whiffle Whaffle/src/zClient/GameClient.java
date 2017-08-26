package zClient;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import aData.GameClientData;
import aData.GameServerData;
import aData.general;
import entities.Player;

public class GameClient extends GameClientHandler{
	
	DatagramSocket sock;
	byte[] buffer;
	DatagramPacket inc;
	
	public GameClient(){
	
		try{
			
			sock = new DatagramSocket(6669);
			buffer = new byte[GameServerData.bufferSize];
			inc = new DatagramPacket(buffer, buffer.length);
			
		}catch(Exception ex){
			System.err.println(ex.getLocalizedMessage());
		}
		
	}
	
	public void send(String text){
		
		try{
			DatagramPacket dp = new DatagramPacket(text.getBytes() , text.getBytes().length , InetAddress.getByName(GameServerData.ip) , GameServerData.ServerPort);
			sock.send(dp);
		}catch(Exception ex){
			System.out.println(ex.getLocalizedMessage());
		}
		
	}
	
	public String getInput(){
		
		try{
			sock.receive(inc);	
			byte[] data = inc.getData();
			String s = new String(data, 0, inc.getLength());
			return(s);	
		}catch(Exception ex){
			System.err.println(ex.getLocalizedMessage());
		}
		return("");
		
	}
	
	public void run(){
		process();
	}
	
}
