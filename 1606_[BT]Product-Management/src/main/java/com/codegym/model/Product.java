package com.codegym.model;

public class Product {
    int id;
    String name;
    int amount;
    float price;
    String detail;
    public static int lastId = 0;
    public Product() {
        this.id = ++lastId;
    }
    public Product(String name, int amount, float price, String detail) {
        this.id = ++lastId;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.detail = detail;
    }

    public Product(int id, String name, int amount, float price, String detail) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
