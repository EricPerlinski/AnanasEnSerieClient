package view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RedirectJPanel extends JPanel{
	
	private static final long serialVersionUID = 197772214206795644L;
	
	private JLabel urlLabel;
	private JTextField urlField;
	
	public RedirectJPanel(){
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
