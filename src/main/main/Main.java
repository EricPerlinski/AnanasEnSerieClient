package main.main;

import urlconnec.UrlRead;




public class Main {

	public static void main(String[] args) {
		
		/*
		UrlRead u = new UrlRead("http://localhost/index.html");
		System.out.println(u.getJSON());
		*/
		
		//Creation d'un QRCode
		SimpleQrcodeGenerator s = new SimpleQrcodeGenerator();
		s.createQRCode("test", "url à joindre", "png", 400);
		System.out.println("QR fini !");
		
	}
	
}
