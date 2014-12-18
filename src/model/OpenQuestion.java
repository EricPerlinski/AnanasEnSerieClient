package model;

public class OpenQuestion extends Question{

	private String question;
	private String response;
	
	public OpenQuestion(){
		question=null;
		response=null;
	}
	
	public void setQuestion(String q){
		question=q;
	}
	
	public String getQuestion(){
		return question;
	}
	
	public void setResponse(String r){
		response=r;
	}
	
	public String getResponse(){
		return response;
	}
	
	/*public static OpenQuestion jsonToObj(String json){
		OpenQuestion q = new OpenQuestion();
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(json);
		  	JSONObject objQuestion = (JSONObject)obj;
		  	q.setName(objQuestion.get("name").toString());
		  	q.setQuestion(objQuestion.get("question").toString());
		  	q.setResponse(objQuestion.get("response").toString());
		}catch(ParseException pe){
			System.out.println("Erreur jsonToObj");
			return null;
		}
		return q;
	}*/
	
	@Override
	public String toJson() {
		StringBuffer json=new StringBuffer();
		json.append("{ \"type\" : \"OpenQuestion\" , ");
		json.append(" \"object\" : {");
		json.append("\"name\" : \""+this.name+"\" , ");
		json.append("\"question\" : \""+question+"\" , \"response\" : \""+response+"\"");
		json.append("}");
		json.append("}");
		return json.toString();
	}

}
