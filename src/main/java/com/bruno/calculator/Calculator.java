package com.bruno.calculator;

import com.bruno.model.Basket;
import com.bruno.model.Item;
import com.bruno.model.Receipt;
import com.bruno.model.ReceiptItem;

import java.math.BigDecimal;

public interface Calculator {


    BigDecimal basicRate = new BigDecimal(0.1);//can be loaded from config file or from db at runtime
    BigDecimal importedItemRate = new BigDecimal(0.05);//can be loaded from config file or from db at runtime

    BigDecimal calculateSaleTaxesFor(Item item);
    ReceiptItem getItemForReceipt(Item item);
    Receipt getReceipt(Basket receiptItems);
}
