package com.znaji.core_lab.service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RetryingPaymentGateway implements PaymentGateway {

        private static final Logger log = LoggerFactory.getLogger(RetryingPaymentGateway.class);

        private final int maxRetries;
        private final Duration backoff;
        private final Clock clock;

        public RetryingPaymentGateway(@Value("${lab.payment.max-retries:2}") int maxRetries,
                        @Value("${lab.payment.backoff:PT0.5S}") Duration backoff, Clock clock) {
                this.maxRetries = maxRetries;
                this.backoff = backoff;
                this.clock = clock;
        }

        @Override
        public String charge(BigDecimal amount) {
                // TODO: Replace the simulation with deterministic retry/backoff logic that integrates with
                // resilience libraries (e.g. Resilience4j) instead of Thread.sleep.
                for (int attempt = 0; attempt <= maxRetries; attempt++) {
                        boolean success = attempt == maxRetries || ThreadLocalRandom.current().nextBoolean();
                        if (success) {
                                String reference = "REF-" + clock.instant().toEpochMilli();
                                log.debug("Charge succeeded on attempt {} with reference {}", attempt, reference);
                                return reference;
                        }
                        log.warn("Charge attempt {} failed, retrying after {}", attempt, backoff);
                        try {
                                Thread.sleep(backoff.toMillis());
                        }
                        catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                throw new IllegalStateException("Retry interrupted", e);
                        }
                }
                throw new IllegalStateException("Charge failed after retries");
        }
}
