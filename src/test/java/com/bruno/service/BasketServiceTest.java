package com.bruno.service;

import com.bruno.model.*;
import org.junit.Before;
import org.junit.Test;

import static com.bruno.model.Item.importedItemInstance;
import static com.bruno.model.Item.itemInstance;
import static com.bruno.util.BigDecimalUtil.bd;
import static org.junit.Assert.*;

public class BasketServiceTest {

    private BasketService basketService;

    @Before
    public void setUp() throws Exception {
        basketService = new BasketServiceImpl( new FileInputService());
    }

    @Test
    public void testLoadBasket() throws Exception {

        Basket basket = basketService.loadBasket();
        assertTrue("basket should not be empty", !basket.getItems().isEmpty());
        assertTrue("basket should contain expected item1", basket.getItems().contains(itemInstance(bd("10.00"), ItemCategory.BOOK, "item1")));
        assertTrue("basket should contain expected item2", basket.getItems().contains(importedItemInstance(bd("20.00"),ItemCategory.OTHER, "item2")));
        assertTrue("basket should contain expected item3", basket.getItems().contains(itemInstance(bd("30.00"),ItemCategory.MEDICAL, "item3")));

    }

    @Test
    public void testGenerateReceiptItem() {
        Item item = itemInstance(bd("12.49"),ItemCategory.BOOK);
        ReceiptItem recItem = basketService.getItemForReceipt(item);
        assertEquals(bd("0.00"), recItem.getTaxes());
        assertEquals(bd("00.0"), recItem.getBasicTaxes());
        assertFalse(recItem.getImportedTaxes().isPresent());
    }

    @Test
    public void testGenerateReceiptItemForImportedItem() {
        Item item = importedItemInstance(bd("47.50"));
        ReceiptItem recItem = basketService.getItemForReceipt(item);
        assertEquals(bd("4.75"),recItem.getBasicTaxes());
        assertEquals(bd("2.4"), recItem.getImportedTaxes().get());
        assertEquals(bd("7.15"), recItem.getTaxes());
    }

    @Test
    public void testGenerateReceiptItemForImportedItemFood() {
        Item item = importedItemInstance(bd("11.25"),ItemCategory.FOOD);
        ReceiptItem recItem = basketService.getItemForReceipt(item);
        assertEquals(bd("0.00"),recItem.getBasicTaxes());
        assertEquals(bd("0.6"), recItem.getImportedTaxes().get());
        assertEquals(bd("0.6"),recItem.getTaxes());
    }

    @Test
    public void testGenerateReceiptFromItems() {
        Item item = importedItemInstance(bd("150.00"));
        Item item2 = itemInstance(bd("15.00"));
        Item item3 = itemInstance(bd("100.00"));
        Basket basket = new Basket();
        basket.addItem(item);
        basket.addItem(item2);
        basket.addItem(item3);
        Receipt receipt = basketService.getReceipt(basket);

        assertEquals("calculate the total for a receipt", bd("299"), receipt.getTotal());
        assertEquals("calculate the total for a receipt", bd("34"), receipt.getTotalTaxes());

    }



}