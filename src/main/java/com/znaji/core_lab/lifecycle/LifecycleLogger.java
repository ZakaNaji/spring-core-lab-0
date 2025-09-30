package com.znaji.core_lab.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class LifecycleLogger implements InitializingBean, DisposableBean {

        private static final Logger log = LoggerFactory.getLogger(LifecycleLogger.class);

        @PostConstruct
        public void postConstruct() {
                log.info("@PostConstruct invoked");
        }

        @Override
        public void afterPropertiesSet() {
                log.info("InitializingBean.afterPropertiesSet invoked");
        }

        @PreDestroy
        public void preDestroy() {
                log.info("@PreDestroy invoked");
        }

        @Override
        public void destroy() {
                log.info("DisposableBean.destroy invoked");
        }
}
