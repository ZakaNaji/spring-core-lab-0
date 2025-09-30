package com.znaji.core_lab.scope;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThreadScopeConfiguration {

        @Bean
        public static CustomScopeConfigurer customScopeConfigurer() {
                CustomScopeConfigurer configurer = new CustomScopeConfigurer();
                configurer.addScope("thread", new ThreadScope());
                return configurer;
        }
}
