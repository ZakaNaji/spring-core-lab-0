package com.znaji.core_lab.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.znaji.core_lab.model.Customer;
import com.znaji.core_lab.model.Order;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class OrderProcessorTests {

        @MockBean
        private PaymentGateway paymentGateway;

        @MockBean
        private ApplicationEventPublisher publisher;

        @MockBean
        private NotificationService notificationService;

        @MockBean
        private OrderRepository orderRepository;

        @MockBean
        private Clock clock;

        private Customer customer;

        @BeforeEach
        void setUp() {
                customer = new Customer("1", "Alice", "alice@example.com");
        }

        @Test
        void processShouldPersistAndNotify() {
                Mockito.when(clock.instant()).thenReturn(Instant.parse("2024-01-01T00:00:00Z"));
                Mockito.when(clock.getZone()).thenReturn(ZoneOffset.UTC);
                Mockito.when(paymentGateway.charge(Mockito.any())).thenReturn("ref");

                Order order = new Order("order-1", customer, BigDecimal.TEN, true);
                OrderProcessor processor = new OrderProcessor(paymentGateway, notificationService, orderRepository,
                                publisher, clock);

                String reference = processor.process(order);

                assertThat(reference).isEqualTo("ref");
                Mockito.verify(orderRepository).save(order);
                Mockito.verify(notificationService).notify(order, "ref");
        }

        @Test
        void processShouldFailWhenGatewayFails() {
                Order order = new Order("order-2", customer, BigDecimal.ONE, false);
                Mockito.when(paymentGateway.charge(Mockito.any())).thenThrow(new IllegalStateException("down"));

                OrderProcessor processor = new OrderProcessor(paymentGateway, notificationService, orderRepository,
                                publisher, Clock.systemUTC());

                assertThatThrownBy(() -> processor.process(order)).isInstanceOf(IllegalStateException.class);
        }
}
