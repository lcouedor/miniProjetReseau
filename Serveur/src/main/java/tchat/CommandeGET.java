package tchat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CommandeGET extends CommandeUtilisateur {

  public CommandeGET(PrintStream ps, String commandeStr, User us) {
    super(ps, commandeStr, us);
  }

  public void execute() {
    try {
      ServerSocket serveurFTP = new ServerSocket(4000);
      //ps.println("création d'un nouveau socket");
      //ps.println("get");

      File file = new File(this.pathBase + "/" + this.user.getPath() + this.commandeArgs[1]);
      if (!file.exists()) {
        ps.println("Erreur : Fichier n'existe pas");
      } else {
        ps.println(this.commandeArgs[0]);

        Socket socket = serveurFTP.accept();

        FileInputStream is = new FileInputStream(file);

        OutputStream os = socket.getOutputStream();
        int n;

        byte buf[] = new byte[1024];

        while ((n = is.read(buf)) != -1) {
          os.write(buf, 0, n);
        }
        os.close();
        is.close();
        socket.close();

      }
      serveurFTP.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}