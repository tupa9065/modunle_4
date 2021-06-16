package com.codegym.repository;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository implements IProductRepository{
    private static Map<Integer,Product> productMap = new HashMap<>();
    static {
        productMap.put(1, new Product("coca", 10, 10, "Hanoi"));

        productMap.put(2, new Product("7up", 9, 10, "Danang"));

        productMap.put(3, new Product("redBull", 8, 12, "ThaiLan"));

        productMap.put(4, new Product("HaNoiBeer", 11, 12, "HaNoi"));

        productMap.put(5, new Product("SaiGonBeer", 12, 15, "Saigon"));

        productMap.put(6, new Product("HeinekenBeer", 13, 20, "Phap"));

    }
    @Override
    public List<Product> findAll() throws Exception {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public Product create(Product product) throws Exception {
        productMap.put(product.getId(), product);
        return product;
    }

    @Override
    public Product findByID(int id) throws Exception {
        return productMap.get(id);
    }

    @Override
    public Product update(int id, Product product) throws Exception {
        productMap.replace(id,product);
        return product;
    }

    @Override
    public void delete(int id) throws Exception {
        productMap.remove(id);
    }

    @Override
    public Product findByName(String name) throws Exception {
        List<Product> products = findAll();
        for (Product product :
                products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }
}
