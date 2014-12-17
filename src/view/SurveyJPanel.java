package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import view.itemsurvey.ButtonQuestionJPanel;
import view.itemsurvey.OpenQuestionJPanel;

public class SurveyJPanel  extends JPanel{

	private JPanel list = new JPanel();
	private JScrollPane scrollablePanel = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JButton checkBox = new JButton("Ajouter des champs CheckBox");
	private JButton radio = new JButton("Ajouter des champs RadioButton");
	private JButton question = new JButton("Ajouter une question");
	private ArrayList<JPanel> listItem = new ArrayList<JPanel>();



	public SurveyJPanel(){
		list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
		scrollablePanel.setPreferredSize(new Dimension(500,600));
		scrollablePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(scrollablePanel);
		JPanel jp = new JPanel();
		jp.add(checkBox);
		jp.add(radio);
		jp.add(question);
		this.add(jp);
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

		JPanel jp = null;

		switch (type) {
		case 0:
			jp = new OpenQuestionJPanel();
			break;
		case 1:
			jp = new ButtonQuestionJPanel(ButtonQuestionJPanel.TYPE_CHECK_BOX);
			break;
		case 2:
			jp = new ButtonQuestionJPanel(ButtonQuestionJPanel.TYPE_RADIO);
			break;
		}

		JButton remove = new JButton("X");
		remove.setBackground(Color.RED);
		final JPanel panel = new JPanel();
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeItem(panel);
			}
		});
		
		panel.add(Box.createGlue());
		panel.add(jp);
		panel.add(remove);
		panel.add(Box.createGlue());
		list.add(panel);
		scrollablePanel.repaint();
		scrollablePanel.revalidate();
	}

	public void removeItem(JPanel jp){
		listItem.remove(jp);
		list.remove(jp);
		list.repaint();
	}

	public static void main(String args[]){
		try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");}catch (Exception e) {}
		JFrame jf = new JFrame();
		jf.setTitle("Allez vient, on créé des QRCode pour le fun !");
		jf.setMinimumSize(new Dimension(900, 900));
		jf.setContentPane(new SurveyJPanel());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

}
