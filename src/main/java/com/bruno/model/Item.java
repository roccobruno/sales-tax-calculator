package com.bruno.model;

import java.math.BigDecimal;

public class Item {
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private BigDecimal price;

    public Item(BigDecimal price) {
         this.price = price;
    }
}
