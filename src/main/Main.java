package main;

import urlconnec.UrlRead;




public class Main {

	public static void main(String[] args) {
			
		UrlRead u = new UrlRead("http://localhost/index.html");
		System.out.println(u.getJSON());
		
	}
}
