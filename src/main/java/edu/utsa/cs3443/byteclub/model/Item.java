package edu.utsa.cs3443.byteclub.model;

public class Item {

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isInStock() { return inStock; }
    public void setInStock(boolean inStock) { this.inStock = inStock; }

    public boolean isHasNutritionalValue() { return hasNutritionalValue; }
    public void setHasNutritionalValue(boolean hasNutritionalValue) { this.hasNutritionalValue = hasNutritionalValue; }

    public enum Category { BOXING, SUPPLEMENTS, APPAREL }

    private String name;
    private double price;
    private boolean inStock;
    private boolean hasNutritionalValue;
    private Category category;

    public Item(String name, double price, boolean inStock, boolean hasNutritionalValue, Category category) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.hasNutritionalValue = hasNutritionalValue;
        this.category = category;

    }



}
