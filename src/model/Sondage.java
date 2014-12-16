package model;




public class Sondage {
	private String path = "-1";
	private int nblike = 0;
	private String pathAdmin = "-1";
	private String titre;
	private String lien;
	
	
	
	public Sondage() {
		super();
	}
	
	public Sondage(String newPath,int newNblike,String newPathAdmin,String newLien){
		this.setPath(newPath);
		this.nblike = newNblike;
		this.pathAdmin = newPathAdmin;
		this.lien = newLien;
	}

	
	public Sondage(String newTitre,String newLien){
		titre = newTitre;
		lien = newLien;
		
		
	}


	public int getNblike() {
		return nblike;
	}

	public void setNblike(int newNbLike) {
		this.nblike = newNbLike;
	}



	public String getLien() {
		return lien;
	}

	public void setLien(String newLien) {
		this.lien = newLien;
	}

	@Override
	public String toString() {
		return "Sondage [path=" + path + ", nblike=" + nblike + ", pathAdmin="
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
