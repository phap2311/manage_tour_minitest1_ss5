package com.example.manager_tour.model;

import javax.persistence.*;

@Entity
@Table(name = "tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String destination;
    private int price;
    private String img;

    public Tour() {
    }

    public Tour(int id, String code, String destination, int price, String img) {
        this.id = id;
        this.code = code;
        this.destination = destination;
        this.price = price;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
