package es.urjc.dad.leaguesports.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionController {
	
	@GetMapping("/sesion")
	public String loadSession(){
		return "session";
	}
}