package tchat;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CommandeMKDIR extends CommandeUtilisateur {
  
    
	
	public CommandeMKDIR(PrintStream ps, String commandeStr,User us) {
		super(ps, commandeStr,us);
	}

	public void execute() {
		if(!this.commandeArgs[0].replaceAll("[^a-zA-Z0-9\\.\\-]", "").equals(this.commandeArgs[0])) {
			ps.println("Erreur : Le nom de dossier contient des caractères interdits");
			return;
		}
		Path path = Paths.get(this.pathBase +"/"+ this.user.getPath() + this.commandeArgs[0] + "/"); 
		if(Files.exists(path)) {
			ps.println("Erreur : Dossier déjà existant");
		}else {
			try {
				Files.createDirectories(path);
				ps.println("Dossier créé");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
