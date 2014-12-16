package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class Sondage {
	private String id = "-1";
	private int nblike = 0;
	private String id_admin = "-1";
	private String titre;
	private String lien;
	
	
	
	public Sondage() {
		super();
	}
	
	public Sondage(String newId,int newNblike,String newId_admin,String newLien){
		this.id = newId;
		this.nblike = newNblike;
		this.id_admin = newId_admin;
		this.lien = newLien;
	}
	
	public void parseJson(String s){
		
		System.out.println("String à parser : "+s);
		
		JSONParser parser = new JSONParser();
		                
		try{
			Object obj= parser.parse(s);
		  	JSONArray array=(JSONArray)obj;
		  	JSONObject obj2=(JSONObject)array.get(0);
		  	this.id = obj2.get("path").toString();
		  	this.id_admin = obj2.get("pathAdmin").toString();
		}catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
	}
	
	
	public Sondage(String newTitre){
		titre = newTitre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNblike() {
		return nblike;
	}

	public void setNblike(int nblike) {
		this.nblike = nblike;
	}

	public String getId_admin() {
		return id_admin;
	}

	public void setId_admin(String id_admin) {
		this.id_admin = id_admin;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	@Override
	public String toString() {
		return "Sondage [id=" + id + ", nblike=" + nblike + ", id_admin="
				+ id_admin + ", lien=" + lien + "]";
	}

	public String toParamsTitre() {
		return "?titre="+titre;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	
}
