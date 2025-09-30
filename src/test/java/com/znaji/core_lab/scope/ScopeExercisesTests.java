package com.znaji.core_lab.scope;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class ScopeExercisesTests {

        @Autowired
        private ObjectProvider<PrototypeAudit> prototypeAuditProvider;

        @Autowired
        private SingletonWorker singletonWorker;

        @Test
        void prototypeBeanShouldProduceNewInstances() {
                PrototypeAudit first = prototypeAuditProvider.getObject();
                PrototypeAudit second = prototypeAuditProvider.getObject();
                assertThat(first).isNotSameAs(second);
        }

        @Test
        void lookupMethodShouldBeOverriddenBySpring() {
                PrototypeAudit audit = singletonWorker.lookupAudit();
                assertThat(audit).isNotNull();
        }
}
