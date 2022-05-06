package es.urjc.dad.rest.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;

@Service
public class EmailService {
	
    private final String REGISTRATION_SUBJECT = "¡Bienvenido a Leaguesports!";
    private final String REGISTRATION_CONTENT = "Bienvenido, {0}";

    private final String EVENT_SUBJECT = "¡Novedades de Leaguesports!";
    private final String EVENT_CONTENT = "Enhorabuena, {0}!\n\nSu equipo ha conseguido insicribirse en el torneo {1}, puede consultar los detalles en https://localhost:8443/torneo/{2} \n";

	@Autowired private JavaMailSender javaMailSender;
    @Autowired private CSVService csvService;
    @Autowired private PDFService pdfService;

    public void sendRegistrationEmail(String receiver, String name) throws MailException {
        
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(receiver);
        msg.setSubject(REGISTRATION_SUBJECT);
        msg.setText(getRegistrationContent(name));

        javaMailSender.send(msg);
    }

    public void sendEventEmail(String receiver, String name, String eventName, long eventId){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(receiver);
        msg.setSubject(EVENT_SUBJECT);
        msg.setText(getEventContent(name, eventName, eventId));

        javaMailSender.send(msg);
    }

    private String getRegistrationContent(String name){
        Object[] params = new Object[]{name};
        return MessageFormat.format(REGISTRATION_CONTENT, params);	
    }

    private String getEventContent(String name, String eventName, long eventId){
        
        Object[] params = new Object[]{name, eventName, eventId};
        return MessageFormat.format(EVENT_CONTENT, params);	
    }

    public void sendGameTableEmail(String receiver, List<Map<String, Object>> games) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(receiver);
        helper.setSubject("Partidos");

        List<String[]> serializedGames = csvService.serializeGameData(games);
        String filepath;
        try {
            filepath = csvService.generateCSV(serializedGames);
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(filepath);
            multipart.addBodyPart(attachment);
            msg.setContent(multipart);
        } catch (IOException e) {
            msg.setText("Lo sentimos, el archivo no se ha podido enviar.");
        }       

        javaMailSender.send(msg);
    }

    public void sendProductEmail(String receiver, String productName, double productPrice) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(receiver);
        helper.setSubject("Factura de compra");
        String filepath;
        try {
            filepath = pdfService.createBillPDF(productName, productPrice);
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(filepath);
            multipart.addBodyPart(attachment);
            msg.setContent(multipart);
        } catch (IOException e) {
            msg.setText("Lo sentimos, el archivo no se ha podido enviar.");
        } catch (DocumentException e) {
            msg.setText("Error en la factura.");
        }
        javaMailSender.send(msg);
    }
}
