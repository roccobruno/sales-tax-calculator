package com.bruno.service;

import com.bruno.model.Receipt;
import org.junit.Test;

import java.net.URL;

import static com.bruno.util.BigDecimalUtil.price;
import static org.junit.Assert.assertEquals;

public class ReceiptGeneratorTest {

    @Test
    public void testReceiptForInputFile1() {
        InputService inputService = new FileInputService(getPathFile("baskets/basket1.csv"));
        BasketService service = new BasketServiceImpl(inputService);

        Receipt receipt = service.generateReceipt();
        assertEquals("total tax should 1.5", price("1.5"), receipt.getTotalTaxes());
        assertEquals("total price should 29.83", price("29.83"), receipt.getTotal());

    }

    @Test
    public void testReceiptForInputFile2() {
        InputService inputService = new FileInputService(getPathFile("baskets/basket2.csv"));
        BasketService service = new BasketServiceImpl(inputService);
        Receipt receipt = service.generateReceipt();


        assertEquals("total tax should 7.65", price("7.65"), receipt.getTotalTaxes());
        assertEquals("total price should 65.15", price("65.15"), receipt.getTotal());


    }

    @Test
    public void testReceiptForInputFile3() {
        InputService inputService = new FileInputService(getPathFile("baskets/basket3.csv"));
        BasketService service = new BasketServiceImpl(inputService);
        Receipt receipt = service.generateReceipt();
        assertEquals("total tax should 6.70", price("6.70"), receipt.getTotalTaxes());
        assertEquals("total price should 74.68", price("74.68"), receipt.getTotal());


    }

    private String getPathFile(String fileName) {
        URL resource = getClass().getClassLoader().getResource(fileName);
        return  resource.getFile();
    }
}
