package view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RedirectPanel extends JPanel{

	private static final long serialVersionUID = 2552821851484094018L;
	
	private JLabel urlLabel;
	private JTextField urlField;
	
	public RedirectPanel(){
		urlLabel=new JLabel("Url : ");
		urlField = new JTextField();
		
		urlField.setSize(50, 50);
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.add(urlLabel);
		this.add(urlField);
	}

	public String getUrlField(){
		return urlField.getText();
	}
	
	
}
