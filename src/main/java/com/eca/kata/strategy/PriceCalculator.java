package com.eca.kata.strategy;


import com.eca.kata.vo.Item;

import java.util.List;

public interface PriceCalculator {

    double calculateTotal(Item item);
}
