package com.znaji.core_lab.events;

import com.znaji.core_lab.model.Order;
import org.springframework.context.ApplicationEvent;

public class OrderPlacedEvent extends ApplicationEvent {

        private final Order order;

        public OrderPlacedEvent(Object source, Order order) {
                super(source);
                this.order = order;
        }

        public Order getOrder() {
                return order;
        }
}
