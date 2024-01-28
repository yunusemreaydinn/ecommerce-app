package com.EmreAydin.ecommerceapi.Service;

import com.EmreAydin.ecommerceapi.Api.Model.ProductBody;
import com.EmreAydin.ecommerceapi.Exception.ProductAlreadyExistsException;
import com.EmreAydin.ecommerceapi.Models.DAO.ProductDAO;
import com.EmreAydin.ecommerceapi.Models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getProducts() {
        return productDAO.findAll();
    }

    public Product addProduct(ProductBody productBody) throws ProductAlreadyExistsException {

        if(productDAO.findByNameIgnoreCase(productBody.getName()).isPresent()){
            throw new ProductAlreadyExistsException();
        }

        Product product = new Product();
        product.setName(productBody.getName());
        product.setPrice(productBody.getPrice());
        product.setShortDescription(productBody.getShortDescription());
        product.setLongDescription(productBody.getLongDescription());
        return productDAO.save(product);
    }

}
