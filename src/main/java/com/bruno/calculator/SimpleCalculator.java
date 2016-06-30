package com.bruno.calculator;

import com.bruno.model.Item;
import com.sun.javafx.css.CalculatedValue;

import java.math.BigDecimal;

public class SimpleCalculator implements Calculator {
    @Override
    public BigDecimal calculateSaleTaxesFor(Item item) {
        return item.getPrice().multiply(new BigDecimal(0.1)).setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}
