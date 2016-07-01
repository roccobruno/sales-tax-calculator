package com.bruno.service;

import com.bruno.calculator.SimpleCalculator;
import com.bruno.model.Basket;
import org.junit.Before;
import org.junit.Test;

import static com.bruno.model.Item.importedItemInstance;
import static com.bruno.model.Item.itemInstance;
import static com.bruno.util.BigDecimalUtil.price;
import static org.junit.Assert.assertTrue;

public class BasketServiceTest {

    private BasketService basketService;

    @Before
    public void setUp() throws Exception {
        basketService = new BasketServiceImpl(new SimpleCalculator(), new FileInputService());
    }

    @Test
    public void testLoadBasket() throws Exception {

        Basket basket = basketService.loadBasket();
        assertTrue("basket should not be empty", !basket.getItems().isEmpty());
        assertTrue("basket should contain expected item1", basket.getItems().contains(itemInstance(price("10.00"), "item1")));
        assertTrue("basket should contain expected item2", basket.getItems().contains(importedItemInstance(price("20.00"), "item2")));
        assertTrue("basket should contain expected item3", basket.getItems().contains(itemInstance(price("30.00"), "item3")));

    }



}