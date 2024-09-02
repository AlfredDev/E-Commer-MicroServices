package com.alfred.product.Service;

import com.alfred.product.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRespository extends JpaRepository<Product, Integer> {
    List<Product> findAllByIdInOrderById(List<Integer> productsIds);
}
