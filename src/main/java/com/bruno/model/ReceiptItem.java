package com.bruno.model;

import java.math.BigDecimal;
import java.util.Optional;

public class ReceiptItem {

    private Item item;
    private BigDecimal basicTaxes;
    private Optional<BigDecimal> importedTaxes;
    private BigDecimal taxes;

    public ReceiptItem(Item item, BigDecimal basicTaxes, Optional<BigDecimal> importedTaxes, BigDecimal taxes) {
        this.item = item;
        this.basicTaxes = basicTaxes;
        this.importedTaxes = importedTaxes;
        this.taxes = taxes;
    }

    public Item getItem() {
        return item;
    }

    public BigDecimal getBasicTaxes() {
        return basicTaxes;
    }

    public Optional<BigDecimal> getImportedTaxes() {
        return importedTaxes;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }
}
