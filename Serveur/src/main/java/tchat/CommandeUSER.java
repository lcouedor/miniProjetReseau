package tchat;

import java.io.File;
import java.io.PrintStream;

public class CommandeUSER extends Commande {
	
	private Communication c;
	
	public CommandeUSER(PrintStream ps, String commandeStr, Communication c) {
		super(ps, commandeStr);
		this.c = c;
	}

	public void execute() {
		if(this.commandeArgs.length == 0) {
			ps.println("Erreur : saisissez un nom d'utilisateur");
			return;
		}
		
	    Boolean trouve = false;
	    File folder = new File("../Serveur/Users/");
        for (File f : folder.listFiles()) {
          if(commandeArgs[0].toLowerCase().equals(f.getName())) {
            trouve = true;
          }
        }
		if(!trouve) {
			ps.println("Erreur : Le user " + commandeArgs[0] + " n'existe pas");
		}else {
			ps.println("User valide, saisissez password");
			c.setLogin(commandeArgs[0]);
			c.setUser(new User(commandeArgs[0]));
		}
		
	}

}
