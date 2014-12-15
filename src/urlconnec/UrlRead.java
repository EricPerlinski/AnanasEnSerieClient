package urlconnec;

import java.io.*;
import java.net.*;

public class UrlRead {

	private URL url;

	public UrlRead(String newUrl){
		try {
			this.url = new URL(newUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}


	public String getJSON(){
		String inputLine = null;
		StringBuffer buff = new StringBuffer();
		try{
			URLConnection yc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			
			while ((inputLine = in.readLine()) != null){
				buff.append(inputLine+"\n");
			}
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return buff.toString();
	}


	public URL getUrl() {
		return url;
	}


	public void setUrl(URL url) {
		this.url = url;
	}


}
