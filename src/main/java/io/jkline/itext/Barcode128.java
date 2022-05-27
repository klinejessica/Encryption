package io.jkline.itext;

import java.io.File;

import com.itextpdf.barcodes.BarcodeEAN;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

public class Barcode128 {

	public static final String Destination = "/Users/jessicakline/Desktop/files/sample2.pdf";
	
	public static void main(String[] args) throws Exception {
		//
		
		File file = new File(Destination);
		file.getParentFile().mkdirs();
		
		new Barcode128().manipulatePdf(Destination);
		
		System.out.println("Success");
	}
	
	protected void manipulatePdf(String Destination) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(Destination));
		Document doc = new Document(pdfDoc);
		Table table = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
		
		for(int i = 0; i < 40; i++) {
			table.addCell(createBarcode(String.format("%08d", i), pdfDoc));
		}
		
		doc.add(table);
		
		doc.close();
	}
	
	private static Cell createBarcode(String code, PdfDocument pdfDoc) {
		BarcodeEAN barcode = new BarcodeEAN(pdfDoc);
		barcode.setCodeType(BarcodeEAN.EAN8);
		barcode.setCode(code);
		
		PdfFormXObject barcodeObject = barcode.createFormXObject(null, null, pdfDoc);
		Cell cell = new Cell().add(new Image(barcodeObject));
		cell.setPadding(10);
		
		return cell;
		
	}


}
