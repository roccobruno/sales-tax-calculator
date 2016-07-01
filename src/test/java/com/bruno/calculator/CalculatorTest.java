package com.bruno.calculator;

import com.bruno.model.Item;
import com.sun.javafx.binding.StringFormatter;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.function.Function;

import static com.bruno.util.BigDecimalUtil.price;
import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() throws Exception {
          calculator = new SimpleCalculator();
    }

    private void assertCalculationBasicRateResult(String itemPrice, String expectedTaxes) {
        assertCalculationResult(itemPrice, expectedTaxes, calculator::calculateSaleTaxesFor, Item::itemInstance);
    }

    private void assertCalculationResult(String itemPrice, String expectedTaxes, Function<Item,BigDecimal> functionCalculator , Function<BigDecimal,Item> createItem ) {
        Item item = createItem.apply(price(itemPrice));
        BigDecimal taxes = functionCalculator.apply(item);
        BigDecimal expected = price(expectedTaxes);
        String message = StringFormatter.format("sale taxes for an item with price  %s should be %s",itemPrice,expectedTaxes).getValue();
        assertEquals(message, expected, taxes);
    }

    private void assertCalculationImportedItemResult(String itemPrice, String expectedTaxes) {
        assertCalculationResult(itemPrice, expectedTaxes, calculator::calculateSaleTaxesFor, Item::importedItemInstance);
    }

    private void assertGenericCalculationImportedItemResult(String itemPrice, String expectedTaxes,Function<Item,BigDecimal> functionCalculator ) {
        assertCalculationResult(itemPrice, expectedTaxes, functionCalculator, Item::importedItemInstance);
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
    public void testCalculateTotalTaxesForImportedItem() {
        assertCalculationImportedItemResult("150.00", "22.5");
    }

    @Test
    public void testCalculateTotalTaxesForImportedItemWithPens() {
        assertCalculationImportedItemResult("150.80", "22.62");
    }

    @Test
    public void testCalculateTotalTaxesForImportedItemWithPriceOfOne() {
        assertCalculationImportedItemResult("1.0", "0.15");
    }

    @Test
    public void testCalculateTotalTaxesForImportedItemWithPriceLessThanOne() {
        assertCalculationImportedItemResult("0.8", "0.12");
    }

    @Test
    public void testCalculateTaxesForImportedItem(){
        assertGenericCalculationImportedItemResult("10.00", "0.5", calculator::calculateTaxesForImportedItem);
    }

    @Test
    public void testCalculateTaxesForItem() {
        assertGenericCalculationImportedItemResult("10.00","1.0",calculator::calculateTaxesForItem);
    }



}
