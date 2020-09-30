package models;

public class Product {

    private String SKU_ID;

    private int price;

    public String getSKU_ID() {
        return SKU_ID;
    }

    public void setSKU_ID(String SKU_ID) {
        this.SKU_ID = SKU_ID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
