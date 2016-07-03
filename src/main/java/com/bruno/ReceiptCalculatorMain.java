package com.bruno;

import com.bruno.model.Basket;
import com.bruno.service.BasketService;
import com.bruno.service.BasketServiceImpl;
import com.bruno.service.FileInputService;
import com.bruno.service.InputService;

import java.util.Scanner;

import static java.lang.String.format;

public class ReceiptCalculatorMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        print("Specify a file (with path) or use one of the  default ones (type '1', '2' o '3') - Q for quit: ");

        String fileOption = scanner.next();

        InputService inputService;

        if (fileOption.equalsIgnoreCase("Q")) {
            print("Bye!");
            System.exit(0);
        } else {
            
            if (fileOption.equalsIgnoreCase("1") || fileOption.equalsIgnoreCase("2") || fileOption.equalsIgnoreCase("3")) {
                inputService = new FileInputService(format("inputs/basket%s.csv", fileOption));
            } else {
                inputService = new FileInputService(fileOption);
            }
            
            BasketService basketService = new BasketServiceImpl(inputService);
            Basket basket = basketService.loadBasket();

            print("\n");
            print("Basket content:");
            if (basket.getItems().isEmpty()) {
                print("Empty");
            } else {
                basket.getItems().stream().forEach(item -> {
                    print(item.toString());
                });
            }
            print("\n");
            print(basketService.getReceipt(basket).toString());

            print("\n");
        }

    }

    private static void print(String message) {
        System.out.println(message);
    }
}
