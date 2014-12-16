package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AppSwing extends JFrame {

	public AppSwing(){
		try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");}catch (Exception e) {}
		
		this.setTitle("Allez vient, on créé des QRCode pour le fun !");
		this.setMinimumSize(new Dimension(400, 400));
		this.setContentPane(new QRCodeView());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String args[]){
		AppSwing app = new AppSwing();
	}

}
