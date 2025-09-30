package com.znaji.core_lab.service;

import com.znaji.core_lab.model.Order;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

        private final Map<String, Order> storage = new ConcurrentHashMap<>();

        public void save(Order order) {
                storage.put(order.getId(), order);
        }

        public Optional<Order> findById(String id) {
                return Optional.ofNullable(storage.get(id));
        }
}
