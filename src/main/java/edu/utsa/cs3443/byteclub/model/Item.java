package edu.utsa.cs3443.byteclub.model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Item {

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isHasNutritionalValue() { return hasNutritionalValue; }
    public void setHasNutritionalValue(boolean hasNutritionalValue) { this.hasNutritionalValue = hasNutritionalValue; }

    public String getImageURL() {
        return imageURL;
    }

    public enum Category { BOXING, SUPPLEMENTS, APPAREL }

    private final int id;
    private String name;
    private double price;
    private String imageURL;
    private boolean hasNutritionalValue;
    private Category category;

    public Item(int id, String name, double price, String imageURL, Category category) {
        this.id =  id;
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
        this.category = category;
    }

    public static Item queryItem(int id) {
        try (Scanner scnr = new Scanner(new File("data/appData/items.csv"));) {
            scnr.nextLine();
            while (scnr.hasNextLine()) {
                Scanner line = new Scanner(scnr.nextLine());
                line.useDelimiter(",");
                if (line.nextInt() == id) {
                    String itemName = line.next();
                    double price = line.nextDouble();
                    String itemURL = line.next();
                    return new Item(id, itemName, price, itemURL, Category.BOXING);
                }
            }
        } catch (IOException e) {}
        return null;
    }


    public int getId() {
        return id;
    }


}
