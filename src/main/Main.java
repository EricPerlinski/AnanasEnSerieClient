package main;

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
			return;
		}
		
		new AppSwing();
		
		/*System.out.println(u.getWebPage());
		 */
		/*Sondage s = new Sondage("titretest");
		s.parseJson(u.registerOnline(s));
		System.out.println(s.toString());
		SimpleQrcodeGenerator qr = new SimpleQrcodeGenerator();
		qr.createQRCode(s.getTitre(), "png", 400);
		System.out.println("QR fini !");*/
		
		
	}
}


