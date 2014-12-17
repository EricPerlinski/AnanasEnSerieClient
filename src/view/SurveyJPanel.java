package view;

import javax.swing.JList;
import javax.swing.JPanel;

public class SurveyJPanel  extends JPanel{

	private static final long serialVersionUID = -584049310533560753L;
	
	private JList list;
	
	public void addList(JPanel jp){
		list.add(jp);
	}

}
