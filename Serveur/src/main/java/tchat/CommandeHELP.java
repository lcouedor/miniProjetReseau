package tchat;

import java.io.PrintStream;

public class CommandeHELP extends Commande {
  public CommandeHELP(PrintStream ps, String commandeStr) {
      super(ps, commandeStr);
  }
  
  public void execute() {
      ps.println("HELP\tAffiche la liste des commandes");
      ps.println("CD\tpour changer de répertoire courant");
      ps.println("GET\tpour télécharger un fichier du serveur vers le client");
      ps.println("LS\tafficher la liste des dossiers et des fichiers du répertoire courant du serveur");
      ps.println("PASS\tpour envoyer le mot de passe");
      ps.println("PWD\tpour afficher le chemin absolu du dossier courant");
      ps.println("STOR\tpour envoyer un fichier vers le dossier courant serveur");
      ps.println("USER\tpour envoyer le nom du login");
      ps.println("CREATE\tCreer un compte utilisateur. Utilisation : CREATE nom mdp");
      ps.println("EXIT\tdeconnexion");
  }

}
