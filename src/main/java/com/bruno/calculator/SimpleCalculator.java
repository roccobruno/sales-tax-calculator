package com.bruno.calculator;

import com.bruno.model.Item;
import com.bruno.model.ReceiptItem;

import java.math.BigDecimal;
import java.util.Optional;

public class SimpleCalculator implements Calculator {
    @Override
    public BigDecimal calculateSaleTaxesFor(Item item) {
        if (item.isImported())
            return calculateSaleTaxesForImportedItem(item);
        else
            return calculateTaxes(item, basicRate);
    }

    @Override
    public ReceiptItem getItemForReceipt(Item item) {
        BigDecimal basicTaxes = calculateTaxes(item, basicRate);
        Optional<BigDecimal> importedTaxes = item.isImported() ? Optional.<BigDecimal>of(calculateTaxes(item, importedItemRate)) : Optional.<BigDecimal>empty();
        BigDecimal taxes =  basicTaxes.add(importedTaxes.orElse(new BigDecimal(0)));
        return new ReceiptItem(item,basicTaxes, importedTaxes,taxes);
    }

    private BigDecimal calculateTaxes(Item item, BigDecimal rate) {
        return item.getPrice().multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
    }


    private BigDecimal calculateSaleTaxesForImportedItem(Item item) {
        return calculateTaxes(item, basicRate).add(calculateTaxes(item, importedItemRate));
    }
}
