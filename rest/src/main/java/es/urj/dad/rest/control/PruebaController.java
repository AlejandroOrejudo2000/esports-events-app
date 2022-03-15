package es.urj.dad.rest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    
}
