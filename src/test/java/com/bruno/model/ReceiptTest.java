package com.bruno.model;

import com.bruno.calculator.Calculator;
import com.bruno.calculator.SimpleCalculator;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.bruno.model.Item.importedItemInstance;
import static com.bruno.model.Item.itemInstance;
import static com.bruno.util.BigDecimalUtil.price;

public class ReceiptTest {



    @Test
    public void testToString() throws Exception {
        Calculator calculator = new SimpleCalculator();

        Item item = importedItemInstance(price("150.00"));
        Item item2 = itemInstance(price("15.00"));
        Item item3 = itemInstance(price("100.00"));
        List<Item> items = new ArrayList<>();
        items.add(item);
        items.add(item2);
        items.add(item3);


        Receipt receipt = calculator.getReceipt(items);


        StringBuilder builder = new StringBuilder();
        receipt.getItemList().stream().map(ReceiptItem::getItem).forEach(itt -> builder.append(itt.toString()).append("\n"));
        builder.append("Sales Taxes:").append(receipt.getTotalTaxes()).append("\n");
        builder.append("Total :").append(receipt.getTotal()).append("\n");

        Assert.assertEquals("checking receipt printing", builder.toString(), receipt.toString());
    }
}