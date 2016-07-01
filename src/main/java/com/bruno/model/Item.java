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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (price != null ? !price.equals(item.price) : item.price != null) return false;
        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        return !(isImported != null ? !isImported.equals(item.isImported) : item.isImported != null);

    }

    @Override
    public int hashCode() {
        int result = price != null ? price.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isImported != null ? isImported.hashCode() : 0);
        return result;
    }
}
