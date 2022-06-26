package tchat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CommandeSTOR extends CommandeUtilisateur {

  public CommandeSTOR(PrintStream ps, String commandeStr, User us) {
    super(ps, commandeStr, us);
  }

  public void execute() {
    try {
      ServerSocket serveurFTP = new ServerSocket(4000);
      //ps.println("création d'un nouveau socket");
      //ps.println("stor");


      File file = new File(this.pathBase + "/" + this.user.getPath() + this.commandeArgs[1]);
      if (file.exists()) {
        ps.println("Erreur : Fichier déjà existant");
      } else {
        ps.println(this.commandeArgs[0]);
        Socket socket = serveurFTP.accept();

        file.createNewFile();
        OutputStream os = new FileOutputStream(file);

        InputStream br = socket.getInputStream();
        int n;

        byte buf[] = new byte[1024];

        while ((n = br.read(buf)) != -1) {
          os.write(buf, 0, n);
        }
        os.close();
        br.close();
        socket.close();

      }
      serveurFTP.close();
      ps.println("Transfert terminé");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}