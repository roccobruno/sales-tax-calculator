package com.bruno.model;

import org.junit.Test;

import static com.bruno.util.BigDecimalUtil.price;
import static org.junit.Assert.*;

public class ItemTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCannotCreatItemWithNegativePrice() {
        new Item(price("-1.99"));
    }
}