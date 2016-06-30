package com.bruno.calculator;

import com.bruno.model.Item;
import com.sun.javafx.css.CalculatedValue;

import java.math.BigDecimal;

public class SimpleCalculator implements Calculator {
    @Override
    public BigDecimal calculateSaleTaxesFor(Item item) {
        return calculateTaxes(item, new BigDecimal(0.1));
    }

    private BigDecimal calculateTaxes(Item item, BigDecimal rate) {
        return item.getPrice().multiply(rate).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public BigDecimal calculateSaleTaxesForImportedItem(Item item) {
        return calculateTaxes(item, new BigDecimal(0.1)).add(calculateTaxes(item, new BigDecimal(0.05)));
    }
}
