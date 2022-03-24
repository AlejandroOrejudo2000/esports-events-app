package es.urj.dad.rest.control;

import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urj.dad.rest.service.EmailService;
import es.urj.dad.rest.service.PDFService;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	@Autowired private EmailService emailService;
	@Autowired private PDFService pdfService;

	@GetMapping("/prueba")
    public void prueba() {
    	emailService.sendEmail();
    }    
    
    @GetMapping("/file")
    public void segundaPrueba() throws MessagingException {
    	emailService.sendEmailWithAttachment();
    }

	@PostMapping("/registration")
	public ResponseEntity<String> sendRegistrationEmail(@RequestBody Map<String, String> body){

		try{
			String receiver = body.get("receiver");
			String username = body.get("username");
			emailService.sendRegistrationEmail(receiver, username);
			return ResponseEntity.ok().body("Email enviado");
		}
		catch (MailException e)  {
			return ResponseEntity.badRequest().body("Error, dirección de correo incorrecta"); 
		}
	}



}
