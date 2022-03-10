package es.urjc.dad.leaguesports.control;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class BaseController {
    
    @ModelAttribute("user")
    public void authenticationAttributes(Model model, HttpServletRequest request){
        
        Principal userPrincipal = request.getUserPrincipal();
        if (userPrincipal != null){
            model.addAttribute("user_logged", true);
            model.addAttribute("user_name", userPrincipal.getName());
        }
        else {
            model.addAttribute("user_logged", false);
        }
    }

}
