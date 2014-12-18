package model;

import java.util.ArrayList;

public class CheckBoxQuestion extends Question{

	private ArrayList<String> items;
	private ArrayList<Boolean> selected;

	public CheckBoxQuestion(){
		items=new ArrayList<String>();
		selected = new ArrayList<Boolean>();
	}

	public void addItem(String item){
		items.add(item);
		selected.add(false);
	}
	
	public void addSelected(int index){
		if(index>=0 && index<items.size()){
			selected.set(index,true);
		}
	}

	public void removeItem(int index){
		if(index>=0 && index<items.size()){
			items.remove(index);
			selected.remove(index);
		}
	}
	
	public int getSize(){
		return items.size();
	}
	
	/*public static CheckBoxQuestion jsonToObj(String json){
		CheckBoxQuestion q = new CheckBoxQuestion();
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(json);
		  	JSONObject objQuestion = (JSONObject)obj;
			
		  	q.setName(objQuestion.get("name").toString());
		  	
		  	JSONArray objItems = (JSONArray)objQuestion.get("items");
		  	for(int i=0;i<objItems.size();i++){
		  		q.addItem(objItems.get(i).toString());
		  	}
		  	
		  	JSONArray objSelected = (JSONArray)objQuestion.get("selected");
		  	for(int i=0;i<objSelected.size();i++){
		  		q.addSelected(Integer.parseInt((String) objSelected.get(i)));
		  	}
		}catch(ParseException pe){
			System.out.println("Erreur jsonToObj");
			return null;
		}
		
		return q;
	}*/
	
	public String toJson(){
		StringBuffer json=new StringBuffer();
		
		json.append("{ \"type\" : \"CheckBoxQuestion\" , ");
		
		json.append(" \"object\" : {");
		json.append("\"name\" : \""+this.name+"\"");
		
		json.append(" , ");
		
		json.append("\"items\" : [");
		for(int i=0;i<items.size();i++){
			json.append("\""+items.get(i)+"\""+(i==items.size()-1?"":" , "));
		}
		json.append("]");
		
		json.append(" , ");
		
		json.append("\"selected\" : [");
		for(int i=0;i<selected.size();i++){
			json.append("\""+selected.get(i)+"\""+(i==selected.size()-1?"":" , "));
		}
		json.append("]");
		
		json.append("}");
		
		json.append("}");
		
		return json.toString();
	}

}
