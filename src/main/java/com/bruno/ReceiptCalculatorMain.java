package com.bruno;

import com.bruno.model.Basket;
import com.bruno.service.BasketService;
import com.bruno.service.BasketServiceImpl;
import com.bruno.service.FileInputService;
import com.bruno.service.InputService;

import java.util.Scanner;

public class ReceiptCalculatorMain {

    public static void main (String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your file name or use default(leave empty): ");

        String fileOption = scanner.next();

        InputService inputService;

        if(fileOption.equalsIgnoreCase("D")) {
            inputService = new FileInputService();
        } else {
            inputService = new FileInputService(fileOption);
        }

        BasketService basketService = new BasketServiceImpl(inputService);
        Basket basket = basketService.loadBasket();
        System.out.println(basketService.getReceipt(basket));

    }

}
