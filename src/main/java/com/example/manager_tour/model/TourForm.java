package com.example.manager_tour.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

public class TourForm {


    private int id;
    private String code;
    private String destination;
    private int price;
    private MultipartFile img;

    public TourForm() {
    }

    public TourForm(int id, String code, String destination, int price, MultipartFile img) {
        this.id = id;
        this.code = code;
        this.destination = destination;
        this.price = price;
        this.img = img;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }
}
