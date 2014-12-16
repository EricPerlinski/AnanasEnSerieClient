package main;

import qrcode.SimpleQrcodeGenerator;
import model.Sondage;
import urlconnec.UrlReadWrite;




public class Main {

	public static void main(String[] args) {
		
		UrlReadWrite u = new UrlReadWrite("http://10.10.99.125/ananasenserie/index.php/admin/add");
		/*System.out.println(u.getWebPage());
		 */
		Sondage s = new Sondage("titretest");
		System.out.println(u.registerOnline(s));
		
		SimpleQrcodeGenerator qr = new SimpleQrcodeGenerator();
		qr.createQRCode("test", "url �joindre", "png", 400);
		System.out.println("QR fini !");
		
		
	}
}


