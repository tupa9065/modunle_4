package com.codegym.service;

import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService implements IProductService{
    @Autowired
    IProductRepository productRepository;
    @Override
    public List<Product> findAll() throws Exception {
        return productRepository.findAll();
    }

    @Override
    public Product create(Product product) throws Exception {
        return productRepository.create(product);
    }

    @Override
    public Product findByID(int id) throws Exception {
        return productRepository.findByID(id);
    }

    @Override
    public Product update(int id, Product product) throws Exception {
        return productRepository.update(id,product);
    }

    @Override
    public void delete(int id) throws Exception {
        productRepository.delete(id);
    }

    @Override
    public Product findByName(String name) throws Exception {
        return productRepository.findByName(name);
    }
}
