package com.EmreAydin.ecommerceapi.Api.Controller.Product;

import com.EmreAydin.ecommerceapi.Api.Model.ProductBody;
import com.EmreAydin.ecommerceapi.Exception.ProductAlreadyExistsException;
import com.EmreAydin.ecommerceapi.Models.Product;
import com.EmreAydin.ecommerceapi.Service.ProductService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> getProducts(){ return productService.getProducts(); }

    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@Valid @RequestBody ProductBody productBody) {
        try {
            productService.addProduct(productBody);
            return ResponseEntity.ok().build();
        } catch (ProductAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
