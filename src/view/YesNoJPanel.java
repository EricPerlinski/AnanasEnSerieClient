package view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class YesNoJPanel  extends JPanel{

	private static final long serialVersionUID = 5937380244650112435L;
	
	private JLabel nameLabel;
	private JTextField nameField;
	
	public YesNoJPanel(){
		nameLabel = new JLabel("question : ");
		nameField = new JTextField();
		nameField.setSize(50, 50);
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.add(nameLabel);
		this.add(nameField);
	}
	
	public String getNameQuestion(){
		return nameField.getText();
	}
	
	

}
