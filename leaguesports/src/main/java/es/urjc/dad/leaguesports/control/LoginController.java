package es.urjc.dad.leaguesports.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/loginerror")
    public String loginerror(){
        return "loginerror";
    }

    @GetMapping("/private") 
    public String _private(){
        return "redirect:/";
    }


}
