package models;

public class Promotion {

    private String SKU_ID;
    private int min_order_elegibility;
    private int rate;
    private int discount;
    private String combo;
    private String comboWith;


    public String getSKU_ID() {
        return SKU_ID;
    }

    public void setSKU_ID(String SKU_ID) {
        this.SKU_ID = SKU_ID;
    }

    public int getMin_order_elegibility() {
        return min_order_elegibility;
    }

    public void setMin_order_elegibility(int min_order_elegibility) {
        this.min_order_elegibility = min_order_elegibility;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getCombo() {
        return combo;
    }

    public void setCombo(String combo) {
        this.combo = combo;
    }

    public String getComboWith() {
        return comboWith;
    }

    public void setComboWith(String comboWith) {
        this.comboWith = comboWith;
    }
}
