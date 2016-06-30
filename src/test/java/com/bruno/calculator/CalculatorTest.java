package com.bruno.calculator;

import com.bruno.model.Item;
import com.sun.javafx.binding.StringFormatter;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.bruno.util.BigDecimalUtil.price;
import static org.junit.Assert.*;

public class CalculatorTest {



    private Calculator calculator;

    @Before
    public void setUp() throws Exception {
          calculator = new SimpleCalculator();
    }

    private void assertCalculationBasicRateResult(String itemPrice, String expectedTaxes) {
        Item item = new Item(price(itemPrice));
        BigDecimal taxes = calculator.calculateSaleTaxesFor(item);
        BigDecimal expected = price(expectedTaxes);
        String message = StringFormatter.format("sale taxes for an item with price  %s should be %s",itemPrice,expectedTaxes).getValue();
        assertEquals(message, expected,taxes);
    }

    @Test
    public void testCalculateSaleTaxesForItem()  {
        assertCalculationBasicRateResult("10.00", "1.00");
    }

    @Test
    public void testCalculateSaleTaxesForItemWithPens()  {
        assertCalculationBasicRateResult("14.99", "1.5");
    }

    @Test
    public void testCalculateSaleTaxesForItemOfOnePrice()  {
        assertCalculationBasicRateResult("1.00", "0.1");
    }

    @Test
    public void testCalculateSaleTaxesForItemWithPriceLessThanOne()  {
        assertCalculationBasicRateResult("0.1", "0.01");
    }

    @Test
    public void testCalculateTaxesForImportedItem() {
        Item item = new Item(price("150.00"));
        BigDecimal taxes = calculator.calculateSaleTaxesForImportedItem(item);
        BigDecimal expected = price("22.5");
        String message = StringFormatter.format("sale taxes for an item with price  %s should be %s","150.00","22.5").getValue();
        assertEquals(message, expected, taxes);
    }

    @Test
    public void testCalculateTaxesForImportedItemWithPens() {
        Item item = new Item(price("150.80"));
        BigDecimal taxes = calculator.calculateSaleTaxesForImportedItem(item);
        BigDecimal expected = price("22.62");
        String message = StringFormatter.format("sale taxes for an item with price  %s should be %s","150.00","22.62").getValue();
        assertEquals(message, expected,taxes);
    }

    @Test
    public void testCalculateTaxesForImportedItemWithPriceOfOne() {
        Item item = new Item(price("1.00"));
        BigDecimal taxes = calculator.calculateSaleTaxesForImportedItem(item);
        BigDecimal expected = price("0.15");
        String message = StringFormatter.format("sale taxes for an item with price  %s should be %s","150.00","0.15").getValue();
        assertEquals(message, expected,taxes);
    }

    @Test
    public void testCalculateTaxesForImportedItemWithPriceLessThanOne() {
        Item item = new Item(price("0.8"));
        BigDecimal taxes = calculator.calculateSaleTaxesForImportedItem(item);
        BigDecimal expected = price("0.12");
        String message = StringFormatter.format("sale taxes for an item with price  %s should be %s","150.00","0.12").getValue();
        assertEquals(message, expected,taxes);
    }



}
