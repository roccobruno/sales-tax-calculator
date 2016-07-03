package com.bruno.model;

import org.junit.Test;

import static com.bruno.model.Item.itemInstance;
import static com.bruno.util.BigDecimalUtil.bd;
import static org.junit.Assert.assertEquals;

public class ItemTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCannotCreatItemWithNegativePrice() {
        itemInstance(bd("-1.99"));
    }


    @Test
    public void testToString() {
        Item item = itemInstance(bd("23.00"));
        assertEquals("printing item description", "1  generic item of type other :23.00", item.toString());
    }
}