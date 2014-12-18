package view.itemsurvey;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import view.GetInfo;

import model.CheckBoxQuestion;
import model.Question;
import model.RadioButtonQuestion;

public class ButtonQuestionJPanel extends JPanel implements GetInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int TYPE_CHECK_BOX = 1;
	public final static int TYPE_RADIO = 2;

	private int type;

	private JLabel titleLabel = new JLabel("Titre : ");
	private JTextField title = new JTextField(20);
	private JPanel list = new JPanel();
	private JScrollPane scrollablePanel = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private ArrayList<JTextField> listFields = new ArrayList<JTextField>();
	private JButton add = new JButton("Ajouter un champ");

	public ButtonQuestionJPanel(int type){
		this.setType(type);
		JPanel jp = new JPanel();
		jp.add(titleLabel);
		jp.add(title);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(jp);
		list.setLayout(new BoxLayout(list, BoxLayout.X_AXIS));
		scrollablePanel.setPreferredSize(new Dimension(500,80));
		this.add(scrollablePanel);
		this.add(add);
		addListenerButton();
	}

	public void addListenerButton(){
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addField();
				scrollablePanel.repaint();
				scrollablePanel.revalidate();
			}
		});
	}

	public void addField(){
		final JTextField name = new JTextField(10);
		JLabel nameLabel = new JLabel("Champs : ");
		JButton remove = new JButton("X");
		remove.setBackground(Color.RED);

		final JPanel jp = new JPanel();
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeField(jp, name);
			}
		});

		jp.add(nameLabel);
		jp.add(name);
		jp.add(remove);

		listFields.add(name);
		list.add(jp);
	}

	public void removeField(JPanel jp, JTextField jf){
		listFields.remove(jf);
		list.remove(jp);
		list.repaint();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Question getInfos(){
		switch (type) {
		case TYPE_CHECK_BOX :
			CheckBoxQuestion q  = new CheckBoxQuestion();
			q.setName(title.getText());
			for(int i=0; i<listFields.size(); i++){
				q.addItem(listFields.get(i).getText());
			}
			return q;
		case TYPE_RADIO:
			RadioButtonQuestion q2  = new RadioButtonQuestion();
			q2.setName(title.getText());
			for(int i=0; i<listFields.size(); i++){
				q2.addItem(listFields.get(i).getText());
			}
			return q2;

		}
		return null;

	}

	/*public static void main(String args[]){
		try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");}catch (Exception e) {}
		JFrame jf = new JFrame();
		jf.setTitle("Allez vient, on créé des QRCode pour le fun !");
		jf.setMinimumSize(new Dimension(400, 500));
		jf.setContentPane(new ButtonQuestionJPanel(1));
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}*/
}
