package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class YesNo extends QRCode{
	
	private String noLien;
	private String question;
	private int nbUnlike;
	
	public YesNo(){
		this.setLien("api/admin/add/yesno");
		this.nbView = 3;
		question=null;
	}
	
	
	
	public void setQuestion(String q){
		question=q;
	}
	
	public String toJson(){
		String jsonSuper = super.toJson();
		StringBuffer json=new StringBuffer();
		
		json.append("{");
		json.append(jsonSuper+" , ");
		json.append("\"question\" : \""+question+"\"");
		json.append("}");
		
		return json.toString();
	}

	public String getNoLien() {
		return noLien;
	}

	public void setNoLien(String noLien) {
		this.noLien = noLien;
	}
	
	public void parseJSON(String res){
		
		JSONParser parser = new JSONParser();
    
		try{
			Object obj= parser.parse(res.toString());
		  	JSONArray array=(JSONArray)obj;
		  	JSONObject obj2=(JSONObject)array.get(0);
		  	this.setPath(obj2.get("path").toString());
		  	this.setNoLien(obj2.get("nopath").toString());
		  	this.setPathAdmin(obj2.get("pathAdmin").toString());
		}catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
	}



	public int getNbUnlike() {
		return nbUnlike;
	}



	public void setNbUnlike(int nbUnlike) {
		this.nbUnlike = nbUnlike;
	}
	
	
}
