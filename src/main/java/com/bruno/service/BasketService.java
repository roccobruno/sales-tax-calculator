package com.bruno.service;

import com.bruno.model.Basket;
import com.bruno.model.Item;
import com.bruno.model.Receipt;
import com.bruno.model.ReceiptItem;

public interface BasketService {

    /**
     * creates a basket from the list of items received from the input service supplied
     * @return basket
     */
    Basket loadBasket();

    /**
     * Creates an item for a receipt
     * @param item the Item in the basket from which create an item for the receipt
     * @return item for the Receipt
     */
    ReceiptItem getItemForReceipt(Item item);

    /**
     * Creates a receipt from a basket
     * @param basket
     * @return receipt
     */
    Receipt getReceipt(Basket basket);

    /**
     * It first loads the basket and than creates its receipt
     * @return receipt
     */
    Receipt generateReceipt();

}
