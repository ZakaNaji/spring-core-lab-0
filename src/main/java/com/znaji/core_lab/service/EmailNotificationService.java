package com.znaji.core_lab.service;

import com.znaji.core_lab.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class EmailNotificationService implements NotificationService {

        private static final Logger log = LoggerFactory.getLogger(EmailNotificationService.class);

        private final String sender;

        public EmailNotificationService(@Value("${lab.notifications.email.sender:no-reply@lab.test}") String sender) {
                this.sender = sender;
        }

        @Override
        public void notify(Order order, String referenceId) {
                log.info("DEV notification to {} from {} about order {} reference {}", order.getCustomer().email(), sender,
                                order.getId(), referenceId);
        }
}
