package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import qrcode.SimpleQrcodeGenerator;

public class QRCodeView extends JPanel {

	private SimpleQrcodeGenerator QRCodeClient;
	private SimpleQrcodeGenerator QRCodeAdmin;
	private File pngQRCode;
	private JButton creator;
	private JLabel image;
	private JLabel name;
	
	private String url;
	
	int width = 0;
	int height = 0;
	//faire lien avec modèle en argument

	public QRCodeView(){
		QRCodeClient = new SimpleQrcodeGenerator();
		this.add(getPrincipalPanel());
	}

	private JPanel getPrincipalPanel(){
		JPanel principal = new JPanel();
		principal.setPreferredSize(new Dimension(400, 400));
		principal.setLayout(null);
		additionJButtonCreate(principal);
		additionJLabel(principal);
		return principal;
	}

	private void additionJButtonCreate(JPanel principal){
		
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
					url = "url/id&question=" + question.getText();
					QRCodeClient.setURL(url);
					pngQRCode = QRCodeClient.createQRCode(imageName.getText(), "png", Integer.parseInt(imageSize.getText()));
					updateGraphics();
				}
			}
		});
		creator.setBounds(100, 10, 200, 30);
		principal.add(creator);
	}
	
	private void additionJLabel(JPanel principal) {
		image = new JLabel();
		image.setBounds(75, 60, 250, 250);
		principal.add(image);
		image.setBorder(BorderFactory.createLineBorder(Color.black));
		name = new JLabel("Aucun QRCode");
		name.setBounds(145, 320, 150, 20);
		principal.add(name);
		
	}
	
	public void updateGraphics(){
		ImageIcon ii = new ImageIcon(pngQRCode.getName());
		name.setText(pngQRCode.getName());
		image.setIcon(ii);
		image.setToolTipText(url);
	}
	
}