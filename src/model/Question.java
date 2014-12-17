package model;

public abstract class Question {
	
	protected String name;
	
	public Question(){
		name=null;
	}
	
	public void setName(String n){
		name=n;
	}
	
	public String getName(){
		return name;
	}

	public abstract String toJson();
	
	
}
