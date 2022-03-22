package es.urjc.dad.leaguesports.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import es.urjc.dad.leaguesports.model.User;
import es.urjc.dad.leaguesports.services.UserService;


@Controller
public class UserController {

    @Autowired private PasswordEncoder encoder;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/usuario/registro")
    public String register(){

        return "register";
    }

    @PostMapping("/usuario/nuevo")
    public String addUser(Model model, String userName, String password, String email){
    	
        if (!userService.checkIfUserExists(userName)){
            User user = userService.registerUser(userName, encoder.encode(password), email);
            System.out.println("----------------------" + user.toString());
            userService.addUser(user);
            return "redirect:/";
        }
        else
            return "redirect:/usuario/registro";
        
    }

}