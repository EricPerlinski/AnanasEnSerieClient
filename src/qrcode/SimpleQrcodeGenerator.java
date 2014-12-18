package qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
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

	private String url = "";

	public static Color color_1 = null;
	public static Color color_2 = null;

	public File createQRCode(String name, String imageFormat, int size,ErrorCorrectionLevel level){


		// encode
		ByteMatrix byteMatrix = null;

		try {
			String outputFileName = name + "." + imageFormat;
			System.out.println(url);
			byteMatrix = generateMatrix(url, level);
			File f = writeImage(outputFileName, imageFormat, byteMatrix, size);
			return f;
		} catch (WriterException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	private ByteMatrix generateMatrix(String data, ErrorCorrectionLevel level) throws WriterException {
		QRCode qr = new QRCode();
		Encoder.encode(data, level, qr);
		ByteMatrix matrix = qr.getMatrix();
		return matrix;
	}

	private File writeImage(String outputFileName, String imageFormat, ByteMatrix matrix, final int size) throws IOException {

		// Java 2D Traitement de Area
		// Futurs modules
		Area a = new Area();
		//Area module = new Area(new Rectangle2D.Float(0, 0, 0.9f, 0.9f)); 
		Area module = new Area(new Rectangle.Float(0, 0, 1, 1));

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
		if(color_1 == null){
			// Modules noir
			Color vert = new Color(0x000000);
			g.setPaint(vert);
		}else if(color_1 != null){
			Color vert = color_1;
			g.setPaint(vert);
		}else if(color_1 != null && color_2 != null){
			g.setBackground(new Color(0xFFFFFF));
			// Debut et fin du gradient
			float[] fractions = { 0.0f, 1.0f }; 
			Color[] colors = { color_1, color_2 };
			g.setPaint(new RadialGradientPaint(size / 2, size / 2, size / 2, fractions, colors));

		}
		// Fond blanc
		g.clearRect(0, 0, size, size);

		// Remplissage des modules
		g.fill(a); 

		// Ecriture sur le disque
		File f = new File(outputFileName);
		f.setWritable(true);
		ImageIO.write(im, imageFormat, f);
		f.createNewFile();

		return f;
	}

	public void setURL(String url) {
		this.url = url;

	}

	public String getURL() {
		return url;
	}

}


