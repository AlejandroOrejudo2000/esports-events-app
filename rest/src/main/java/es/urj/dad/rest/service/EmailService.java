package es.urj.dad.rest.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
    private final String REGISTRATION_SUBJECT = "¡Bienvenido a Leaguesports!";
    private final String REGISTRATION_CONTENT = "Bienvenido, %%USERNAME%%";

	@Autowired private JavaMailSender javaMailSender;
	
	public void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("wanas74285@f1xm.com");

        msg.setSubject("Prueba correo Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);
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

    public void sendRegistrationEmail(String receiver, String name) throws MailException {
        
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(receiver);

        msg.setSubject(REGISTRATION_SUBJECT);
        msg.setText(getRegistrationContent(name));

        javaMailSender.send(msg);
    }

    private String getRegistrationContent(String name){
        return new String(REGISTRATION_CONTENT).replace("%%USERNAME%%", name);
    }
	
}
