package com.eca;

import com.eca.kata.item.ItemService;
import com.eca.kata.item.domain.Item;
import com.eca.kata.item.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.eca.KataAppConfiguration.DEFAULT_ITEMS;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class KataAppConfigurationIT extends ContextSetup{

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void repositoryShouldBeInitialized() {
        Iterable<Item> persisted = itemRepository.findAll();
        Assert.assertThat(persisted, containsInAnyOrder(DEFAULT_ITEMS.toArray()));
    }
}