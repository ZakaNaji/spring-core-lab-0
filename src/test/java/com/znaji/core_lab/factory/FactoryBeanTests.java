package com.znaji.core_lab.factory;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class FactoryBeanTests {

        @Autowired
        private Instant timestampFactoryBean;

        @Autowired
        private TimestampFactoryBean factoryBean;

        @Test
        void factoryBeanProducesDifferentInstances() {
                Instant first = timestampFactoryBean;
                Instant second = factoryBean.getObject();
                assertThat(first).isNotEqualTo(second);
        }
}
