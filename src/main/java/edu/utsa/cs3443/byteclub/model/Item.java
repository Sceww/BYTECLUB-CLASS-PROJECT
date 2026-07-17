package edu.utsa.cs3443.byteclub;

public class Item {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public boolean isHasNutritionalValue() {
        return hasNutritionalValue;
    }

    public void setHasNutritionalValue(boolean hasNutritionalValue) {
        this.hasNutritionalValue = hasNutritionalValue;
    }

    private double price;
    private boolean inStock;
    private boolean hasNutritionalValue;

    Item(String name, double price, boolean inStock, boolean hasNutritionalValue) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.hasNutritionalValue = hasNutritionalValue;

    }



}
