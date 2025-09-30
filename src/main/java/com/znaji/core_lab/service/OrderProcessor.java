package com.znaji.core_lab.service;

import com.znaji.core_lab.events.OrderPlacedEvent;
import com.znaji.core_lab.model.Order;
import java.time.Clock;
import java.time.Instant;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class OrderProcessor {

        private static final Logger log = LoggerFactory.getLogger(OrderProcessor.class);

        private final PaymentGateway paymentGateway;
        private final NotificationService notificationService;
        private final OrderRepository orderRepository;
        private final ApplicationEventPublisher publisher;
        private final Clock clock;

        public OrderProcessor(PaymentGateway paymentGateway, NotificationService notificationService,
                        OrderRepository orderRepository, ApplicationEventPublisher publisher, Clock clock) {
                this.paymentGateway = Objects.requireNonNull(paymentGateway);
                this.notificationService = Objects.requireNonNull(notificationService);
                this.orderRepository = Objects.requireNonNull(orderRepository);
                this.publisher = Objects.requireNonNull(publisher);
                this.clock = Objects.requireNonNull(clock);
        }

        public String process(Order order) {
                Objects.requireNonNull(order, "order is required");
                var reference = paymentGateway.charge(order.getAmount());
                order.markProcessed(Instant.now(clock));
                orderRepository.save(order);
                notificationService.notify(order, reference);
                publisher.publishEvent(new OrderPlacedEvent(this, order));
                log.info("Processed order {} with reference {}", order.getId(), reference);
                return reference;
        }
}
