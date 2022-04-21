package es.urjc.dad.rest.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PDFService {
	
	private final String filepath = "./file.pdf";
    
    public String createPDF(String content) throws FileNotFoundException, DocumentException{
    	Document document = new Document();
    	
		PdfWriter.getInstance(document, new FileOutputStream(filepath));
		
    	document.open();
    	Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
    	Chunk chunk = new Chunk(content, font);

		document.add(chunk);
    	document.close();
    	
    	return filepath;
    }

	public String createBillPDF(String productName, double productPrice) throws FileNotFoundException, DocumentException{
    	Document document = new Document();
    	
		PdfWriter.getInstance(document, new FileOutputStream(filepath));
		
    	document.open();
    	Font fontheader = FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.BLACK);
		Font fontbase = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Paragraph header = new Paragraph("Factura de compra: \n\n", fontheader);
		Paragraph content = new Paragraph(productName + ":\t\t" + productPrice +"\n", fontbase);
		document.add(header);
		document.add(content);
    	document.close();
    	
    	return filepath;
    }
}
