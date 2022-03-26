package es.urj.dad.rest.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
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
    
}
