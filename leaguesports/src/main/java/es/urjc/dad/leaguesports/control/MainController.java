package es.urjc.dad.leaguesports.control;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController extends BaseController{
	
	@GetMapping("/")
	public String loadMain(){
		return "main";
	}
	
private static final Path IMAGE_FOLDER = Paths.get(System.getProperty("user.dir"), "images");
	
	@GetMapping("/image/lol.jpg")
	public ResponseEntity<Object> downloadLol(Model model) throws MalformedURLException {
		
		Path imagePath = IMAGE_FOLDER.resolve("lol.jpg");
		Resource image = new UrlResource(imagePath.toUri());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "lol/jpg").body(image);
	}
	
	@GetMapping("/image/teams.png")
	public ResponseEntity<Object> downloadTeams(Model model) throws MalformedURLException {
		
		Path imagePath = IMAGE_FOLDER.resolve("teams.png");
		Resource image = new UrlResource(imagePath.toUri());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "teams/png").body(image);
	}
	
	@GetMapping("/image/tournaments.png")
	public ResponseEntity<Object> downloadTournaments(Model model) throws MalformedURLException {
		
		Path imagePath = IMAGE_FOLDER.resolve("tournaments.png");
		Resource image = new UrlResource(imagePath.toUri());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "tournaments/png").body(image);
	}
	
	@GetMapping("/image/players.png")
	public ResponseEntity<Object> downloadPlayers(Model model) throws MalformedURLException {
		
		Path imagePath = IMAGE_FOLDER.resolve("players.png");
		Resource image = new UrlResource(imagePath.toUri());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "players/png").body(image);
	}
	
	@GetMapping("/image/products.png")
	public ResponseEntity<Object> downloadProducts(Model model) throws MalformedURLException {
		
		Path imagePath = IMAGE_FOLDER.resolve("products.png");
		Resource image = new UrlResource(imagePath.toUri());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "products/png").body(image);
	}
	
}
