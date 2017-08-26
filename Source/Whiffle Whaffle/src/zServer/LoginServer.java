/*
 * Eric Purvis
 */
package zServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import entities.Player;

import aData.GameServerData;
import aData.LoginServerData;

public class LoginServer extends GameServerData{

	private String path="res/users/";
	
	private Socket sock;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public int id=0;
	
	public LoginServer(){
		
		try{
			refresh();
			reader = new BufferedReader(new InputStreamReader(sock.getInputStream(), "UTF-8"));
			writer = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(), "UTF-8"));
		}catch(Exception ex){
			if(players[id]!=null){
				players[id].username="//toBeRemoved//";
				System.err.println(ex.getLocalizedMessage());
			}
		}
		
	}
	
	public static void bind(){
		try{
			LoginServerData.ss = new ServerSocket(LoginServerData.port);
		}catch(Exception ex){}
	}
	
	public void refresh(){
		try{
			sock=LoginServerData.ss.accept();
		}catch(Exception ex){}
	}
	
	public void run(){
		
		String input=getInput();
		//Check Online Status
		if(input.equals("you okay?")){
			send("ya bruh");
			flush();
		}
		//Attempt Register
		if(input.equals("RegUser!")){
			String username=getInput();
			String password=getInput();
			try{
				//success
				File playerFolder = new File(path+username);
				if(!playerFolder.exists()){
					playerFolder.mkdir();
					File player = new File(path+username+"/info.txt");
					FileWriter fw = new FileWriter(player);
					BufferedWriter w = new BufferedWriter(fw);
					w.write(password+"\n");
					w.flush();
					w.close();
					
					send("good");
					flush();
				}else{
					//fail
					send("taken");
					flush();
				}
				
			}catch(Exception ex){
				//fail
				send("taken");
				flush();
			}
			
		}
		
		//Login 
		if(input.equals("login Time!")){
			String username=getInput();
			String password=getInput();
			File file = new File(path+username+"/info.txt");
			if(file.exists()){
				try{
					Scanner in = new Scanner(file);
					if(in.nextLine().equals(password)){
						send("loginSuccess");
						send(""+id);
						flush();	
					}else{
						send("loginFailed");
						flush();
					}
					in.close();
				}catch(Exception ex){
					System.out.println(ex.getLocalizedMessage());
					send("loginFailed");
					flush();
				}
			}else{
				send("loginFailed");
				flush();
			}
			
		}
		
	}
	
	public void send(String msg){
		try{
			writer.write(msg+"\n");
		}catch(Exception ex){}
	}
	
	public void flush(){
		try{
			writer.flush();
		}catch(Exception ex){}
	}
	
	public String getInput(){
		try{
			return ( reader.readLine() );
		}catch(Exception ex){
			refresh();
		}
		return("");
	}
	
}
