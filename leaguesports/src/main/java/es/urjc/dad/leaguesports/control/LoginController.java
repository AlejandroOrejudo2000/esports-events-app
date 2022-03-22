package es.urjc.dad.leaguesports.control;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.urjc.dad.leaguesports.model.User;
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

    @GetMapping("/profile")
    public String profile(Model model){
        Optional<User> u = userService.getUserbyName(model.getAttribute("user_name").toString());
        if(u.isPresent()){
            model.addAttribute("email", u.get().getEmail());
        }
        else{
            model.addAttribute("email", "");
        }
        return "profile";
    }

}
