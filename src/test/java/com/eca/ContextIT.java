package com.eca;

import com.eca.kata.facade.ComputationService;
import com.eca.kata.item.domain.Item;
import com.eca.kata.item.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.eca.KataAppConfiguration.DEFAULT_ITEMS;
import static org.hamcrest.Matchers.containsInAnyOrder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(KataAppConfiguration.class)
@WebAppConfiguration()
@DirtiesContext
public class ContextIT {

    @Autowired
    protected ComputationService sut;

    @Autowired
    protected ItemRepository itemRepository;

    @Test
    public void applicationShouldStart_withGivenConfiguration() {
    }

    @Test
    public void repositoryShouldBeInitialized() {
        Iterable<Item> persisted = itemRepository.findAll();
        Assert.assertThat(persisted, containsInAnyOrder(DEFAULT_ITEMS.toArray()));
    }

}
