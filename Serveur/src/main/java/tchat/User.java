package tchat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class User {
  
	private String name;
	private String password;
	private String path;
	private boolean connecte;
	
	//Cr�er un tout nouvel utilisateur
	public User(String name, String password) throws IOException {
		this.name = name;
		this.password = password;
		this.path = "Users/"+name + "/";
		this.connecte = false;
		Path folder = Paths.get(path);
		Files.createDirectories(folder);
		File f = new File(folder.toString()+"/pw.txt");
	    BufferedWriter writer = new BufferedWriter(new FileWriter(f));
	    writer.write(password);
	    writer.close();
	}
	
	//Initialiser un User d�j� existant
	public User(String name) {
		Path pathBase = Paths.get(new File("../Serveur/Users/").getAbsolutePath());
		try
        {
            File file = new File(pathBase+"/"+name+"/pw.txt"); //ouvrir le fichier pw de son dossier attitr�
            BufferedReader br = new BufferedReader(new FileReader(file));
            this.password = br.readLine().replace("\n", "");
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
		this.name = name;
		this.connecte = false;
		this.path = name + "/";
	}
	
	public boolean connecte() {
	    this.connecte = true;
		if(this.connecte == true) {
			return false;
		}else {
			this.connecte = true;
			return true;
		}
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}
	
	public void setPath(String p) {
	  this.path = p;
	}
	
	public boolean getConnecte() {
		return connecte;
	}
	
	public void setConnecte(boolean p) {
	  this.connecte = p;
	}
}
