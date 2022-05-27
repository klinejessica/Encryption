package io.jkline.itext;

import java.io.File;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.TextAlignment;

public class WaterMark {

	public static final String Destination = "/Users/jessicakline/Desktop/files/ImageWithWatermark.pdf";
	
	public static final String Image1 = "./src/main/resources/images/IMG_0854.jpeg";
	public static final String Image2 = "./src/main/resources/images/IMG_0973.jpeg";
	public static final String Image3 = "./src/main/resources/images/IMG_1015.jpeg";
	public static final String Image4 = "./src/main/resources/images/IMG_1062.jpeg";
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file = new File(Destination);
		file.getParentFile().mkdirs();
		
		new WaterMark().manipulatePdf(Destination);
	}
	
	protected void manipulatePdf(String Destination) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(Destination));
		Document doc = new Document(pdfDoc);
		
		Image img = getWatermarkedImage(pdfDoc, new Image(ImageDataFactory.create(Image1)), "first");
		doc.add(img);
		
		doc.close();
		
	}

	private static Image getWatermarkedImage(PdfDocument pdfDoc, Image image, String watermark) {
		// TODO Auto-generated method stub
		float width = image.getImageScaledWidth();
		float height = image.getImageScaledHeight();
		PdfFormXObject template = new PdfFormXObject(new Rectangle(width,height));
		new Canvas(template, pdfDoc)
			.add(image)
			.setFontColor(DeviceGray.BLACK)
			.showTextAligned(watermark, width / 2, height / 2, TextAlignment.CENTER, (float) Math.PI / 6)
			.close();
		return new Image(template);
	}

}
