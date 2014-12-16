package main;

import helpers.ReadPropertyFile;

import qrcode.SimpleQrcodeGenerator;
import model.Sondage;
import urlconnec.UrlReadWrite;




public class Main {

	public static void main(String[] args) {
		
		/*System.out.println(u.getWebPage());
		 */
		String url = null;
		try{
			url = new ReadPropertyFile().getUrl();
			System.out.println(url);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		UrlReadWrite u = new UrlReadWrite(url+"index.php/api/admin/add");
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


