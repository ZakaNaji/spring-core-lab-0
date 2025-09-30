package com.znaji.core_lab.factory;

import java.time.Instant;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TimestampFactoryBean implements FactoryBean<Instant> {

        private Instant lastCreated;

        @Override
        public Instant getObject() {
                // TODO: control the precision or caching behavior
                lastCreated = Instant.now();
                return lastCreated;
        }

        @Override
        public Class<?> getObjectType() {
                return Instant.class;
        }

        @Override
        public boolean isSingleton() {
                return false;
        }

        @Nullable
        public Instant getLastCreated() {
                return lastCreated;
        }
}
