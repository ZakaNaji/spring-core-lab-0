package com.znaji.core_lab.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FinalMethodService {

        private static final Logger log = LoggerFactory.getLogger(FinalMethodService.class);

        public final void finalMethod() {
                // TODO: Observe that CGLIB proxies cannot advise final methods.
                log.info("Final method invoked");
        }
}
