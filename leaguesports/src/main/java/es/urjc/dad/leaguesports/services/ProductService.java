package es.urjc.dad.leaguesports.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.urjc.dad.leaguesports.model.Product;
import es.urjc.dad.leaguesports.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired private ProductRepository productRepository;

    public Page<Product> getAllProducts(Pageable page){

        Page<Product> products = productRepository.findAll(page);
        return products;
    }

    public Optional<Product> getProductById(long id){

        Optional<Product> product = productRepository.findById(id);
        return product;
    }

    public void addProduct(Product product) {

        productRepository.save(product);
    }

    public void removeProduct(long id) {

        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
        	Product p = product.get();
            productRepository.delete(p);
        }            
    }

    public boolean hasAnyProduct(){
        return productRepository.count() > 0;
    }

}
