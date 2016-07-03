package com.bruno.service;

import com.bruno.model.Item;

import java.util.List;

public interface InputService {

    /**
     * List of items to create the receipt from
     * @return collection of item
     */
    List<Item> loadItems();

}
