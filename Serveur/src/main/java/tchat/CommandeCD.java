package tchat;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class CommandeCD extends CommandeUtilisateur {
	
	public CommandeCD(PrintStream ps, String commandeStr,User us) {
		super(ps, commandeStr,us);
	}

	public void execute() {
		if(this.commandeArgs.length == 0) {
			ps.println("Erreur : saisissez un argument");
			return;
		}
		
	    if(this.commandeArgs[0].equals("~")) { //caract�re home, retour au home
	    	this.user.setPath(this.user.getName()+"/");
	    }else if(this.commandeArgs[0].equals("..")){ //retour en arri�re de 1 niveau
	    	if(!this.user.getPath().equals(this.user.getName()+"/")) { //s'il est d�j� dans son home, interdire d'aller plus haut
	    		String path = this.user.getPath();
	    		path = path.substring(0, path.length()-1); //retirer le dernier / pour faire l'op�ration suivante
	    		int endIndex = path.lastIndexOf("/");
	    	    this.user.setPath(path.substring(0, endIndex)+"/");
	    	}
	    }else {
	    	File f = new File(this.pathBase +"/"+ this.user.getPath()+this.commandeArgs[0]);
	    	
	    	try {
				if(!f.getCanonicalPath().startsWith(this.pathBase +"\\"+ this.user.getName())) {
					this.user.setPath(this.user.getName()+"/");
				}else {
					if(f.isDirectory()) {
			    		this.user.setPath(this.user.getPath()+this.commandeArgs[0]+"/");
			    	}else {
			    		ps.println("Erreur : L'élément spécifié n'existe pas ou n'est pas un dossier");
			    	}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}	    	
	    }
	}

}
