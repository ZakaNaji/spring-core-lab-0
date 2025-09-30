package com.znaji.core_lab.service;

import com.znaji.core_lab.model.Order;

public interface NotificationService {

        void notify(Order order, String referenceId);
}
