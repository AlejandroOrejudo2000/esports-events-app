package es.urj.dad.rest.control;

import java.io.IOException;
import java.util.List;
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

import es.urj.dad.rest.service.CSVService;
import es.urj.dad.rest.service.EmailService;
import es.urj.dad.rest.service.PDFService;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	@Autowired private EmailService emailService;
	@Autowired private PDFService pdfService;
	@Autowired private CSVService csvService;

	@GetMapping("/prueba")
    public void prueba() {
    	emailService.sendEmail();
    }    
    
    @GetMapping("/file")
    public void segundaPrueba() throws MessagingException {
    	emailService.sendEmailWithAttachment();
    }

	@PostMapping("/registration")
	public ResponseEntity<String> sendRegistrationEmail(@RequestBody Map<String, Object> body){

		String receiver = (String) body.get("receiver");
		String username = (String) body.get("username");
		try{			
			emailService.sendRegistrationEmail(receiver, username);
			return ResponseEntity.ok().body("Email enviado");
		}
		catch (MailException e)  {
			return ResponseEntity.badRequest().body("Error, dirección de correo incorrecta"); 
		}
	}

	@PostMapping("/event")
	public ResponseEntity<String> sendEventEmail(@RequestBody Map<String, Object> body){
		String receiver = (String) body.get("receiver");
		String username = (String) body.get("username");
		String eventName = (String) body.get("eventName");
		long eventId = (Integer) body.get("eventId");
		try{			
			emailService.sendEventEmail(receiver, username, eventName, eventId);
			return ResponseEntity.ok().body("Email enviado");
		}
		catch (MailException e)  {
			return ResponseEntity.badRequest().body("Error, dirección de correo incorrecta"); 
		}
	}

	@PostMapping("/gametable")
	public ResponseEntity<String> sendGameTableEmail(@RequestBody Map<String, Object> body){
		String receiver = (String) body.get("receiver");
		List<Map<String, Object>> games = (List<Map<String, Object>>) body.get("games");
		try{			
			emailService.sendGameTableEmail(receiver, games);
			return ResponseEntity.ok().body("Email enviado");
		}
		catch (MailException e)  {
			return ResponseEntity.badRequest().body("Error, dirección de correo incorrecta."); 
		} catch (MessagingException e) {
			return ResponseEntity.badRequest().body("Error, problema con el envío del mensaje."); 
		}
	}
}
