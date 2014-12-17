package model;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RadioButtonQuestion extends Question{

	private ArrayList<String> items;
	private int selected;

	public RadioButtonQuestion(){
		items=new ArrayList<String>();
		selected=-1;
	}

	public void addItem(String item){
		items.add(item);
	}

	public void removeItem(int index){
		if(index>=0 && index<items.size()){
			items.remove(index);
		}
	}
	
	public int getSize(){
		return items.size();
	}
	
	public void setSelected(int index){
		if(index>=0 && index<items.size()){
			selected=index;
		}
	}
	
	/*public static RadioButtonQuestion jsonToObj(String json){
		RadioButtonQuestion q = new RadioButtonQuestion();
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(json);
		  	JSONObject objQuestion = (JSONObject)obj;
			
		  	q.setName(objQuestion.get("name").toString());
		  	
		  	JSONArray objItems = (JSONArray)objQuestion.get("items");
		  	for(int i=0;i<objItems.size();i++){
		  		q.addItem(objItems.get(i).toString());
		  	}
		  	
		  	q.setSelected(Integer.parseInt((String)objQuestion.get("selected")));
		  	
		  	
		}catch(ParseException pe){
			System.out.println("Erreur jsonToObj");
			return null;
		}
		
		return q;
	}*/
	
	public String toJson(){
		StringBuffer json=new StringBuffer();
		
		json.append("{ \"type\" : \"RadioButtonQuestion\" , ");
		
		json.append(" \"object\" : {");
		json.append("\"name\" : \""+this.name+"\"");
		
		json.append(" , ");
		
		json.append("\"items\" : [");
		for(int i=0;i<items.size();i++){
			json.append("\""+items.get(i)+"\""+(i==items.size()-1?"":" , "));
		}
		json.append("]");
		
		json.append(" , ");
		
		json.append("\"selected\" : \""+selected+"\"");
		
		json.append("}");
		
		json.append("}");
		
		return json.toString();
	}
	

}
