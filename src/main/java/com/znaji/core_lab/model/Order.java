package com.znaji.core_lab.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

/**
 * Represents an incoming order that needs processing. Validation annotations are used so the
 * exercises can demonstrate validator integration.
 */
public class Order {

        private final String id;

        @Valid
        @NotNull
        private final Customer customer;

        @DecimalMin(value = "0.0", inclusive = false)
        private BigDecimal amount;

        private boolean priority;

        private Instant processedAt;

        public Order(String id, Customer customer, BigDecimal amount, boolean priority) {
                this.id = Objects.requireNonNull(id, "id is required");
                this.customer = Objects.requireNonNull(customer, "customer is required");
                this.amount = Objects.requireNonNull(amount, "amount is required");
                this.priority = priority;
        }

        public String getId() {
                return id;
        }

        public Customer getCustomer() {
                return customer;
        }

        public BigDecimal getAmount() {
                return amount;
        }

        public void setAmount(BigDecimal amount) {
                this.amount = Objects.requireNonNull(amount, "amount is required");
        }

        public boolean isPriority() {
                return priority;
        }

        public void setPriority(boolean priority) {
                this.priority = priority;
        }

        public Instant getProcessedAt() {
                return processedAt;
        }

        public void markProcessed(Instant processedAt) {
                this.processedAt = processedAt;
        }
}
