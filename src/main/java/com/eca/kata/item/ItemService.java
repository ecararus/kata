package com.eca.kata.item;

import com.eca.kata.vo.Item;

import java.util.List;

public interface ItemService {

    List<Item> getItems(List<String> itemsName);

}
