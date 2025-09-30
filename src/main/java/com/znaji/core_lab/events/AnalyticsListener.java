package com.znaji.core_lab.events;

import com.znaji.core_lab.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AnalyticsListener {

        private static final Logger log = LoggerFactory.getLogger(AnalyticsListener.class);

        @Async
        @EventListener
        public void handle(OrderPlacedEvent event) {
                Order order = event.getOrder();
                log.info("Async analytics for order {}", order.getId());
        }
}
