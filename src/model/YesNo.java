package model;

public class YesNo extends QRCode{
	
	private String noLien;
	private String question;
	
	public YesNo(){
		this.setLien("/api/admin/add/yesno");
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
	
	
}
