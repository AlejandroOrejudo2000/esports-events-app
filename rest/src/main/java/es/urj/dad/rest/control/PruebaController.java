package es.urj.dad.rest.control;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {
	
	@Autowired
    private JavaMailSender javaMailSender;
	
    @GetMapping("/email")
    public Prueba prueba() {
    	sendEmail();
        return new Prueba("A", 1);
    }
    
    public void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("hecoyek688@shopxda.com");

        msg.setSubject("Prueba correo Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }
    
    @GetMapping("/emailfile")
    public Prueba segundaPrueba() throws MessagingException {
    	sendEmailWithAttachment();
    	return new Prueba("A", 1);
    }
    
    public void sendEmailWithAttachment() throws MessagingException {
        
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        
        helper.setTo("hecoyek688@shopxda.com");
        
        helper.setSubject("Prueba 2 correo Spring Boot");
        helper.setText("Email with file \n Spring Boot Email");
            
        helper.addAttachment("example.txt", new ClassPathResource("example.txt"));

        javaMailSender.send(msg);

    }
    
}
