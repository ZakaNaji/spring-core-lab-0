package com.znaji.core_lab;

import static org.assertj.core.api.Assertions.assertThat;

import com.znaji.core_lab.config.PricingProperties;
import com.znaji.core_lab.service.OrderProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class CoreLabApplicationTests {

        @Autowired
        private PricingProperties pricingProperties;

        @Autowired
        private OrderProcessor orderProcessor;

        @Test
        void contextLoads() {
                assertThat(pricingProperties.discountPercentage()).isEqualTo(10.0);
                assertThat(orderProcessor).isNotNull();
        }
}
