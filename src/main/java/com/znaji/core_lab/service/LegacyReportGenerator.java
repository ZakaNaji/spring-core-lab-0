package com.znaji.core_lab.service;

import com.znaji.core_lab.model.Order;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Purposely written in an old-fashioned style to demonstrate the differences between setter and
 * constructor injection. Learners will refactor this class as part of the IoC exercises.
 */
@Component
public class LegacyReportGenerator {

        private static final Logger log = LoggerFactory.getLogger(LegacyReportGenerator.class);

        @Nullable
        private NotificationService notificationService;

        @Autowired // TODO: Replace with constructor injection and make notificationService mandatory.
        public void setNotificationService(NotificationService notificationService) {
                this.notificationService = notificationService;
        }

        public void export(Order order) {
                Objects.requireNonNull(order, "order");
                log.info("Exporting order {}", order.getId());
                if (notificationService != null) {
                        notificationService.notify(order, "REPORT-" + order.getId());
                }
        }
}
