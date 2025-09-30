package com.znaji.core_lab.scope;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * Minimal custom scope that stores beans per-thread. Learners will flesh out destruction callbacks
 * and context propagation.
 */
public class ThreadScope implements Scope {

        private final ThreadLocal<Map<String, Object>> storage = ThreadLocal.withInitial(ConcurrentHashMap::new);

        @Override
        public Object get(String name, ObjectFactory<?> objectFactory) {
                return storage.get().computeIfAbsent(name, key -> objectFactory.getObject());
        }

        @Override
        public Object remove(String name) {
                return storage.get().remove(name);
        }

        @Override
        public void registerDestructionCallback(String name, Runnable callback) {
                // TODO: Implement destruction callbacks for thread scope cleanup.
        }

        @Override
        public Object resolveContextualObject(String key) {
                return null;
        }

        @Override
        public String getConversationId() {
                return Thread.currentThread().getName();
        }
}
