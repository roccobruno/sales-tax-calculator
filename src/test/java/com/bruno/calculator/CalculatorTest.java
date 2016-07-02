package com.bruno.calculator;

import com.bruno.model.Item;
import com.bruno.model.ItemCategory;
import com.sun.javafx.binding.StringFormatter;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.bruno.model.ItemCategory.*;
import static com.bruno.util.BigDecimalUtil.price;
import static org.junit.Assert.assertEquals;

public class CalculatorTest {


    private void assertCalculationBasicRateResult(String itemPrice, String expectedTaxes, ItemCategory category) {
        assertCalculationResult(itemPrice, expectedTaxes,category, Calculator::calculateSaleTaxesFor, Item::itemInstance);
    }

    private void assertCalculationResult(String itemPrice, String expectedTaxes,ItemCategory category, Function<Item,BigDecimal> functionCalculator , BiFunction<BigDecimal,ItemCategory,Item> createItem ) {
        Item item = createItem.apply(price(itemPrice),category);
        BigDecimal taxes = functionCalculator.apply(item);
        BigDecimal expected = price(expectedTaxes);
        String message = StringFormatter.format("sale taxes for an item with price  %s should be %s",itemPrice,expectedTaxes).getValue();
        assertEquals(message, expected, taxes);
    }

    private void assertCalculationImportedItemResult(String itemPrice, String expectedTaxes, ItemCategory category) {
        assertCalculationResult(itemPrice, expectedTaxes, category, Calculator::calculateSaleTaxesFor, Item::importedItemInstance);
    }

    private void assertGenericCalculationItemResult(String itemPrice, String expectedTaxes, ItemCategory category, Function<Item, BigDecimal> functionCalculator) {
        assertCalculationResult(itemPrice, expectedTaxes,category, functionCalculator, Item::importedItemInstance);
    }

    @Test
    public void testCalculateSaleTaxesForItem()  {
        assertCalculationBasicRateResult("10.00", "1.00", OTHER);
    }

    @Test
    public void testCalculateSaleTaxesForItemWithSpecialCategory() {
        List<ItemCategory> categories = new ArrayList<>();
        categories.add(FOOD);
        categories.add(BOOK);
        categories.add(MEDICAL);

        categories.stream().forEach(category ->
                assertCalculationBasicRateResult("12.99", "0", category));

    }

    @Test
    public void testCalculateSaleTaxesForItemWithPens()  {
        assertCalculationBasicRateResult("14.99", "1.5",OTHER);
    }

    @Test
    public void testCalculateSaleTaxesForItemOfOnePrice()  {
        assertCalculationBasicRateResult("1.00", "0.1",OTHER);
    }

    @Test
    public void testCalculateSaleTaxesForItemWithPriceLessThanOne()  {
        assertCalculationBasicRateResult("0.1", "0.05",OTHER);
    }

    @Test
    public void testCalculateTotalTaxesForImportedItem() {
        assertCalculationImportedItemResult("150.00", "22.5", OTHER);
    }

    @Test
    public void testCalculateTotalTaxesForImportedItemOfSpecialCategory() {
        List<ItemCategory> categories = new ArrayList<>();
        categories.add(FOOD);
        categories.add(BOOK);
        categories.add(MEDICAL);

        categories.stream().forEach(category ->
                assertCalculationImportedItemResult("150.00", "7.5", category));

    }

    @Test
    public void testCalculateTotalTaxesForImportedItemWithPens() {
        assertCalculationImportedItemResult("150.80", "22.65",OTHER);
    }

    @Test
    public void testCalculateTotalTaxesForImportedItemWithPriceOfOne() {
        assertCalculationImportedItemResult("1.0", "0.15",OTHER);
    }

    @Test
    public void testCalculateTotalTaxesForImportedItemWithPriceLessThanOne() {
        assertCalculationImportedItemResult("0.8", "0.15",OTHER);
    }
    @Test
    public void testCalculateTotalTaxesForImportedItemWithPriceLessThanOne2() {
        assertCalculationImportedItemResult("47.50", "7.15",OTHER);
    }

    @Test
    public void testCalculateTaxesForImportedItem(){
        assertGenericCalculationItemResult("10.00", "0.5",OTHER, Calculator::calculateTaxesForImportedItem);
    }

    @Test
    public void testCalculateTaxesForItem() {
        assertGenericCalculationItemResult("10.00", "1.0",OTHER, Calculator::calculateTaxesForItem);
    }



}
