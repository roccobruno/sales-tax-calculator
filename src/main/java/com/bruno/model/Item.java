package com.bruno.model;

import java.math.BigDecimal;

public class Item {

    public static Item itemInstance(BigDecimal price) {
        return new Item(price);
    }

    public static Item importedItemInstance(BigDecimal price) {
        return new Item(price, true);
    }

    private Item(BigDecimal price, Boolean isImported) {
        if(price.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("price cannot be negative");
        this.price = price;
        this.isImported = isImported;
    }

    private Item(BigDecimal price) {
         if(price.compareTo(BigDecimal.ZERO) < 0)
             throw new IllegalArgumentException("price cannot be negative");
         this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean isImported() {
        return isImported;
    }

    private BigDecimal price;
    private Boolean isImported;
}
