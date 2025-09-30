package com.znaji.core_lab.config;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@PropertySource("classpath:lab.properties")
public class CoreConfig {

        @Bean
        public Clock clock() {
                return Clock.systemUTC();
        }
}
