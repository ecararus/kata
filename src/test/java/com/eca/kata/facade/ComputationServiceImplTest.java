package com.eca.kata.facade;

import com.eca.ContextSetup;
import com.eca.kata.item.domain.Item;
import com.eca.kata.item.repository.ItemRepository;
import com.eca.kata.transformer.ItemTransformer;
import com.eca.kata.vo.Basket;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ComputationServiceImplTest extends ContextSetup {

    public static final Item ITEM_1 = Item.builder().name("Item1").price(10.0D).offerPrice(13D).offerQty(3).build();
    public static final Item ITEM_2 = Item.builder().name("Item2").price(1.5D).offerPrice(2.2D).offerQty(2).build();
    public static final Item ITEM_3 = Item.builder().name("Item3").price(0.20D).build();
    public static final Item ITEM_4 = Item.builder().name("Item4").price(15D).build();
    public static final List TESTEE_ITEMS = ImmutableList.builder()
            .add(ITEM_1)
            .add(ITEM_2)
            .add(ITEM_3)
            .add(ITEM_4)
            .build();

    @Autowired
    private ComputationService sut;

    @Autowired
    private ItemRepository itemRepository;

    @Before
    public void setUp() throws Exception {
        itemRepository.deleteAll();
        itemRepository.save(TESTEE_ITEMS);
    }

    @Test
    public void shouldAcceptEmptyList() {
        Basket basket = sut.compute(emptyList());
        assertThat(basket.getTotal(), equalTo("0.0"));
        assertThat(basket.getItems(), empty());
    }

    @Test
    public void shouldCompute_ForOnlyOneItem() {
        ArrayList<String> itemsName = newArrayList("Item4");
        Basket basket = sut.compute(itemsName);
        assertThat(basket.getTotal(), equalTo("15.0"));
        assertThat(basket.getItems(), hasItem(ItemTransformer.toVO(ITEM_4, itemsName)));
    }

    @Test
    public void shouldCompute_ForMultipleItems() {
        Basket basket = sut.compute(newArrayList("Item2", "Item3"));
        assertThat(basket.getTotal(), equalTo("1.7"));
        assertThat(basket.getItems(), containsInAnyOrder(ItemTransformer.toVO(ITEM_2, newArrayList("Item2")), ItemTransformer.toVO(ITEM_3, newArrayList("Item3"))));
    }

    @Test
    public void shouldCompute_SpecialOffer() {
        ArrayList<String> itemsName = newArrayList("Item2", "Item2", "Item2");
        Basket basket = sut.compute(itemsName);
        com.eca.kata.vo.Item expectedItem = ItemTransformer.toVO(ITEM_2, itemsName);
        assertThat(basket.getTotal(), equalTo("3.7"));
        assertThat(basket.getItems(), containsInAnyOrder(expectedItem));
    }

    @Test
    public void shouldCompute_StandardPrice() {
        ArrayList<String> itemsName = newArrayList("Item3", "Item3", "Item4");
        Basket basket = sut.compute(itemsName);
        assertThat(basket.getTotal(), equalTo("15.4"));
        assertThat(basket.getItems(), containsInAnyOrder(ItemTransformer.toVO(ITEM_3, itemsName), ItemTransformer.toVO(ITEM_4, itemsName)));
    }

    @Test
    public void shouldCompute_CompositeBasket() {
        ArrayList<String> itemsName = newArrayList("Item3", "Item3", "Item4", "Item1", "Item4", "Item2", "Item4", "Item2", "Item1");
        Basket basket = sut.compute(itemsName);
        assertThat(basket.getTotal(), equalTo("67.6"));
        assertThat(basket.getItems(), containsInAnyOrder(
                ItemTransformer.toVO(ITEM_1, itemsName),
                ItemTransformer.toVO(ITEM_2, itemsName),
                ItemTransformer.toVO(ITEM_3, itemsName),
                ItemTransformer.toVO(ITEM_4, itemsName)));
    }

}