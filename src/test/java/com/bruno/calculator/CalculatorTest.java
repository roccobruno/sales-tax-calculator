package com.bruno.calculator;

import com.bruno.model.Item;
import com.bruno.util.BigDecimalUtil;
import com.sun.javafx.binding.StringFormatter;
import org.junit.Assert;
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

    private void assertCalculationResult(String itemPrice, String expectedTaxes) {
        Item item = new Item(price(itemPrice));
        BigDecimal taxes = calculator.calculateSaleTaxesFor(item);
        BigDecimal expected = price(expectedTaxes);
        String message = StringFormatter.format("sale taxes for an item with price  %s should be %s",itemPrice,expectedTaxes).getValue();
        assertEquals(message, expected,taxes);
    }

    @Test
    public void testCalculateSaleTaxesForItem()  {
        assertCalculationResult("10.00","1.00");
    }

    @Test
    public void testCalculateSaleTaxesForItemWithPens()  {
        assertCalculationResult("14.99", "1.5");
    }

    @Test
    public void testCalculateSaleTaxesForItemOfOnePrice()  {
        assertCalculationResult("1.00", "0.1");
    }

    @Test
    public void testCalculateSaleTaxesForItemWithPriceLessThanOne()  {
        assertCalculationResult("0.1", "0.01");
    }



}
