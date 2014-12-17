package view;

import helpers.ReadPropertyFile;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import urlconnec.UrlReadWrite;

import model.QRCode;

public class AppSwing extends JFrame {

	private JButton creator;
	QRCodeView client = new QRCodeView();
	QRCodeView admin = new QRCodeView();

	public AppSwing(){
		try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");}catch (Exception e) {}

		this.setTitle("Allez vient, on créé des QRCode pour le fun !");
		this.setMinimumSize(new Dimension(900, 500));
		this.setLayout(new BorderLayout());
		JPanel jp = new JPanel();
		GridLayout gr = new GridLayout(1,2);
		jp.setLayout(gr);
		jp.add(client);
		jp.add(admin);
		
		JButton jb = getCreator();
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.X_AXIS));
		panelButton.add(Box.createGlue());
		panelButton.add(jb);
		panelButton.add(Box.createGlue());
		
		this.add(panelButton, BorderLayout.NORTH);
		this.add(jp, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private JButton getCreator(){

		creator = new JButton("Créer un QRCode");

		final JTextField question = new JTextField(30);
		final JTextField imageName = new JTextField();
		final JTextField imageSize = new JTextField();
		final JPanel myPanel = new JPanel();
		BoxLayout gl = new BoxLayout(myPanel, BoxLayout.Y_AXIS);
		myPanel.setLayout(gl);
		myPanel.add(new JLabel("Votre question : "));
		myPanel.add(question);
		myPanel.add(new JLabel("Nom du QRCode : "));
		myPanel.add(imageName);
		myPanel.add(new JLabel("Taille (pixels) : "));
		imageSize.setText("250");
		myPanel.add(imageSize);


		creator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane jop = new JOptionPane();
				jop.setSize(new Dimension(100, 50));
				UIManager.put("OptionPane.cancelButtonText", "Annuler");
				UIManager.put("OptionPane.okButtonText", "Sauvegarder, flasher !");
				int result = jop.showConfirmDialog(null, myPanel, "Formulaire", JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) {
					
					QRCode s = new QRCode();
					s.setTitre(question.getText());
					
					String url = null;
					try{
						url = new ReadPropertyFile().getUrl();
						System.out.println(url);
					}catch(Exception ex){
						ex.printStackTrace();
					}
					UrlReadWrite u = new UrlReadWrite(url+"index.php/api/admin/add");
					u.registerOnline(s);
					
					client.getQRCode().setURL(url+"index.php/flash/"+s.getPath());
					client.setFile(client.getQRCode().createQRCode(imageName.getText(), "png", Integer.parseInt(imageSize.getText())));
					client.updateGraphics();
					
					admin.getQRCode().setURL(url+"index.php/admin/get/"+s.getPathAdmin());
					admin.setFile(admin.getQRCode().createQRCode(imageName.getText() + "_admin", "png", Integer.parseInt(imageSize.getText())));
					admin.updateGraphics();
					
				}
			}
		});
		return creator;
	}

	public static void main(String args[]){
		AppSwing app = new AppSwing();
	}

}
