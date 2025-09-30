package com.znaji.core_lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalConfig {

        @Bean
        @Conditional(OnWindowsCondition.class)
        public String windowsOnlyBean() {
                return "windows-specific";
        }
}
