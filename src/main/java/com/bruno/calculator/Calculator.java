package com.bruno.calculator;

import com.bruno.model.Item;
import com.bruno.model.ItemCategory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Calculator  {


    public static BigDecimal basicRate = new BigDecimal(0.1);//can be loaded from config file or from db at runtime
    public static BigDecimal importedItemRate = new BigDecimal(0.05);//can be loaded from config file or from db at runtime

    private static Map<ItemCategory,RuleContainer> rules;

    static  {

        RuleContainer exemptItem = new RuleContainer(Calculator::calculateTaxesForSpecialItem,Calculator::calculateTaxesWithImportedItemCheck);
        RuleContainer otherItem = new RuleContainer(Calculator::calculateTaxesForItem,Calculator::calculateTaxesWithImportedItemCheck);

        rules = new HashMap<>();
        rules.put(ItemCategory.FOOD,exemptItem);
        rules.put(ItemCategory.BOOK,exemptItem);
        rules.put(ItemCategory.MEDICAL,exemptItem);
        rules.put(ItemCategory.OTHER,otherItem);
    }

    private static BigDecimal calculateTaxesForSpecialItem(Item item) {
        return new BigDecimal("0.00");
    }


    private static BigDecimal calculateTaxesWithImportedItemCheck(Item item) {
        if(item.isImported())
             return calculateTaxes(item, importedItemRate);
        return new BigDecimal("0.00");
    }

    public static BigDecimal calculateSaleTaxesFor(Item item) {

       return rules.get(item.getCategory()).getRules().stream().
               map(func -> func.apply(item)).
               reduce((a, b) -> a.add(b)).
                       get();
    }


    public static BigDecimal calculateTaxesForImportedItem(Item item) {
        return calculateTaxes(item, importedItemRate);
    }

    public static BigDecimal calculateBasicRateTaxesForItem(Item item) {
        return rules.get(item.getCategory()).basicRateRule.apply(item);
    }

    public static BigDecimal calculateTaxesForItem(Item item) {
        return calculateTaxes(item, basicRate);
    }

    private static BigDecimal calculateTaxes(Item item, BigDecimal rate) {
        return round(item.getPrice().multiply(rate));
    }

    private static BigDecimal round(BigDecimal amount) {
        BigDecimal increment = new BigDecimal(0.05);
        return round(amount,increment,RoundingMode.UP).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal round(BigDecimal value, BigDecimal increment,
                                   RoundingMode roundingMode) {
        if (increment.signum() == 0) {
            return value;
        } else {
            BigDecimal divided = value.divide(increment, 0, roundingMode);
            BigDecimal result = divided.multiply(increment);
            return result;
        }
    }


    private static class RuleContainer {
        public final Function<Item,BigDecimal> basicRateRule;
        public final Function<Item,BigDecimal> importedRateRule;

        private RuleContainer(Function<Item, BigDecimal> basicRateRule, Function<Item, BigDecimal> importedRateRule) {
            this.basicRateRule = basicRateRule;
            this.importedRateRule = importedRateRule;
        }

        public List<Function<Item,BigDecimal>> getRules() {
            List<Function<Item,BigDecimal>> rules = new ArrayList<>();
            rules.add(basicRateRule);
            rules.add(importedRateRule);
            return rules;
        }
    }

}
