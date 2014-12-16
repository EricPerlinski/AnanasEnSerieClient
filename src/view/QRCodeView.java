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

	private SimpleQrcodeGenerator QRCode;
	
	
	private File pngQRCode;	
	private JLabel image;
	private JLabel name;

	private String url = "";

	public QRCodeView(){
		QRCode= new SimpleQrcodeGenerator();
		this.add(getPrincipalPanel());
	}

	private JPanel getPrincipalPanel(){
		JPanel principal = new JPanel();
		principal.setPreferredSize(new Dimension(400, 400));
		principal.setLayout(null);
		additionJLabel(principal);
		return principal;
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
	
	public void setFile(File f){
		this.pngQRCode = f;
	}
	
	public SimpleQrcodeGenerator getQRCode(){
		return this.QRCode;
	}
	
}