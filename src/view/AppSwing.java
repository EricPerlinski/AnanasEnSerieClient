package view;

import helpers.ReadPropertyFile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
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
	private JPanel jp;
	private Color c1;
	private JButton showc1;
	private JButton showc2;
	private Color c2;
	private GridLayout gr;
	private JLabel labelc1;
	private JLabel labelc2;
	private JPanel Coul;
	
	
	
	QRCodeView client = new QRCodeView();
	QRCodeView non = new QRCodeView();
	QRCodeView admin = new QRCodeView();
	



	public AppSwing(){
		try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");}catch (Exception e) {}

		this.setTitle("Allez vient, on créé des QRCode pour le fun !");
		this.setMinimumSize(new Dimension(900, 500));
		this.setLayout(new BorderLayout());
		
		JButton jb = getCreator();
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.X_AXIS));
		panelButton.add(Box.createGlue());
		panelButton.add(jb);
		panelButton.add(Box.createGlue());

		this.add(panelButton, BorderLayout.NORTH);
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
		QRCodeType.setSelectedIndex(2);
		
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
		Coul = new JPanel();
		c1 = Color.black;
		c2 = Color.white;
		showc1 = new JButton("première couleur");
		showc2 = new JButton("deuxième couleur");
		labelc1 = new JLabel("              ");
		labelc1.setBackground(c1);
		labelc2 = new JLabel("              ");
		labelc2.setBackground(c2);
		labelc1.setOpaque(true);
		labelc2.setOpaque(true);
		Coul.add(showc1);
		Coul.add(labelc1);
		Coul.add(showc2);
		Coul.add(labelc2);
		myPanel.add(Coul);
		
		
		showc1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			        Color initialBackground = Color.BLACK;
			        Color c1 = JColorChooser.showDialog(null, "Change Button Background", initialBackground);
			        if (c1 != null) {
			        	System.out.println(c1.toString());
			        	labelc1.setBackground(c1);
			        }
			}
		});
		
		showc2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        Color initialBackground = Color.BLACK;
		        Color c2 = JColorChooser.showDialog(null, "Change Button Background", initialBackground);
		        if (c2 != null) {
		        	System.out.println(c2.toString());
		        	labelc2.setBackground(c2);
		        }
			}
		});
		
		QRCodeType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch(QRCodeType.getSelectedIndex()){
					case 0: 
						showc1.setText("couleur");
						showc2.setText("deuxième couleur");
						Coul.remove(showc1);
						Coul.remove(labelc1);
						Coul.remove(showc2);
						Coul.remove(labelc2);
						System.out.println("0");
						Coul.repaint();
						Coul.revalidate();
						break;
					case 1: 
						showc1.setText("couleur");
						showc2.setText("deuxième couleur");
						Coul.remove(showc1);
						Coul.remove(labelc1);
						Coul.remove(showc2);
						Coul.remove(labelc2);
						Coul.add(showc1,0);
						Coul.add(labelc1,1);
						System.out.println("1");
						Coul.repaint();
						Coul.revalidate();
						break;
					case 2:
						showc1.setText("première couleur");
						showc2.setText("deuxième couleur");
						Coul.remove(showc1);
						Coul.remove(labelc1);
						Coul.remove(showc2);
						Coul.remove(labelc2);
						Coul.add(showc1,0);
						Coul.add(labelc1,1);
						Coul.add(showc2,2);
						Coul.add(labelc2,3);
						System.out.println("2");
						Coul.repaint();
						Coul.revalidate();
						break;
					default: break;
				}
			}
		});
		
		
		
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
				
				jop.setSize(new Dimension(1000, 50));
				
				ErrorCorrectionLevel level = null;
			
				UIManager.put("OptionPane.cancelButtonText", "Annuler");
				UIManager.put("OptionPane.okButtonText", "Continuer");

			
				int result = JOptionPane.showConfirmDialog(null, myPanel, "Formulaire", JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) {

					QRCode qr = null;
				
					switch(type.getSelectedIndex()){
					case 0:
						qr=new Like();
						jp = new JPanel();
						gr = new GridLayout(1,2);
						jp.setLayout(gr);
						jp.add(client);
						jp.add(admin);
						add(jp, BorderLayout.CENTER);
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
						jp = new JPanel();
						gr = new GridLayout(1,2);
						jp.setLayout(gr);
						jp.add(client);
						jp.add(admin);
						add(jp, BorderLayout.CENTER);
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
						jp = new JPanel();
						gr = new GridLayout(1,3);
						jp.setLayout(gr);
						jp.add(client);
						jp.add(non);
						jp.add(admin);
						add(jp, BorderLayout.CENTER);
						break;
					case 3:
						System.out.println("Sondage");
						SurveyJPanel SPanel = new SurveyJPanel();
						UIManager.put("OptionPane.cancelButtonText", "Annuler");
						UIManager.put("OptionPane.okButtonText", "Sauvegarder");
						result = JOptionPane.showConfirmDialog(null,SPanel,"Sondage",JOptionPane.OK_CANCEL_OPTION);
						if(result == JOptionPane.OK_OPTION){
							qr = SPanel.getSurvey();
						}else
						jp = new JPanel();
						gr = new GridLayout(1,2);
						jp.setLayout(gr);
						jp.add(client);
						jp.add(admin);
						add(jp, BorderLayout.CENTER);
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
						
						switch(QRCodeType.getSelectedIndex()){
						case 0: client.getQRCode().setURL(url+qr.getPath());
								
								if(qr.getNbView()==3){
									non.getQRCode().setURL(url+((YesNo) qr).getNoLien());
								}
								admin.getQRCode().setURL(url+"admin/get/"+qr.getPathAdmin());
								break;
						case 1: 
								break;
						case 2: 
								break;
						default:
								break;
						}
						
						
						



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
								if(qr.getNbView()==3){
									non.setFile(non.getQRCode().createQRCode(filename+"_non", "png", Integer.parseInt(imageSize.getText()),level));
									non.updateGraphics();
								}
								admin.setFile(admin.getQRCode().createQRCode(filename+ "_admin", "png", Integer.parseInt(imageSize.getText()), level));
								admin.updateGraphics();

							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
					

				}
			}
		});
		return creator;
	}


	public JPanel getJp() {
		return jp;
	}


	public void setJp(JPanel jp) {
		this.jp = jp;
	}


	public GridLayout getGr() {
		return gr;
	}


	public void setGr(GridLayout gr) {
		this.gr = gr;
	}



	public JButton getShowc1() {
		return showc1;
	}


	public void setShowc1(JButton showc1) {
		this.showc1 = showc1;
	}


	public JButton getShowc2() {
		return showc2;
	}


	public void setShowc2(JButton showc2) {
		this.showc2 = showc2;
	}


	public JLabel getLabelc1() {
		return labelc1;
	}


	public void setLabelc1(JLabel labelc1) {
		this.labelc1 = labelc1;
	}


	public JLabel getLabelc2() {
		return labelc2;
	}


	public void setLabelc2(JLabel labelc2) {
		this.labelc2 = labelc2;
	}

}
