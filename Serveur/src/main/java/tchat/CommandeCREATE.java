package tchat;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class CommandeCREATE extends Commande {
	
	private Communication c;
	
	public CommandeCREATE (PrintStream ps, String commandeStr, Communication c) {
		super(ps, commandeStr);
		this.c = c;
	}

	public void execute() {
		if(this.commandeArgs.length != 2) {
			ps.println("Erreur : nom d'utilisateur et mot de passe requis");
			return;
		}
		
		if(!this.commandeArgs[0].replaceAll("[^a-zA-Z0-9\\.\\-]", "").equals(this.commandeArgs[0])) {
			ps.println("Erreur : Le nom d'utilisateur contient des caractères interdits");
			return;
		}
		
		if(!this.commandeArgs[1].replaceAll("[^a-zA-Z0-9\\.\\-]", "").equals(this.commandeArgs[1])) {
			ps.println("Erreur : Le mot de passe contient des caract�res interdits");
			return;
		}
		
		Boolean valide = true;
	    File folder = new File("../Serveur/Users/");
        for (File f : folder.listFiles()) {
          if(commandeArgs[0].toLowerCase().equals(f.getName())) {
            valide = false;
          }
        }
        
        if(valide) {
        	try {
    			new User(this.commandeArgs[0], this.commandeArgs[1]);
    			this.c.setLogin(this.commandeArgs[0]);
    			this.c.setUser(new User(commandeArgs[0]));
    			this.c.getUser().setConnecte(true);
                c.setConnecter(true);

    			//ps.print(this.c.getUser().getPath()+">");
    			ps.println("");
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }else {
        	ps.println("Erreur : Nom d'utilisateur déjà utilisé");
        }
		
		
	}
}
