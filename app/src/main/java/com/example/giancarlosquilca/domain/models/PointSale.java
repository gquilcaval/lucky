package com.example.giancarlosquilca.domain.models;

/**
 * Created by Giancarlos Quilca on 27/05/2022.
 */
public class PointSale {

    private Integer id;
    private String name;
    private String code;
    private String direction;
    private String photo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() { return photo; }

    public void setPhoto(String photo) { this.photo = photo; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}

