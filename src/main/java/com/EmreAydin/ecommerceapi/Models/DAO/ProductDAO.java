package com.EmreAydin.ecommerceapi.Models.DAO;

import com.EmreAydin.ecommerceapi.Models.Product;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface ProductDAO extends ListCrudRepository<Product, Long> {

    Optional<Product> findByNameIgnoreCase(String name);

}
