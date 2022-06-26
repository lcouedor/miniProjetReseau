package tchat;

/*
 * TP JAVA RIP
 * Min Serveur FTP
 * */

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
	
	private static Communication com;
	private static ServerSocket serveurFTP;
	private static Socket socket;
	
	static ArrayList<User> users;

	public static void main(String[] args) throws Exception {
		new User("personne", "abcd");
		
		System.out.println("Le Serveur FTP");

		serveurFTP = new ServerSocket(3000);
		while(true) {
	        socket = serveurFTP.accept();
	        
	        com = new Communication(socket);
	        com.start();

		}
	}
		
}
