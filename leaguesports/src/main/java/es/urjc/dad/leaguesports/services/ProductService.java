package es.urjc.dad.leaguesports.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.urjc.dad.leaguesports.model.Product;
import es.urjc.dad.leaguesports.repositories.ProductRepository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@Service
@CacheConfig
public class ProductService {

    @Autowired private ProductRepository productRepository;

    //@Cacheable(cacheNames = "products", key = "#page")
    public Page<Product> getAllProducts(Pageable page){

        Page<Product> products = productRepository.findAll(page);
        return products;
    }

    public Product getProductById(long id){

        Optional<Product> product = productRepository.findById(id);
        
        if(product.isPresent())
            return product.get();
        else
            return null;
    }

    public void addProduct(Product product) {

        productRepository.save(product);
    }

    //@CacheEvict(cacheNames = "products")
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
