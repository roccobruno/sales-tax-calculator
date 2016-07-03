package com.bruno.service;

import com.bruno.model.Item;
import com.bruno.model.ItemCategory;
import com.bruno.util.BigDecimalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileInputService implements InputService {
    private final Logger logger = LoggerFactory.getLogger(FileInputService.class);

    private final String fileName;

    public FileInputService() {
        URL resource = getClass().getClassLoader().getResource(basketFileName);
        this.fileName = resource.getFile();
    }

    public FileInputService(String path) {
        this.fileName = path;
    }

    private final String basketFileName = "basket.csv";

    private List<Item> generateBasketItems(String fileName) {
        List<Item> result = new ArrayList<>();
        try {
            Path path = Paths.get(fileName);
            Files.readAllLines(path).stream().filter(s -> !s.isEmpty()).filter(s -> !s.startsWith("Description")).forEach(s -> parseLine(s, result));
        } catch (IOException e) {
            // to log warning message - no file found
            logger.error("File not found - " + e.getMessage() + "\n" + " - Returning empty list of item");
        }
        return result;
    }

    private void parseLine(String s, List<Item> result) {
        String[] values = s.split(",");
        try {
            if(values.length == 4) {
                BigDecimal price = BigDecimalUtil.bd(values[1]);
                Boolean isImported = Boolean.parseBoolean(values[2]);
                ItemCategory category = ItemCategory.valueOf(values[3]);
                String itemDescription = values[0];
                if (isImported)
                    result.add(Item.importedItemInstance(price, category, itemDescription));
                else
                    result.add(Item.itemInstance(price, category, itemDescription));
            }
        }  catch (IllegalArgumentException e) {
            //invalid content - ignoring line
            logger.warn("The following line seems to have wrong content : "+s+". Line ignored");

        }
    }

    @Override
    public List<Item> loadItems() {
        return generateBasketItems(fileName);
    }

}
