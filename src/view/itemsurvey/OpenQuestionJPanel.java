package view.itemsurvey;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.GetInfo;

import model.OpenQuestion;
import model.Question;

public class OpenQuestionJPanel extends JPanel implements GetInfo {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField question = new JTextField(20);
	private JLabel nameLabel = new JLabel("Question ouverte : ");
	
	public OpenQuestionJPanel(){
		this.add(nameLabel);
		this.add(question);
	}
	
	public Question getInfos(){
		OpenQuestion q = new OpenQuestion();
		q.setQuestion(question.getText());
		return q;
	}
	

}
