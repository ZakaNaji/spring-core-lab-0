package com.znaji.core_lab.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SelfInvocationDemoService {

        private static final Logger log = LoggerFactory.getLogger(SelfInvocationDemoService.class);

        public void outerCall() {
                log.info("Outer call start");
                internalWork(); // TODO: Demonstrate why this bypasses proxies when combined with @Transactional/@Async.
        }

        public void internalWork() {
                log.info("Internal work executing");
        }
}
