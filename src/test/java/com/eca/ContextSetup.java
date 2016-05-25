package com.eca;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(KataAppConfiguration.class)
@WebAppConfiguration
@DirtiesContext
public class ContextSetup {

    @Test
    public void applicationShouldStart_withGivenConfiguration() {
    }

}
