package es.urj.dad.rest.control;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urj.dad.rest.service.EmailService;
import es.urj.dad.rest.service.PDFService;

@RestController
public class EmailController {
	
	@Autowired private EmailService emailService;
	@Autowired private PDFService pdfService;
	
    @GetMapping("/email")
    public void prueba() {
    	emailService.sendEmail();
    }
    
    
    @GetMapping("/emailfile")
    public void segundaPrueba() throws MessagingException {
    	emailService.sendEmailWithAttachment();
    }
    
    
}
