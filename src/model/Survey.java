package model;

import java.util.ArrayList;

public class Survey extends QRCode{

	private ArrayList<Question> questions;
	
	public Survey(){
		this.nbView = 2;
		questions=new ArrayList<Question>();
	}
	
	public void addQuestion(Question q){
		questions.add(q);
	}
	
	public void removeQuestion(int index){
		questions.remove(index);
	}
	
	
	/*public Survey jsonToObj(String json){
		Survey s = new Survey();
		
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(json);
			JSONArray objItems = (JSONArray) ((JSONObject)obj).get("Survey");
		  	
		  	for(int i=0;i<objItems.size();i++){
		  		
		  		String jsonStr = objItems.get(i).toString();
		  		JSONParser p = new JSONParser();
		  		JSONObject jsonObj = (JSONObject) parser.parse(jsonStr);
		  		String type = jsonObj.get("type").toString();
		  		String object = jsonObj.get("object").toString();
		  		Question q=null;
		  		if(type.equals("CheckBoxQuestion")){
		  			q= CheckBoxQuestion.jsonToObj(object);
		  		}else if(type.equals("RadioButtonQuestion")){
		  			q= RadioButtonQuestion.jsonToObj(object);
		  		}else if(type.equals("OpenQuestion")){
		  			q= OpenQuestion.jsonToObj(object);
		  		}
		  		s.addQuestion(q);
		  	}
		  	
		}catch(ParseException pe){
			System.out.println("Erreur jsonToObj");
			return null;
		}
		
		return s;
	}*/
	
	public String toJson(){
		String jsonSuper = super.toJson();
		StringBuffer json = new StringBuffer();
		
		json.append("{");
		
		json.append(jsonSuper+" , ");
		json.append("\"questions\" : [");
		
		for(int i=0;i<questions.size();i++){
			json.append(questions.get(i).toJson());
			json.append((i==questions.size()-1?"":" , "));
		}
		
		json.append("]");
		json.append("}");
		
		return json.toString();
	}
	
}
