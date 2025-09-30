package com.znaji.core_lab.scope;

import java.time.Instant;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PrototypeAudit {

        private static final Logger log = LoggerFactory.getLogger(PrototypeAudit.class);

        private final String id = UUID.randomUUID().toString();
        private final Instant createdAt = Instant.now();

        public void record(String message) {
                log.info("[{} at {}] {}", id, createdAt, message);
        }
}
