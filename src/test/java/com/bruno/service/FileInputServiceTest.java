package com.bruno.service;

import com.bruno.model.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.bruno.model.Item.importedItemInstance;
import static com.bruno.model.Item.itemInstance;
import static com.bruno.util.BigDecimalUtil.price;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileInputServiceTest {

    private InputService service;
    @Before
    public void setUp() throws Exception {
        service = new FileInputService();
    }

    @Test
    public void testLoadItems() throws Exception {
       List<Item> items = service.loadItems();
       assertEquals("the service should load 3 items",3,items.size());
        assertTrue("basket should contain expected item1", items.contains(itemInstance(price("10.00"), "item1")));
        assertTrue("basket should contain expected item2", items.contains(importedItemInstance(price("20.00"), "item2")));
        assertTrue("basket should contain expected item3", items.contains(itemInstance(price("30.00"), "item3")));
    }

    @Test
    public void testLoadItemsFromSpecificFileName() throws Exception {

        List<Item> items = service.loadItems("basket2.csv");
        assertTrue("basket should not be empty", !items.isEmpty());
        assertTrue("basket should not contain bad format item", items.size() == 2);
        assertTrue("basket should contain expected item1", items.contains(itemInstance(price("10.00"), "item1")));
        assertTrue("basket should contain expected item3", items.contains(itemInstance(price("30.00"), "item3")));

    }


    @Test(expected = IllegalArgumentException.class)
    public void testLoadBasketException() {
        service.loadItems("not_existing_file.csv");
    }
}