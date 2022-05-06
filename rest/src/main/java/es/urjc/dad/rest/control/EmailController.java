package es.urjc.dad.rest.control;

import java.io.FileNotFoundException;
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

import com.itextpdf.text.DocumentException;

import es.urjc.dad.rest.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	@Autowired private EmailService emailService;

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
			return ResponseEntity.internalServerError().body("Error, problema con el envío del mensaje."); 
		}
	}

	@PostMapping("/product")
	public ResponseEntity<String> sendProductEmail(@RequestBody Map<String, Object> body){
		String receiver = (String) body.get("receiver");
		String productName = (String) body.get("productName");
		double productPrice = (Double) body.get("productPrice");
		try{			
			emailService.sendProductEmail(receiver, productName, productPrice);
			return ResponseEntity.ok().body("Email enviado");
		}
		catch (MailException e)  {
			return ResponseEntity.badRequest().body("Error, dirección de correo incorrecta."); 
		} catch (MessagingException e) {
			return ResponseEntity.internalServerError().body("Error, problema con el envío del mensaje."); 
		}
	}
}
