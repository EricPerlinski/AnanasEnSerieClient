package qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;


public class SimpleQrcodeGenerator {

	public void createQRCode(String name, String message, String imageFormat, int size){

		// encode
		ByteMatrix byteMatrix = null;
		ErrorCorrectionLevel level = ErrorCorrectionLevel.H;
		try {
			String outputFileName = name + "." + imageFormat;
			byteMatrix = generateMatrix(name, level);
			writeImage(outputFileName, imageFormat, byteMatrix, size);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ByteMatrix generateMatrix(String data, ErrorCorrectionLevel level) throws WriterException {
		final QRCode qr = new QRCode();
		Encoder.encode(data, level, qr);
		final ByteMatrix matrix = qr.getMatrix();
		return matrix;
	}

	private static void writeImage(String outputFileName, String imageFormat, ByteMatrix matrix, final int size) throws IOException {

		// Java 2D Traitement de Area
		// Futurs modules
		Area a = new Area();
		Area module = new Area(new Rectangle2D.Float(0, 0, 0.9f, 0.9f)); 


		// Deplacement du module
		AffineTransform at = new AffineTransform(); 
		int width = matrix.getWidth();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				if (matrix.get(j, i) == 1) {
					// Ajout du module
					a.add(module);
				}
				// Decalage a droite
				at.setToTranslation(1, 0); 
				module.transform(at);
			}

			// Ligne suivante
			at.setToTranslation(-width, 1); 
			module.transform(at);
		}

		// Agrandissement de l'Area pour le remplissage de l'image
		double ratio = size / (double) width;

		// Quietzone : 4 modules de bordures autour du QR Code (zone vide pour bien identifier le code dans la page imprimee)
		double adjustment = width / (double) (width + 8);
		ratio = ratio * adjustment;
		at.setToTranslation(4, 4); 
		a.transform(at);

		// On agrandit le tour a la taille souhaitee.
		at.setToScale(ratio, ratio); 
		a.transform(at);


		BufferedImage im = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) im.getGraphics();

		g.setBackground(new Color(0xFFFFFF));
		Color couleur1 = new Color(0x27CEB8);
		Color couleur2 = new Color(0x606640);
		// Debut et fin du gradient
		float[] fractions = { 0.0f, 1.0f }; 
		Color[] colors = { couleur1, couleur2 };
		g.setPaint(new RadialGradientPaint(size / 2, size / 2, size / 2, fractions, colors));


		// Fond blanc
		g.clearRect(0, 0, size, size);

		// Remplissage des modules
		g.fill(a); 


		// Ecriture sur le disque
		File f = new File(outputFileName);
		f.setWritable(true);
		ImageIO.write(im, imageFormat, f);
		f.createNewFile();
	}

}

