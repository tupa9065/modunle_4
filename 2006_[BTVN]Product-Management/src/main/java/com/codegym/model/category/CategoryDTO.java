package com.codegym.model.category;

public class CategoryDTO implements Cloneable{
    Long Id;
    String Name;

    public CategoryDTO() {
    }

    public CategoryDTO(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
