package es.urjc.dad.leaguesports.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LoggingController {
	Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @RequestMapping("/loginerror")
    public String index() {
        logger.debug("Intentando entrar como usuario");
        logger.warn("Se ha producido un error al intentar entrar como usuario");

        return "fallo de login";
    }
}
