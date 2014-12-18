package urlconnec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.QRCode;

public class UrlReadWrite {

	private HttpURLConnection conn;
	private String url;
	
	public UrlReadWrite(String newUrl){
		conn = null;
		setUrl(newUrl);
	}





	public String getWebPage(String addUrl){
		String inputLine = null;
		StringBuffer buff = new StringBuffer();
		
		try{
			conn = (HttpURLConnection) new URL(url+addUrl).openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			while ((inputLine = in.readLine()) != null){
				buff.append(inputLine+"\n");
			}
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return buff.toString();
	}


	public void registerOnline(QRCode sondage) {
		StringBuffer res = null;
		try{
			System.out.println(url+sondage.getLien());
			conn = (HttpURLConnection) new URL(url+sondage.getLien()).openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			List<NameValuePair> paramHttp = new ArrayList<NameValuePair>();				

			paramHttp.add(new NameValuePair("objet",sondage.toJson()));

			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(paramHttp));
			writer.flush();
			writer.close();
			os.close();
			
			InputStream inputStream = conn.getInputStream();
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK && inputStream != null) {
				InputStreamReader reader = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(reader);
				String line;
				res = new StringBuffer();
				while((line=br.readLine())!=null){
					res.append(line);
				}
				reader.close();
				br.close();
				
			}else if(conn.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND){
				System.out.println("404");
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
		sondage.parseJSON(res.toString());
		}catch(Exception e){
			e.printStackTrace();
		}


	}
	
	
	public boolean testConnection(){
		StringBuffer res = null;
		try{
			conn = (HttpURLConnection) new URL(url+"/api/test").openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			
			InputStream inputStream = conn.getInputStream();
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK && inputStream != null) {
				InputStreamReader reader = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(reader);
				String line;
				res = new StringBuffer();
				while((line=br.readLine())!=null){
					res.append(line);
				}
				reader.close();
				br.close();
				
			}else if(conn.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND){
				System.out.println("404");
			}

		}catch(Exception e){
			System.out.println("Le serveur n'est pas disponible, veuillez vérifier l'adresse fournie dans le fichier /config/server.properties");
		}
		
		if(! (res == null)){
			return res.toString().equalsIgnoreCase("ananas");
		}else{
			return false;
		}
		
		


	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void updateSondage(QRCode qr) {
		StringBuffer res = null;
		String addUrl = "api/admin/get/"+qr.getPathAdmin();
		try{
			conn = (HttpURLConnection) new URL(url+addUrl).openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			List<NameValuePair> paramHttp = new ArrayList<NameValuePair>();				

			paramHttp.add(new NameValuePair("title",qr.getTitre()));

			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(paramHttp));
			writer.flush();
			writer.close();
			os.close();
			
			InputStream inputStream = conn.getInputStream();
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK && inputStream != null) {
				InputStreamReader reader = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(reader);
				String line;
				res = new StringBuffer();
				while((line=br.readLine())!=null){
					res.append(line);
				}
				reader.close();
				br.close();
				
			}else if(conn.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND){
				System.out.println("404");
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("String à parser : "+res);
		
		JSONParser parser = new JSONParser();
		                
		try{
			Object obj= parser.parse(res.toString());
		  	JSONArray array=(JSONArray)obj;
		  	JSONObject obj2=(JSONObject)array.get(0);
		  	qr.setNblke(Integer.parseInt(obj2.get("counter").toString()));
		}catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
		


	}


	


	private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException{
		StringBuilder result = new StringBuilder();
		boolean first = true;

		for (NameValuePair pair : params){
			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
		}
		return result.toString();
	}

	public HttpURLConnection getHttpUrlConnection() {
		return conn;
	}


	public void setHttpUrlConnection(HttpURLConnection newCon) {
		this.conn = newCon;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


}
