package com.bruno.calculator;

import com.bruno.model.Item;
import com.bruno.model.ItemCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Calculator  {


    public static BigDecimal basicRate = new BigDecimal(0.1);//can be loaded from config file or from db at runtime
    public static BigDecimal importedItemRate = new BigDecimal(0.05);//can be loaded from config file or from db at runtime

    private static Map<ItemCategory,List<Function<Item,BigDecimal>>> rules;

    static  {
        List<Function<Item,BigDecimal>> functionsForSpecialCategory = new ArrayList<>();
        functionsForSpecialCategory.add(Calculator::calculateTaxesForSpecialItem);
        functionsForSpecialCategory.add(Calculator::calculateTaxesWithImportedItemCheck);

        List<Function<Item,BigDecimal>> functionsForCategory = new ArrayList<>();
        functionsForCategory.add(Calculator::calculateTaxesForItem);
        functionsForCategory.add(Calculator::calculateTaxesWithImportedItemCheck);


        rules = new HashMap<>();
        rules.put(ItemCategory.FOOD,functionsForSpecialCategory);
        rules.put(ItemCategory.BOOK,functionsForSpecialCategory);
        rules.put(ItemCategory.MEDICAL,functionsForSpecialCategory);
        rules.put(ItemCategory.OTHER,functionsForCategory);
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

       return rules.get(item.getCategory()).stream().
               map(func -> func.apply(item)).
               reduce((a, b) -> a.add(b)).
                       get();
    }

    public static BigDecimal calculateTaxesForImportedItem(Item item) {
        return calculateTaxes(item, importedItemRate);
    }

    public static BigDecimal calculateTaxesForItem(Item item) {
        return calculateTaxes(item, basicRate);
    }

    private static BigDecimal calculateTaxes(Item item, BigDecimal rate) {
        return item.getPrice().multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
    }


}
