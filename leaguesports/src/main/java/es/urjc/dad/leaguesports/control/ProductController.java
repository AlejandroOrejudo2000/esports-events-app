package es.urjc.dad.leaguesports.control;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.urjc.dad.leaguesports.model.Product;
import es.urjc.dad.leaguesports.model.User;
import es.urjc.dad.leaguesports.services.EmailService;
import es.urjc.dad.leaguesports.services.ProductService;
import es.urjc.dad.leaguesports.services.UserService;


@Controller
public class ProductController extends BaseController{

    @Autowired
    private ProductService productService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @GetMapping("/productos")
    public String showPlayers(Model model, Pageable page) {   
        Page<Product> productpage = productService.getAllProducts(page);

        model.addAttribute("productList", productpage);
        model.addAttribute("hasprevious", productpage.hasPrevious());
        model.addAttribute("hasnext", productpage.hasNext());
        model.addAttribute("previous", productpage.getNumber() - 1);
        model.addAttribute("next", productpage.getNumber() + 1);

        return "products";
    }

    @GetMapping("/producto/{id}")
    public String showProductDetails(Model model, @PathVariable long id) {

        Product product = productService.getProductById(id);
        if(product != null) {
            model.addAttribute("product", product);                            
        }      
        return "product";    
    }

    @GetMapping("/private/producto/crear")
    public String newProduct(){
        return "createproduct";
    }

    @PostMapping("/private/producto/nuevo")
    public String addProduct(Model model, Product product){
    	
        productService.addProduct(product);
        model.addAttribute("id", (int)product.getId());
        return "productcreated";
    }

    @GetMapping("/private/producto/{id}/borrar")
    public String deleteProduct(@PathVariable long id){
    	
        productService.removeProduct(id);
        return "redirect:/productos";
    }

    @GetMapping("producto/{id}/comprar")
    public String buyProduct(Model model, HttpServletRequest request, @PathVariable long id){
    	Product product = productService.getProductById(id);
        if(product != null) {
            Principal userPrincipal = request.getUserPrincipal();
            if(userPrincipal != null){
                Optional<User> u = userService.getUserbyName(userPrincipal.getName().toString());
                if(u.isPresent()){
                    String email = u.get().getEmail();
                    emailService.sendProductEmail(email, product);
                    productService.removeProduct(id);
                }
            }
        }        
        return "redirect:/productos";
    }   
    
}

