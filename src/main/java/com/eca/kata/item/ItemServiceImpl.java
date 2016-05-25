package com.eca.kata.item;

import com.eca.kata.item.repository.ItemRepository;
import com.eca.kata.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.eca.kata.transformer.ItemTransformer.toVO;
import static java.util.stream.Collectors.toList;

@Component
public final class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getItems(List<String> itemsName) {
        return itemRepository
                .findByNameIn(itemsName)
                .stream()
                .map(item -> toVO(item, itemsName))
                .collect(toList());
    }

}
