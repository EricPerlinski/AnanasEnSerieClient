package view.itemsurvey;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ButtonQuestionJPanel extends JPanel {
	
	public final static int TYPE_CHECK_BOX = 1;
	public final static int TYPE_RADIO = 2;
	
	private int type; //
	
	private JLabel titleLabel = new JLabel("Titre : ");
	private JTextField title = new JTextField(20);
	private JPanel list = new JPanel();
	private JScrollPane scrollablePanel = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	private ArrayList<JPanel> listFields = new ArrayList<JPanel>();
	private JButton add = new JButton("Ajouter un champ");
	
	
	public ButtonQuestionJPanel(int type){
		this.type = type;
		JPanel jp = new JPanel();
		jp.add(titleLabel);
		jp.add(title);
		this.add(jp);
		list.setLayout(new BoxLayout(list, BoxLayout.X_AXIS));
		scrollablePanel.setPreferredSize(new Dimension(310,60));
		scrollablePanel.setBorder(BorderFactory.createLineBorder(Color.black));
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
		JTextField name = new JTextField(10);
		JLabel nameLabel = new JLabel("Nom du champs : ");
		JButton remove = new JButton("X");
		remove.setBackground(Color.RED);

		final JPanel jp = new JPanel();
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeField(jp);
			}
		});
		jp.add(nameLabel);
		jp.add(name);
		jp.add(remove);
		listFields.add(jp);
		list.add(jp);
	}
	
	public void removeField(JPanel jp){
		listFields.remove(jp);
		list.remove(jp);
		list.repaint();
	}
	
	public static void main(String args[]){
		try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");}catch (Exception e) {}
		JFrame jf = new JFrame();
		jf.setTitle("Allez vient, on créé des QRCode pour le fun !");
		jf.setMinimumSize(new Dimension(400, 500));
		jf.setContentPane(new ButtonQuestionJPanel(1));
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}
