package es.urj.dad.rest.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {
    
    @GetMapping("/prueba")
    public Prueba prueba() {
        return new Prueba("A", 1);
    }
}
