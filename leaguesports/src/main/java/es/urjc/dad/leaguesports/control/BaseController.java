package es.urjc.dad.leaguesports.control;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import es.urjc.dad.leaguesports.model.UserRoles;


@Controller
public class BaseController {
    
    @ModelAttribute("user")
    public void authenticationAttributes(Model model, HttpServletRequest request){
        
        Principal userPrincipal = request.getUserPrincipal();
        if (userPrincipal != null){
            model.addAttribute("user_logged", true);
            model.addAttribute("user_name", userPrincipal.getName());
            System.out.println(request.isUserInRole(UserRoles.Organizer.toString()));
            model.addAttribute("user_organizer", request.isUserInRole(UserRoles.Organizer.toString()));
            model.addAttribute("user_admin", request.isUserInRole(UserRoles.Admin.toString()));
        }
        else {
            model.addAttribute("user_logged", false);
        }
    }

}
