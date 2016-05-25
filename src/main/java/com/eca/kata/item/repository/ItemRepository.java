package com.eca.kata.item.repository;

import com.eca.kata.item.domain.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, String> {

    List<Item> findByNameIn(List<String> name);

}
