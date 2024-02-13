package com.devdroid.ecomadmin;

public class ProductModel {

    private String id;
    private String title;
    private  String description;
    private String price;
    private String image;
    private boolean show;

    public ProductModel() {
    }

    public ProductModel(String id, String title, String description,String price, String image, boolean show) {
        this.title = title;
        this.id =id;
        this.price =price;
        this.description = description;
        this.image = image;
        this.show = show;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }
}
