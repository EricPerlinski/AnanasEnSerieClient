package model;

public class Sondage {
	private int id = -1;
	private int nblike;
	private int id_admin = -1;
	private String lien;
	
	
	
	public Sondage() {
		super();
	}
	
	public Sondage(int newId,int newNblike,int newId_admin,String newLien){
		this.id = newId;
		this.nblike = newNblike;
		this.id_admin = newId_admin;
		this.lien = newLien;
	}
	
	public Sondage(int newId){
		this.id = newId;
		this.nblike = 0;
		this.id_admin = 0;
		this.lien = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNblike() {
		return nblike;
	}

	public void setNblike(int nblike) {
		this.nblike = nblike;
	}

	public int getId_admin() {
		return id_admin;
	}

	public void setId_admin(int id_admin) {
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

	
	
}
