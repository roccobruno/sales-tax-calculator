package com.bruno.model;

import com.sun.javafx.binding.StringFormatter;

import java.math.BigDecimal;

public class Item {

    public static Item itemInstance(BigDecimal price) {
        return new Item(price, "generic item");
    }

    public static Item itemInstance(BigDecimal price, String description) {
        return new Item(price,description);
    }

    public static Item importedItemInstance(BigDecimal price) {
        return new Item(price, true, "generic item");
    }

    public static Item importedItemInstance(BigDecimal price, String description) {
        return new Item(price, true, "generic item");
    }

    private Item(BigDecimal price, Boolean isImported, String description) {
        if(price.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("price cannot be negative");
        this.price = price;
        this.isImported = isImported;
        this.description = description;
    }


    private Item(BigDecimal price, String description) {
        if(price.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("price cannot be negative");
        this.price = price;
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Boolean isImported() {
        return isImported;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String importedMessage = this.isImported ? "imported" : "";
        return  StringFormatter.format("1 %s %s :%s", importedMessage, this.description, this.price.toString() ).getValue();
    }

    private BigDecimal price;
    private String description;
    private Boolean isImported = false;


}
