package main;

import qrcode.SimpleQrcodeGenerator;
import model.Sondage;
import urlconnec.UrlReadWrite;




public class Main {

	public static void main(String[] args) {
		
		UrlReadWrite u = new UrlReadWrite("http://10.10.99.125/ananasenserie/index.php/api/admin/add");
		/*System.out.println(u.getWebPage());
		 */
		Sondage s = new Sondage("titretest");
		s.parseJson(u.registerOnline(s));
		System.out.println(s.toString());
		SimpleQrcodeGenerator qr = new SimpleQrcodeGenerator();
		qr.createQRCode("test", s.getId(), "png", 400);
		System.out.println("QR fini !");
		
		
	}
}


