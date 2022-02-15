package es.urjc.dad.leaguesports.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String loadMain(){
		return "main";
	}
}
