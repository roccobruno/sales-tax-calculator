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
        return reduceValues(((it) -> it.getTaxes()));
    }

    private BigDecimal reduceValues(Function<ReceiptItem,BigDecimal> func) {
        return this.getItemList().stream().map(it -> func.apply(it)).reduce(new BigDecimal(0), (a, b) -> a.add(b));
    }

    public Receipt(List<ReceiptItem> itemList) {
        this.itemList = itemList;
    }

    public List<ReceiptItem> getItemList() {
        return itemList;
    }
}
