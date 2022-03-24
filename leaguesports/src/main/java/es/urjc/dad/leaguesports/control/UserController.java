package es.urjc.dad.leaguesports.control;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import es.urjc.dad.leaguesports.model.User;
import es.urjc.dad.leaguesports.model.UserRoles;
import es.urjc.dad.leaguesports.services.EmailService;
import es.urjc.dad.leaguesports.services.UserService;


@Controller
public class UserController {

    @Autowired private PasswordEncoder encoder;    
    @Autowired private UserService userService;
    @Autowired private EmailService emailService;
    
    @GetMapping("/usuario/registro")
    public String register(){

        return "register";
    }

    @PostMapping("/usuario/nuevo")
    public String addUser(Model model, String userName, String password, String email, String organizer, String admin){
    	
        if (!userService.checkIfUserExists(userName)){
            User user = new User(userName, encoder.encode(password), email);
            user.addRole(UserRoles.User);

            emailService.SendRegisterEmail(email, userName);

            System.out.println(organizer);
            if(organizer != null) user.addRole(UserRoles.Organizer);
            if(admin != null) user.addRole(UserRoles.Admin);
            userService.addUser(user);
            return "redirect:/";
        }
        else
            return "redirect:/usuario/registro";        
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request){
        Principal userPrincipal = request.getUserPrincipal();
        if(userPrincipal != null){
            Optional<User> u = userService.getUserbyName(userPrincipal.getName().toString());
            model.addAttribute("user_logged", true);
            model.addAttribute("user_name", userPrincipal.getName());
            model.addAttribute("user_organizer", request.isUserInRole(UserRoles.Organizer.toString()));
            model.addAttribute("user_admin", request.isUserInRole(UserRoles.Admin.toString()));
            if(u.isPresent()){                
                model.addAttribute("email", u.get().getEmail());
            }
            else{
                model.addAttribute("email", "");
            }            
        }
        return "profile";        
    }
    
}