package es.urjc.dad.leaguesports.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import es.urjc.dad.leaguesports.services.UserService;

@Controller
public class LoginController extends BaseController{
    
    @Autowired UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/loginerror")
    public String loginerror(){
        return "loginerror";
    }   
}
