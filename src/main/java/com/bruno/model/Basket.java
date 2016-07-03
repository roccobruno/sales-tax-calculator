package com.bruno.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private final List<Item> items;

    public Basket(List<Item> items) {
        this.items = items;
    }

    public Basket() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }
}
