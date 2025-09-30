package com.znaji.core_lab.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuditTrailListener {

        private static final Logger log = LoggerFactory.getLogger(AuditTrailListener.class);

        @EventListener
        public void onOrderPlaced(OrderPlacedEvent event) {
                log.info("Synchronous audit for order {}", event.getOrder().getId());
        }
}
