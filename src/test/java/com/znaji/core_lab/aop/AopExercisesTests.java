package com.znaji.core_lab.aop;

import static org.assertj.core.api.Assertions.assertThat;

import com.znaji.core_lab.service.SelfInvocationDemoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class AopExercisesTests {

        @Autowired
        private SelfInvocationDemoService selfInvocationDemoService;

        @Autowired(required = false)
        private String windowsOnlyBean;

        @Test
        void contextProvidesSelfInvocationBean() {
                selfInvocationDemoService.outerCall();
                assertThat(selfInvocationDemoService).isNotNull();
        }

        @Test
        @DisabledIfSystemProperty(named = "os.name", matches = "(?i).*win.*", disabledReason = "Fails on Windows once OnWindowsCondition matches")
        void conditionalBeanIsAbsentOnNonWindows() {
                assertThat(windowsOnlyBean).isNull();
        }
}
