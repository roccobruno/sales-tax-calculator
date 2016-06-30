package com.bruno.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Receipt {

    private List<ReceiptItem> itemList = new ArrayList<>();

    public BigDecimal getTotal() {
        BigDecimal totalPricesNoTaxes = reduceValues(((it) -> it.getItem().getPrice()));
        return totalPricesNoTaxes.add(getTotalTaxes());
    }

    public BigDecimal getTotalTaxes() {
        return reduceValues((ReceiptItem::getTaxes));
    }

    private BigDecimal reduceValues(Function<ReceiptItem,BigDecimal> func) {
        return this.getItemList().stream().map(func::apply).reduce(new BigDecimal(0), BigDecimal::add);
    }

    public Receipt(List<ReceiptItem> itemList) {
        this.itemList = itemList;
    }

    public List<ReceiptItem> getItemList() {
        return itemList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.getItemList().stream().map(ReceiptItem::getItem).forEach(item -> builder.append(item.toString()).append("\n"));
        builder.append("Sales Taxes:").append(this.getTotalTaxes()).append("\n");
        builder.append("Total :").append(this.getTotal()).append("\n");
        return  builder.toString();
    }
}
