package com.codeGym.model;

import org.springframework.web.multipart.MultipartFile;



public class CustomerDTO {

    private Long id;


    private String name;

    private MultipartFile avatar;

    public CustomerDTO() {
    }

    public CustomerDTO(String name, MultipartFile avatar) {
        this.name = name;
        this.avatar = avatar;
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

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
