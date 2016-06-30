package com.bruno.calculator;

import com.bruno.model.Item;
import com.bruno.model.ReceiptItem;

import java.math.BigDecimal;

public interface Calculator {


    BigDecimal basicRate = new BigDecimal(0.1);
    BigDecimal importedItemRate = new BigDecimal(0.05);

    BigDecimal calculateSaleTaxesFor(Item item);

    ReceiptItem getItemForReceipt(Item item);
}
