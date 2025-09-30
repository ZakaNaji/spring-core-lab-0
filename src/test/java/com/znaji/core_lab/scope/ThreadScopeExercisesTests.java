package com.znaji.core_lab.scope;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class ThreadScopeExercisesTests {

        @Autowired
        private ConfigurableApplicationContext context;

        @Test
        void threadScopedBeanShouldDifferAcrossThreads() throws ExecutionException, InterruptedException {
                Assumptions.assumeTrue(Boolean.getBoolean("runThreadScopeExercise"),
                                "TODO: enable property 'runThreadScopeExercise' to execute this expensive test");

                ObjectProvider<PrototypeAudit> provider = context.getBeanProvider(PrototypeAudit.class);
                Callable<PrototypeAudit> task = provider::getObject;
                var executor = Executors.newFixedThreadPool(2);
                Future<PrototypeAudit> first = executor.submit(task);
                Future<PrototypeAudit> second = executor.submit(task);
                assertThat(first.get()).isNotEqualTo(second.get());
                executor.shutdownNow();
        }
}
