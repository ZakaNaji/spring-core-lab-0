package com.znaji.core_lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfiledDataSourceConfig {

        @Bean(name = "connectionDescription")
        @Profile("dev")
        public String devConnection() {
                return "H2 in-memory database";
        }

        @Bean(name = "connectionDescription")
        @Profile("prod")
        public String prodConnection() {
                // TODO: Point to a production-ready JDBC URL
                return "PostgreSQL production cluster";
        }
}
