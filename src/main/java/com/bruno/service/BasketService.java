package com.bruno.service;

import com.bruno.model.Basket;
import com.bruno.model.Item;
import com.bruno.model.Receipt;
import com.bruno.model.ReceiptItem;

public interface BasketService {

    Basket loadBasket();
    ReceiptItem getItemForReceipt(Item item);
    Receipt getReceipt(Basket receiptItems);
    Receipt generateReceipt();

}
