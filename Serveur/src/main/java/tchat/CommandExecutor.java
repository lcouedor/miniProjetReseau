package tchat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.UnknownHostException;

public class CommandExecutor {


	public static void executeCommande(PrintStream ps, String commande,User us, Communication c) throws UnknownHostException, IOException {
	    
		if(us != null && c.getConnecter()) {
			switch(commande.split(" ")[0].toLowerCase()) {
			case "cd":
				(new CommandeCD(ps, commande,us)).execute();

				break;
				
			case "get":
				(new CommandeGET(ps, commande, us)).execute();

				break;
				
			case "ls":
				(new CommandeLS(ps, commande,us)).execute();

				break;
				
			case "pwd":
				(new CommandePWD(ps, commande, us)).execute();

				break;
				
			case "stor":
				(new CommandeSTOR(ps, commande, us)).execute();

				break;
			
			case "help":
				(new CommandeHELP(ps, commande)).execute();

				break;
				
			case "mkdir":
				(new CommandeMKDIR(ps, commande, us)).execute();

				break;
				
			case "exit":
				(new CommandeEXIT(ps, commande, c)).execute();
				break;
				
			default:
				ps.println("nom de commande invalide, commande HELP pour voir toutes les commandes");

			}

		}else {
			
			switch(commande.split(" ")[0].toLowerCase()) {
				case "pass":
					(new CommandePASS(ps, commande, c)).execute();
					break;
					
				case "user":
					(new CommandeUSER(ps, commande, c)).execute();
					break;
					
				case "create":
					(new CommandeCREATE(ps, commande, c)).execute();
					break;
					
				case "help":
					(new CommandeHELP(ps, commande)).execute();
					break;
					
				default:
					ps.println("Connectez vous avant toute opération");
			}
		}
	}

}
