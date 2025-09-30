package com.znaji.core_lab.events;

import com.znaji.core_lab.model.Order;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class OrderPublisher {

        private final ApplicationEventPublisher publisher;

        public OrderPublisher(ApplicationEventPublisher publisher) {
                this.publisher = publisher;
        }

        public void publish(Order order) {
                // TODO: publish a custom event after enriching the payload.
                publisher.publishEvent(new OrderPlacedEvent(this, order));
        }
}
