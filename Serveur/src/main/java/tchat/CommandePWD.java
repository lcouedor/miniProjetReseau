package tchat;

import java.io.PrintStream;

public class CommandePWD extends CommandeUtilisateur {
	
	public CommandePWD(PrintStream ps, String commandeStr, User us) {
		super(ps, commandeStr, us);
	}

	public void execute() {
		ps.println(this.user.getPath());
	}
}
