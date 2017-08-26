/*
 * Eric Purvis
 */
package aMenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import aData.LoginServerData;

public class Register {

	public Register(){
		
		//objects
		final TextField user = new TextField(400/2-200/2, 100, 200, 25);
		final PasswordField pass = new PasswordField(400/2-200/2, 125, 200, 25);
		final Button submit = new Button(400/2-200/2, 175, 200, 25);
		final TextField taken = new TextField(400/2-200/2, 0, 200, 25);
		final TextField status = new TextField(400/2-200/2, 250, 200, 25);
		
		//Allow one
		LoginServerData.registerOpen=true;
		
		//Frame
		final Window win = new Window("Register");
		win.setBackground(Color.black);
		win.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		win.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				LoginServerData.registerOpen=false;
				win.dispose();
			}
		});
		
		//add
		win.add(user);
		win.add(pass);
		win.add(submit);
		win.add(taken);
		win.add(status);
		win.validate();
		
		//stuff
		user.setText("UserName");
		pass.setText("Password");
		submit.setText("Register");
		status.setText("Server: "+LoginServerData.status);
		taken.setEditable(false);
		status.setEditable(false);

		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
					
					//Gather data
					LoginServerData.username=user.getText();
					LoginServerData.password=new String(pass.getPassword());
					
					//Request Register
					LoginServerData.logClient.send("RegUser!");
					LoginServerData.logClient.send(LoginServerData.username);
					LoginServerData.logClient.send(LoginServerData.password);
					LoginServerData.logClient.flush();
					
					//Test Server results
					String input=(LoginServerData.logClient.getInput());
					if(input.equals("taken")){
						taken.setText("User Taken");
					}
					if(input.equals("good")){
						LoginServerData.registerOpen=false;
						win.dispose();
					}
					
					
				}catch(Exception ex){}
			}
		});
		
	}
	
}
