package com.bruno.calculator;

import com.bruno.model.Item;

import java.math.BigDecimal;

public interface Calculator {


    BigDecimal calculateSaleTaxesFor(Item item);
}
