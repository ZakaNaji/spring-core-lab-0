package com.znaji.core_lab.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CircularBService {

        private static final Logger log = LoggerFactory.getLogger(CircularBService.class);
        private final CircularAService circularAService;

        public CircularBService(CircularAService circularAService) {
                this.circularAService = circularAService;
        }

        public String callCascaded() {
                log.debug("Calling A from B");
                return "B";
        }
}
