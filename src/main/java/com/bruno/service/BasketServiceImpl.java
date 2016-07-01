package com.bruno.service;

import com.bruno.calculator.SimpleCalculator;
import com.bruno.model.Basket;

public class BasketServiceImpl implements BasketService {
    private final SimpleCalculator calculator;
    private final InputService inputService;

    public BasketServiceImpl(SimpleCalculator simpleCalculator, InputService inputService) {
        calculator = simpleCalculator;
        this.inputService = inputService;
    }




    @Override
    public Basket loadBasket() {
        return new Basket(inputService.loadItems());
    }


}
