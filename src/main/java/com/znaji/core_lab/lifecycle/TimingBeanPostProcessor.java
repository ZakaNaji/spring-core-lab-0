package com.znaji.core_lab.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class TimingBeanPostProcessor implements BeanPostProcessor {

        private static final Logger log = LoggerFactory.getLogger(TimingBeanPostProcessor.class);

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                log.trace("Before initialization of {}", beanName);
                return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                log.trace("After initialization of {}", beanName);
                return bean;
        }
}
