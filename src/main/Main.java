package main;

import javax.swing.JOptionPane;
import helpers.ReadPropertyFile;
import urlconnec.UrlReadWrite;
import view.AppSwing;




public class Main {

	public static void main(String[] args) {
		
		/*System.out.println(u.getWebPage());
		 */
		String url = null;
		try{
			url = ReadPropertyFile.getUrl();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		UrlReadWrite u = new UrlReadWrite(url);
		if(!u.testConnection()){
			JOptionPane.showMessageDialog(null,"Le serveur n'est pas disponible, veuillez\n vérifier l'adresse fournie dans le fichier \n/config/server.properties","Erreur fatale",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		new AppSwing();
		
		
	}
}


