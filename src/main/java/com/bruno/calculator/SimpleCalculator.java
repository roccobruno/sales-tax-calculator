package com.bruno.calculator;

import com.bruno.model.Item;

import java.math.BigDecimal;

public class SimpleCalculator implements Calculator {
    @Override
    public BigDecimal calculateSaleTaxesFor(Item item) {
        if (item.isImported())
            return calculateSaleTaxesForImportedItem(item);
        else
            return calculateTaxes(item, basicRate);
    }

    private BigDecimal calculateTaxes(Item item, BigDecimal rate) {
        return item.getPrice().multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
    }


    private BigDecimal calculateSaleTaxesForImportedItem(Item item) {
        return calculateTaxes(item, basicRate).add(calculateTaxes(item, importedItemRate));
    }
}
