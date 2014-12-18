package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import model.QRCode;
import model.Survey;

import view.itemsurvey.ButtonQuestionJPanel;
import view.itemsurvey.OpenQuestionJPanel;

public class SurveyJPanel extends JPanel{

	private JPanel list = new JPanel();
	private JScrollPane scrollablePanel = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JButton checkBox = new JButton("Ajouter des champs CheckBox");
	private JButton radio = new JButton("Ajouter des champs RadioButton");
	private JButton question = new JButton("Ajouter une question");
	private ArrayList<JPanel> listItem = new ArrayList<JPanel>();
	
	public SurveyJPanel(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
		scrollablePanel.setPreferredSize(new Dimension(500,600));
		scrollablePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(Box.createGlue());
		this.add(scrollablePanel);
		JPanel jp = new JPanel();
		jp.add(checkBox);
		jp.add(radio);
		jp.add(question);
		this.add(jp);
		this.add(Box.createGlue());
		addListenersButtons();
	}

	public void addListenersButtons(){
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addList(ButtonQuestionJPanel.TYPE_CHECK_BOX);
			}
		});
		radio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addList(ButtonQuestionJPanel.TYPE_RADIO);
			}
		});
		question.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addList(0);
			}
		});
	}

	public void addList(int type){

		JPanel question = new JPanel() ;
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		switch (type) {
		case 0:
			question = new OpenQuestionJPanel();
			break;
		case 1:
			question = new ButtonQuestionJPanel(ButtonQuestionJPanel.TYPE_CHECK_BOX);
			panel.add(new JLabel("CheckBox"));
			break;
		case 2:
			question = new ButtonQuestionJPanel(ButtonQuestionJPanel.TYPE_RADIO);
			panel.add(new JLabel("RadioButton"));
			break;
		}

		final JPanel qf = question;
		
		JButton remove = new JButton("X");
		remove.setBackground(Color.RED);
		
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeItem(panel, qf);
			}
		});
		
		listItem.add(question);
		JPanel tmp = new JPanel();
		tmp.add(question);
		tmp.add(remove);
		panel.add(tmp);
		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		list.add(panel);
		scrollablePanel.repaint();
		scrollablePanel.revalidate();
	}

	public void removeItem(JPanel jp, JPanel question){
		listItem.remove(question);
		list.remove(jp);
		list.repaint();
	}
	
	public Survey getSurvey(){
		Survey survey = new Survey();
		for(int i=0; i<listItem.size(); i++){
			survey.addQuestion(((GetInfo)listItem.get(i)).getInfos());
		}
		return survey;
	}

	public static void main(String args[]){
		try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");}catch (Exception e) {}
		JFrame jf = new JFrame();
		jf.setTitle("Creating QRCode Service");
		jf.setMinimumSize(new Dimension(500, 500));
		jf.setContentPane(new SurveyJPanel());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

}
