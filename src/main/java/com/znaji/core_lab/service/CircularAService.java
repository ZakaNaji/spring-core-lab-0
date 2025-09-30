package com.znaji.core_lab.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CircularAService {

        private static final Logger log = LoggerFactory.getLogger(CircularAService.class);

        private CircularBService circularBService;

        @Autowired // TODO: Experiment by removing @Lazy and see the circular dependency failure.
        public void setCircularBService(@Lazy CircularBService circularBService) {
                this.circularBService = circularBService;
        }

        public String callB() {
                        log.debug("Calling B from A");
                        return "A->" + circularBService.callCascaded();
        }
}
