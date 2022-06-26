package tchat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CommandePASS extends Commande {
	
	private Communication c;
	
	public CommandePASS(PrintStream ps, String commandeStr, Communication c) {
		super(ps, commandeStr);
		this.c = c;
	}

	public void execute() {
		if(c.getLogin() == null) {
			ps.println("Erreur : Login requis.");
		}else {
			Path pathBase = Paths.get(new File("../Serveur/Users/").getAbsolutePath());
			try
	        {
	            File file = new File(pathBase+"/"+c.getLogin()+"/pw.txt"); //ouvrir le fichier pw de son dossier attitré
	            BufferedReader br = new BufferedReader(new FileReader(file));
	            String pw = br.readLine();
	            if(this.commandeArgs.length!=0 && pw.equals(commandeArgs[0])) {
	    			ps.println("1 Commande pass OK");
	    			//ps.println("0 Vous êtes bien connecté sur notre serveur");
	    			c.getUser().connecte();	
	    			c.setConnecter(true);
	            }else {
	    			ps.println("Erreur : Le mode de passe de l'utilisateur "+ c.getLogin() +" est faux");
	    		}
	            br.close();
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }	
		}
	}

}
