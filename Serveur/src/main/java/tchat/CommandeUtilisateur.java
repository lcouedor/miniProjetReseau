package tchat;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class CommandeUtilisateur extends Commande {
  
    protected User user;
    protected Path pathBase;
    
    public CommandeUtilisateur(PrintStream ps, String commandeStr,User u) {
       super(ps,commandeStr);
       this.user = u;
       this.pathBase = Paths.get(new File("Users/").getAbsolutePath());
    }

    public abstract void execute();

}
