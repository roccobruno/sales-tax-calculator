package com.bruno.service;

import com.bruno.model.Item;
import com.bruno.model.ItemCategory;
import com.bruno.util.BigDecimalUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileInputService implements InputService {

    private final String fileName;

    public FileInputService() {
        this.fileName = basketFileName;
    }

    public FileInputService(String path) {
        this.fileName = path;
    }

    private final String basketFileName = "basket.csv";

    private List<Item> generateBasketItems(String fileName) {
        List<Item> result = new ArrayList<>();
        try {
            URL resource = getClass().getClassLoader().getResource(fileName);
            if(resource == null)
                throw new IllegalArgumentException(String.format("no file %s found",fileName));

            String file = resource.getFile();
            Files.readAllLines(Paths.get(file)).stream().filter(s -> !s.isEmpty()).forEach(s -> parseLine(s, result));
        } catch (IOException e) {
            // to log warning message - no file found
            System.out.println(e.getMessage());
        }
        return result;
    }

    private void parseLine(String s, List<Item> result) {
        String[] values = s.split(",");
        try {
            if(values.length == 4) {
                BigDecimal price = BigDecimalUtil.price(values[1]);
                Boolean isImported = Boolean.parseBoolean(values[2]);
                ItemCategory category = ItemCategory.valueOf(values[3]);
                String itemDescription = values[0];
                if (isImported)
                    result.add(Item.importedItemInstance(price, category, itemDescription));
                else
                    result.add(Item.itemInstance(price, category, itemDescription));
            }
        } catch (NumberFormatException e) {
            //invalid price specified - ignoring line
        } catch (IllegalArgumentException e) {
            //invalid category specified - ignoring line
        }
    }

    @Override
    public List<Item> loadItems() {
        return generateBasketItems(this.fileName);
    }

}
