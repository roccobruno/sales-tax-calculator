package com.bruno.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private List<Item> items = new ArrayList<>();

    public Basket(List<Item> items) {
        this.items = items;
    }

    public Basket() {}

    public void addItem(Item item) {
        this.items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }
}
