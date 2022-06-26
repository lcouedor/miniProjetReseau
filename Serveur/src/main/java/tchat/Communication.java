package tchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Communication extends Thread {

	private User user;
	private boolean running = true;
	private BufferedReader br;
	private PrintStream ps;
	private String login = null;
	private boolean connecter = false;
	private Socket socket;

	public Communication(Socket socket) throws IOException {
		this.socket = socket;
		this.running = true;
		this.user = null;
		this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.ps = new PrintStream(socket.getOutputStream(), false, StandardCharsets.UTF_8);
	}

	public void arreter() {
		System.out.println("tentative arret");
		ps.println("arret...");
		this.running = false;
	}


	public void run() {
		//ps.println("1 Bienvenue ! ");
		//ps.println("1 Serveur FTP Personnel.");
		//ps.println("0 Authentification :");
		//ps.println("Liste des commandes : HELP");
		//ps.println("quitter l'application : EXIT");

		String commande = "";
		try {
			while (!(commande = br.readLine()).equals("EXIT") && this.running == true) {
				System.out.println(">> " + commande);
				CommandExecutor.executeCommande(ps, commande, user, this);
				if (connecter) {
					//ps.print(user.getPath().substring(0, user.getPath().length() - 1) + ">\r");
				}

			}
		} catch (IOException e) {
			System.out.println("Erreur client");
			try {
				this.socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void deconnexion() {
	  this.connecter = false;
	  this.user = null;
		this.login = null;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setConnecter(Boolean b) {
		this.connecter = b;
	}

	public String getLogin() {
		return this.login;
	}

	public boolean getConnecter() {
	  return this.connecter;
	}
}
