package model;

public class Questions {
	
	private String question;
	private String reponse;
	
	public Questions(){
		question = null;
		reponse  = null;
	}
	
	public Questions(String newQuestion,String newReponse){
		question = newQuestion;
		reponse  = newReponse;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
