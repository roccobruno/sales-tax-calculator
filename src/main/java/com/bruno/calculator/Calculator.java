package com.bruno.calculator;

import com.bruno.model.Item;

import java.math.BigDecimal;

public interface Calculator {


    BigDecimal basicRate = new BigDecimal(0.1);
    BigDecimal importedItemRate = new BigDecimal(0.05);

    BigDecimal calculateSaleTaxesFor(Item item);

}
