package es.urjc.dad.leaguesports.control;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.urjc.dad.leaguesports.model.Product;
import es.urjc.dad.leaguesports.services.ProductService;


@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/productos")
    public String showPlayers(Model model, Pageable page) {   

        model.addAttribute("productList", productService.getAllProducts(page));
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

