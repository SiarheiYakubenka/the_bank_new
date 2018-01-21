package by.itclass.the_bank.core.accounts;

import java.math.BigDecimal;

public enum Currency {

    BYN("Белорусские рубли","1.9684"),
    USD("Доллары США","1"),
    EUR("Евро","0.855194"),
    RUR("Российские рубли","57.720954");

    private BigDecimal price, commission;
    private String description;

    Currency(String description, String price){
        this.price = new BigDecimal(price);
        this.description = description;
    }
    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена валюты не может быть <= 0");
        }
        this.price = new BigDecimal(String.valueOf(price));
    }


    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
