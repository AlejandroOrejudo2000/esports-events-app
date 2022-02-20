package es.urjc.dad.leaguesports.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column
    private String productName;
    
    @Column
    private float price;
    
    @ManyToOne
    private User user;

    public Product(String productName, int price) {        
        this.productName = productName;
        this.price = price;
    }

    protected Product() {
    }

    //#region Getters & Setters

    public long getId() {
        return Id;
    }
    
    public void setId(long id) {
        Id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    //#endregion

    @Override
    public String toString() {
        return "Product [" + productName + "]";
    }
	
}
