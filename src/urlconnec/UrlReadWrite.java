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

import model.Sondage;

public class UrlReadWrite {

	private HttpURLConnection conn;
	private String url;
	
	public UrlReadWrite(String newUrl){
		conn = null;
		setUrl(newUrl);
	}





	public String getWebPage(){
		String inputLine = null;
		StringBuffer buff = new StringBuffer();
		
		try{
			conn = (HttpURLConnection) new URL(url).openConnection();
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


	public String registerOnline(Sondage sondage) {
		StringBuffer res = null;
		try{
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			List<NameValuePair> paramHttp = new ArrayList<NameValuePair>();				

			paramHttp.add(new NameValuePair("t",sondage.getTitre()));

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
		return res.toString();





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
