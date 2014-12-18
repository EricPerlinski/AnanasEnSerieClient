package view;

import helpers.ReadPropertyFile;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import model.Like;
import model.QRCode;
import model.Redirect;
import model.Survey;
import model.YesNo;
import urlconnec.UrlReadWrite;

public class AppSwing extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		final JComboBox<String> type = new JComboBox<String>();
		final JComboBox<String>	QRCodeType = new JComboBox<String>();
		final JComboBox<String> QRCodeRedundance = new JComboBox<String>();
		
		type.addItem("Like");
		type.addItem("Redirection");
		type.addItem("Oui/Non");
		type.addItem("Sondage");
		
		QRCodeType.addItem("Noir et blanc");
		QRCodeType.addItem("Couleur unie");
		QRCodeType.addItem("Dégradé en 2 couleurs");
		QRCodeType.setSelectedIndex(0);
		
		QRCodeRedundance.addItem("Low");
		QRCodeRedundance.addItem("Medium (recommandé)");
		QRCodeRedundance.addItem("High");
		QRCodeRedundance.setSelectedIndex(1);
		
		final JTextField imageSize = new JTextField();
		final JPanel myPanel = new JPanel();
		BoxLayout gl = new BoxLayout(myPanel, BoxLayout.Y_AXIS);
		myPanel.setLayout(gl);
		myPanel.add(new JLabel("Titre affiché pour le QrCode : "));
		myPanel.add(question);
		
		myPanel.add(new JLabel("Type de QrCode : "));
		myPanel.add(QRCodeType);
		
		myPanel.add(new JLabel("Redondance du QrCode : "));
		myPanel.add(QRCodeRedundance);

		myPanel.add(new JLabel("Type d'utilisation : "));
		myPanel.add(type);

		myPanel.add(new JLabel("Taille (pixels) : "));
		imageSize.setText("250");
		myPanel.add(imageSize);
		
		


		creator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane jop = new JOptionPane();
				jop.setSize(new Dimension(100, 50));
				
				ErrorCorrectionLevel level = null;
				switch(QRCodeRedundance.getSelectedIndex()){
					case 0: level = ErrorCorrectionLevel.L;break;
					case 1: level = ErrorCorrectionLevel.M;break;
					case 2: level = ErrorCorrectionLevel.H;break;
					default: level = ErrorCorrectionLevel.M;break;
				}
			
				UIManager.put("OptionPane.cancelButtonText", "Annuler");
				UIManager.put("OptionPane.okButtonText", "Continuer");

				int result = JOptionPane.showConfirmDialog(null, myPanel, "Formulaire", JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) {

					QRCode qr = null;
				
					switch(type.getSelectedIndex()){
					case 0:
						qr=new Like();
						break;
					case 1:
						RedirectJPanel RedirectPanel = new RedirectJPanel();
						UIManager.put("OptionPane.cancelButtonText", "Annuler");
						UIManager.put("OptionPane.okButtonText", "Sauvegarder");
						result = JOptionPane.showConfirmDialog(null,RedirectPanel,"Oui/Non",JOptionPane.OK_CANCEL_OPTION);
						if(result == JOptionPane.OK_OPTION){
							System.out.println("ok");
							qr=new Redirect();
							System.out.println(RedirectPanel.getUrlField());
							((Redirect)qr).setUrl(RedirectPanel.getUrlField());
						}
						break;
					case 2:
						YesNoJPanel YNPanel = new YesNoJPanel();
						YNPanel = new YesNoJPanel();
						UIManager.put("OptionPane.cancelButtonText", "Annuler");
						UIManager.put("OptionPane.okButtonText", "Sauvegarder");
						result = JOptionPane.showConfirmDialog(null,YNPanel,"Oui/Non",JOptionPane.OK_CANCEL_OPTION);
						if(result == JOptionPane.OK_OPTION){
							System.out.println("ok");
							qr=new YesNo();
							System.out.println(YNPanel.getNameQuestion());
							((YesNo)qr).setQuestion(YNPanel.getNameQuestion());
						}
						break;
					case 3:
						System.out.println("Sondage");
						qr=new Survey();
						break;
					}


					if(qr!=null){

						qr.setTitre(question.getText());

						System.out.println(qr.toJson());

						String url = null;
						try{
							url = ReadPropertyFile.getUrl();
							System.out.println(url);
						}catch(Exception ex){
							ex.printStackTrace();
						}
						UrlReadWrite u = new UrlReadWrite(url);
						u.registerOnline(qr);

						client.getQRCode().setURL(url+"index.php/"+qr.getPath());
						admin.getQRCode().setURL(url+"index.php/admin/get/"+qr.getPathAdmin());



						/* File Chooser pour le nom des QRCodes */ 
						JFileChooser chooser = new JFileChooser();
						chooser.setCurrentDirectory(new File("."));
						int retrival = chooser.showSaveDialog(null);
						if (retrival == JFileChooser.APPROVE_OPTION) {
							try {
								String filename = null;
								String str = chooser.getSelectedFile().getPath();
								if(str.charAt(str.length()-4) == '.' && str.charAt(str.length()-3) == 'p' && str.charAt(str.length()-2) == 'n' && str.charAt(str.length()-1) == 'g'){
									filename = str.substring(0, str.length()-4);
								}else{
									filename = str;
								}
								
								
								
								
								client.setFile(client.getQRCode().createQRCode(filename, "png", Integer.parseInt(imageSize.getText()),level));
								client.updateGraphics();


								admin.setFile(admin.getQRCode().createQRCode(filename+ "_admin", "png", Integer.parseInt(imageSize.getText()), level));
								admin.updateGraphics();

							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
					/*
					client.getQRCode().setURL(url+"index.php/flash/"+s.getPath());
					client.setFile(client.getQRCode().createQRCode(imageName.getText(), "png", Integer.parseInt(imageSize.getText())));
					client.updateGraphics();

					admin.getQRCode().setURL(url+"index.php/admin/get/"+s.getPathAdmin());
					admin.setFile(admin.getQRCode().createQRCode(imageName.getText() + "_admin", "png", Integer.parseInt(imageSize.getText())));
					admin.updateGraphics();
					 */

				}
			}
		});
		return creator;
	}

}
