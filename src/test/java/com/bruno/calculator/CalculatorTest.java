package com.bruno.calculator;

import com.bruno.model.Item;
import com.bruno.model.Receipt;
import com.bruno.model.ReceiptItem;
import com.sun.javafx.binding.StringFormatter;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.bruno.model.Item.importedItemInstance;
import static com.bruno.model.Item.itemInstance;
import static com.bruno.util.BigDecimalUtil.price;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
        assertCalculationImportedItemResult("150.00", "22.5");
    }

    @Test
    public void testCalculateTaxesForImportedItemWithPens() {
        assertCalculationImportedItemResult("150.80", "22.62");
    }

    @Test
    public void testCalculateTaxesForImportedItemWithPriceOfOne() {
        assertCalculationImportedItemResult("1.0","0.15");
    }

    @Test
    public void testCalculateTaxesForImportedItemWithPriceLessThanOne() {
        assertCalculationImportedItemResult("0.8","0.12");
    }

    @Test
    public void testGenerateReceiptItem() {
        Item item = itemInstance(price("150.00"));
        ReceiptItem recItem = calculator.getItemForReceipt(item);
        assertEquals(price("15.0"),recItem.getTaxes());
        assertEquals(price("15.0"),recItem.getBasicTaxes());
        assertFalse(recItem.getImportedTaxes().isPresent());
    }

    @Test
    public void testGenerateReceiptItemForImportedItem() {
        Item item = importedItemInstance(price("150.00"));
        ReceiptItem recItem = calculator.getItemForReceipt(item);
        assertEquals(price("22.5"),recItem.getTaxes());
        assertEquals(price("15.0"),recItem.getBasicTaxes());
        assertEquals(price("7.5"), recItem.getImportedTaxes().get());
    }

    @Test
    public void testGenerateReceiptFromItems() {
        Item item = importedItemInstance(price("150.00"));
        Item item2 = itemInstance(price("15.00"));
        Item item3 = itemInstance(price("100.00"));
        List<Item> items = new ArrayList<>();
        items.add(item);
        items.add(item2);
        items.add(item3);
        Receipt receipt = calculator.getReceipt(items);

        assertEquals("calculate the total for a receipt",price("299"),receipt.getTotal() );
        assertEquals("calculate the total for a receipt",price("34"),receipt.getTotalTaxes() );

    }



}
