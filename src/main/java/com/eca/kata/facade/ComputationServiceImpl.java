package com.eca.kata.facade;

import com.eca.kata.item.ItemService;
import com.eca.kata.vo.Basket;
import com.eca.kata.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.eca.kata.strategy.PriceProvider.getCalculator;
import static java.lang.String.valueOf;

@Service
public final class ComputationServiceImpl implements ComputationService {

    private final ItemService itemService;

    @Autowired
    public ComputationServiceImpl(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public Basket compute(List<String> itemsName) {
        List<Item> itemsVo = itemService.getItems(itemsName);
        return Basket.builder()
                .setItems(itemsVo)
                .setTotal(getTotal(itemsVo))
                .build();
    }

    private String getTotal(List<Item> itemsVo) {
        double sum = itemsVo
                .stream()
                .mapToDouble(item -> getCalculator(item).calculateTotal(item))
                .sum();
        return valueOf(sum);
    }
}
