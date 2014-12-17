package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import view.itemsurvey.ButtonQuestionJPanel;
import view.itemsurvey.QuestionJPanel;

public class SurveyJPanel  extends JPanel{

	private static final long serialVersionUID = -584049310533560753L;
	
	private JList list;
	private JButton checkBox = new JButton("Ajouter des champs CheckBox");
	private JButton radio = new JButton("Ajouter des champs RadioButton");
	private JButton question = new JButton("Ajouter une question");
	
	
	public void addListenersButtons(){
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addList(new ButtonQuestionJPanel(ButtonQuestionJPanel.TYPE_CHECK_BOX));
			}
		});
		radio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addList(new ButtonQuestionJPanel(ButtonQuestionJPanel.TYPE_RADIO));
			}
		});
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addList(new QuestionJPanel());
			}
		});
		
	}
	
	public void addList(JPanel jp){
		list.add(jp);
	}

}
