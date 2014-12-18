package model;

public class Redirect extends QRCode{
	
	private String url;
	
	public Redirect(){
		url=null;
		this.nbView=2;
		this.setLien("/api/admin/add/redirect");
	}
	
	public void setUrl(String u){
		url=u;
	}
	
	
	
	
	public String toJson(){
		String jsonSuper = super.toJson();
		StringBuffer json = new StringBuffer();
		
		
		json.append("{");
		json.append(jsonSuper+" , ");
		json.append("\"url\" : \""+url+"\"");
		json.append("}");
		
		
		
		
		return json.toString();
	}

}
