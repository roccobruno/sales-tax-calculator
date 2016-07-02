package com.bruno.service;

import com.bruno.calculator.Calculator;
import com.bruno.model.Basket;
import com.bruno.model.Item;
import com.bruno.model.Receipt;
import com.bruno.model.ReceiptItem;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

public class BasketServiceImpl implements BasketService {
    private final InputService inputService;

    public BasketServiceImpl( InputService inputService) {
        this.inputService = inputService;
    }


    @Override
    public ReceiptItem getItemForReceipt(Item item) {
        BigDecimal basicTaxes = Calculator.calculateTaxesForItem(item);
        Optional<BigDecimal> importedTaxes = item.isImported() ? Optional.of(Calculator.calculateTaxesForImportedItem(item)) : Optional.<BigDecimal>empty();
        BigDecimal taxes =  basicTaxes.add(importedTaxes.orElse(new BigDecimal(0)));
        return new ReceiptItem(item,basicTaxes, importedTaxes,taxes);
    }

    @Override
    public Receipt getReceipt(Basket receiptItems) {
        return new Receipt(receiptItems.getItems().stream().map(it -> getItemForReceipt(it)).
                collect(Collectors.toList()));
    }


    @Override
    public Basket loadBasket() {
        return new Basket(inputService.loadItems());
    }


}
