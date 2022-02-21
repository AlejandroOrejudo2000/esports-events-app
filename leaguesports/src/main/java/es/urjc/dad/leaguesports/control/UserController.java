package es.urjc.dad.leaguesports.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import es.urjc.dad.leaguesports.model.User;
import es.urjc.dad.leaguesports.services.UserService;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/usuario/nuevo")
    public String addUser(Model model, User user){
    	
        userService.addUser(user);
        model.addAttribute("id", (int)user.getId());
        return "redirect:/";
    }

}