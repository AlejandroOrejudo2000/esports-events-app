package es.urjc.dad.leaguesports.control;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.urjc.dad.leaguesports.model.Product;
import es.urjc.dad.leaguesports.services.ProductService;


@Controller
public class ProductController extends BaseController{

    @Autowired
    private ProductService productService;

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

        Optional<Product> product = productService.getProductById(id);
        if(product.isPresent()) {
            model.addAttribute("product", product.get());                            
        }      
        return "product";    
    }

    @GetMapping("/nuevoproducto")
    public String newProduct(){
        return "createproduct";
    }

    @PostMapping("/producto/nuevo")
    public String addProduct(Model model, Product product){
    	
        productService.addProduct(product);
        model.addAttribute("id", (int)product.getId());
        return "productcreated";
    }

    @GetMapping("/producto/{id}/borrar")
    public String deleteProduct(@PathVariable long id){
    	
        productService.removeProduct(id);
        return "redirect:/productos";
    }
    
}

