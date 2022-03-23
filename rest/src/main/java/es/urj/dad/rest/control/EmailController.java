package es.urj.dad.rest.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

/*public class EmailController {
	RestTemplate restTemplate = new RestTemplate();
	String url="https://www.googleapis.com/.../volumes?q=intitle:"+title;
}*/

class VolumeInfo {
	 public String title;
}

//Si usasemos Feign Client
/*@RestController
public class BooksController {
@Autowired BooksService service;
@GetMapping("/booktitles")
public List<String> getBookTitles(@RequestParam String title) {
BooksResponse data = service.getBooks("intitle:" + title);
List<String> bookTitles = new ArrayList<String>();
for (Book book : data.items) {
bookTitles.add(book.volumeInfo.title);
}
return bookTitles;
}
}*/