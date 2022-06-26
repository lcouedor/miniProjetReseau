package tchat;

import java.io.PrintStream;

public class CommandeEXIT extends Commande {
	
	private Communication c;
	
	public CommandeEXIT(PrintStream ps, String commandeStr, Communication c) {
		super(ps, commandeStr);
		this.c = c;
	}

	public void execute() {
		c.deconnexion();
		//ps.println("déconnecté\n--------\n");
		
		//ps.println("1 Bienvenue ! ");
		//ps.println("1 Serveur FTP Personnel.");
		//ps.println("0 Authentification :");
		//ps.println("Liste des commandes : HELP");
		//ps.println("quitter l'application : EXIT");
	}

}
