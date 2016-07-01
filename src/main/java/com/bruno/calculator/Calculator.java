package com.bruno.calculator;

import com.bruno.model.Item;

import java.math.BigDecimal;

public interface Calculator {


    BigDecimal basicRate = new BigDecimal(0.1);//can be loaded from config file or from db at runtime
    BigDecimal importedItemRate = new BigDecimal(0.05);//can be loaded from config file or from db at runtime
    BigDecimal calculateSaleTaxesFor(Item item);
    BigDecimal calculateTaxesForImportedItem(Item item);
    BigDecimal calculateTaxesForItem(Item item);
}
