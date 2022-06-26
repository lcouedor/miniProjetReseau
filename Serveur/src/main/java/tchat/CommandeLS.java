package tchat;

import java.io.File;
import java.io.PrintStream;

public class CommandeLS extends CommandeUtilisateur {
	
	public CommandeLS(PrintStream ps, String commandeStr,User us) {
		super(ps, commandeStr,us);
	}

	public void execute() {      
      File folder = new File(this.pathBase +"/"+ this.user.getPath());
      if(folder.list()!=null) {
        for(String s:folder.list()) {
        	File f = new File(this.pathBase +"/"+ this.user.getPath()+s);
        	if(f.isDirectory()) {
        		ps.println("d- "+s);
        	}else {
        		ps.println("f- "+s);
        	}
          
        }
      }
      ps.println("-----");

	}

}
