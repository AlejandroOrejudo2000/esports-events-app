package es.urjc.dad.leaguesports.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.dad.leaguesports.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
