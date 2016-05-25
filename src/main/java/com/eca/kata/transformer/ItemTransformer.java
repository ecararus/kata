package com.eca.kata.transformer;

import com.eca.kata.item.domain.Item;
import com.eca.kata.vo.SpecialPrice;

import java.util.List;

import static com.eca.kata.vo.Item.builder;
import static com.google.common.base.Optional.fromNullable;
import static java.util.stream.Collectors.counting;

public final class ItemTransformer {

    private ItemTransformer() {
        throw new UnsupportedOperationException();
    }

    public static com.eca.kata.vo.Item toVO(com.eca.kata.item.domain.Item item, List<String> itemsRequest) {
        return builder()
                .setName(item.getName())
                .setPrice(item.getPrice())
                .setQty(countSimilarItemsInRequest(item, itemsRequest))
                .setSpecialPrice(prepareSpecialPrice(item))
                .build();
    }

    private static int countSimilarItemsInRequest(Item item, List<String> itemsName) {
        return itemsName.stream().filter(name -> name.equals(item.getName())).collect(counting()).intValue();
    }

    private static SpecialPrice prepareSpecialPrice(Item item) {
        if (fromNullable(item.getOfferPrice()).isPresent()) {
            return SpecialPrice.builder().setPrice(item.getOfferPrice()).setQty(item.getOfferQty()).build();
        }
        return null;
    }
}

