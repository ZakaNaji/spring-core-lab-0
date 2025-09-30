package com.znaji.core_lab.service;

import java.math.BigDecimal;

/**
 * Represents some external payment provider. Learners will implement retry logic and understand
 * how Spring manages bean lifecycles around it.
 */
public interface PaymentGateway {

        /**
         * Perform the payment and return a processor reference id.
         *
         * @implNote This method intentionally throws {@link UnsupportedOperationException}. Learners are
         * expected to implement the logic during the exercises.
         */
        default String charge(BigDecimal amount) {
                // TODO: provide a real implementation such as delegating to an HTTP client or a mock.
                throw new UnsupportedOperationException("Implement charge() with retry/backoff logic");
        }
}
