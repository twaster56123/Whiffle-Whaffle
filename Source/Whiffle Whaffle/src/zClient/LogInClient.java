/*
 * Eric Purvis
 */
package zClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import aData.LoginServerData;

public class LogInClient {

	Socket sock;
	BufferedWriter writer;
	BufferedReader reader;
	
	public LogInClient(String ip){
		
		try{
			sock = new Socket(ip, LoginServerData.port);
			reader = new BufferedReader(new InputStreamReader(sock.getInputStream(), "UTF-8"));
			writer = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(), "UTF-8"));
			checkServer();
		}catch(Exception ex){
			System.err.println("LogInClient Init Failed!");
		}
		
	}
	
	public void checkServer(){
		try{
			send("you okay?");
			flush();
			if(getInput().equals("ya bruh")){
				LoginServerData.status=true;
			}
		}catch(Exception ex){}
	}
	
	public void send(String msg) throws Exception{
		writer.write(msg+"\n");
	}
	
	public void flush() throws Exception{
		writer.flush();
	}
	
	public String getInput() throws Exception{
		return ( reader.readLine() );
	}
	
}
