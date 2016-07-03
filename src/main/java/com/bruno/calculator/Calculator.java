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

/**
 * collection of functions to calculate taxes
 */
public class Calculator  {


    public static BigDecimal basicRate = new BigDecimal(0.1);//can be loaded from config file or from db at runtime
    public static BigDecimal importedItemRate = new BigDecimal(0.05);//can be loaded from config file or from db at runtime

    private static Map<ItemCategory,RuleContainer> rules;

    static  {

        //can be loaded from config file or from db at runtime

        RuleContainer exemptItem = new RuleContainer(Calculator::calculateTaxesForExemptItem,Calculator::calculateTaxesWithImportedItemCheck);
        RuleContainer otherItem = new RuleContainer(Calculator::calculateTaxesForItem,Calculator::calculateTaxesWithImportedItemCheck);

        rules = new HashMap<>();
        rules.put(ItemCategory.FOOD,exemptItem);
        rules.put(ItemCategory.BOOK,exemptItem);
        rules.put(ItemCategory.MEDICAL,exemptItem);
        rules.put(ItemCategory.OTHER,otherItem);
    }

    /*
      taxes for exempted item
     */
    private static BigDecimal calculateTaxesForExemptItem(Item item) {
        return new BigDecimal("0.00");
    }

    /**
     * checks whether the item is imported and than works out taxes
     * @param item for which it works out the additional taxes
     * @return addiotnal sale taxes
     */
    private static BigDecimal calculateTaxesWithImportedItemCheck(Item item) {
        if(item.isImported())
             return calculateTaxes(item, importedItemRate);
        return new BigDecimal("0.00");
    }


    /**
     * works out the total sale taxes for an item
     * @param item for which it works out the taxes
     * @return total sale taxes for item
     */
    public static BigDecimal calculateSaleTaxesFor(Item item) {

       return rules.get(item.getCategory()).getRules().stream().
               map(func -> func.apply(item)).
               reduce((a, b) -> a.add(b)).
                       get();
    }

    /**
     * taxes for imported item
     * @param item the imported item to work out the additional tax for
     * @return additional tax for the imported item
     */
    public static BigDecimal calculateTaxesForImportedItem(Item item) {
        return calculateTaxes(item, importedItemRate);
    }

    /**
     * basic taxes for an item
     * @param item the  item to work out the basic tax for
     * @return basic tax for the item
     */
    public static BigDecimal calculateBasicRateTaxesForItem(Item item) {
        return rules.get(item.getCategory()).basicRateRule.apply(item);
    }

    public static BigDecimal calculateTaxesForItem(Item item) {
        return calculateTaxes(item, basicRate);
    }

    private static BigDecimal calculateTaxes(Item item, BigDecimal rate) {
        return round(item.getPrice().multiply(rate));
    }

    /*
      Round rule, up to the nearest 0.05
     */
    private static BigDecimal round(BigDecimal amount) {
        BigDecimal increment = new BigDecimal(0.05);
        return round(amount,increment,RoundingMode.UP).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * generic round rule
     * @param value amount to round
     * @param increment how much the value must be rounded
     * @param roundingMode direction of round
     * @return the rounded value
     */
    private static BigDecimal round(BigDecimal value, BigDecimal increment,
                                   RoundingMode roundingMode) {
        if (increment.signum() == 0) {
            return value;
        } else {
            BigDecimal divided = value.divide(increment, 0, roundingMode);
            BigDecimal result = divided.multiply(increment);
            return result;
        }
    }


    /*
      Item category rules container
     */
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
