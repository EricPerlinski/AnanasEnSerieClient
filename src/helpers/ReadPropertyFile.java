package helpers;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadPropertyFile {
	
	
	public String getUrl() throws IOException{
		
		FileReader file = new FileReader("src/main/server.properties");
		Scanner sc = new Scanner(file); 
		sc.useDelimiter("$^");
		String texte = sc.next(); 
		sc.close(); 
		
		System.out.println("URL Serveur :" + texte);
		
		JSONParser parser = new JSONParser();
        String urlparse = null;
		try{
			Object obj= parser.parse(texte);
		  	JSONArray array=(JSONArray)obj;
		  	JSONObject obj2=(JSONObject)array.get(0);
		  	urlparse = obj2.get("url").toString();
		}catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
		if(urlparse.charAt(urlparse.length()-1) != '/'){
			urlparse.concat("/");
		}
		
		return urlparse;
		
	}

}
