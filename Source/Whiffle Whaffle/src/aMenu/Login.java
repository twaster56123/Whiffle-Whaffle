package aMenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;

import javax.swing.JFrame;

import entities.aYourPlayer;

import aData.GameServerData;
import aData.LoginServerData;
import aData.general;
import aaMapEngine.Map;
import aaMapEngine.saveFile;

public class Login {
	
	public Login(){
		
		//objects
		final TextField user = new TextField(400/2-200/2, 100, 200, 25);
		final PasswordField pass = new PasswordField(400/2-200/2, 125, 200, 25);
		final Button submit = new Button(400/2-200/2, 175, 200, 25);
		final TextField failed = new TextField(400/2-200/2, 0, 200, 25);
		final TextField status = new TextField(400/2-200/2, 250, 200, 25);
		
		//Allow one
		LoginServerData.loginOpen=true;
		
		//Frame
		final Window win = new Window("Login");
		win.setBackground(Color.black);
		win.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		win.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				LoginServerData.loginOpen=false;
				win.dispose();
			}
		});
		
		//add
		win.add(user);
		win.add(pass);
		win.add(submit);
		win.add(failed);
		win.add(status);
		win.validate();
		
		//stuff
		user.setText("UserName");
		pass.setText("Password");
		submit.setText("Login");
		status.setText("Server: "+LoginServerData.status);
		failed.setEditable(false);
		status.setEditable(false);
	
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
					
					//Request Register
					LoginServerData.logClient.send("login Time!");
					LoginServerData.logClient.send(user.getText());
					LoginServerData.logClient.send(new String(pass.getPassword()));
					LoginServerData.logClient.flush();
					
					//Test Server results
					String input=(LoginServerData.logClient.getInput());
					if(input.equals("loginFailed")){
						failed.setText("Login Failed");
					}
					if(input.equals("loginSuccess")){
						
						GameServerData.username = user.getText();
						GameServerData.password = new String(pass.getPassword());
						
						aYourPlayer.id = Integer.parseInt(LoginServerData.logClient.getInput());
						aYourPlayer.username = GameServerData.username;
						
						Map.init();
						general.mode="game";
						
						
						LoginServerData.loginOpen=false;
						win.dispose();
					}
					
				}catch(Exception ex){}
			}
		});
		
	}
}
