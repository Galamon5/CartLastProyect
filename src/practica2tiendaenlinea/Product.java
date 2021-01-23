package practica2tiendaenlinea;

import java.io.Serializable;
import java.util.*;

public class Product implements Serializable {

    private long ID;
    private String name;
    private String description;
    private double price;
    private int stock;
    private ArrayList<byte[]> images;

    public Product(long ID, String name, String description, double price, int stock) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        images = new ArrayList<byte[]>();
    }

    public long getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public void rename(String n) {
        this.name = n;
    }

    public String getDescription() {
        return this.description;
    }

    public void changeDescription(String s) {
        this.description = s;
    }

    public double getOriginalPrice() {
        return this.price;
    }

    public void changePrice(double d) {
        this.price = d;
    }

    public int getStock() {
        return this.stock;
    }

    public void addStock(int n) {
        this.stock += n;
    }

    public byte[] getImage(int i) {
        if (i < images.size()) {
            return this.images.get(i);
        }
        return null;
    }

    public void addImage(byte[] img) {
        this.images.add(img);
    }

    public int imageCount() {
        return this.images.size();
    }
}
