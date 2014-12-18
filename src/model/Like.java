package model;


public class Like extends QRCode{

	public int nbLike;

	public Like(){
		this.nbView = 2;
		this.setLien("/api/admin/add/like");
		nbLike=0;
	}

	public String toJson(){
		String jsonSuper = super.toJson();
		
		StringBuffer json = new StringBuffer();

		
		json.append("{");
		json.append(jsonSuper+" , ");
		json.append("\"nbLike\" : "+nbLike);
		json.append("}");


		return json.toString();
	}


	
}
