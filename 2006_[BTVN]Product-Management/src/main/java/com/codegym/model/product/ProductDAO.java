package com.codegym.model.product;

import org.springframework.web.multipart.MultipartFile;

public class ProductDAO {
    Long id;
    String name;
    float price;
    String description;
    MultipartFile avatar;
    Long category_id;

    public ProductDAO() {
    }

    public ProductDAO(String name, float price, String description, MultipartFile avatar ,Long category_id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.avatar = avatar;
        this.category_id = category_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
