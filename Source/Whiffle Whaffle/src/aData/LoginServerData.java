/*
 * Eric Purvis
 */
package aData;

import java.net.ServerSocket;

import zClient.LogInClient;
import zServer.LoginServer;

public class LoginServerData {

	//Determine Method
	public static boolean registerOpen=false;
	public static boolean userTaken=false;
	public static boolean loginOpen=false;
	public static boolean status=false;
	
	//User Input
	public static String username="";
	public static String password="";
	
	//
	public static LogInClient logClient;
	
	//public static Server port
	public static volatile ServerSocket ss;
	public static int port =6969;
}
