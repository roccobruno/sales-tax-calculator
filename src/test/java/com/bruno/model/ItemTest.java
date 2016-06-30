package com.bruno.model;

import org.junit.Assert;
import org.junit.Test;

import static com.bruno.model.Item.itemInstance;
import static com.bruno.util.BigDecimalUtil.price;

public class ItemTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCannotCreatItemWithNegativePrice() {
        itemInstance(price("-1.99"));
    }


    @Test
    public void testToString() {
        Item item = itemInstance(price("23.00"));
        Assert.assertEquals("printing item description", "1  generic item 23.00", item.toString());
    }
}