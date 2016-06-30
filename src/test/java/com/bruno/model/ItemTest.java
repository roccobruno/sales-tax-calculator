package com.bruno.model;

import org.junit.Test;

import static com.bruno.model.Item.itemInstance;
import static com.bruno.util.BigDecimalUtil.price;

public class ItemTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCannotCreatItemWithNegativePrice() {
        itemInstance(price("-1.99"));
    }
}