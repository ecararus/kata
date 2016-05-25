package com.eca.kata.strategy;

import com.eca.kata.vo.Item;

final class BasePriceCalculator implements PriceCalculator {

    @Override
    public double calculateTotal(Item item) {
        return item.getQty() * item.getPrice();
    }
}
