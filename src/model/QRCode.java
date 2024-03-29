package model;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class QRCode {
	
	protected String titre;
	protected int nbView  = 0;
	protected String path;
	protected String pathAdmin;
	protected String lien;
	
	private ArrayList<Question> questions;
	
	public QRCode() {
		super();
	}
	
	public QRCode(String newPath,int newNbView,String newPathAdmin,String newLien){
		this.setPath(newPath);
		this.nbView = newNbView;
		this.pathAdmin = newPathAdmin;
		this.lien = newLien;
		questions=new ArrayList<Question>();
	}

	public void parseJSON(String res){
		
		JSONParser parser = new JSONParser();
    
		try{
			Object obj= parser.parse(res.toString());
		  	JSONArray array=(JSONArray)obj;
		  	JSONObject obj2=(JSONObject)array.get(0);
		  	this.setPath(obj2.get("path").toString());
		  	this.setPathAdmin(obj2.get("pathAdmin").toString());
		}catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
	}
	
	public QRCode(String newTitre,String newLien){
		titre = newTitre;
		lien = newLien;
	}
	
	
	public void addQuestion(Question q){
		questions.add(q);
	}
	
	public void removeQuestion(int index){
		if(index>=0 && index<questions.size()){
			questions.remove(index);
		}
	}
	
	public String toJson(){
		StringBuffer json=new StringBuffer();

		json.append("\"title\" : \""+titre+"\"");
		json.append(" , ");
		json.append("\"nbView\" : "+nbView);
		json.append(" , ");
		json.append("\"path\" : \""+path+"\"");
		json.append(" , ");
		json.append("\"pathAdmin\" : \""+pathAdmin+"\"");
		json.append(" , ");
		json.append("\"lien\" : \""+lien+"\"");
		
		return json.toString();
	}


	public int getNbView() {
		return nbView;
	}

	public void setNblke(int newNbView) {
		this.nbView = newNbView;
	}



	public String getLien() {
		return lien;
	}

	public void setLien(String newLien) {
		this.lien = newLien;
	}

	@Override
	public String toString() {
		return "Sondage [path=" + path + ", nbview=" + nbView + ", pathAdmin="
				+ pathAdmin + ", lien=" + lien + ", titre=" + titre + "]";
	}

	public String toParamsTitre() {
		return "?titre="+titre;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String newTitre) {
		this.titre = newTitre;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String newPath) {
		this.path = newPath;
	}
	
	public String getPathAdmin() {
		return pathAdmin;
	}

	public void setPathAdmin(String newPathAdmin) {
		this.pathAdmin = newPathAdmin;
	}
	
	
}
