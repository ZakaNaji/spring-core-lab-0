package com.znaji.core_lab.service;

import com.znaji.core_lab.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class SmsNotificationService implements NotificationService {

        private static final Logger log = LoggerFactory.getLogger(SmsNotificationService.class);

        @Override
        public void notify(Order order, String referenceId) {
                // TODO: Send a real SMS via some provider.
                log.info("PROD SMS notification to {} about order {} reference {}", order.getCustomer().email(),
                                order.getId(), referenceId);
        }
}
