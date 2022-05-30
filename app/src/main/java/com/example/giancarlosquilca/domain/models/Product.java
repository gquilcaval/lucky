package com.example.giancarlosquilca.domain.models;

/**
 * Created by Giancarlos Quilca on 27/05/2022.
 */
public class Product {

    private int id;
    private String name;
    private int cost;
    private int priceRev;
    private int stock;
    private int idPoint;

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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getPriceRev() {
        return priceRev;
    }

    public void setPriceRev(int priceRev) {
        this.priceRev = priceRev;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdPoint() {
        return idPoint;
    }

    public void setIdPoint(int idPoint) {
        this.idPoint = idPoint;
    }
}

