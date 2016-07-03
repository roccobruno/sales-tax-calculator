package com.bruno.model;

import com.bruno.service.BasketService;
import com.bruno.service.BasketServiceImpl;
import com.bruno.service.FileInputService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.bruno.model.Item.importedItemInstance;
import static com.bruno.model.Item.itemInstance;
import static com.bruno.util.BigDecimalUtil.bd;

public class ReceiptTest {



    @Test
    public void testToString() throws Exception {
        BasketService basketService = new BasketServiceImpl(new FileInputService());

        Item item = importedItemInstance(bd("150.00"));
        Item item2 = itemInstance(bd("15.00"));
        Item item3 = itemInstance(bd("100.00"));
        List<Item> items = new ArrayList<>();
        items.add(item);
        items.add(item2);
        items.add(item3);


        Receipt receipt = basketService.getReceipt(new Basket(items));


        StringBuilder builder = new StringBuilder();
        builder.append("Receipt for ").append(receipt.getItemList().size()).append(" items:\n");
        receipt.getItemList().stream().forEach(itt -> builder.append(itt.toString()).append("\n"));
        builder.append("Sales Taxes:").append(receipt.getTotalTaxes()).append("\n");
        builder.append("Total :").append(receipt.getTotal()).append("\n");

        Assert.assertEquals("checking receipt printing", builder.toString(), receipt.toString());
    }
}