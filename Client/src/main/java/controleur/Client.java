package controleur;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import view.UserException;

public class Client {

	private Socket client;
	private OutputStream os;
	private InputStream is;
	private PrintStream ps;
	private BufferedReader bf;

	public Client(String addr, int port){
		try {
			client = new Socket(addr, port);
		} catch (IOException e) {
			System.out.println("Erreur création socket");
			e.printStackTrace();
		}
		try {
			os = client.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			is = client.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ps = new PrintStream(os);
		bf = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
	}

	public void arreter() throws InterruptedException, IOException {
		ps.close();
		client.close();
		is.close();
	}

	public void ecriture(String cmd, String val) throws InterruptedException, IOException {
		String s = cmd+" "+val;
		ps.println(s);
	}

	public String lecture() throws IOException, UserException {
		String s = bf.readLine();
		if(s.startsWith("Erreur")) {
			throw new UserException(s);
		}
		System.out.println(s);
		return s;
	}

	public void stor() throws IOException {
		Socket fichier;
		String s;
		s = bf.readLine();
		if (!s.equals("Erreur : Fichier déjà existant")) {
			InputStream isf = new FileInputStream(s);
			int n;
			fichier = new Socket("localhost", 4000);
			InputStream issf = fichier.getInputStream();
			OutputStream osf = fichier.getOutputStream();
			byte buf[] = new byte[1024];
			while ((n = isf.read(buf)) != -1) {
				osf.write(buf, 0, n);
			}
			isf.close();
			issf.close();
			osf.close();
			fichier.close();
		}		
	}
	
	public void get() throws IOException {
		String s;
		Socket fichier;
		s = bf.readLine();
        if(!s.equals("Fichier n'existe pas")){
	        //System.out.println("aze" + s);
	        File file = new File(s);
	        file.createNewFile();
	        FileOutputStream osf = new FileOutputStream(file);
	        fichier = new Socket("localhost", 4000);
	        InputStream isf = fichier.getInputStream();
	        InputStream issf = fichier.getInputStream();
	        byte buf[] = new byte[1024];
	        int n;
	        while ((n = issf.read(buf)) != -1) {
	          osf.write(buf, 0, n);
	        }
	        isf.close();
	        issf.close();
	        osf.close();
	        fichier.close();
        }
	
	}
}
