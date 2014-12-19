package view;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class YesNoJPanel  extends JPanel{

	private static final long serialVersionUID = 5937380244650112435L;
	
	private JLabel nameLabel;
	private JTextField nameField;
	
	public YesNoJPanel(){
		nameLabel = new JLabel("Question : ");
		nameField = new JTextField(40);
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.add(nameLabel);
		this.add(nameField);
	}
	
	public String getNameQuestion(){
		return nameField.getText();
	}
	
	

}
