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

    @Test
    public void testCalculateSaleTaxesForItem()  {

        Item item = new Item(price("10.00"));
        BigDecimal taxes = calculator.calculateSaleTaxesFor(item);
        BigDecimal expected = price("1.00");
        assertEquals("sale taxes for an item with price 10.00 should be 1.00", expected,taxes);


    }

    @Test
    public void testCalculateSaleTaxesForItemWithPens()  {

        Item item = new Item(price("14.99"));
        BigDecimal taxes = calculator.calculateSaleTaxesFor(item);
        BigDecimal expected = price("1.5");
        assertEquals("sale taxes for an item with price 14.99 should be 1.5", expected,taxes);


    }

    @Test
    public void testCalculateSaleTaxesForItemOfOnePrice()  {

        Item item = new Item(price("1.00"));
        BigDecimal taxes = calculator.calculateSaleTaxesFor(item);
        BigDecimal expected = price("0.1");
        assertEquals("sale taxes for an item with price 1.00 should be 0.1", expected,taxes);


    }


}
